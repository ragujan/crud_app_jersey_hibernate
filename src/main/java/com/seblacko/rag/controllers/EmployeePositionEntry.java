package com.seblacko.rag.controllers;

import com.seblacko.rag.entities.EmployeePosition;
import com.seblacko.rag.util.InputValidator;
import com.seblacko.rag.util.hibernate.AddRow;
import com.seblacko.rag.util.hibernate.RowChecker;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/employee_position_entry")
public class EmployeePositionEntry {

    @POST
    public String addDepartment(@FormDataParam("employeePositionName") String employeePositionName) {
        if(!InputValidator.inputTextIsValid(employeePositionName)){
           return "invalid input";
        }else{
            System.out.println("Received employee position name dep2 : " + employeePositionName);

           if(RowChecker.rowExists("EmployeePosition","positionName",employeePositionName)){
                return "name already exists";
            }else{
                EmployeePosition employeePosition = new EmployeePosition(employeePositionName);
                AddRow.addRow(employeePosition);
                return "ok";
            }
        }

    }


}
