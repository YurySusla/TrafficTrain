import getTrains from "./getTrains.js";
import getDayInput from "./getDayInput.js";
import checkedInput, {dataChecking} from "./checkInput.js";
let jsonData;
let minAgeValueChecked = false;
let maxAgeValueChecked = false;
let minExperienceValueChecked = false;
let maxExperienceValueChecked = false;
let minAgeValue;
let maxAgeValue;
let minExperienceValue;
let maxExperienceValue;


/**
 * function searchEmployeeForm(dataInputDiv, typeOfChange) is a function for generating a request form
 * for receiving data about employees
 * @param dataInputDiv this is the element in which the form for entering data about employees
 * on the HTML page will be located
 * @param typeOfChange is a numeric expression of the choice of data entry type for employee search
 */
export default function searchEmployeeForm(dataInputDiv, typeOfChange) {
    let acceptButton;
    switch (typeOfChange) {
        case "1":
            dataInputDiv.style.height = 'auto';
            acceptButton = addAcceptButton();
            acceptButton.style.marginBottom = "10px";
            dataInputDiv.appendChild(acceptButton);
            break;
        case "2":
            dataInputDiv.style.height = 'auto';
            searchByAgeForm(dataInputDiv);
            acceptButton = addAcceptButton();
            acceptButton.style.height = '20px';
            acceptButton.style.marginBottom = "20px";
            dataInputDiv.appendChild(document.createElement('br'));
            dataInputDiv.appendChild(document.createElement('br'));
            dataInputDiv.appendChild(acceptButton);
            break;
        case "3":
            dataInputDiv.style.height = 'auto';
            searchByExperienceForm(dataInputDiv);
            acceptButton = addAcceptButton();
            acceptButton.style.height = '20px';
            acceptButton.style.marginBottom = "20px";
            dataInputDiv.appendChild(document.createElement('br'));
            dataInputDiv.appendChild(document.createElement('br'));
            dataInputDiv.appendChild(acceptButton);
            break;
        case "4":
            dataInputDiv.style.height = 'auto';
            searchByPositionsForm(dataInputDiv);
            acceptButton = addAcceptButton();
            acceptButton.style.height = '20px';
            acceptButton.style.marginBottom = "20px";
            dataInputDiv.appendChild(document.createElement('br'));
            dataInputDiv.appendChild(document.createElement('br'));
            dataInputDiv.appendChild(acceptButton);
            break;
        case "5":
            dataInputDiv.style.height = 'auto';
            searchByTrainAndDayForm(dataInputDiv);
            acceptButton = addAcceptButton();
            dataInputDiv.appendChild(document.createElement('br'));
            dataInputDiv.appendChild(acceptButton);
            break;
    }
}

/**
 * function searchAllEmployees() this is a function that generates a request
 * in the JSON format for the output of all employees
 * @returns {string}
 */
export function searchAllEmployees() {
    jsonData = JSON.stringify(
        {
            type_of_choice : 1
        }
    );
    return jsonData;
}

/**
 * function searchByAgeForm(dataInputDiv) is a function that forms the entry form for employee
 * search data by age
 * @param dataInputDiv is an element to insert an input form on the HTML page
 */
function searchByAgeForm(dataInputDiv) {
    dataInputDiv.appendChild(addLabel("min. age"));
    dataInputDiv.appendChild(addInput(0, "min_age"));
    dataInputDiv.appendChild(addLabel(" - max. age"));
    dataInputDiv.appendChild(addInput(0, "max_age"));
    minAgeValue = dataInputDiv.querySelectorAll('input[name = min_age]')[0];
    maxAgeValue = dataInputDiv.querySelectorAll('input[name = max_age]')[0];
    let ageRegExp = /[0-9]/g;
    minAgeValue.addEventListener("blur",function () {
        minAgeValueChecked = dataChecking(minAgeValue.value, ageRegExp);
        checkedInput(minAgeValue, minAgeValueChecked);
    });
    maxAgeValue.addEventListener("blur",function () {
        maxAgeValueChecked = dataChecking(maxAgeValue.value, ageRegExp);
        checkedInput(maxAgeValue, maxAgeValueChecked);
    });
}

/**
 * function searchByAge(dataInputDiv) is a function that generates a request in the JSON format
 * for searching employees by age
 * @param dataInputDiv is the form from which data is taken
 * @returns {string}
 */
export function searchByAge(dataInputDiv){
    if(maxAgeValueChecked == true && minAgeValueChecked == true){
        jsonData = JSON.stringify(
            {
                type_of_choice : 2,
                min_age : minAgeValue.value,
                max_age: maxAgeValue.value
            }
        )
    } else {
        jsonData = null;
    }
    return jsonData;
}

/**
 * function searchByExperienceForm(dataInputDiv) is a function that forms a data entry form
 * for searching employees by experience
 * @param dataInputDiv is an element to insert an input form on the HTML page
 */
