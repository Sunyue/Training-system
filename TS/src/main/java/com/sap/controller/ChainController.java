package com.sap.controller;

import com.sap.domain.Chain;
import com.sap.domain.ChainView;
import com.sap.service.ChainService;
import com.sap.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChainController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private ChainService chainService;

    private static final Logger log = LoggerFactory.getLogger(ChainController.class);

    @RequestMapping("/chain")
    public String getCourse(Model model){
        log.info("Hello Chain");
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

}
