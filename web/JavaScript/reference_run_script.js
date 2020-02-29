import getTrains from './modules/getTrains.js';
import getStations from './modules/getStations.js';
import searchTrainsForm, {
    searchAllTrains,
    searchByStationAndTime,
    searchSortTrains
} from "./modules/searchTrains.js";
import checkedInput, {dataChecking} from "./modules/checkInput.js";

/**
 * dataTable is a table for displaying data on train or station
 * @type {HTMLElement}
 */
let dataTable = document.getElementById('data');
/**
 * checkTrains is a sign of the choice of displaying trains
 * @type {boolean}
 */
let checkTrains = false;
/**
 * inputSearchBox is the place to enter data to search
 * @type {HTMLElement}
 */
let inputSearchBox = document.getElementById('input_search_box');
/**
 * inputBox is the place to display the data entry form
 * @type {HTMLElement}
 */
let inputBox = document.getElementById('input_box');

/**
 * checkStations is a sign of the choice of displaying stations
 * @type {boolean}
 */
let checkStations = false;
/**
 * className is the name of the class for choosing the ss for working with the necessary record
 * of either the table with trains or stations
 * @type {null}
 */
let className = null;
/**
 * url is the address of the servlet with which data will be exchanged
 * @type {null}
 */
let url = null;
/**
 * This is a function that starts when the HTML page is first loaded with url 'ReferenceBook.html'
 */
