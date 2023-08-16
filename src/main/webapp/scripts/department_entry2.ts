const addDepartmentName = async (): Promise<void> => {
    let url: string = "./department_entry";
    let name: HTMLInputElement | null = document.querySelector("#department_name");

    if (name) {
        let formData: FormData = new FormData();
        formData.append("departmentName", name.value);

        await fetch(url, {
            body: formData,
            method: "POST",
        })
            .then((res: Response): Promise<string> => res.text())
            .then((text: string): void => {
                console.log(text);
            });
    }
};

const addBtn:HTMLInputElement | null= document.querySelector("#add");

if(addBtn){
    addBtn.addEventListener("click",async ():Promise<void>=>{
       await addDepartmentName()
    })
}