package com.sap.dao;

import com.sap.domain.Chain;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ChainDao {

    /**
     * @return List<Chain>
     */
    public List<Chain> selectAllChain();
 
}
