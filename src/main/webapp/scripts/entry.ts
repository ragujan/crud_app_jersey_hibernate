const addDepartmentName = async (): Promise<void> => {
    let url: string = "./department_entry";
    let name: HTMLInputElement | null = document.querySelector("#department_name");

    if (name) {
        let formData = new FormData();
        formData.append("departmentName", name.value);
        const response = await fetch(url, {
            body: formData,
            method: "POST",
        });
        const text = await response.text()
        console.log(text);
    }
};

const addBtn = document.querySelector<HTMLButtonElement>("#add");

if (addBtn) {
    addBtn.addEventListener("click", async (): Promise<void> => {
        await addDepartmentName()
    })
}

const addEmployeePosition = async (): Promise<void> => {
    console.log("bro bro");
    let url: string = "./employee_position_entry";
    let name = document.querySelector<HTMLInputElement>("#employee_position_name");
    if (!name) {
        console.log("name is null")
        return;
    }
    ;
    if (name) {
        console.log("name is not null")
        const formData = new FormData();
        formData.append("employeePositionName", name.value);

        const response = await fetch(url, {
            body: formData,
            method: "POST",
        });
        const text = await response.text()
        console.log(text);
    }
};


const addEmployeePositionBtn = document.querySelector<HTMLInputElement>("#add_employee_position_name");
addEmployeePositionBtn?.addEventListener('click', async () => {
    await addEmployeePosition();
})
const loadDepartmentNames = async () => {
    let url: string = "./get_department_names";
    const response = await fetch(url);
    const text = await response.text();
    console.log(text);

    const jsonNames = JSON.parse(text);
    console.log(jsonNames);
    const selectTag: HTMLElement | null = document.getElementById("dep_names");
    if (selectTag) {
        console.log("select tag is detected")

        for (let i = 0; i < jsonNames.length; i++) {
            console.log("json object is ", jsonNames[i]["department_name"])
            const optionTag = document.createElement("option")
            optionTag.innerText = jsonNames[i]["department_name"];
            optionTag.setAttribute("value", `${jsonNames[i]["department_id"]}`);
            selectTag?.append(optionTag);
        }
    }
}

const loadEmployeePositionNames = async () => {
    let url: string = "./get_employee_position_names";
    const response = await fetch(url);
    const text = await response.text();
    console.log(text);

    const jsonNames = JSON.parse(text);
    console.log(jsonNames);
    const selectTag: HTMLElement | null = document.getElementById("emp_pos_names");
    if (selectTag) {
        console.log("select tag is detected")

        for (let i = 0; i < jsonNames.length; i++) {
            console.log("json object is ", jsonNames[i]["employee_position_name"])
            const optionTag = document.createElement("option")
            optionTag.innerText = jsonNames[i]["employee_position_name"];
            optionTag.setAttribute("value", `${jsonNames[i]["employee_position_id"]}`);
            selectTag?.append(optionTag);
        }
    }
}

window.addEventListener('load', async () => {
    if (document.getElementById("dep_names") && document.getElementById("emp_pos_names")) {
        console.log("elments are found")
        await loadEmployeePositionNames();
        await loadDepartmentNames();
    } else {
        console.log("normal")
    }

})

const addEmployee = async () => {
    const fname = document.getElementById("employee_first_name") as HTMLInputElement;
    const lname  = document.getElementById("employee_last_name") as HTMLInputElement;
    const posName = document.getElementById("emp_pos_names") as HTMLSelectElement;
    const depName= document.getElementById("dep_names") as HTMLSelectElement;
    const url = "./employee_entry";


    if (fname && lname && posName && depName) {
        const formData = new FormData();
        formData.append("fname", fname.value);
        formData.append("lname", lname.value);
        formData.append("posName", posName.value);
        formData.append("depName", depName.value);

        console.log(formData);
        const response = await fetch(url, {method: "POST",body:formData})
        const text =await response.text();
        console.log(text)
    }
}
const addEmployeeBtn = document.getElementById("add_employee") as HTMLButtonElement;

addEmployeeBtn!.addEventListener('click',async  ()=>{
    await addEmployee();
})

export {}