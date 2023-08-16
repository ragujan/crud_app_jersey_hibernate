package com.seblacko.rag.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

import java.util.Enumeration;

@Path("/department_entry")
public class DepartmentEntry {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addDepartment(Res department) {
        System.out.println("Received department: " + department.getName());
        return "ok";
    }

    @GET
    public String test() {

        return "Success";
    }
}
