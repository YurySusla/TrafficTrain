import getEmployees from './modules/getEmployees.js';
import searchEmployeeForm, {searchByAge, searchByTrainAndDay, searchByPositions, searchByExperience, searchAllEmployees} from "./modules/searchEmployeeInput.js";
import checkedInput , {dataChecking} from "./modules/checkInput.js";

/**
 * This is a function that starts when the HTML page is first loaded with url 'HRDepartment.html'
 */
window.onload = function () {
    let employeesTable = document.getElementById('employees');
    let inputData;
    let checkSearchType = document.getElementsByTagName('select')[0];
    let inputSearchBox = document.getElementById('input_search_box');
    checkSearchType.addEventListener("change", function () {
        inputSearchBox.innerHTML ="";
        inputSearchBox.style.height = "0px";
        let typeOfChange = this.value;
        employeesTable.innerHTML = "";
        searchEmployeeForm(inputSearchBox, typeOfChange);
        let accept = inputSearchBox.querySelectorAll('button')[0];
        accept.addEventListener("click", function () {
            switch (typeOfChange) {
                case "1":
                    inputData = searchAllEmployees();
                    break;
                case "2":
                    inputData = searchByAge(inputSearchBox);
                    break;
                case "3":
                    inputData = searchByExperience(inputSearchBox);
                    break;
                case "4":
                    inputData = searchByPositions(inputSearchBox);
                    break;
                case "5":
                    inputData = searchByTrainAndDay(inputSearchBox);
                    break;
            }
            if(inputData != null){
                getEmployees(employeesTable, true, inputData);
                inputSearchBox.innerHTML ="";
                inputSearchBox.style.height = "0px";
            }

        });
    });
    changeEmployee();
    addEmployee();
    removeEmployee();
}

/**
 * function removeEmployee() is a function that selects an entry from the table of received data and generates
 * a request to delete an entry in the servlet to delete an entry in the database by id
 */
function removeEmployee() {
    let removeButton = document.getElementById('remove_button');
    removeButton.addEventListener("click", function () {
        let radioButtons = document.getElementsByName('check_employees');
        for(let i=0; i<radioButtons.length; i++){
            if(radioButtons[i].checked){
                let xhr = new XMLHttpRequest();
                xhr.open('DELETE','human_resource', true);
                let json = JSON.stringify({
                   id : radioButtons[i].value
                });
                sendAndUpdate(xhr, json);
            }
        }
    });
}

/**
 * function addEmployee() is a function that selects an entry from the table of received data and generates
 * a request to the servlet to edit the record in the database by id and passes the changed parameters
 */
function addEmployee() {
    let add_button = document.getElementById('add_button');
    add_button.addEventListener("click",function () {
        createInput(null,null,null,null, null,2, null);
    });
}

/**
 * function changeEmployee() is a function that adds a new record to the database from data obtained
 * from data obtained from the form
 */
function changeEmployee() {
    let change_button = document.getElementById('change_button');
    change_button.addEventListener("click", function () {
        let radioButtons = document.getElementsByName('check_employees');
        for(let i=0; i<radioButtons.length; i++){
            if(radioButtons[i].checked){
                let tr = document.getElementById(radioButtons[i].value);
                let td = tr.getElementsByTagName('td');
                let name = td[0].innerText;
                let surname = td[1].innerText;
                let age = td[2].innerText;
                let experience = td[3].innerText;
                let profession = td[4].innerText;

                createInput(name, surname, age, experience, profession, 1, radioButtons[i].value);
            }
        }
    });
}

/**
 * function createInput(name, surname, age, experience, profession, functionSelection, idEmployee) is a function
 * that creates a data entry form for adding or editing id records of the employee table in the database
 * @param name is the name of the employee
 * @param surname is the surname of the employee
 * @param age is the age of the employee
 * @param experience is the experience of the employee
 * @param profession is the profession of the employee
 * @param functionSelection is a numerical expression of the choice of either the function for adding
 * a record or editing a record in the table
 * @param idEmployee is id entries in the employee table
 */
