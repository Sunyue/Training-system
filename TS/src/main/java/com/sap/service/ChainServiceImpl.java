package com.sap.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sap.domain.Chain;
import com.sap.mapper.ChainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChainServiceImpl implements ChainService{

    @Autowired
    private final ChainMapper chainMapper;

    public ChainServiceImpl(ChainMapper chainMapper) {
        this.chainMapper = chainMapper;
    }

    public PageInfo<Chain> selectAllChain(int start, int limit) {
        PageHelper.startPage(start, limit);
        List<Chain> list = chainMapper.selectAllChain();
        PageInfo<Chain> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public PageInfo<Chain> selectChainByUser(String username, int start, int limit) {
        PageHelper.startPage(start, limit);
        List<Chain> list = chainMapper.selectChainByUser(username);
        PageInfo<Chain> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Boolean checkUserChainRelation(String username, Integer chainId) {
        if(chainMapper.selectChainByUserAndChain(username, chainId).size()>0) {
            return true;
        }
        return false;
    }

}
