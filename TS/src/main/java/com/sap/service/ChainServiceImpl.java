package com.sap.service;

import com.sap.domain.Chain;
import com.sap.domain.Course;
import com.sap.mapper.ChainMapper;
import com.sap.mapper.CourseMapper;
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

    public List<Chain> selectAllChain() {
        return chainMapper.selectAllChain();
    }

    public List<Chain> selectChainByUser(String username) {
        // TODO Auto-generated method stub
        return chainMapper.selectChainByUser(username);
    }

}