function createInput(name, surname, age, experience, profession, functionSelection, idEmployee) {
    let inputBox = document.getElementById('input_box');
    let nameInput = document.createElement('input');
    let nameLabel = document.createElement('p');
    let surnameInput = document.createElement('input');
    let surnameLabel = document.createElement('p');
    let ageInput = document.createElement('input');
    let ageLabel = document.createElement('p');
    let experienceInput = document.createElement('input');
    let experienceLabel = document.createElement('p');
    let professionInput = document.createElement('div');
    let buttonAccept = document.createElement('button');
    nameLabel.innerText = "Name";
    nameInput.value = name;
    surnameLabel.innerText = "Surname";
    surnameInput.value = surname;
    ageLabel.innerText = "Age";
    ageInput.value = age;
    experienceLabel.innerText = "Experience";
    experienceInput.value = experience;let nameInputChecked = false;
    let surnameInputChecked = false;
    let ageInputChecked = false;
    let experienceInputChecked = false;
    let nameAndSurnameRegExp = /[a-zA-Zа-яА-Я]/g;
    let experienceAndAgeRegExp = /[0-9]/g;
    nameInput.addEventListener("blur",function () {
        nameInputChecked = dataChecking(nameInput.value, nameAndSurnameRegExp);
        checkedInput(nameInput, nameInputChecked);
    });
    surnameInput.addEventListener("blur",function () {
        surnameInputChecked = dataChecking(surnameInput.value, nameAndSurnameRegExp);
        checkedInput(surnameInput, surnameInputChecked);
    });
    ageInput.addEventListener("blur",function () {
        ageInputChecked = dataChecking(ageInput.value, experienceAndAgeRegExp);
        checkedInput(ageInput, ageInputChecked);
    });
    experienceInput.addEventListener("blur",function () {
        experienceInputChecked = dataChecking(experienceInput.value, experienceAndAgeRegExp);
        checkedInput(experienceInput, experienceInputChecked);
    });
    buttonAccept.innerText = "Accept";
    buttonAccept.id = "accept";

    for(let i=1; i<5; i++){
        let checkbox = document.createElement('input');
        let labelProfession = document.createElement('label');
        checkbox.name = "profession";
        checkbox.type = "radio";
        switch (i) {
            case 1: checkbox.value = "driver";
                break;
            case 2: checkbox.value = "assistant";
                break;
            case 3: checkbox.value = "chief";
                break;
            case 4: checkbox.value = "conductor";
                break;
        }
        if(checkbox.value == profession){
            checkbox.checked = true;
        }
        labelProfession.innerHTML = checkbox.value;
        professionInput.appendChild(labelProfession);
        professionInput.appendChild(checkbox);
    }

    inputBox.appendChild(nameLabel);
    inputBox.appendChild(nameInput);
    inputBox.appendChild(surnameLabel);
    inputBox.appendChild(surnameInput);
    inputBox.appendChild(ageLabel);
    inputBox.appendChild(ageInput);
    inputBox.appendChild(experienceLabel);
    inputBox.appendChild(experienceInput);
    inputBox.appendChild(professionInput);
    inputBox.appendChild(buttonAccept);

    buttonAccept.addEventListener("click", function () {
        let checked_profession;

        let radioButtons = document.getElementsByName('profession');
        for(let i=0; i<radioButtons.length; i++){
            if(radioButtons[i].checked){
                checked_profession = radioButtons[i].value;
            }
        }
        if(nameInputChecked == true && surnameInputChecked == true && ageInputChecked == true && experienceInputChecked == true){
        sendData(nameInput.value, surnameInput.value, ageInput.value, experienceInput.value, checked_profession, functionSelection, idEmployee);
        }
    });
}

/**
 * function send_data(nameInput, surnameInput, ageInput, experienceInput, professionInput, functionSelection,
 * idEmployee) is a function that sets all the parameters for a request to work with
 * a servlet with url 'human_resource' and generates data in JSON format
 * @param nameInput is the employee name input field
 * @param surnameInput is the employee surname input field
 * @param ageInput is the employee age input field
 * @param experienceInput is the employee experience input field
 * @param professionInput is the employee profession input field
 * @param functionSelection is a numerical expression of the choice of either the function for adding
 * a record or editing a record in the table
 * @param idEmployee is id entries in the employee table
 */
function sendData(nameInput, surnameInput, ageInput, experienceInput, professionInput, functionSelection, idEmployee) {
    let xhr = new XMLHttpRequest();
    let json;
    switch (functionSelection) {
        case 1: xhr.open('PUT', 'human_resource', true);
            json = JSON.stringify(
                {
                    name : nameInput,
                    surname: surnameInput,
                    age : ageInput,
                    experience: experienceInput,
                    profession: professionInput,
                    id: idEmployee
                }
            );
            break;
        case 2: xhr.open('POST', 'human_resource',true);
            json = JSON.stringify(
                {
                    name : nameInput,
                    surname: surnameInput,
                    age : ageInput,
                    experience: experienceInput,
                    profession: professionInput
                }
            );
            break;
    }
    sendAndUpdate(xhr, json);
}

/**
 * function sendAndUpdate(xhr, json) is a function that sends data to a servlet
 * @param xhr is an object of type XMLHttpRequest
 * @param json is the generated data for the request in the JSON format
 */
function sendAndUpdate(xhr, json) {
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);
    xhr.onreadystatechange = function () {
        if(xhr.readyState == 4){
                if(xhr.status == 200){
                document.getElementById('input_box').innerHTML = "";
                document.getElementById('employees').innerHTML = "";
                let employeesTable = document.getElementById('employees');
                getEmployees(employeesTable, true);
            }
        }
    }
}

