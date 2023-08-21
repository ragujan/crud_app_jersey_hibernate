package com.seblacko.rag.dtos;

public class EmployeePositionNameDTO {
    private String positionName;

    public EmployeePositionNameDTO() {
    }

    public EmployeePositionNameDTO(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
