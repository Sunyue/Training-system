package com.sap.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class MultistepController {
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
    }
}
