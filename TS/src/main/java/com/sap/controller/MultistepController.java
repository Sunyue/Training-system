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
import java.util.Comparator;
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
            List<Integer> finalNumbers = new ArrayList<>();
            for(int i=pageInfo.getPageNum()-1;i>=pageInfo.getPageNum()-3 && i>=0;i--){
                finalNumbers.add(pageNumbers.get(i));
            }
            for(int i=pageInfo.getPageNum();i<= pageInfo.getPageNum()+1 && i<pageNumbers.size();i++){
                finalNumbers.add(pageNumbers.get(i));
            }
            finalNumbers.sort(Comparator.comparingInt(Integer::intValue));
            model.addAttribute("pageNumbers", finalNumbers);

            int trimHeadFlag = 0;
            int trimEndFlag = 0;
            if(finalNumbers.get(0) != pageNumbers.get(0)){
                trimHeadFlag = 1;
            }
            if(finalNumbers.get(finalNumbers.size()-1) != pageNumbers.get(pageNumbers.size()-1)){
                trimEndFlag = 1;
            }
            model.addAttribute("trimHead", trimHeadFlag);
            model.addAttribute("trimEnd", trimEndFlag);
        }
        model.addAttribute("prePage",pageInfo.getPrePage());
        model.addAttribute("nextPage",pageInfo.getNextPage());
        model.addAttribute("firstPage",pageInfo.getFirstPage());
        model.addAttribute("lastPage",pageInfo.getLastPage());





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
