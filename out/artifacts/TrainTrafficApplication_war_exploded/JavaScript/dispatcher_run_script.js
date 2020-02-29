import getTrains from './modules/getTrains.js';
import getStations from './modules/getStations.js';
import getStaff from "./modules/getStaff.js";
import getTimeTable from "./modules/getTimeTable.js";
import getEmployees from "./modules/getEmployees.js";
import checkDayTable from "./modules/getDayInput.js";
import searchEmployeeForm, {
    searchAllTimeTable,
    searchByTypeOfTrain,
    searchByTypeOfTrainAndStation
} from "./modules/searchTimeTable.js";

/**
 * dataTable is a table for displaying data on train schedules or staff
 * @type {HTMLElement}
 */
let dataTable = document.getElementById('data');
/**
 * inputBox is the place to display the data entry form
 * @type {HTMLElement}
 */
let inputBox = document.getElementById('input_box');
/**
 * checkTimeTable is a sign of the choice of displaying the train schedule
 * @type {boolean}
 */
let checkTimeTable = false;
/**
 * checkStaff is a sign of the choice of displaying data on staff
 * @type {boolean}
 */
let checkStaff = false;
/**
 * className is the name of the class for choosing the ss for working with the necessary record
 * of either the table with the schedule of trains or the staff
 * @type {null}
 */
let className = null;
/**
 * url is the address of the servlet with which data will be exchanged
 * @type {null}
 */
let url = null;
/**
 * This is a function that starts when the HTML page is first loaded with url 'DispatcherPage.html'
 */
window.onload = function () {
    let radioButtons = document.getElementsByName('reference');
    let searchDiv = document.getElementById('search_time_table');
    for(let i=0; i<radioButtons.length;i++){
        radioButtons[i].addEventListener("click",function () {
            //dataTable.innerHTML = "";
            switch (radioButtons[i].value) {
                case "staff_checked": getStaff(dataTable, true);
                    dataTable.innerHTML = "";
                    inputBox.innerHTML = "";
                    checkTimeTable = false;
                    checkStaff = true;
                    url = 'staff';
                    className = 'check_staff';
                    searchDiv.style.visibility = 'hidden';
                    searchDiv.style.height = '0px';
                    break;
                case "time_table_checked":
                    dataTable.innerHTML = "";
                    inputBox.innerHTML = "";
                    let inputData;
                    searchDiv.style.visibility = 'visible';
                    searchDiv.style.height = 'auto';
                    let checkSearchType = searchDiv.querySelectorAll('select[id = select]')[0];
                    let inputSearchBox = document.getElementById('input_search_box');
                    checkSearchType.addEventListener("change", function () {
                        inputSearchBox.innerHTML = "";
                        inputSearchBox.style.height = "0px";
                        let typeOfChange = this.value;
                        dataTable.innerHTML = "";
                        searchEmployeeForm(inputSearchBox, typeOfChange);
                        let accept = inputSearchBox.querySelector('button');
                        accept.addEventListener("click", function () {
                            switch (typeOfChange) {
                                case "1":
                                    inputData = searchAllTimeTable();
                                    break;
                                case "2":
                                    inputData = searchByTypeOfTrain(inputSearchBox);
                                    break;
                                case "3":
                                    inputData = searchByTypeOfTrainAndStation(inputSearchBox);
                                    break;
                            }
                            getTimeTable(dataTable,inputData);
                            inputSearchBox.innerHTML ="";
                            inputSearchBox.style.height = "0px";
                        });
                    });
                    checkTimeTable = true;
                    checkStaff = false;
                    url = 'time_table';
                    className = 'check_time_table';
                    break;
            }
        });
    }
    addFunction();
    removeFunction();
    changeFunction();
}

/**
 * function removeFunction() is a function that selects an entry from the table of received data and generates
 * a request to delete an entry in the servlet to delete an entry in the database by id
 */
function removeFunction() {
    let removeButton = document.getElementById('remove_button');
    removeButton.addEventListener("click", function () {
        inputBox.innerHTML = "";
        let radioButtons = document.getElementsByName(className);
        for(let i=0; i<radioButtons.length; i++){
            if(radioButtons[i].checked){
                let xhr = new XMLHttpRequest();
                xhr.open('DELETE',url, true);
                let json = JSON.stringify({
                    id : radioButtons[i].value
                });
                sendAndUpdate(xhr, json);
            }
        }
    });
}

