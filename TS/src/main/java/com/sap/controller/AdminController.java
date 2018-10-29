package com.sap.controller;

import com.github.pagehelper.PageInfo;
import com.sap.domain.Chain;
import com.sap.domain.Course;
import com.sap.domain.Material;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController extends MultistepController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping("/home")
    public String goToHomePage(Model model){
        return "admin";
    }

    @RequestMapping("/")
    @Override
    public String getChain(Model model, @RequestParam(value="start", defaultValue = "1") int start,
                           @RequestParam(value="limit", defaultValue = "3") int limit) {
        PageInfo<Chain> pageInfoChain = chainService.selectAllChain(start,limit);
        prepareChainViewList(model, pageInfoChain);
        setPageInfo(model,pageInfoChain);
        return "chain";
    }

    @RequestMapping("/course")
    @Override
    public String getCourse(Model model, @RequestParam(value="chainId", defaultValue="1") Integer chainId,
                            @RequestParam(value="start", defaultValue = "1") int start,
                            @RequestParam(value="limit", defaultValue = "4") int limit){
        log.info("Chain Id:" + chainId);
        PageInfo<Course> pageInfo = courseService.selectCourseByChain(chainId, start, limit);
        model.addAttribute("courseList", pageInfo.getList());
        model.addAttribute("chainId",chainId);
        setPageInfo(model,pageInfo);
        return "course";
    }

    @RequestMapping("/material")
    public String getMaterial(Model model, @RequestParam(value="courseId", defaultValue="1") Integer courseId,HttpSession session){
        log.info("Course Id:" + courseId);
        session.setAttribute("courseId", courseId);
        List<Material> materialList = materialService.selectMaterialByCourse(courseId);
        Course course = courseService.selectCourseById(courseId);
        model.addAttribute("courseName", course.getCourseName());
        model.addAttribute("materialList", materialList);
        return "course_detail";
    }
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFile( HttpSession session, MultipartHttpServletRequest multiReq) throws IOException {


        MultipartFile file = multiReq.getFile("file");
        String fileName = file.getOriginalFilename();
        String pathWebroot = this.getClass().getResource("/").toString();
        pathWebroot = pathWebroot + "static/";
        String path = pathWebroot.substring(5);
        FileOutputStream fos = new FileOutputStream(new File(path +fileName));
        FileInputStream fs = (FileInputStream) file.getInputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = fs.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        fos.close();
        fs.close();

        String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        Material newMaterial = new Material();
        newMaterial .setFileType(fileType);
        log.info("path:" + path +fileName);
        newMaterial.setMaterialName(fileName);
        newMaterial.setAttachFilepath(path +fileName);
        newMaterial.setCourseId((Integer) session.getAttribute("courseId"));
        materialService.addMaterial(newMaterial);
        log.info("courseId=" + newMaterial.getCourseId());
        return "redirect:/admin/material?courseId=" + newMaterial.getCourseId();
    }
    @RequestMapping(value = "/download")
    public void download( @RequestParam(value="materialId") Integer materialId,HttpServletResponse response){
        Material material = materialService.selectMaterialById(materialId);
      String path =  material.getAttachFilepath();
      log.info("download file:"+path);

        File file = new File(path);
        if(file.exists()){
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + path);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
