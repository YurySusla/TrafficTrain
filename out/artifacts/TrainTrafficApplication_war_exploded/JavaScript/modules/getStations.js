/**
 * function getStations(stationsTable, allData, callback) is a function that sends a GET request to a servlet
 * with url 'stations'  to receive data about stations from the database, receives a response from the servlet in the
 * JSON format and forms a table with the possibility of further work with records
 * @param stationsTable is a table for placing data about stations on HTML page
 * @param allData is a sign of the output of all data on the station table
 * @param callback is the function of automatic selection of entries in the table when the table is full
 */
export default function getStations(stationsTable, allData, callback) {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'stations', true);
    xhr.timeout = 2000;
    xhr.responseType = 'json';
    if(allData == false){
        xhr.onload = () => callback();
    }
    xhr.send();
    xhr.onreadystatechange = function () {
        if(xhr.readyState == 4){
            if(xhr.status == 200){
                let stations = xhr.response;
                let tableDiv = document.createElement('div');
                tableDiv.style.overflowY = 'scroll';
                tableDiv.style.height = '150px';
                tableDiv.style.border = '1px solid black';
                let trStationsHead = document.createElement('tr');
                let th_name = document.createElement('th');
                th_name.innerText = "Name";
                trStationsHead.appendChild(th_name);
                let th_type = document.createElement('th');
                th_type.innerText = "Station type";
                trStationsHead.appendChild(th_type);
                if(allData == true){
                    let thWaitingHall = document.createElement('th');
                    thWaitingHall.innerText = "Waiting hall";
                    trStationsHead.appendChild(thWaitingHall);
                }
                let thCheck = document.createElement('th');
                thCheck.innerText = "Check";
                trStationsHead.appendChild(thCheck);
                tableDiv.appendChild(trStationsHead);

                for(let i = 0; i < stations.length; i++){
                    let trStation = document.createElement('tr');
                    trStation.id = stations[i].id;

                    let tdName = document.createElement('td');
                    let tdType = document.createElement('td');
                    let tdWaitingHall = document.createElement('td');
                    let td_check = document.createElement('td');

                    tdName.textContent = stations[i].name;
                    tdType.textContent = stations[i].type;
                    if(allData == true){
                        tdWaitingHall.textContent = stations[i].isWaitingHall;
                    }
                    let checkbox = document.createElement('input');
                    checkbox.type = "radio";
                    checkbox.value = stations[i].id;
                    checkbox.name = "check_stations";
                    td_check.appendChild(checkbox);
                    trStation.appendChild(tdName);
                    trStation.appendChild(tdType);
                    if(allData == true){
                        trStation.appendChild(tdWaitingHall);
                    }
                    trStation.appendChild(td_check);
                    tableDiv.appendChild(trStation)
                }
                stationsTable.appendChild(tableDiv);
            }
        }
    }
}