/**
 * function changeFunction() is a function that selects an entry from the table of received data and generates
 * a request to the servlet to edit the record in the database by id and passes the changed parameters
 */
function changeFunction() {
    let changeButton = document.getElementById('change_button');
    changeButton.addEventListener("click", function () {
        inputBox.innerHTML = "";
        let radioButtons = document.getElementsByName(className);
        for(let i=0; i<radioButtons.length; i++){
            if(radioButtons[i].checked){
                let tr = document.getElementById(radioButtons[i].value);
                let td = tr.getElementsByTagName('td');
                if(checkTimeTable == true && checkStaff == false){
                    let trainId = td[0].id;
                    let stationId = td[1].id;
                    let day = td[2].id;
                    let arrivalTime = td[3].innerText;
                    let departureTime = td[4].innerText;
                    createInputTimeTable(trainId, stationId, day, arrivalTime, departureTime, 1, radioButtons[i].value);
                } else {
                    let trainId = td[0].id;
                    let employeeId = td[1].id;
                    let day = td[3].id;
                    createInputStaff(trainId, employeeId, day, 1, radioButtons[i].value);
                }
            }
        }
    });
}

/**
 * function addFunction() is a function that adds a new record to the database from data obtained
 * from data obtained from the form
 */
function addFunction() {
    let addButton = document.getElementById('add_button');
    addButton.addEventListener("click",function () {
        inputBox.innerHTML = "";
        if(checkTimeTable == true && checkStaff == false){
            createInputTimeTable(null, null, null, null, null, 2, null);
        } else {
            createInputStaff(null, null, null, 2, null);
        }
    });
}

/**
 * function createInputTimeTable(trainId, stationId, day, arrivalTime, departureTime, functionSelection, timetableId)
 * is a function that creates a form for entering data for adding and editing table records with train
 * schedules in the database
 * @param trainId is id records in the train table in the database
 * @param stationId is the id of the entries in the table of stations in the database
 * @param day is the serial number of the day of the week
 * @param arrivalTime is the train departure time
 * @param departureTime is the arrival time of the train
 * @param functionSelection is a numerical expression of the choice of either the function for adding
 * a record or editing a record in the table
 * @param timetableId is id entries in the train schedule table
 */
function createInputTimeTable(trainId, stationId, day, arrivalTime, departureTime, functionSelection, timetableId) {

    let arrivalTimeLabel = document.createElement('p');
    arrivalTimeLabel.innerText = "Arrival time: ";
    let arrivalTimeInput = document.createElement('input');
    arrivalTimeInput.type = "time";
    arrivalTimeInput.value = arrivalTime;

    let departureTimeLabel = document.createElement('p');
    departureTimeLabel.innerText = "Departure time: ";
    let departureTimeInput = document.createElement('input');
    departureTimeInput.type = "time";
    departureTimeInput.value = departureTime;


    let tableInput = document.createElement('table');
    tableInput.id = "table_input";
    let tableTrInput = document.createElement('tr');

    let tdDay = document.createElement('td');
    tdDay.id = "input_day";
    checkDayTable(tdDay);
    let days = tdDay.querySelectorAll('input[name=day]');
    for(let i=0; i<days.length; i++){
        if(days[i].id == day){
            days[i].checked = true;
        }
    }
    tableTrInput.appendChild(tdDay);

    let stationsTable = document.createElement('table');
    stationsTable.id = "station_table";
    let tdStations = document.createElement('td');
    tdStations.appendChild(stationsTable);
    tableTrInput.appendChild(tdStations);

    let trainsTable = document.createElement('table');
    trainsTable.id = "trains_table";
    let tdTrains = document.createElement('td');
    tdTrains.appendChild(trainsTable);
    tableTrInput.appendChild(tdTrains);

    let stationsRows = 0;
    getStations(stationsTable,false, ()=>{
        delay(2000).then(() => {
            stationsRows = selectRow(stationsTable, stationId, "check_stations");
        });
    });

    let trainsRows = 0;
    let temp = JSON.stringify(
        {
            type_of_choice : 1
        });
    getTrains(trainsTable, false, temp, ()=>{
        delay(2000).then(()=>{
            trainsRows = selectRow(trainsTable, trainId, "check_trains");
        });
    });

    tableInput.appendChild(tableTrInput);

    let buttonAccept = document.createElement('button');
    buttonAccept.innerText = "Accept";
    buttonAccept.id = "accept";
    inputBox.appendChild(arrivalTimeLabel);
    inputBox.appendChild(arrivalTimeInput);
    inputBox.appendChild(departureTimeLabel);
    inputBox.appendChild(departureTimeInput);
    inputBox.appendChild(tableInput);
    inputBox.appendChild(buttonAccept);
    buttonAccept.addEventListener("click", function () {
    let checkedTrain = null;
    let checkedStation = null;
    let checkedDay = null;
    for(let i=0; i<trainsRows.length; i++){
        if(trainsRows[i].checked){
          checkedTrain = trainsRows[i].value;
        }
    }
    for(let i=0; i<stationsRows.length; i++){
        if(stationsRows[i].checked){
           checkedStation = stationsRows[i].value;
        }
    }
    for(let i=0; i<days.length; i++){
        if(days[i].checked){
           checkedDay = days[i].id;
        }
    }
    sendDataTimetable(checkedTrain, checkedStation, checkedDay, arrivalTimeInput.value,
        departureTimeInput.value, functionSelection, timetableId);
    });

}

