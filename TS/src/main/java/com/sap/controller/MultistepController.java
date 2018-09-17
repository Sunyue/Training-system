package com.sap.controller;

import com.github.pagehelper.PageInfo;
import com.sap.Constant.Consts;
import com.sap.domain.Chain;
import com.sap.domain.ChainView;
import com.sap.service.ChainService;
import com.sap.service.CourseService;
import com.sap.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class MultistepController {

    @Autowired
    protected CourseService courseService;
    @Autowired
    protected ChainService chainService;
    @Autowired
    protected MaterialService materialService;

    abstract String getChain(Model model, int start, int limit);
    abstract String getCourse(Model model, Integer chainId, int start, int limit);
    abstract String getMaterial(Model model, Integer courseId);

    protected void setPageInfo(Model model, PageInfo pageInfo){
        model.addAttribute("currentPage", pageInfo.getPageNum());
        model.addAttribute("totalPage",pageInfo.getPages());
        model.addAttribute("pageSize",pageInfo.getPageSize());
        if (pageInfo.getPages() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, pageInfo.getPages())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("prePage",pageInfo.getPrePage());
        model.addAttribute("nextPage",pageInfo.getNextPage());
    }

    protected void prepareChainViewList(Model model, PageInfo<Chain> pageInfoChain){
        List<ChainView> chainViewList = new ArrayList<>();
        for(Chain chain: pageInfoChain.getList()) {
            ChainView chainView = new ChainView();
            chainView.setChain(chain);
            PageInfo<String> pageInfo = courseService.selectCoursenameByChain(chain.getChainId(), 0, Consts.defaultLimit);
            chainView.setCourseNames(pageInfo.getList());
            chainViewList.add(chainView);
        }
        model.addAttribute("chainViewList", chainViewList);
    }
}
