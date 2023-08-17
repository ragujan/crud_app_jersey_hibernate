package com.seblacko.rag.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.seblacko.rag.entities.Department;
import com.seblacko.rag.entities.Employee;
import com.seblacko.rag.entities.EmployeePosition;
import com.seblacko.rag.util.hibernate.LoadTable;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/")
public class Employees {
    @GET
    @Path("get_all_employees")
    public String getEmployees() {
        List<Object[]> employees = LoadTable.loadAll("Employee");
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode jsonArray = mapper.createArrayNode();

        for (Object[] result : employees) {
            Employee employee = (Employee) result[0];
            Department department = employee.getDepartment();
            EmployeePosition employeePosition = employee.getEmployeePosition();
            ObjectNode jsonObject = mapper.createObjectNode();
            jsonObject.put("employee_id", employee.getId());
            jsonObject.put("employee_first_name", employee.getFirstName());
            jsonObject.put("employee_last_name", employee.getLastName());
            jsonObject.put("hired_at", employee.getHiredDate());
            jsonObject.put("employee_department", department.getName());
            jsonObject.put("employee_department_id", department.getId());
            jsonObject.put("employee_position",employeePosition.getPositionName());
            jsonObject.put("employee_position_id",employeePosition.getId());
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();

    }
}
