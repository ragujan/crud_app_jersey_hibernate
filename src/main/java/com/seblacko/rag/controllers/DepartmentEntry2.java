package com.seblacko.rag.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.util.Enumeration;

@Path("/department_entry2")
public class DepartmentEntry2 {

    @Context
    private HttpServletRequest httpServletRequest;

    @POST
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String addDepartment(@FormDataParam("departmentName") String departmentName) {

        System.out.println("Received department name dep2 : " + departmentName);
        return "ok";
    }

    @GET
    public String test() {

        return "Success";
    }
}
