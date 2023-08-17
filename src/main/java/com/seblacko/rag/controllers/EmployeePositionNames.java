package com.seblacko.rag.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.seblacko.rag.entities.Department;
import com.seblacko.rag.entities.EmployeePosition;
import com.seblacko.rag.util.hibernate.LoadTable;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/get_employee_position_names")
public class EmployeePositionNames {
    @GET
    public String getDepartmentNames(){
        List<Object[]> resultList = LoadTable.loadAll("EmployeePosition");
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode jsonArray = mapper.createArrayNode();

        for(Object[] result : resultList){
            EmployeePosition employeePosition = (EmployeePosition) result[0];
            ObjectNode jsonObject = mapper.createObjectNode();
            jsonObject.put("employee_position_name",employeePosition.getPositionName());
            jsonObject.put("employee_position_id",employeePosition.getId());
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }
}
