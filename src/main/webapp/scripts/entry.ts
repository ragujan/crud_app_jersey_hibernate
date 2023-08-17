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
            optionTag.setAttribute("id", `${jsonNames[i]["department_id"]}`);
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
            optionTag.setAttribute("id", `${jsonNames[i]["employee_position_id"]}`);
            selectTag?.append(optionTag);
        }
    }
}

window.addEventListener('load', async () => {
    await loadDepartmentNames();
    await loadEmployeePositionNames();

})
export {}