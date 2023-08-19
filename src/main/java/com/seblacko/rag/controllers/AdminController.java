package com.seblacko.rag.controllers;

import com.seblacko.rag.dtos.AuthResponseDTO;
import com.seblacko.rag.services.Admin;
import com.seblacko.rag.services.UserService;
import com.seblacko.rag.util.JwtTokenUtil;
import com.seblacko.rag.util.UserDetails;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response adminLogin(@FormParam("email") String email, @FormParam("password") String password) {
        if (email.equals(Admin.getEmail()) && password.equals(Admin.getPassword())) {
            UserDetails userDetails = userService.getAdminByEmail(email, password);
            String token = tokenUtil.generateAccessToken(userDetails);
            Date expireDate = tokenUtil.getExpiredDateFromToken(token);
            String refreshToken = tokenUtil.generateRefreshToken(userDetails);

            AuthResponseDTO dto = new AuthResponseDTO();
            dto.setAccessToken(token);
            dto.setExpireIn(expireDate.toString());
            dto.setRefreshToken(refreshToken);
            return Response.ok().entity(dto).build();

        } else {
            return Response.ok().entity("not authorized").build();
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
