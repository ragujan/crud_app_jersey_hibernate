const login = async () => {
    const email = document.getElementById("email") as HTMLInputElement;
    const password = document.getElementById("password") as HTMLInputElement;

    // let formData = new FormData();
    // formData.append("email","admin@gmail.com");
    // formData.append("password", "123");
    // console.log("check function")
    let formData :string = JSON.stringify({email:email.value,password:password.value})
    let url = "./admin_auth";
    let response = await fetch(url,{body:formData,method:"POST"})
    let text = await response.text();
    console.log(JSON.parse(text));
    if(text != "invalid credentials".toLowerCase()){
        const token = JSON.parse(text)[0]['token'];
        const rfTOken = JSON.parse(text)[0]['refresh_token'];
        console.log(token)
        localStorage.setItem("jwt_token",'Bearer '+token);
        localStorage.setItem("jwt_rf_token",'Bearer '+rfTOken);
        url = "/admin_home";
        console.log("hey hey")

    }

}

window.addEventListener('load',async ()=>{
    await login();
})