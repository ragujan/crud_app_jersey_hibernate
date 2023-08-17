package com.seblacko.rag.controllers;

import com.seblacko.rag.entities.Department;
import com.seblacko.rag.util.InputValidator;
import com.seblacko.rag.util.hibernate.AddRow;
import com.seblacko.rag.util.hibernate.RowChecker;
import jakarta.ws.rs.*;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/department_entry")
public class DepartmentEntry {

    @POST
    public String addDepartment(@FormDataParam("departmentName") String departmentName) {
        if(!InputValidator.inputTextIsValid(departmentName)){
           return "invalid input, only letters and numbers are allowed";
        }else{
            System.out.println("Received department name: " + departmentName);
            if(RowChecker.rowExists("Department","name",departmentName)){
                return "name already exists";
            }else{
                Department department = new Department(departmentName);
                AddRow.addRow(department);
                return "ok";
            }
        }

    }


}
