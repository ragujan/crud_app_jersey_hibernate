package com.seblacko.rag.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.seblacko.rag.entities.Department;
import com.seblacko.rag.entities.Employee;
import com.seblacko.rag.entities.EmployeePosition;
import com.seblacko.rag.util.InputValidator;
import com.seblacko.rag.util.hibernate.*;
import jakarta.ws.rs.*;
import org.glassfish.jersey.server.mvc.Viewable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/")
public class EmployeeController {

    @POST
    @Path("/employee_entry")
    public String addEmployee(@FormParam("fname") String firstName,
                              @FormParam("lname") String lastName,
                              @FormParam("depName") String depName,
                              @FormParam("posName") String posName,
                              @FormParam("email") String email) {

        if (!InputValidator.inputTextIsValid(firstName)) {
            return "employee first name is invalid";
        } else if (!InputValidator.inputTextIsValid(lastName)) {
            return "employee last name is invalid";
        } else if (!InputValidator.inputNumberIsValid(depName) || !RowChecker.rowExists("Department", "id", depName)) {
            return "employee department name is invalid";
        } else if (!InputValidator.inputNumberIsValid(posName) || !RowChecker.rowExists("EmployeePosition", "id", posName)) {
            return "employee position name is invalid";
        } else if (!InputValidator.inputEmailIsValid(email) || RowChecker.rowExists("Employee", "email", email)) {
            return "employee position email is invalid or already exists";
        } else {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(format);

            EmployeePosition employeePosition = new EmployeePosition();
            employeePosition.setId(Integer.parseInt(posName));
            Department department = new Department();
            department.setId(Integer.parseInt(depName));

            Employee employee = new Employee();

            employee.setDepartment(department);
            employee.setEmployeePosition(employeePosition);
            employee.setFirstName(firstName);
            employee.setEmail(email);
            employee.setLastName(lastName);
            employee.setHiredDate(formattedDateTime);
            AddRow.addRow(employee);
            return "Success";
        }
    }

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
            jsonObject.put("employee_email", employee.getEmail());
            jsonObject.put("employee_first_name", employee.getFirstName());
            jsonObject.put("employee_last_name", employee.getLastName());
            jsonObject.put("hired_at", employee.getHiredDate());
            jsonObject.put("employee_department", department.getName());
            jsonObject.put("employee_department_id", department.getId());
            jsonObject.put("employee_position", employeePosition.getPositionName());
            jsonObject.put("employee_position_id", employeePosition.getId());
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();

    }

    @PUT
    @Path("/update_employee")
//    @Consumes(MediaType.APPLICATION_JSON)
    public String update(@FormParam("email") String email, @FormParam("fname") String fname,
                         @FormParam("lname") String lname,
                         @FormParam("department") String department,
                         @FormParam("position") String position) {
        System.out.println("fname is " + fname);
        System.out.println("lname is " + lname);
        System.out.println("dep is " + department);
        System.out.println("pos is " + position);

        if (!InputValidator.inputTextIsValid(fname)) {

            return "First name is not valid";
        } else if (!InputValidator.inputTextIsValid(lname)) {
            return "last name is not valid";
        } else if (!InputValidator.inputEmailIsValid(email) || !RowChecker.rowExists("Employee", "email", email)) {
            return "email does not exists";
        } else if (!InputValidator.inputNumberIsValid(department) || !RowChecker.rowExists("Department", "id", department)) {
            return "department name is invalid";
        } else if (!InputValidator.inputNumberIsValid(position) || !RowChecker.rowExists("EmployeePosition", "id", position)) {
            return "employee position name is invalid";
        } else {
            System.out.println("position is " + position);
            System.out.println("department is " + department);
            Employee employee = RowChecker.getEntityByColumn(Employee.class, "email", email);
            employee.getDepartment().setId(Integer.parseInt(department));
            employee.getEmployeePosition().setId(Integer.parseInt(position));
            employee.setFirstName(fname);
            employee.setLastName(lname);
            UpdateRow.update(employee);
            return "Ok";
        }

    }

    @DELETE
    @Path("/delete_employee")
    public String delete(@FormParam("email") String email) {
        if (!InputValidator.inputEmailIsValid(email) || !RowChecker.rowExists("Employee", "email", email)) {
            return "email does not exists or invalid";
        } else {
            Employee employee = RowChecker.getEntityByColumn(Employee.class, "email", email);
            System.out.println("email is "+employee.getEmail());
            System.out.println("email is "+employee.getFirstName());
            System.out.println("email is "+employee.getLastName());
            System.out.println("email is "+employee.getHiredDate());
            SessionFactory sessionFactory = InitialSessionFactory.getSessionFactory();
            Session session = sessionFactory.openSession();
            try {
//                DeleteRow.delete(employee);
                session.beginTransaction();
                session.delete(employee);
                session.getTransaction().commit();
            }catch (HibernateException ex){

            }
//            DeleteRow.delete(employee);
            return "ok";
        }
    }

}
