<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 8/19/2023
  Time: 11:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee Salary</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<h1>View Salary</h1>
<table id="table">
    <thead>
    <th>id</th>
    <th>f_name</th>
    <th>l_name</th>
    <th>email</th>
    <th>paid at</th>
    <th>amount</th>
    </thead>
    <tbody id="tbody">

    </tbody>
</table>
<div id="edit_section">
    <span>Amount</span>
    <input id="amount"/>
    <button id="add_salary_btn">Add Salary</button>
</div>
<script src="${BASE_URL}scripts/employee_salary.js"></script>
</body>
</html>
