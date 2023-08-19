package com.seblacko.rag.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.seblacko.rag.entities.Department;
import com.seblacko.rag.util.InputValidator;
import com.seblacko.rag.util.hibernate.AddRow;
import com.seblacko.rag.util.hibernate.LoadTable;
import com.seblacko.rag.util.hibernate.RowChecker;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.util.List;

@Path("/")
public class DepartmentController {
    @POST
    @Path("/department_entry")
    public String addDepartment(@FormDataParam("departmentName") String departmentName) {
        if (!InputValidator.inputTextIsValid(departmentName)) {
            return "invalid input, only letters and numbers are allowed";
        } else {
            System.out.println("Received department name: " + departmentName);
            if (RowChecker.rowExists("Department", "name", departmentName)) {
                return "name already exists";
            } else {
                Department department = new Department(departmentName);
                AddRow.addRow(department);
                return "ok";
            }
        }

    }

    @GET
    @Path("/get_department_names")
    public String getDepartmentNames() {
        List<Object[]> resultList = LoadTable.loadAll("Department");
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode jsonArray = mapper.createArrayNode();

        for (Object[] result : resultList) {
            Department department = (Department) result[0];
            ObjectNode jsonObject = mapper.createObjectNode();
            jsonObject.put("department_name", department.getName());
            jsonObject.put("department_id", department.getId());
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }

}
