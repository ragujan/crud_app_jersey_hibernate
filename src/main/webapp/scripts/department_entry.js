const add = async () => {
    let url = "./department_entry";
    let name = document.querySelector("#department_name");
    let data = {"name": "rag", "id": 1};  // JSON data

    await fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': "application/json"  // Use application/json for JSON data
        },
        body: JSON.stringify(data)  // Convert JSON object to string
    })
        .then((res) => res.text())
        .then((text) => {
            console.log(text);
        });
}
const add2 = async () => {
    let url = "./department_entry2";
    let name = document.querySelector("#department_name");
    let formData = new FormData();
    formData.append("departmentName", name.value);
    await fetch(url, {
        body:formData,
        method: "POST",

    })
        .then((res) => res.text())
        .then((text) => {
            console.log(text);
        });
}
const addBtn = document.querySelector("#add_btn");

addBtn.addEventListener('click', () => {
    // add();
    add2();
})