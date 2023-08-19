console.log("employee_salary");
const searchParams = new URLSearchParams(window.location.search);
const getAllSalary = async () => {
    const url = "./get_all_salary";
    try {
        const response = await fetch(url, { method: "GET" });
        if (!response.ok) {
            throw new Error(`failed status: ${response.status}`);
        }
        const text = await response.text();
        console.log(text);
        jsonObject = JSON.parse(text);
        const table = document.getElementById("table") ;
        const tBody = document.getElementById("tbody") ;
        const columnCount = table.getElementsByTagName("th").length;
        for (let i = 0; i < jsonObject.length; i++) {
            const id = jsonObject[i]['salary_id']
            const fname = jsonObject[i]["employee_first_name"];
            const lname = jsonObject[i]["employee_last_name"];
            const email = jsonObject[i]["employee_email"];
            const paid_at = jsonObject[i]["paid_at"];
            const paid_amount= jsonObject[i]["paid_amount"];
            const tRow = document.createElement("tr") ;
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

            td[4].innerText = paid_at;
            tRow.appendChild(td[4]);
            td[5].innerText = paid_amount;
            tRow.appendChild(td[5]);
            tBody.appendChild(tRow);
        }

    } catch (error) {
        console.error("Error salary data:", error);
    }
};
const addEmpSalary = async ()=>{
    const url = "./add_salary";
    const formData = new FormData();

    formData.append("amount",document.getElementById("amount").value)
    formData.append("email",searchParams.get("email"))
    const response = await fetch(url,{body:formData,method:"POST"})
    const text = await  response.text();
    console.log(text)
    const tBody = document.getElementById("tbody") ;

    if (tBody) {
        const newTbody = document.createElement("tbody");
        newTbody.setAttribute("id","tbody");
        tBody.parentNode?.replaceChild(newTbody, tBody);
    }
    await getAllSalary();
}
const addSalaryBtn = document.getElementById("add_salary_btn");
addSalaryBtn.addEventListener('click',async ()=>{
    await addEmpSalary();
})
window.addEventListener('load',async ()=>{
    getAllSalary();
})