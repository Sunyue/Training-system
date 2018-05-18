package com.sap.domain;

import java.util.List;

public class ChainView {
    private Integer chainId;
    private String  chainName;
    private Integer isdefined;
    private List<String> courseNames;

    public Integer getChainId() {
        return chainId;
    }

    public void setChainId(Integer chainId) {
        this.chainId = chainId;
    }

    public String getChainName() {
        return chainName;
    }

    public void setChainName(String chainName) {
        this.chainName = chainName;
    }

    public Integer getIsdefined() {
        return isdefined;
    }

    public void setIsdefined(Integer isdefined) {
        this.isdefined = isdefined;
    }

    public List<String> getCourseNames() {
        return courseNames;
    }

    public void setCourseNames(List<String> courseNames) {
        this.courseNames = courseNames;
    }

    public void setChain(Chain chain) {
        this.chainId = chain.getChainId();
        this.chainName = chain.getChainName();
        this.isdefined = chain.getIsdefined();
    }
}
