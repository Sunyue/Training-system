package com.sap.service;


import com.github.pagehelper.PageInfo;
import com.sap.domain.Chain;

import java.util.List;

public interface ChainService {
	PageInfo<Chain> selectAllChain(int start, int limit);
	PageInfo<Chain> selectChainByUser(String username, int start, int limit);
	Boolean checkUserChainRelation(String username, Integer chainId);
	Integer createChain(String chainName);
	Integer getChainIdByName(String chainName);
}
