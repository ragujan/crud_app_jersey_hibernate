package com.seblacko.rag.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.seblacko.rag.entities.Department;
import com.seblacko.rag.util.hibernate.LoadTable;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/get_department_names")
public class DepartmentNames {
    @GET
    public String getDepartmentNames(){
        List<Object[]> resultList = LoadTable.loadAll("Department");
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode jsonArray = mapper.createArrayNode();

        for(Object[] result : resultList){
            Department department = (Department) result[0];
            ObjectNode jsonObject = mapper.createObjectNode();
            jsonObject.put("department_name",department.getName());
            jsonObject.put("department_id",department.getId());
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }
}
