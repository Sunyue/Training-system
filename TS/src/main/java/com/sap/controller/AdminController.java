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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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
}
