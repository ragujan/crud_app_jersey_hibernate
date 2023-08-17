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
const addDepartmentName = () => __awaiter(void 0, void 0, void 0, function* () {
    let url = "./department_entry";
    let name = document.querySelector("#department_name");
    if (name) {
        let formData = new FormData();
        formData.append("departmentName", name.value);
        const response = yield fetch(url, {
            body: formData,
            method: "POST",
        });
        const text = yield response.text();
        console.log(text);
    }
});
const addBtn = document.querySelector("#add");
if (addBtn) {
    addBtn.addEventListener("click", () => __awaiter(void 0, void 0, void 0, function* () {
        yield addDepartmentName();
    }));
}
const addEmployeePosition = () => __awaiter(void 0, void 0, void 0, function* () {
    console.log("bro bro");
    let url = "./employee_position_entry";
    let name = document.querySelector("#employee_position_name");
    if (!name) {
        console.log("name is null");
        return;
    }
    ;
    if (name) {
        console.log("name is not null");
        const formData = new FormData();
        formData.append("employeePositionName", name.value);
        const response = yield fetch(url, {
            body: formData,
            method: "POST",
        });
        const text = yield response.text();
        console.log(text);
    }
});
const addEmployeePositionBtn = document.querySelector("#add_employee_position_name");
addEmployeePositionBtn === null || addEmployeePositionBtn === void 0 ? void 0 : addEmployeePositionBtn.addEventListener('click', () => __awaiter(void 0, void 0, void 0, function* () {
    yield addEmployeePosition();
}));
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
            optionTag.setAttribute("id", `${jsonNames[i]["department_id"]}`);
            selectTag === null || selectTag === void 0 ? void 0 : selectTag.append(optionTag);
        }
    }
});
const loadEmployeePositionNames = () => __awaiter(void 0, void 0, void 0, function* () {
    let url = "./get_employee_position_names";
    const response = yield fetch(url);
    const text = yield response.text();
    console.log(text);
    const jsonNames = JSON.parse(text);
    console.log(jsonNames);
    const selectTag = document.getElementById("emp_pos_names");
    if (selectTag) {
        console.log("select tag is detected");
        for (let i = 0; i < jsonNames.length; i++) {
            console.log("json object is ", jsonNames[i]["employee_position_name"]);
            const optionTag = document.createElement("option");
            optionTag.innerText = jsonNames[i]["employee_position_name"];
            optionTag.setAttribute("id", `${jsonNames[i]["employee_position_id"]}`);
            selectTag === null || selectTag === void 0 ? void 0 : selectTag.append(optionTag);
        }
    }
});
window.addEventListener('load', () => __awaiter(void 0, void 0, void 0, function* () {
    if (document.getElementById("dep_names") && document.getElementById("emp_pos_names")) {
        console.log("elments are found");
        yield loadEmployeePositionNames();
        yield loadDepartmentNames();
    }
    else {
        console.log("normal");
    }
}));
