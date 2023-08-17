package com.seblacko.rag.controllers;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/employee_entry")
public class EmployeeEntry {
    @POST
    public String addEmployee(){

        return "Ok";
    }
}
