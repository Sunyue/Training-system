package com.sap.controller;

import com.github.pagehelper.PageInfo;
import com.sap.Constant.Consts;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController extends MultistepController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping("/home")
    public String goToHomePage(Model model, HttpSession session){
        setBreadCrumb(model,"Home",session);
        return "admin";
    }

    @RequestMapping("/")
    @Override
    public String getChain(Model model, @RequestParam(value="start", defaultValue = "1") int start,
                           @RequestParam(value="limit", defaultValue = "3") int limit, HttpSession session) {
        PageInfo<Chain> pageInfoChain = chainService.selectAllChain(start,limit);
        prepareChainViewList(model, pageInfoChain);
        setPageInfo(model,pageInfoChain);
        setBreadCrumb(model,"Edit Course Chain",session);
        return "chain";
    }

    @RequestMapping("/course")
    @Override
    public String getCourse(Model model, @RequestParam(value="chainId", defaultValue="1") Integer chainId,
                            @RequestParam(value="start", defaultValue = "1") int start,
                            @RequestParam(value="limit", defaultValue = "6") int limit, HttpSession session){
        log.info("Chain Id:" + chainId);
        PageInfo<Course> pageInfo = courseService.selectCourseByChain(chainId, start, limit);
        model.addAttribute("courseList", pageInfo.getList());
        model.addAttribute("chainId",chainId);
        setPageInfo(model,pageInfo);
        setBreadCrumb(model,"Course Chain",session);
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
        setBreadCrumb(model,"Material",session);
        return "course_detail";
    }
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFile(HttpServletRequest req, HttpSession session, MultipartHttpServletRequest multiReq) throws IOException {


        MultipartFile file = multiReq.getFile("file");
        String fileName = file.getOriginalFilename();
        FileOutputStream fos = new FileOutputStream(new File("Documents://"+fileName));
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
        newMaterial.setMaterialName(fileName);
        newMaterial.setCourseId((Integer) session.getAttribute("courseId"));
        materialService.addMaterial(newMaterial);
        return "redirect:/course_detail?courseId=" + newMaterial.getCourseId();
    }

    protected String getBreadScrumbUrl(String pageName){
        String url = null;
        switch(pageName){
            case "Home":
                url = Consts.adminHomePage;
                break;
            case "Edit Course Chain":
                url = "/admin/";
                break;
            case "Course Chain":
                url = "/admin/course";
                break;
            case "Material":
                url = "/admin/material";
                break;
            default:
                break;
        }
        return url;
    }
}
