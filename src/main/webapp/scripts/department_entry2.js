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
const addDepartmentName = () => __awaiter(void 0, void 0, void 0, function* () {
    let url = "./department_entry";
    let name = document.querySelector("#department_name");
    if (name) {
        let formData = new FormData();
        formData.append("departmentName", name.value);
        yield fetch(url, {
            body: formData,
            method: "POST",
        })
            .then((res) => res.text())
            .then((text) => {
            console.log(text);
        });
    }
});
const addBtn = document.querySelector("#add");
if (addBtn) {
    addBtn.addEventListener("click", () => __awaiter(void 0, void 0, void 0, function* () {
        yield addDepartmentName();
    }));
}
