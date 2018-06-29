package com.sap.controller;

import com.sap.domain.Chain;
import com.sap.domain.ChainView;
import com.sap.domain.Course;
import com.sap.domain.Material;
import com.sap.service.ChainService;
import com.sap.service.CourseService;
import com.sap.service.MaterialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController extends MultistepController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private ChainService chainService;
    @Autowired
    private MaterialService materialService;

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping("/home")
    public String goToHomePage(Model model){
        return "admin";
    }

    @RequestMapping("/")
    @Override
    public String getChain(Model model) {
        ModelAndView mav = new ModelAndView("chain");
        List<ChainView> chainViewList = new ArrayList<>();
        List<Chain> chainList = chainService.selectAllChain();
        for(Chain chain: chainList) {
            ChainView chainView = new ChainView();
            chainView.setChain(chain);
            List<String> courseNames = courseService.selectCoursenameByChain(chain.getChainId());
            chainView.setCourseNames(courseNames);
            chainViewList.add(chainView);
        }
        model.addAttribute("chainViewList", chainViewList);
        return "chain";
    }

    @RequestMapping("/course")
    @Override
    public String getCourse(Model model, @RequestParam(value="chainId", defaultValue="1") Integer chainId){
        log.info("Chain Id:" + chainId);
        ModelAndView mav = new ModelAndView("course");
        List<Course> courseList = courseService.selectCourseByChain(chainId);
        model.addAttribute("courseList", courseList);
        return "course";
    }

    @RequestMapping("/material")
    @Override
    public String getMaterial(Model model, @RequestParam(value="courseId", defaultValue="1") Integer courseId){
        log.info("Course Id:" + courseId);
        ModelAndView mav = new ModelAndView("material");
        List<Material> materialList = materialService.selectMaterialByCourse(courseId);
        model.addAttribute("materialList", materialList);
        return "course_detail";
    }
}
