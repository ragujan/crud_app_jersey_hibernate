"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
const loadAllEmployees = () => __awaiter(void 0, void 0, void 0, function* () {
    const url = "./get_all_employees";
    const response = yield fetch(url);
    const text = yield response.text();
    let jsonObject;
    if (text) {
        jsonObject = JSON.parse(text);
        const table = document.getElementById("table");
        const tBody = document.getElementById("tbody");
        const columnCount = table.getElementsByTagName("th").length;
        for (let i = 0; i < jsonObject.length; i++) {
            const id = jsonObject[i]['employee_id'];
            const fname = jsonObject[i]["employee_first_name"];
            const lname = jsonObject[i]["employee_last_name"];
            const email = jsonObject[i]["employee_email"];
            const hired_at = jsonObject[i]["hired_at"];
            const deparment = jsonObject[i]["employee_department"];
            const deparment_id = jsonObject[i]["employee_department_id"];
            const position = jsonObject[i]["employee_position"];
            const position_id = jsonObject[i]["employee_position_id"];
            const tRow = document.createElement("tr");
            const td = [];
            for (let j = 0; j < columnCount; j++) {
                td.push(document.createElement("td"));
            }
            td[0].innerText = id;
            tRow.appendChild(td[0]);
            td[1].innerText = fname;
            tRow.appendChild(td[1]);
            td[2].innerText = lname;
            tRow.appendChild(td[2]);
            td[3].innerText = email;
            tRow.appendChild(td[3]);
            td[4].innerText = hired_at;
            tRow.appendChild(td[4]);
            td[5].innerText = deparment;
            tRow.appendChild(td[5]);
            td[6].innerText = position;
            tRow.appendChild(td[6]);
            let editBtn = document.createElement("button");
            editBtn.innerText = "edit";
            // editBtn.onclick = setItemsForEdit(id,fname,lname,deparment, position);
            editBtn.onclick = function () {
                setItemsForEdit(id, email, fname, lname, deparment_id, position_id);
            };
            td[7].appendChild(editBtn);
            tRow.appendChild(td[7]);
            let deleteBtn = document.createElement("button");
            deleteBtn.innerText = "delete";
            deleteBtn.onclick = function () {
                deleteEmployee(email);
            };
            td[8].appendChild(deleteBtn);
            tRow.appendChild(td[8]);
            let paySalary = document.createElement("button");
            paySalary.innerText = "salary";
            paySalary.onclick = () => {
                window.location.href = "./employee_salary?email=" + email;
            };
            td[9].appendChild(paySalary);
            tRow.appendChild(td[9]);
            tBody.appendChild(tRow);
        }
    }
});
const loadEmployeePositionNames = (selectTag, position) => __awaiter(void 0, void 0, void 0, function* () {
    let url = "./get_employee_position_names";
    const response = yield fetch(url);
    const text = yield response.text();
    console.log(text);
    const jsonNames = JSON.parse(text);
    // console.log(jsonNames);
    // const selectTag: HTMLElement | null = document.getElementById("emp_pos_names");
    if (selectTag) {
        for (let i = 0; i < jsonNames.length; i++) {
            // console.log("json object is ", jsonNames[i]["employee_position_name"])
            const optionTag = document.createElement("option");
            optionTag.innerText = jsonNames[i]["employee_position_name"];
            optionTag.setAttribute("value", `${jsonNames[i]["employee_position_id"]}`);
            selectTag === null || selectTag === void 0 ? void 0 : selectTag.append(optionTag);
        }
        selectTag.value = position;
    }
});
const loadDepartmentNames = (selectTag, department) => __awaiter(void 0, void 0, void 0, function* () {
    let url = "./get_department_names";
    const response = yield fetch(url);
    const text = yield response.text();
    const jsonNames = JSON.parse(text);
    // const selectTag: HTMLElement | null = document.getElementById("dep_names");
    if (selectTag) {
        for (let i = 0; i < jsonNames.length; i++) {
            // console.log("json object is ", jsonNames[i]["department_name"])
            const optionTag = document.createElement("option");
            optionTag.innerText = jsonNames[i]["department_name"];
            optionTag.setAttribute("value", `${jsonNames[i]["department_id"]}`);
            selectTag === null || selectTag === void 0 ? void 0 : selectTag.append(optionTag);
        }
        selectTag.value = department;
    }
});
const setItemsForEdit = (id, email, fname, lname, department, position) => __awaiter(void 0, void 0, void 0, function* () {
    const html = ` <span>Employee email</span>
    <input type="text" value="${email}" id="employee_email">
    <br>   
    <span>Employee first name</span>
    
    <input type="text" value="${fname}" id="employee_first_name">
    <br>
    <span>Employee last name</span>
    <input type="text" value="${lname}" id="employee_last_name">
    <br>
    <span>Department Name</span>
    <select id="dep_names"  ></select>

    <span>Employee position Name</span>
    <select id="emp_pos_names"></select>
    <br>
    <button onclick="updateEmployee();" id="update_btn">Update</button>`;
    const editSection = document.getElementById("edit_section");
    editSection.innerHTML = html;
    const departmentsTag = document.getElementById("dep_names");
    const positionsTag = document.getElementById("emp_pos_names");
    yield loadEmployeePositionNames(positionsTag, position);
    yield loadDepartmentNames(departmentsTag, department);
    // if (positionsTag) positionsTag.value = position;
});
const updateEmployee = () => __awaiter(void 0, void 0, void 0, function* () {
    const email = document.getElementById("employee_email");
    const fname = document.getElementById("employee_first_name");
    const lname = document.getElementById("employee_last_name");
    const departmentName = document.getElementById("dep_names");
    const positionName = document.getElementById("emp_pos_names");
    const url = './update_employee';
    if (fname && lname && departmentName && positionName && email) {
        // console.log("everything is fine and everthing will be fine ")
        const formData = new FormData();
        formData.append("email", email.value);
        formData.append("fname", fname.value);
        formData.append("lname", lname.value);
        formData.append("position", positionName.value);
        formData.append("department", departmentName.value);
        const response = yield fetch(url, { method: "PUT", body: formData });
        const text = yield response.text();
        console.log(text);
    }
});
const deleteEmployee = (email) => __awaiter(void 0, void 0, void 0, function* () {
    var _a;
    const url = './delete_employee';
    if (email) {
        // console.log("everything is fine and everthing will be fine ")
        const formData = new FormData();
        formData.append("email", email);
        const response = yield fetch(url, { method: "DELETE", body: formData });
        const text = yield response.text();
        console.log(text);
        if (text.toLocaleLowerCase() === "ok") {
            const tBody = document.getElementById("tbody");
            if (tBody) {
                const newTbody = document.createElement("tbody");
                newTbody.setAttribute("id", "tbody");
                (_a = tBody.parentNode) === null || _a === void 0 ? void 0 : _a.replaceChild(newTbody, tBody);
            }
            loadAllEmployees();
        }
    }
});
const updateBtn = document.getElementById("update_btn");
window.addEventListener('load', () => __awaiter(void 0, void 0, void 0, function* () {
    yield loadAllEmployees();
}));
