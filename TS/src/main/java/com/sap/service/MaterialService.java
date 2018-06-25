package com.sap.service;


import com.sap.domain.Material;

import java.util.List;

public interface MaterialService {
	List<Material> selectMaterialByCourse(Integer courseId);
}
