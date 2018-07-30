package com.sap.controller;

import com.github.pagehelper.PageInfo;
import com.sap.Constant.Consts;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/student")
public class StudentController extends MultistepController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private ChainService chainService;
    @Autowired
    private MaterialService materialService;

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @RequestMapping("/")
    public String getChain(Model model, @RequestParam(value="start", defaultValue = "1") int start,
                           @RequestParam(value="limit", defaultValue = "3") int limit){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("User '"+ auth.getName() + "' with role '" + auth.getAuthorities() + "' is reaching to chain page");
        List<ChainView> chainViewList = new ArrayList<>();
        PageInfo<Chain> pageInfoChain = chainService.selectChainByUser(auth.getName(), start, limit);
        for(Chain chain: pageInfoChain.getList()) {
            ChainView chainView = new ChainView();
            chainView.setChain(chain);
            PageInfo<String> pageInfo = courseService.selectCoursenameByChain(chain.getChainId(), 0, Consts.defaultLimit);
            chainView.setCourseNames(pageInfo.getList());
            chainViewList.add(chainView);
        }
        model.addAttribute("chainViewList", chainViewList);
        model.addAttribute("currentPage", pageInfoChain.getPageNum());
        model.addAttribute("totalPage",pageInfoChain.getPages());
        model.addAttribute("pageSize",pageInfoChain.getPageSize());
        if (pageInfoChain.getPages() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, pageInfoChain.getPages())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "chain";
    }

    @RequestMapping("/course")
    public String getCourse(Model model, @RequestParam(value="chainId", defaultValue="1") Integer chainId,
                            @RequestParam(value="start", defaultValue = "1") int start,
                            @RequestParam(value="limit", defaultValue = "4") int limit){
        log.info("Chain Id:" + chainId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(chainService.checkUserChainRelation(auth.getName(), chainId) == true){
            PageInfo<Course> pageInfo = courseService.selectCourseByChain(chainId, start, limit);
            model.addAttribute("courseList", pageInfo.getList());
            model.addAttribute("currentPage", pageInfo.getPageNum());
            model.addAttribute("totalPage",pageInfo.getPages());
            model.addAttribute("pageSize",pageInfo.getPageSize());
            model.addAttribute("chainId",chainId);
            if (pageInfo.getPages() > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, pageInfo.getPages())
                        .boxed()
                        .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }
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
