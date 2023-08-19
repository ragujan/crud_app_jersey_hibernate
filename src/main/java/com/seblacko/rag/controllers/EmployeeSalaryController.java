package com.seblacko.rag.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.seblacko.rag.entities.Employee;
import com.seblacko.rag.entities.EmployeeSalary;
import com.seblacko.rag.util.InputValidator;
import com.seblacko.rag.util.hibernate.AddRow;
import com.seblacko.rag.util.hibernate.LoadTable;
import com.seblacko.rag.util.hibernate.RowChecker;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/")
public class EmployeeSalaryController {
    @POST
    @Path("/add_salary")
    public String addSalary(@FormParam("amount") String amount,@FormParam("email") String email){
        if (!InputValidator.inputEmailIsValid(email) || !RowChecker.rowExists("Employee", "email", email)) {
            return "email does not exists or invalid";
        }else if(!InputValidator.inputPriceIsValid(amount)){
            return "Amount is not valid";
        }else{
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(format);
            Employee employee = RowChecker.getEntityByColumn(Employee.class,"email",email);
            System.out.println(employee.getEmail());
            EmployeeSalary employeeSalary = new EmployeeSalary();
            employeeSalary.setEmployee(employee);
            employeeSalary.setPaidAt(formattedDateTime);
            employeeSalary.setPaidAmount(Double.parseDouble(amount));
            AddRow.addRow(employeeSalary);
            return "ok";
        }
    }
    @GET
    @Path("get_all_salary")
    public String getSalaryAll(){
        List<Object[]> employeeSalaries = LoadTable.loadAll("EmployeeSalary");
        if(!employeeSalaries.get(0)[0].equals("empty")){
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode jsonArray = mapper.createArrayNode();
            for(Object[] object:employeeSalaries){
                EmployeeSalary employeeSalary =(EmployeeSalary) object[0];
                Employee employee = employeeSalary.getEmployee();
                ObjectNode jsonObject = mapper.createObjectNode();
                jsonObject.put("salary_id",employeeSalary.getId());
                jsonObject.put("paid_at",employeeSalary.getPaidAt());
                jsonObject.put("paid_amount",employeeSalary.getPaidAmount());
                jsonObject.put("employee_id", employee.getId());
                jsonObject.put("employee_email", employee.getEmail());
                jsonObject.put("employee_first_name", employee.getFirstName());
                jsonObject.put("employee_last_name", employee.getLastName());

                jsonArray.add(jsonObject);

            }
            return jsonArray.toString();
        }else{
            return "empty rows";
        }
    }
}
