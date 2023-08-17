package com.seblacko.rag.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.swing.text.View;
@Path("/")
public class AdminHome {
    @Path("/admin_home")
    @GET
    public Viewable viewAdminHome() {
        System.out.println("admin home");
        return new Viewable("/admin_home_view");
    }
    @Path("/enter_employee")
    @GET
    public Viewable enterEmployee(){
        System.out.println("admin_enter_employee");
        return new Viewable("/enter_employee");
    }
    @Path("/enter_employee_position")
    @GET
    public Viewable enterEmployeePosition(){
        System.out.println("admin_enter_employee_position");
        return new Viewable("/enter_employee_position");
    }
    @Path("/enter_department")
    @GET
    public Viewable enterDepartment(){
        System.out.println("admin_enter_department");
        return new Viewable("/enter_department");
    }
    @Path("/view_employees")
    @GET
    public Viewable view_employees(){
        System.out.println("view_employees");
        return new Viewable("/view_employees");
    }
}
