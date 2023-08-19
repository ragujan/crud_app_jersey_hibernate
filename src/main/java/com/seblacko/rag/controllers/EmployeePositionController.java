package com.seblacko.rag.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.seblacko.rag.entities.EmployeePosition;
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
public class EmployeePositionController {
    @POST
    @Path("/employee_position_entry")
    public String addEmployee(@FormDataParam("employeePositionName") String employeePositionName) {
        if(!InputValidator.inputTextIsValid(employeePositionName)){
            return "invalid input";
        }else{
            System.out.println("Received employee position name dep2 : " + employeePositionName);

            if(RowChecker.rowExists("EmployeePosition","positionName",employeePositionName)){
                return "name already exists";
            }else{
                EmployeePosition employeePosition = new EmployeePosition(employeePositionName);
                AddRow.addRow(employeePosition);
                return "ok";
            }
        }

    }
    @GET
    @Path("/get_employee_position_names")
    public String getEmployees(){
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
