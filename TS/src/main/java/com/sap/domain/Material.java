package com.sap.domain;

public class Material {
    private Integer materialId;
    private Integer courseId;
    private Integer materialLearnType;
    private String  attachFilepath;
    private String  fileType;
    private Integer materialStatus;
    private Double  materialScore;
    private Double  materialWeight;

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getMaterialLearnType() {
        return materialLearnType;
    }

    public void setMaterialLearnType(Integer materialLearnType) {
        this.materialLearnType = materialLearnType;
    }

    public String getAttachFilepath() {
        return attachFilepath;
    }

    public void setAttachFilepath(String attachFilepath) {
        this.attachFilepath = attachFilepath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getMaterialStatus() {
        return materialStatus;
    }

    public void setMaterialStatus(Integer materialStatus) {
        this.materialStatus = materialStatus;
    }

    public Double getMaterialScore() {
        return materialScore;
    }

    public void setMaterialScore(Double materialScore) {
        this.materialScore = materialScore;
    }

    public Double getMaterialWeight() {
        return materialWeight;
    }

    public void setMaterialWeight(Double materialWeight) {
        this.materialWeight = materialWeight;
    }

}
