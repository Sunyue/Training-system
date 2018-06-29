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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private ChainService chainService;
    @Autowired
    private MaterialService materialService;

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @RequestMapping("/")
    public String getChain(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("User '"+ auth.getName() + "' with role '" + auth.getAuthorities() + "' is reaching to chain page");
        ModelAndView mav = new ModelAndView("chain");

        List<ChainView> chainViewList = new ArrayList<>();

        List<Chain> chainList = chainService.selectChainByUser(auth.getName());
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
    public String getCourse(Model model, @RequestParam(value="chainId", defaultValue="1") Integer chainId){
        log.info("Hello Course");
        ModelAndView mav = new ModelAndView("course");
        List<Course> courseList = courseService.selectCourseByChain(chainId);
        model.addAttribute("courseList", courseList);
        return "course";
    }

    @RequestMapping("/material")
    public String getMaterial(Model model, @RequestParam(value="courseId", defaultValue="1") Integer courseId){
        log.info("Hello Material");
        ModelAndView mav = new ModelAndView("material");
        List<Material> materialList = materialService.selectMaterialByCourse(courseId);
        model.addAttribute("materialList", materialList);
        return "course_detail";
    }

}
