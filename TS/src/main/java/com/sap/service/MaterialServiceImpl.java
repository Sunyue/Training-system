package com.sap.service;


import com.sap.domain.Material;
import com.sap.mapper.MaterialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService{

    @Autowired
    private final MaterialMapper materialMapper;

    public MaterialServiceImpl(MaterialMapper materialMapper) {
        this.materialMapper = materialMapper;
    }

	public List<Material> selectMaterialByCourse(Integer courseId) {
		// TODO Auto-generated method stub
		return materialMapper.selectMaterialByCourse(courseId);
	}
	public Material selectMaterialById(Integer materialId)
    {
        return materialMapper.selectMaterialById(materialId);
    }

    @Override
    public void addMaterial(Material material) {
        materialMapper.addMaterial(material);
    }


}
