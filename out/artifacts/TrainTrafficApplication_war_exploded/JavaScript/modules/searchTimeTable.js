import getStations from "./getStations.js";
let jsonData;
/**
 * function searchTimeTableForm(dataInputDiv, typeOfChange) is a function that selects the type
 * of data entry form for searching records from train schedules by input numeric expression
 * @param dataInputDiv is the element inside which the data entry form is placed
 * @param typeOfChange is a numeric expression for choosing the type of input form
 */
export default function searchTimeTableForm(dataInputDiv, typeOfChange) {
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
            searchByTypeOfTrainForm(dataInputDiv);
            acceptButton = addAcceptButton();
            acceptButton.style.height = '20px';
            acceptButton.style.marginBottom = "20px";
            dataInputDiv.appendChild(document.createElement('br'));
            dataInputDiv.appendChild(document.createElement('br'));
            dataInputDiv.appendChild(acceptButton);
            break;
        case "3":
            dataInputDiv.style.height = 'auto';
            searchByTypeOfTrainAndStationForm(dataInputDiv);
            acceptButton = addAcceptButton();
            acceptButton.style.height = '20px';
            acceptButton.style.marginBottom = "20px";
            dataInputDiv.appendChild(document.createElement('br'));
            dataInputDiv.appendChild(document.createElement('br'));
            dataInputDiv.appendChild(acceptButton);
            break;
    }
}

/**
 * function searchAllTimeTable() is a function that generates a JSON request to display all train schedule entries
 * @returns {string}
 */
export function searchAllTimeTable() {
    jsonData = JSON.stringify(
        {
            type_of_choice : 1
        }
    );
    return jsonData;
}

/**
 * function searchByTypeOfTrainForm(dataInputDiv) is a function that forms a data entry form for
 * searching train schedule records by train type
 * @param dataInputDiv is the tag where the input form will be placed
 */
function searchByTypeOfTrainForm(dataInputDiv) {
        for(let i=0;i<2;i++) {
            let inputPosition = document.createElement('input');
            inputPosition.type = "radio";
            inputPosition.name = "train_type";
            inputPosition.id = i;
            switch (i) {
                case 0:
                    dataInputDiv.appendChild(addLabel("Long-distance"));
                    break;
                case 1:
                    dataInputDiv.appendChild(addLabel("Suburban"));
                    break;
            }
            dataInputDiv.appendChild(inputPosition);
        }
}

/**
 * function searchByTypeOfTrain(dataInputDiv) is a function for generating a request
 * in the JSON format for finding records in the train schedule by train type
 * @param dataInputDiv is the form from which data is taken
 * @returns {*}
 */
export function searchByTypeOfTrain(dataInputDiv) {
    let checkbox_position = dataInputDiv.querySelectorAll('input[name = train_type]');
    for(let i=0;i<checkbox_position.length;i++){
        if(checkbox_position[i].checked){
            jsonData = JSON.stringify(
                {
                    type_of_choice : 2,
                    train_type : checkbox_position[i].id
                }
            );
        }
    }
    return jsonData;
}

/**
 * function searchByTypeOfTrainForm(dataInputDiv) is a function that forms a data entry form for
 * searching train schedule records by train type and station
 * @param dataInputDiv is the tag where the input form will be placed
 */
function searchByTypeOfTrainAndStationForm(dataInputDiv) {
    let stationsTable = document.createElement('table');
    getStations(stationsTable, true, null);
    dataInputDiv.appendChild(stationsTable);
    searchByTypeOfTrainForm(dataInputDiv)
}

/**
 * function searchByTypeOfTrain(dataInputDiv) is a function for generating a request
 * in the JSON format for finding records in the train schedule by train type and station
 * @param dataInputDiv is the form from which data is taken
 * @returns {*}
 */
export function searchByTypeOfTrainAndStation(dataInputDiv) {
    let stationRows = dataInputDiv.querySelectorAll('input[name = check_stations]');
    let trainRows = dataInputDiv.querySelectorAll('input[name = train_type]');
    let checkedTypeOfTrain = null;
    let checkedStation = null;
    for(let i=0; i<stationRows.length; i++){
        if(stationRows[i].checked){
            checkedStation = stationRows[i].value;
        }
    }
    for(let i=0; i<trainRows.length; i++){
        if(trainRows[i].checked){
            checkedTypeOfTrain = trainRows[i].id;
        }
    }
    jsonData = JSON.stringify(
        {
            type_of_choice : 3,
            train_type : checkedTypeOfTrain,
            station_id: checkedStation
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