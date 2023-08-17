<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 8/17/2023
  Time: 5:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>

<body>
<h1>View Employees</h1>
<table id="table">
    <thead>
    <th>id</th>
    <th>f_name</th>
    <th>l_name</th>
    <th>hired_at</th>
    <th>depatment</th>
    <th>position</th>
    <th>edit</th>
    <th>delete</th>
    <th>salary pay</th>
    </thead>
    <tbody id="tbody">

    </tbody>
</table>
<div id="edit_section">
    <span>Employee first name</span>
    <input type="text" id="employee_first_name">
    <br>
    <span>Employee last name</span>
    <input type="text" id="employee_last_name">
    <br>
    <span>Employee last name</span>
    <br>
    <span>Department Name</span>
    <%--department names should  be the select tag--%>
    <select id="dep_names"></select>

    <span>Employee position Name</span>
    <%--department names should  be the select tag--%>
    <select id="emp_pos_names"></select>
    <br>
    <button>Update</button>
</div>
<script>var exports = {};</script>
<script src="${BASE_URL}scripts/view_employees.js"></script>
</body>
</html>
