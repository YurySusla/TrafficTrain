import getStations from "./getStations.js";
let jsonData;
/**
 * function searchTrainsForm(dataInputDiv, typeOfChange) is a function that generates a data entry form
 * for searching for trains depending on the input numerical expression
 * @param dataInputDiv is the element inside which the data entry form is placed
 * @param typeOfChange is a numeric expression for choosing the type of input form
 */
export default function searchTrainsForm(dataInputDiv, typeOfChange) {
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
            searchByStationAndTimeForm(dataInputDiv);
            acceptButton = addAcceptButton();
            acceptButton.style.marginBottom = "10px";
            dataInputDiv.appendChild(document.createElement('br'));
            dataInputDiv.appendChild(document.createElement('br'));
            dataInputDiv.appendChild(acceptButton);
            break;
        case "3":
            dataInputDiv.style.height = 'auto';
            acceptButton = addAcceptButton();
            acceptButton.style.marginBottom = "10px";
            dataInputDiv.appendChild(acceptButton);
            break;
    }
}

/**
 * function searchAllTrains() this is a function that generates a request in the JSON format
 * to display information about all trains
 * @returns {string}
 */
export function searchAllTrains() {
    jsonData = JSON.stringify(
        {
            type_of_choice : 1
        }
    );
    return jsonData;
}

/**
 * function searchSortTrains() this is a function that generates a request in the JSON format for displaying information
 * about all trains sorted by time in transit between the start and end stations
 * @returns {string}
 */
export function searchSortTrains() {
    jsonData = JSON.stringify(
        {
            type_of_choice : 3
        }
    );
    return jsonData;
}

/**
 * function searchByStationAndTimeForm(dataInputDiv) is a function that forms a data entry form for
 * searching trains by station and time
 * @param dataInputDiv is the tag where the input form will be placed
 */
function searchByStationAndTimeForm(dataInputDiv) {
    dataInputDiv.appendChild(addLabel('Begin: '))
    dataInputDiv.appendChild(addInput('begin_time'));
    dataInputDiv.appendChild(addLabel('- End: '))
    dataInputDiv.appendChild(addInput('end_time'));
    let station_table = document.createElement('table');
    getStations(station_table, true, null);
    dataInputDiv.appendChild(station_table);

}

/**
 * function searchByStationAndTime(dataInputDiv)  is a function for generating a request
 * in the JSON format for finding trains by station and time
 * @param dataInputDiv is the form from which data is taken
 * @returns {string}
 */
export function searchByStationAndTime(dataInputDiv) {
    let stationRows = dataInputDiv.querySelectorAll('input[name = check_stations]');
    let checkedStation = null;
    for(let i=0; i<stationRows.length; i++){
        if(stationRows[i].checked){
            checkedStation = stationRows[i].value;
        }
    }
    let beginTime = dataInputDiv.querySelectorAll('input[name = begin_time]')[0];
    let endTime = dataInputDiv.querySelectorAll('input[name = end_time]')[0];
    jsonData = JSON.stringify(
        {
            type_of_choice : 2,
            station_id : checkedStation,
            begin_time: beginTime.value,
            end_time: endTime.value
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
 * function addLabel(labelText) is a function that forms a label tag
 * @param labelText is the text in the tag
 * @returns {HTMLLabelElement}
 */
function addLabel(labelText) {
    let label = document.createElement('label');
    label.innerText = labelText;
    return label;
}
/**
 * function addInput(defaultValue, inputName) is the function that adds the input tag
 * @param defaultValue is the default output in input
 * @param inputName is tag name
 * @returns {HTMLInputElement}
 */
function addInput(inputName) {
    let inputTag = document.createElement('input');
    inputTag.type = "time";
    inputTag.name = inputName;
    return inputTag;
}