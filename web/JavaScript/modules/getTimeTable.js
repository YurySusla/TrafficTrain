import getDayName from './getDayName.js';

/**
 * function getTimeTable(trainTimeTable, json) is a function that sends POST in the form of a JSON format
 * to a servlet with url 'search_timetable' for receiving the necessary data about the train schedule,
 * receives a response in the JSON format and generates a table of train schedules
 * with the possibility of further selecting an entry in the table
 * @param trainTimeTable is a table for placing data on the train schedule on the HTML page
 * @param json is the data for the request in JSON format
 */
export default function getTimeTable(trainTimeTable, json) {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'search_timetable');
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.responseType = 'json';
    xhr.send(json);
    xhr.onreadystatechange = function () {
        if(xhr.readyState == 4){
            if(xhr.status == 200){
                let timeTableResponse = xhr.response;
                let tableDiv = document.createElement('div');
                tableDiv.style.overflowY = 'scroll';
                tableDiv.style.height = '150px';
                tableDiv.style.border = '1px solid black';
                let trTimeTableHead = document.createElement('tr');
                let thTrainNumber = document.createElement('th');
                thTrainNumber.innerText = "Number of Train";
                trTimeTableHead.appendChild(thTrainNumber);
                let thStationName = document.createElement('th');
                thStationName.innerText = "Name of station";
                trTimeTableHead.appendChild(thStationName);
                let thDay = document.createElement('th');
                thDay.innerText = "Day";
                trTimeTableHead.appendChild(thDay);
                let thArrivalTime = document.createElement('th');
                thArrivalTime.innerText = "Arrival time";
                trTimeTableHead.appendChild(thArrivalTime);
                let thDepartureTime = document.createElement('th');
                thDepartureTime.innerText = "Departure time";
                trTimeTableHead.appendChild(thDepartureTime);
                let thCheck = document.createElement('th');
                thCheck.innerText = "Check";
                trTimeTableHead.appendChild(thCheck);
                tableDiv.appendChild(trTimeTableHead);

                for(let i = 0; i < timeTableResponse.length; i++){
                    let trTimeTable = document.createElement('tr');
                    trTimeTable.id = timeTableResponse[i].timetable_id;

                    let tdTrainNumber = document.createElement('td');
                    let tdStationName = document.createElement('td');
                    let tdDay = document.createElement('td');
                    let tdArrivalTime = document.createElement('td');
                    let tdDepartureTime = document.createElement('td');
                    let tdCheck = document.createElement('td');

                    tdTrainNumber.textContent = timeTableResponse[i].train_number;
                    tdTrainNumber.id = timeTableResponse[i].train_id;
                    tdStationName.textContent = timeTableResponse[i].station_name;
                    tdStationName.id = timeTableResponse[i].station_id;
                    tdDay.textContent = getDayName(timeTableResponse[i].day);
                    tdDay.id = timeTableResponse[i].day;
                    tdArrivalTime.textContent = timeTableResponse[i].arrival_time;
                    tdDepartureTime.textContent = timeTableResponse[i].departure_time;
                    let checkbox = document.createElement('input');
                    checkbox.type = "radio";
                    checkbox.value = timeTableResponse[i].timetable_id;
                    checkbox.name = "check_time_table";
                    tdCheck.appendChild(checkbox);

                    trTimeTable.appendChild(tdTrainNumber);
                    trTimeTable.appendChild(tdStationName);
                    trTimeTable.appendChild(tdDay);
                    trTimeTable.appendChild(tdArrivalTime);
                    trTimeTable.appendChild(tdDepartureTime);
                    trTimeTable.appendChild(tdCheck);
                    tableDiv.appendChild(trTimeTable);
                }
                trainTimeTable.appendChild(tableDiv);
            }
        }
    }
}