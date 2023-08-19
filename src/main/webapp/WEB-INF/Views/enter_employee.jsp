<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 8/16/2023
  Time: 6:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Add an employee</h1>
<span>Email</span>
<input type="text" id="email">
<br>
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
<button id="add_employee">add employee</button>

<script>var exports = {};</script>
<script src="${BASE_URL}scripts/entry.js"></script>
</body>
</html>