/**
 * function createInputStaff(trainId, employeeId, day, functionSelection, staffId) is a function that creates a form
 * for entering or editing ID entries in the table of staff of the train
 * in the database from the data from the table of employees and the train table
 * @param trainId is id records in the train table in the database
 * @param employeeId is id records in the employee table in the database
 * @param day is the serial number of the day of the week
 * @param functionSelection is a numerical expression of the choice of either the function for adding
 * a record or editing a record in the table
 * @param staffId is id records in the staff table of the train staff in the database
 */
function createInputStaff(trainId, employeeId, day, functionSelection, staffId) {
    let tableInput = document.createElement('table');
    tableInput.id = "table_input";
    let tableTrInput = document.createElement('tr');

    let tdDay = document.createElement('td');
    tdDay.id = "input_day";
    checkDayTable(tdDay);
    let days = tdDay.querySelectorAll('input[name=day]');
    for(let i=0; i<days.length; i++){
        if(days[i].id == day){
            days[i].checked = true;
        }
    }
    tableTrInput.appendChild(tdDay);
    let temp = JSON.stringify(
        {
            type_of_choice : 1
        });

    let tdTrains = document.createElement('td');
    let trainsTable = document.createElement('table');
    trainsTable.id = "trains_table";
    let trainsRows = 0;
    getTrains(trainsTable, false, temp,()=>{
        delay(2000).then(()=>{
            trainsRows = selectRow(trainsTable, trainId, "check_trains");
            tdTrains.appendChild(trainsTable);
            tableTrInput.appendChild(tdTrains);
        });
    });

    let tdEmployees = document.createElement('td');
    let employeesTable = document.createElement('table');
    employeesTable.id = "employees_table";
    let employeesRows = 0;
    getEmployees(employeesTable, false, temp,()=>{
        delay(2000).then(()=>{
            employeesRows = selectRow(employeesTable, employeeId, "check_employees");
            tdEmployees.appendChild(employeesTable);
            tableTrInput.appendChild(tdEmployees);
        });
    });

    tableInput.appendChild(tableTrInput);
    let buttonAccept = document.createElement('button');
    buttonAccept.innerText = "Accept";
    buttonAccept.id = "accept";
    inputBox.appendChild(tableInput);
    inputBox.appendChild(buttonAccept);
    buttonAccept.addEventListener("click", function () {
        let checkedTrain = null;
        let checkedEmployee = null;
        let checkedDay = null;
        for(let i=0; i<trainsRows.length; i++){
            if(trainsRows[i].checked){
                checkedTrain = trainsRows[i].value;
            }
        }
        for(let i=0; i<employeesRows.length; i++){
            if(employeesRows[i].checked){
                checkedEmployee = employeesRows[i].value;
            }
        }
        for(let i=0; i<days.length; i++){
            if(days[i].checked){
                checkedDay = days[i].id;
            }
        }
        sendDataStaff(checkedTrain, checkedEmployee, checkedDay, functionSelection, staffId);

    });
}

