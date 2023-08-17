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
        console.log(jsonObject);
        const table = document.getElementById("table");
        const tBody = document.getElementById("tbody");
        const columnCount = table.getElementsByTagName("th").length;
        console.log("column count is ", columnCount);
        for (let i = 0; i < jsonObject.length; i++) {
            const id = jsonObject[i]['employee_id'];
            const fname = jsonObject[i]["employee_first_name"];
            const lname = jsonObject[i]["employee_last_name"];
            const hired_at = jsonObject[i]["hired_at"];
            const deparment = jsonObject[i]["employee_department"];
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
            td[3].innerText = hired_at;
            tRow.appendChild(td[3]);
            td[4].innerText = deparment;
            tRow.appendChild(td[4]);
            td[5].innerText = position;
            tRow.appendChild(td[5]);
            let editBtn = document.createElement("button");
            editBtn.innerText = "edit";
            // editBtn.onclick = setItemsForEdit(id,fname,lname,deparment, position);
            editBtn.onclick = function () {
                setItemsForEdit(id, fname, lname, deparment, position_id);
            };
            td[6].appendChild(editBtn);
            tRow.appendChild(td[6]);
            let deleteBtn = document.createElement("button");
            deleteBtn.innerText = "delete";
            td[7].appendChild(deleteBtn);
            tRow.appendChild(td[7]);
            let paySalary = document.createElement("button");
            paySalary.innerText = "salary";
            td[8].appendChild(paySalary);
            tRow.appendChild(td[8]);
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
    console.log(jsonNames);
    // const selectTag: HTMLElement | null = document.getElementById("emp_pos_names");
    if (selectTag) {
        console.log("select tag is detected");
        for (let i = 0; i < jsonNames.length; i++) {
            console.log("json object is ", jsonNames[i]["employee_position_name"]);
            const optionTag = document.createElement("option");
            optionTag.innerText = jsonNames[i]["employee_position_name"];
            optionTag.setAttribute("value", `${jsonNames[i]["employee_position_id"]}`);
            selectTag === null || selectTag === void 0 ? void 0 : selectTag.append(optionTag);
        }
        selectTag.value = position;
    }
});
const loadDepartmentNames = () => __awaiter(void 0, void 0, void 0, function* () {
    let url = "./get_department_names";
    const response = yield fetch(url);
    const text = yield response.text();
    console.log(text);
    const jsonNames = JSON.parse(text);
    console.log(jsonNames);
    const selectTag = document.getElementById("dep_names");
    if (selectTag) {
        console.log("select tag is detected");
        for (let i = 0; i < jsonNames.length; i++) {
            console.log("json object is ", jsonNames[i]["department_name"]);
            const optionTag = document.createElement("option");
            optionTag.innerText = jsonNames[i]["department_name"];
            optionTag.setAttribute("value", `${jsonNames[i]["department_id"]}`);
            selectTag === null || selectTag === void 0 ? void 0 : selectTag.append(optionTag);
        }
    }
});
const setItemsForEdit = (id, fname, lname, department, position) => __awaiter(void 0, void 0, void 0, function* () {
    const html = `    <span>Employee first name</span>
    <input type="text" value="${fname}" id="employee_first_name">
    <br>
    <span>Employee last name</span>
    <input type="text" value="${lname}" id="employee_last_name">
    <br>
    <span>Employee last name</span>
    <br>
    <span>Department Name</span>
    <select id="dep_names"  ></select>

    <span>Employee position Name</span>
    <select id="emp_pos_names"></select>
    <br>
    <button>Update</button>`;
    const editSection = document.getElementById("edit_section");
    editSection.innerHTML = html;
    const departmentsTag = document.getElementById("dep_names");
    const positionsTag = document.getElementById("emp_pos_names");
    yield loadEmployeePositionNames(positionsTag, position);
    // if (positionsTag) positionsTag.value = position;
});
window.addEventListener('load', () => __awaiter(void 0, void 0, void 0, function* () {
    yield loadAllEmployees();
}));
