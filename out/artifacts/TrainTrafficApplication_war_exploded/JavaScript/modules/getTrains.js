/**
 * function getTrains(trainsTable, allData, json, callback) is a function that sends a POST request
 * to the servlet with url 'search_trains' to receive data about trains from the database,
 * receives a response in JSON format and forms a table with the possibility of further selecting a record
 * @param trainsTable is a table in which train data will be placed
 * @param allData is a sign of displaying full information about trains
 * @param json is the data for the request in JSON format
 * @param callback is a function to automatically select a row
 */
export default function getTrains(trainsTable, allData, json, callback) {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'search_trains',true);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.responseType = 'json';
    if(allData == false){
        xhr.onload = () => callback();
    }
    xhr.send(json);
    xhr.onreadystatechange = function () {
        if(xhr.readyState == 4){
            if(xhr.status == 200){
                let trains = xhr.response;
                let tableDiv = document.createElement('div');
                tableDiv.style.overflowY = 'scroll';
                tableDiv.style.height = '150px';
                tableDiv.style.border = '1px solid black';
                let trTrainsHead = document.createElement('tr');
                let thNumber = document.createElement('th');
                thNumber.innerText = "Number";
                trTrainsHead.appendChild(thNumber);
                let thType = document.createElement('th');
                thType.innerText = "Type of train";
                trTrainsHead.appendChild(thType);
                if(allData == true){
                    let thDistance = document.createElement('th');
                    thDistance.innerText = "Distance";
                    trTrainsHead.appendChild(thDistance);
                    let th_branded = document.createElement('th');
                    th_branded.innerText = "Is branded";
                    trTrainsHead.appendChild(th_branded);
                    let thArrivalTime = document.createElement('th');
                    thArrivalTime.innerText = "Arrival time";
                    trTrainsHead.appendChild(thArrivalTime);
                    let thArrivalStation = document.createElement('th');
                    thArrivalStation.innerText = "Arrival station";
                    trTrainsHead.appendChild(thArrivalStation);
                    let thDepartureTime = document.createElement('th');
                    thDepartureTime.innerText = "Departure time";
                    trTrainsHead.appendChild(thDepartureTime);
                    let th_departure_station = document.createElement('th');
                    th_departure_station.innerText = "Departure station";
                    trTrainsHead.appendChild(th_departure_station);
                }

                let th_check = document.createElement('th');
                th_check.innerText = "Check";
                trTrainsHead.appendChild(th_check);
                tableDiv.appendChild(trTrainsHead);
                for(let i = 0; i < trains.length; i++){
                    let trTrain = document.createElement('tr');
                    trTrain.id = trains[i].id;

                    let tdNumber = document.createElement('td');
                    let tdType = document.createElement('td');
                    let tdCheck = document.createElement('td');

                    tdNumber.textContent = trains[i].number;
                    tdType.textContent = trains[i].train_type;

                    let tdDistance = document.createElement('td');
                    let tdBranded = document.createElement('td');
                    let tdArrivalTime = document.createElement('td');
                    let tdArrivalStation = document.createElement('td');
                    let tdDepartureTime = document.createElement('td');
                    let tdDepartureStation = document.createElement('td');
                    if(allData == true) {
                        tdDistance.textContent = trains[i].distance;
                        tdBranded.textContent = trains[i].is_branded;
                        tdArrivalTime.textContent = trains[i].arrival_time;
                        tdArrivalStation.textContent = trains[i].arrival_station;
                        tdDepartureTime.textContent = trains[i].departure_time;
                        tdDepartureStation.textContent = trains[i].departure_station;
                    }
                    let checkbox = document.createElement('input');
                    checkbox.type = "radio";
                    checkbox.value = trains[i].id;
                    checkbox.name = "check_trains";
                    tdCheck.appendChild(checkbox);

                    trTrain.appendChild(tdNumber);
                    trTrain.appendChild(tdType);
                    if(allData == true){
                        trTrain.appendChild(tdDistance);
                        trTrain.appendChild(tdBranded);
                        trTrain.appendChild(tdArrivalTime);
                        trTrain.appendChild(tdArrivalStation);
                        trTrain.appendChild(tdDepartureTime);
                        trTrain.appendChild(tdDepartureStation);
                    }

                    trTrain.appendChild(tdCheck);
                    tableDiv.appendChild(trTrain)
                }
                trainsTable.appendChild(tableDiv);
            }
        }
    }
}