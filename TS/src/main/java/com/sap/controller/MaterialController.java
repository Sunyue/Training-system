package com.sap.controller;

import com.sap.domain.Material;
import com.sap.service.MaterialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    private static final Logger log = LoggerFactory.getLogger(MaterialController.class);


    //    @RequestMapping("/material")
//    public String getCourse(Model model, @RequestParam(value="courseId", defaultValue="1") Integer courseId){
//        log.info("Hello Material");
//        ModelAndView mav = new ModelAndView("material");
//        List<Material> materialList = materialService.selectMaterialByCourse(courseId);
//        model.addAttribute("materialList", materialList);
//        return "material";
//    }
    @RequestMapping(value = "/admin/upload", method = RequestMethod.POST)
    public void uploadFile( HttpServletRequest req, HttpSession session,MultipartHttpServletRequest multiReq) throws IOException {
        log.info("upload a file");
        FileOutputStream fos = new FileOutputStream(new File("C://Users//I345719//Desktop//New folder//test.doc"));
        MultipartFile file = multiReq.getFile("file");
        FileInputStream fs = (FileInputStream) file.getInputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = fs.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        fos.close();
        fs.close();
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        Material newMaterial = new Material();
        newMaterial .setFileType(fileType);
        newMaterial.setMaterialName(fileName);
//        String courseIdStr = session.getAttribute("courseId").toString();
        newMaterial.setCourseId((Integer) session.getAttribute("courseId"));
        materialService.addMaterial(newMaterial);
    }
}