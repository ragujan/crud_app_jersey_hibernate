package com.seblacko.rag.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.seblacko.rag.dtos.AdminAuthDTO;
import com.seblacko.rag.services.Admin;
import com.seblacko.rag.services.UserService;
import com.seblacko.rag.util.JwtTokenUtil;
import com.seblacko.rag.util.UserDetails;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.glassfish.jersey.server.mvc.Viewable;

import java.util.Date;

@Path("/")
public class AdminController {
    @Inject
    private UserService userService;
    @Inject
    private JwtTokenUtil tokenUtil;

    @Path("/admin_auth")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
//    public String adminLogin(@FormParam("email") String email, @FormParam("password") String password) {
        public String adminLogin(AdminAuthDTO dto) {
        System.out.println("hey admin auth request here");
        String email = dto.getEmail();
        String password =dto.getAdminPassword();
        if (email.equals(Admin.getEmail()) && password.equals(Admin.getPassword())) {
            UserDetails userDetails = userService.getAdminByEmailPassword(email, password);
            String token = tokenUtil.generateAccessToken(userDetails);
            Date expireDate = tokenUtil.getExpiredDateFromToken(token);
            String refreshToken = tokenUtil.generateRefreshToken(userDetails);
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode jsonArray = mapper.createArrayNode();
            ObjectNode jsonObject = mapper.createObjectNode();

            jsonObject.put("token",token);
            jsonObject.put("refresh_token",refreshToken);
            jsonObject.put("expireDate",expireDate.toString());

            jsonArray.add(jsonObject);

            return jsonArray.toString();

        } else {
            return "invalid credentials";
        }
    }

    @Path("/admin_home")
    @GET
    public Viewable viewAdminHome() {
        System.out.println("admin home");
        return new Viewable("/admin_home_view");
    }

    @Path("/enter_employee")
    @GET
    public Viewable enterEmployee() {
        System.out.println("admin_enter_employee");
        return new Viewable("/enter_employee");
    }

    @Path("/enter_employee_position")
    @GET
    public Viewable enterEmployeePosition() {
        System.out.println("admin_enter_employee_position");
        return new Viewable("/enter_employee_position");
    }

    @Path("/enter_department")
    @GET
    public Viewable enterDepartment() {
        System.out.println("admin_enter_department");
        return new Viewable("/enter_department");
    }

    @Path("/view_employees")
    @GET
    public Viewable view_employees() {
        System.out.println("view_employees");
        return new Viewable("/view_employees");
    }

    @GET
    @Path("employee_salary")
    public Viewable view_salary() {
        return new Viewable("/employee_salary");
    }
}
