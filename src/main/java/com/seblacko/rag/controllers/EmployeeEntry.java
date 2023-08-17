package com.seblacko.rag.controllers;

import com.seblacko.rag.entities.Employee;
import com.seblacko.rag.util.InputValidator;
import com.seblacko.rag.util.hibernate.AddRow;
import com.seblacko.rag.util.hibernate.RowChecker;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Path("/employee_entry")
public class EmployeeEntry {
    @POST
    public String addEmployee(@FormParam("fname") String firstName,
                              @FormParam("lname") String lastName,
                              @FormParam("depName") String depName,
                              @FormParam("posName") String posName) {

        if(!InputValidator.inputTextIsValid(firstName)){
            return "employee first name is invalid";
        }else if(!InputValidator.inputTextIsValid(lastName)){
            return "employee last name is invalid";
        }else if(!InputValidator.inputNumberIsValid(depName) || !RowChecker.rowExists("Department","id",depName)){
            return "employee department name is invalid";
        }else if(!InputValidator.inputNumberIsValid(posName) || !RowChecker.rowExists("EmployeePosition","id",posName)){
            return "employee position name is invalid";
        }else{
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(format);
            System.out.println(formattedDateTime);
            Employee employee = new Employee();
            employee.setEmployeePositionId(Integer.parseInt(posName));
            employee.setDepartmentId(Integer.parseInt(depName));
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setHiredDate(formattedDateTime);
            AddRow.addRow(employee);
            return "Success";
        }
    }
}
