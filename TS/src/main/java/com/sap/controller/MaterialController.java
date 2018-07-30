package com.sap.controller;

import com.sap.domain.Material;
import com.sap.service.MaterialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller 
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    private static final Logger log = LoggerFactory.getLogger(MaterialController.class);


    
    @RequestMapping("/material")
    public String getCourse(Model model, @RequestParam(value="courseId", defaultValue="1") Integer courseId){
        log.info("Hello Material");
        ModelAndView mav = new ModelAndView("material");
        List<Material> materialList = materialService.selectMaterialByCourse(courseId);
        model.addAttribute("materialList", materialList);
        return "material";
    }
}