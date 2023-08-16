package com.seblacko.rag.controllers;

import com.seblacko.rag.util.StringInputValidator;
import jakarta.ws.rs.*;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.util.Enumeration;

@Path("/department_entry")
public class DepartmentEntry {

    @POST
    public String addDepartment(@FormDataParam("departmentName") String departmentName) {
        if(!StringInputValidator.isValid(departmentName)){
           return "invalid input";
        }else{
            System.out.println("Received department name dep2 : " + departmentName);
            return "ok";
        }

    }

    @GET
    public String test() {

        return "Success";
    }
}