function searchByExperienceForm(dataInputDiv) {
    dataInputDiv.appendChild(addLabel("min. experience"));
    dataInputDiv.appendChild(addInput(0, "min_exp"));
    dataInputDiv.appendChild(addLabel("- max. experience"));
    dataInputDiv.appendChild(addInput(0, "max_exp"));
    minExperienceValue = dataInputDiv.querySelectorAll('input[name = min_exp]')[0];
    maxExperienceValue = dataInputDiv.querySelectorAll('input[name = max_exp]')[0];
    let experienceRegExp = /[0-9]/g;
    minExperienceValue.addEventListener("blur",function () {
        minExperienceValueChecked = dataChecking(minExperienceValue.value, experienceRegExp);
        checkedInput(minExperienceValue, minExperienceValueChecked);
    });
    maxExperienceValue.addEventListener("blur",function () {
        maxExperienceValueChecked = dataChecking(maxExperienceValue.value, experienceRegExp);
        checkedInput(maxExperienceValue, maxExperienceValueChecked);
    });
}

/**
 * function searchByExperience(dataInputDiv) is a function that generates a request in the JSON format
 * for searching employees by experience
 * @param dataInputDiv is the form from which data is taken
 * @returns {string}
 */
export function searchByExperience(dataInputDiv){
    if(minExperienceValueChecked == true && maxExperienceValueChecked == true){
        jsonData = JSON.stringify(
            {
                type_of_choice : 3,
                min_experience : minExperienceValue.value,
                max_experience: maxExperienceValue.value
            }
        )
    } else {
        jsonData = null;
    }
    return jsonData;
}

/**
 * function searchByPositionsForm(dataInputDiv) is a function that forms the entry form for employee
 * search data by position
 * @param dataInputDiv is an element to insert an input form on the HTML page
 */
function searchByPositionsForm(dataInputDiv) {
    for(let i=0;i<4;i++) {
        let input_position = document.createElement('input');
        input_position.type = "radio";
        input_position.name = "position";
        input_position.id = i + 1;
        switch (i) {
            case 0:
                dataInputDiv.appendChild(addLabel("Driver"));
                break;
            case 1:
                dataInputDiv.appendChild(addLabel("Assistant"));
                break;
            case 2:
                dataInputDiv.appendChild(addLabel("Chief"));
                break;
            case 3:
                dataInputDiv.appendChild(addLabel("Conductor"));
                break;
        }
        dataInputDiv.appendChild(input_position);
    }
}

/**
 * function searchByPositions(dataInputDiv) is a function that generates a request in the JSON format
 * for searching employees by position
 * @param dataInputDiv is the form from which data is taken
 * @returns {*}
 */
export function searchByPositions(dataInputDiv){
    let checkbox_position = dataInputDiv.querySelectorAll('input[name = position]');
    for(let i=0;i<checkbox_position.length;i++){
        if(checkbox_position[i].checked){
            jsonData = JSON.stringify(
                {
                    type_of_choice : 4,
                    position : checkbox_position[i].id
                }
            );
        }
    }
    return jsonData;
}

/**
 * function searchByTrainAndDayForm(dataInputDiv) is a function that forms the entry form for employee
 * search data by train and day
 * @param dataInputDiv is an element to insert an input form on the HTML page
 */
function searchByTrainAndDayForm(dataInputDiv) {
    let trainsTable = document.createElement('table');
    let temp = JSON.stringify(
        {
            type_of_choice : 1
            }
    );
    getTrains(trainsTable, true, temp, null);
    dataInputDiv.appendChild(trainsTable);
    let dayDiv = document.createElement('div');
    getDayInput(dayDiv);
    dataInputDiv.appendChild(dayDiv);
}

/**
 * function searchByTrainAndDay(dataInputDiv) is a function that generates a request in the JSON format
 * for searching employees by train and day
 * @param dataInputDiv is the form from which data is taken
 * @returns {string}
 */
export function searchByTrainAndDay(dataInputDiv) {
    let trainRows = dataInputDiv.querySelectorAll('input[name = check_trains]');
    let dayRows = dataInputDiv.querySelectorAll('input[name = day]');
    let checkedTrain = null;
    let checkedDay = null;
    for(let i=0; i<trainRows.length; i++){
        if(trainRows[i].checked){
            checkedTrain = trainRows[i].value;
        }
    }
    for(let i=0; i<dayRows.length; i++){
        if(dayRows[i].checked){
            checkedDay = dayRows[i].id;
        }
    }
    jsonData = JSON.stringify(
        {
            type_of_choice : 5,
            train_id : checkedTrain,
            day_of_week: checkedDay
        }
    );
    return jsonData;
}

/**
 * function addAcceptButton() is a function that forms the Accept button
 * @returns {HTMLButtonElement}
 */
function addAcceptButton() {
    let acceptButton = document.createElement('button');
    acceptButton.innerText = "Accept";
    return acceptButton;
}

/**
 * function addLabel(label_text) is a function that forms a label tag
 * @param label_text is the text in the tag
 * @returns {HTMLLabelElement}
 */
function addLabel(label_text) {
    let label = document.createElement('label');
    label.innerText = label_text;
    return label;
}

/**
 * function addInput(defaultValue, inputName) is the function that adds the input tag
 * @param defaultValue is the default output in input
 * @param inputName is tag name
 * @returns {HTMLInputElement}
 */
function addInput(defaultValue, inputName) {
    let inputTag = document.createElement('input');
    inputTag.type = "text";
    inputTag.name = inputName;
    inputTag.value = defaultValue;
    return inputTag;
}