window.onload = function () {
    let radioButtons = document.getElementsByName('reference');
    let searchDiv = document.getElementById('search_trains');
    for(let i=0; i<radioButtons.length;i++){
        radioButtons[i].addEventListener("click",function () {
            dataTable.innerHTML = "";
            inputBox.innerHTML ="";
            switch (radioButtons[i].value) {
                case "trains_checked":
                    let inputData;
                    searchDiv.style.visibility = 'visible';
                    searchDiv.style.height = 'auto';
                    let checkSearchType = searchDiv.querySelectorAll('select[id = select]')[0];
                    checkSearchType.addEventListener("change", function () {
                        inputSearchBox.innerHTML ="";
                        inputBox.innerHTML ="";
                        inputSearchBox.style.height = "0px";
                        let typeOfChange = this.value;
                        dataTable.innerHTML = "";
                        searchTrainsForm(inputSearchBox, typeOfChange);
                        let accept = inputSearchBox.querySelector('button');
                        accept.addEventListener("click", function () {
                            switch (typeOfChange) {
                                case "1":
                                    inputData = searchAllTrains();
                                    break;
                                case "2":
                                    inputData = searchByStationAndTime(inputSearchBox);
                                    break;
                                case "3":
                                    inputData = searchSortTrains(inputSearchBox);
                                    break;
                            }
                            getTrains(dataTable,true,inputData);
                            inputSearchBox.innerHTML ="";
                            inputSearchBox.style.height = "0px";
                        });
                    });
                    checkTrains = true;
                    checkStations = false;
                    className = 'check_trains';
                    url = 'trains';
                    break;
                case "stations_checked":
                    inputSearchBox.innerHTML ="";
                    inputBox.innerHTML ="";
                    getStations(dataTable,true);
                    checkStations = true;
                    checkTrains = false;
                    className = 'check_stations';
                    url = 'stations';
                    searchDiv.style.visibility = 'hidden';
                    searchDiv.style.height = '0px';
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
    removeButton.addEventListener("click", function (){
        inputBox.innerHTML ="";
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
        inputBox.innerHTML ="";
        let radioButtons = document.getElementsByName(className);
        for(let i=0; i<radioButtons.length; i++){
            if(radioButtons[i].checked){
                let tr = document.getElementById(radioButtons[i].value);
                let td = tr.getElementsByTagName('td');
                if(checkTrains == true && checkStations == false){
                    let number = td[0].innerText;
                    let trainType = td[1].innerText;
                    let isBranded = td[3].innerText;
                    let distance = td[2].innerText;
                    createInputTrains(number, trainType, isBranded, distance, 1, radioButtons[i].value);
                } else {
                    let name = td[0].innerText;
                    let stationType = td[1].innerText;
                    let isWaitingHall = td[2].innerText;
                    createInputStation(name, stationType, isWaitingHall, 1, radioButtons[i].value);
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
        inputBox.innerHTML ="";
        if(checkTrains == true && checkStations == false){
            createInputTrains(null,null,null,null,2,null);
        } else {
            createInputStation(null,null,null,2,null);
        }
    });
}

/**
 * function createInputTrains(number, trainType, isBranded, distance, functionSelection, idTrain)
 * is a function that creates a form for entering data for adding and editing table records with trains
 * in the database
 * @param number is the number of the train
 * @param trainType is the type of the train
 * @param isBranded is a sign that the train is branded
 * @param distance is the distance of the train
 * @param functionSelection is a numerical expression of the choice of either the function for adding
 * a record or editing a record in the table
 * @param idTrain is id entries in the trains table
 */
function createInputTrains(number, trainType, isBranded, distance, functionSelection, idTrain) {

    let numberInput = document.createElement('input');
    let numberLabel = document.createElement('p');
    let trainTypeInput = document.createElement('div');
    let isBrandedInput = document.createElement('div');
    let distanceInput = document.createElement('input');
    let distanceLabel = document.createElement('p');
    let buttonAccept = document.createElement('button');
    let numberInputChecked = false;
    let distanceInputChecked = false;
    let distanceRegExp = /[0-9]/g;
    let nameRegExp = /[a-zA-Z0-9]/g;
    numberInput.addEventListener("blur",function () {
        numberInputChecked = dataChecking(numberInput.value, nameRegExp);
        checkedInput(numberInput, numberInputChecked);
    });
    distanceInput.addEventListener("blur",function () {
        distanceInputChecked = dataChecking(distanceInput.value, distanceRegExp);
        checkedInput(distanceInput, distanceInputChecked);
    });
    numberLabel.innerText = "Number";
    numberInput.value = number;
    distanceLabel.innerText = "Distance";
    distanceInput.value = distance;
    buttonAccept.innerText = "Accept";
    buttonAccept.id = "accept";

    for(let i=1; i<3; i++){
        let checkbox = document.createElement('input');
        let labelTrainType = document.createElement('label');
        checkbox.name = "Train type";
        checkbox.type = "radio";
        switch (i) {
            case 1: checkbox.value = "long-distance";
                break;
            case 2: checkbox.value = "suburban";
                break;
        }
        if(checkbox.value == trainType){
            checkbox.checked = true;
        }
        labelTrainType.innerHTML = checkbox.value;
        trainTypeInput.appendChild(labelTrainType);
        trainTypeInput.appendChild(checkbox);
    }

    for(let i=1; i<3; i++){
        let checkbox = document.createElement('input');
        let labelIsBranded = document.createElement('label');
        checkbox.name = "Is branded";
        checkbox.type = "radio";
        switch (i) {
            case 1: checkbox.value = "true";
                break;
            case 2: checkbox.value = "false";
                break;
        }
        if(checkbox.value == isBranded){
            checkbox.checked = true;
        }
        labelIsBranded.innerHTML = checkbox.value;
        isBrandedInput.appendChild(labelIsBranded);
        isBrandedInput.appendChild(checkbox);
    }

    inputBox.appendChild(numberLabel);
    inputBox.appendChild(numberInput);
    inputBox.appendChild(trainTypeInput);
    inputBox.appendChild(isBrandedInput);
    inputBox.appendChild(distanceLabel);
    inputBox.appendChild(distanceInput);
    inputBox.appendChild(document.createElement('br'));
    inputBox.appendChild(document.createElement('br'));
    inputBox.appendChild(buttonAccept);

    buttonAccept.addEventListener("click", function () {
        let checkedTrainType;
        let checkedIsBranded;
        let radioButtonsTrainType = document.getElementsByName('Train type');
        let radioButtonsIsBranded = document.getElementsByName('Is branded');
        for(let i=0; i<radioButtonsTrainType.length; i++){
            if(radioButtonsTrainType[i].checked){
                checkedTrainType = radioButtonsTrainType[i].value;
            }
        }
        for(let i=0; i<radioButtonsIsBranded.length; i++){
            if(radioButtonsIsBranded[i].checked){
                checkedIsBranded = radioButtonsIsBranded[i].value;
            }
        }

        if(numberInputChecked == true && distanceInputChecked == true) {
            send_data_train(numberInput.value, checkedTrainType, checkedIsBranded, distanceInput.value, functionSelection, idTrain);
        }
    });
}

/**
 * function send_data_train(numberInput, checkedTrainType, checkedIsBranded, distanceInput,
 * functionSelection, idTrain) is a function that sets all the parameters for a request to work with
 * a servlet with url 'trains' and generates data in JSON format
 * @param numberInput is the train number input field
 * @param checkedTrainType is the train type input field
 * @param checkedIsBranded is an input field indicating whether the train is branded
 * @param distanceInput is the train distance input field
 * @param functionSelection is the selected function for adding or editing data
 * @param idTrain is id entries in the trains table
 */
function send_data_train(numberInput, checkedTrainType, checkedIsBranded, distanceInput, functionSelection, idTrain) {
    let xhr = new XMLHttpRequest();
    let json;
    switch (functionSelection) {
        case 1: xhr.open('PUT', url, true);
            json = JSON.stringify(
                {
                    number : numberInput,
                    train_type: checkedTrainType,
                    is_branded : checkedIsBranded,
                    distance: distanceInput,
                    id: idTrain
                }
            );
            break;
        case 2: xhr.open('POST', url,true);
            json = JSON.stringify(
                {
                    number : numberInput,
                    train_type: checkedTrainType,
                    is_branded : checkedIsBranded,
                    distance: distanceInput
                }
            );
            break;
    }
    sendAndUpdate(xhr, json);
}

/**
 * function createInputStation(name, stationType, isWaitingHall, functionSelection, idStation)
 * is a function that creates a form for entering data for adding and editing table records with stations
 * in the database
 * @param name is the name of the station
 * @param stationType is the type of the station
 * @param isWaitingHall is a sign that there is a waiting room
 * @param functionSelection is a numerical expression of the choice of either the function for adding
 * a record or editing a record in the table
 * @param idStation is id entries in the stations table
 */
function createInputStation(name, stationType, isWaitingHall, functionSelection, idStation) {
    let inputBox = document.getElementById('input_box');
    let nameInput = document.createElement('input');
    let nameInputChecked = false;
    let nameRegExp = /[a-zA-Zа-яА-Я]/g;
    nameInput.addEventListener("blur",function () {
        nameInputChecked = dataChecking(nameInput.value, nameRegExp);
        checkedInput(nameInput, nameInputChecked);
    });
    let nameLabel = document.createElement('p');
    let stationTypeInput = document.createElement('div');
    let isWaitingHallInput = document.createElement('div');
    let buttonAccept = document.createElement('button');

    nameLabel.innerText = "Station name";
    nameInput.value = name;

    buttonAccept.innerText = "Accept";
    buttonAccept.id = "accept";

    for(let i=1; i<3; i++){
        let checkbox = document.createElement('input');
        let label_station_type = document.createElement('label');
        checkbox.name = "Station type";
        checkbox.type = "radio";
        switch (i) {
            case 1: checkbox.value = "railroad station";
                break;
            case 2: checkbox.value = "train stop";
                break;
        }
        if(checkbox.value == stationType){
            checkbox.checked = true;
        }
        label_station_type.innerHTML = checkbox.value;
        stationTypeInput.appendChild(label_station_type);
        stationTypeInput.appendChild(checkbox);
    }

    for(let i=1; i<3; i++){
        let checkbox = document.createElement('input');
        let label_is_waiting_hall = document.createElement('label');
        checkbox.name = "Is waiting hall";
        checkbox.type = "radio";
        switch (i) {
            case 1: checkbox.value = true;
                break;
            case 2: checkbox.value = false;
                break;
        }
        if(checkbox.value == isWaitingHall){
            checkbox.checked = true;
        }
        label_is_waiting_hall.innerHTML = checkbox.value;
        isWaitingHallInput.appendChild(label_is_waiting_hall);
        isWaitingHallInput.appendChild(checkbox);
    }

    inputBox.appendChild(nameLabel);
    inputBox.appendChild(nameInput);
    inputBox.appendChild(stationTypeInput);
    inputBox.appendChild(isWaitingHallInput);
    inputBox.appendChild(buttonAccept);

    buttonAccept.addEventListener("click", function () {
        let checkedStationType;
        let checkedIsWaitingHall;

        let radioButtons_station_type = document.getElementsByName('Station type');
        let radioButtonsIsWaitingHall = document.getElementsByName('Is waiting hall');
        for(let i=0; i<radioButtons_station_type.length; i++){
            if(radioButtons_station_type[i].checked){
                checkedStationType = radioButtons_station_type[i].value;
            }
        }
        for(let i=0; i<radioButtonsIsWaitingHall.length; i++){
            if(radioButtonsIsWaitingHall[i].checked){
                checkedIsWaitingHall = radioButtonsIsWaitingHall[i].value;
            }
        }
        if(nameInputChecked == true) {
            send_data_station(nameInput.value, checkedStationType, checkedIsWaitingHall, functionSelection, idStation);
        }
    });
}

/**
 * function send_data_station(nameInput, checkedStationType, checkedIsWaitingHall, functionSelection,
 * idStation) is a function that sets all the parameters for a request to work with
 * a servlet with url 'stations' and generates data in JSON format
 * @param nameInput is the station name input field
 * @param checkedStationType is the station type input field
 * @param checkedIsWaitingHall is the selection field whether there is a waiting room
 * @param functionSelection is the selected function for adding or editing data
 * @param idStation is id entries in the stations table
 */
function send_data_station(nameInput, checkedStationType, checkedIsWaitingHall, functionSelection, idStation) {
    let xhr = new XMLHttpRequest();
    let json;
    switch (functionSelection) {
        case 1: xhr.open('PUT', url, true);
            json = JSON.stringify(
                {
                    name : nameInput,
                    station_type: checkedStationType,
                    is_waiting_hall : checkedIsWaitingHall,
                    id: idStation
                }
            );
            break;
        case 2: xhr.open('POST', url,true);
            json = JSON.stringify(
                {
                    name : nameInput,
                    station_type: checkedStationType,
                    is_waiting_hall : checkedIsWaitingHall
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
                if(checkTrains == true && checkStations == false){
                    getTrains(dataTable, true);
                } else {
                    getStations(dataTable,true);
                }
            }
        }
    }
}