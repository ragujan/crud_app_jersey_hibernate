package com.seblacko.rag.dtos;

public class DepartmentEntryDTO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DepartmentEntryDTO() {
    }

    public DepartmentEntryDTO(String name) {
        this.name = name;
    }
}
