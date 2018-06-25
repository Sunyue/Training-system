package com.sap.service;


import com.sap.domain.Chain;

import java.util.List;

public interface ChainService {
	List<Chain> selectAllChain();
	List<Chain> selectChainByUser(String username);
}
