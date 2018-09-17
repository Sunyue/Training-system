package com.sap.controller;

import com.github.pagehelper.PageInfo;
import com.sap.domain.Chain;
import com.sap.domain.Course;
import com.sap.domain.Material;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController extends MultistepController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @RequestMapping("/")
    public String getChain(Model model, @RequestParam(value="start", defaultValue = "1") int start,
                           @RequestParam(value="limit", defaultValue = "3") int limit){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("User '"+ auth.getName() + "' with role '" + auth.getAuthorities() + "' is reaching to chain page");
        PageInfo<Chain> pageInfoChain = chainService.selectChainByUser(auth.getName(), start, limit);
        prepareChainViewList(model, pageInfoChain);
        setPageInfo(model,pageInfoChain);
        return "chain";
    }

    @RequestMapping("/course")
    public String getCourse(Model model, @RequestParam(value="chainId", defaultValue="1") Integer chainId,
                            @RequestParam(value="start", defaultValue = "1") int start,
                            @RequestParam(value="limit", defaultValue = "6") int limit){
        log.info("Chain Id:" + chainId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(chainService.checkUserChainRelation(auth.getName(), chainId) == true){
            PageInfo<Course> pageInfo = courseService.selectCourseByChain(chainId, start, limit);
            model.addAttribute("courseList", pageInfo.getList());
            model.addAttribute("chainId",chainId);
            setPageInfo(model,pageInfo);
        }
        return "course";
    }

    @RequestMapping("/material")
    public String getMaterial(Model model, @RequestParam(value="courseId", defaultValue="1") Integer courseId){
        log.info("Course Id:" + courseId);
        List<Material> materialList = materialService.selectMaterialByCourse(courseId);
        model.addAttribute("materialList", materialList);
        return "course_detail";
    }

}
