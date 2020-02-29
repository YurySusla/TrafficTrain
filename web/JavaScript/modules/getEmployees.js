/**
 * function getEmployees(employeesTable, allData, inputData, callback) this function sends a request to the servlet
 * to receive data about employees in the form of JSON and converts the received response
 * into a table for further output to the user
 * @param employeesTable is a user table
 * @param allData is a sign of displaying full information
 * @param inputData is the data for the request to the servlet
 * @param callback is a function that is called after the response is fully loaded
 */
export default function getEmployees(employeesTable, allData, inputData, callback) {
    /**
     * xhr is an object of type XMLHttpRequest for asynchronous exchange of information with a servlet
     * @type {XMLHttpRequest}
     */
    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'search_employee',true);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.responseType = 'json';
    xhr.send(inputData);
    if(allData == false){
        xhr.onload = () => callback();
    }
    xhr.onreadystatechange = function () {
        if(xhr.readyState == 4){
            if(xhr.status == 200){
                /**
                 * employees is a variable storing the servlet response in the form of JSON
                 * with a list of employee data
                 * @type {any}
                 */
                let employees = xhr.response;
                let tableDiv = document.createElement('div');
                tableDiv.style.overflowY = 'scroll';
                tableDiv.style.height = '150px';
                tableDiv.style.border = '1px solid black';
                let trEmployeeHead = document.createElement('tr');
                let thName = document.createElement('th');
                thName.innerText = "Name";
                trEmployeeHead.appendChild(thName);
                let thSurname = document.createElement('th');
                thSurname.innerText = "Surname";
                trEmployeeHead.appendChild(thSurname);
                if(allData == true){
                    let th_age = document.createElement('th');
                    th_age.innerText = "Age";
                    trEmployeeHead.appendChild(th_age);
                    let thExperience = document.createElement('th');
                    thExperience.innerText = "Experience";
                    trEmployeeHead.appendChild(thExperience);
                }
                let thProfession = document.createElement('th');
                thProfession.innerText = "Profession";
                trEmployeeHead.appendChild(thProfession);
                let th_check = document.createElement('th');
                th_check.innerText = "Check";
                trEmployeeHead.appendChild(th_check);
                tableDiv.appendChild(trEmployeeHead);

                for(let i = 0; i < employees.length; i++){
                    let trEmployee = document.createElement('tr');
                    trEmployee.id = employees[i].id;
                    let tdName = document.createElement('td');
                    let tdSurname = document.createElement('td');
                    let tdAge = document.createElement('td');
                    let tdExperience = document.createElement('td');
                    let tdProfession = document.createElement('td');
                    let tdCheck = document.createElement('td');
                    tdName.textContent = employees[i].name;
                    tdSurname.textContent = employees[i].surname;
                    if(allData == true){
                        tdAge.textContent = employees[i].age;
                        tdExperience.textContent = employees[i].experience;
                    }
                    tdProfession.textContent = employees[i].profession;
                    let checkbox = document.createElement('input');
                    checkbox.type = "radio";
                    checkbox.value = employees[i].id;
                    checkbox.name = "check_employees";
                    tdCheck.appendChild(checkbox);
                    trEmployee.appendChild(tdName);
                    trEmployee.appendChild(tdSurname);
                    if(allData == true){
                        trEmployee.appendChild(tdAge);
                        trEmployee.appendChild(tdExperience);
                    }
                    trEmployee.appendChild(tdProfession);
                    trEmployee.appendChild(tdCheck);
                    tableDiv.appendChild(trEmployee);
                }
                employeesTable.appendChild(tableDiv);
            }
        }
    }
}