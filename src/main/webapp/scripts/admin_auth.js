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
const login = () => __awaiter(void 0, void 0, void 0, function* () {
    const email = document.getElementById("email");
    const password = document.getElementById("password");
    let formData = new FormData();
    formData.append("email", "admin@gmail.com");
    formData.append("password", "123");
    console.log("check function");
    // let formData :string = JSON.stringify({email:email.value,password:password.value})
    let url = "./admin_auth";
    let response = yield fetch(url, { body: formData, method: "POST" });
    let text = yield response.text();
    console.log(JSON.parse(text));
    if (text != "invalid credentials".toLowerCase()) {
        const token = JSON.parse(text)[0]['token'];
        const rfTOken = JSON.parse(text)[0]['refresh_token'];
        console.log(token);
        localStorage.setItem("jwt_token", 'Bearer ' + token);
        localStorage.setItem("jwt_rf_token", 'Bearer ' + rfTOken);
        url = "/admin_home";
        console.log("hey hey");
    }
});
window.addEventListener('load', () => __awaiter(void 0, void 0, void 0, function* () {
    yield login();
}));