/**
 * function sendDataTimetable(checkedTrain, checkedStation, checkedDay, inputArrivalTime, inputDepartureTime,
 * functionSelection, timetableId) is a function that sets all the parameters for a request to work with
 * a servlet with url 'time_table' and generates data in JSON format
 * @param checkedTrain is the id of the train selected from the input form
 * @param checkedStation is the id of the station selected from the input form
 * @param checkedDay is the serial number of the day of the week obtained from the input form
 * @param inputArrivalTime is the train arrival time obtained from the input form
 * @param inputDepartureTime is the train departure time obtained from the input form
 * @param functionSelection is the selected function for adding or editing data for train break records
 * @param timetableId is the id of the train schedule table entry in the database
 */
function sendDataTimetable(checkedTrain, checkedStation, checkedDay, inputArrivalTime, inputDepartureTime,
                             functionSelection, timetableId) {
    let xhr = new XMLHttpRequest();
    let json;
    switch (functionSelection) {
        case 1: xhr.open('PUT', 'time_table', true);
            json = JSON.stringify(
                {
                    train_id : checkedTrain,
                    station_id: checkedStation,
                    day : checkedDay,
                    arrival_time: inputArrivalTime,
                    departure_time: inputDepartureTime,
                    time_table_id: timetableId
                }
            );
            break;
        case 2: xhr.open('POST', 'time_table',true);
            json = JSON.stringify(
                {
                    train_id : checkedTrain,
                    station_id: checkedStation,
                    day : checkedDay,
                    arrival_time: inputArrivalTime,
                    departure_time: inputDepartureTime
                }
            );
            break;
    }
    sendAndUpdate(xhr, json);
}

/**
 * function sendDataStaff(checkedTrain, checkedEmployee, checkedDay, functionSelection, staffId) it is a function
 * that sets all request parameters for sending to the servlet with url 'staff' and generates data in JSON format
 * @param checkedTrain is the id of the train selected from the input form
 * @param checkedEmployee is the id of the employee selected from the input form
 * @param checkedDay is the id of the train selected from the input form
 * @param functionSelection is the selected function for adding or editing data for train break records
 * @param staffId is the id of the staff table entry in the database
 */
function sendDataStaff(checkedTrain, checkedEmployee, checkedDay, functionSelection, staffId) {
    let xhr = new XMLHttpRequest();
    let json;
    switch (functionSelection) {
        case 1: xhr.open('PUT', 'staff', true);
            json = JSON.stringify(
                {
                    train_id : checkedTrain,
                    employee_id: checkedEmployee,
                    day : checkedDay,
                    staff_id: staffId
                }
            );
            break;
        case 2: xhr.open('POST', 'staff',true);
            json = JSON.stringify(
                {
                    train_id : checkedTrain,
                    employee_id: checkedEmployee,
                    day : checkedDay
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
                document.getElementById('data').innerHTML = "";
                if(checkTimeTable == true && checkStaff == false){
                    getTimeTable(dataTable, true);
                } else {
                    getStaff(dataTable,true);
                }
            }
        }
    }
}

/**
 * function selectRow(table, id, className) is a function to select checkbox
 * @param table is the table in which checkbox is located
 * @param id is the id of the table entry
 * @param className is the class name for checkbox selection
 * @returns {NodeListOf<HTMLElementTagNameMap[string]> | NodeListOf<Element> | NodeListOf<SVGElementTagNameMap[string]>}
 */
function selectRow(table, id, className)
{
    let selectRows = table.querySelectorAll('input[name = '+ className +']');
    for(let i=0; i<selectRows.length; i++){
        if(selectRows[i].value == id ){
            selectRows[i].checked = true;
        }
    }
    return selectRows;
}

/**
 * function delay(ms) is a delay setting function
 * @param ms is delay time
 * @returns {Promise<unknown>}
 */
function delay(ms) {
    return new Promise(resolve => {
        setTimeout(resolve, ms);
    });
}