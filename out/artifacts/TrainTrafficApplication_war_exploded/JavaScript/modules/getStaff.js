import getDayName from './getDayName.js';
/**
 * function getStaff(staffTable) is a function that sends a GET request to servlet to receive data about
 * the staff records in the database, receives a response in the ss format and displays them
 * in the form of a table with the possibility of further selecting an entry in the table
 * @param staffTable is a place to place the table of staff of trains on the HTML page
 */
export default function getStaff(staffTable) {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'staff');
    xhr.responseType = 'json';
    xhr.send();
    xhr.onreadystatechange = function () {
        if(xhr.readyState == 4){
            if(xhr.status == 200){
                let staffResponse = xhr.response;
                let tableDiv = document.createElement('div');
                tableDiv.style.overflowY = 'scroll';
                tableDiv.style.height = '150px';
                tableDiv.style.border = '1px solid black';
                let trStaffHead = document.createElement('tr');
                let thTrainNumber = document.createElement('th');
                thTrainNumber.textContent = "Number of train";
                trStaffHead.appendChild(thTrainNumber);
                let thEmployee = document.createElement('th');
                thEmployee.textContent = "Employee";
                trStaffHead.appendChild(thEmployee);
                let thProfession = document.createElement('th');
                thProfession.textContent = "Profession";
                trStaffHead.appendChild(thProfession);
                let th_day = document.createElement('th');
                th_day.textContent = "Day";
                trStaffHead.appendChild(th_day);
                let thCheck = document.createElement('th');
                thCheck.textContent = "Check";
                trStaffHead.appendChild(thCheck);
                tableDiv.appendChild(trStaffHead);
                for(let i = 0; i < staffResponse.length; i++){
                    let trStaff = document.createElement('tr');
                    trStaff.id = staffResponse[i].staff_id;
                    let tdTrainNumber = document.createElement('td');
                    let tdEmployee = document.createElement('td');
                    let tdProfession = document.createElement('td');
                    let tdDay = document.createElement('td');
                    let tdCheck = document.createElement('td');
                    tdTrainNumber.textContent = staffResponse[i].train_number;
                    tdTrainNumber.id = staffResponse[i].train_id;
                    tdEmployee.textContent = staffResponse[i].employee_info;
                    tdEmployee.id = staffResponse[i].employee_id;
                    tdProfession.textContent = staffResponse[i].employee_profession;
                    tdDay.textContent = getDayName(staffResponse[i].day);
                    tdDay.id = staffResponse[i].day;
                    let checkbox = document.createElement('input');
                    checkbox.type = "radio";
                    checkbox.value = staffResponse[i].staff_id;
                    checkbox.name = "check_staff";
                    tdCheck.appendChild(checkbox);
                    trStaff.appendChild(tdTrainNumber);
                    trStaff.appendChild(tdEmployee);
                    trStaff.appendChild(tdProfession);
                    trStaff.appendChild(tdDay);
                    trStaff.appendChild(tdCheck);
                    tableDiv.appendChild(trStaff);
                }
                staffTable.appendChild(tableDiv);
            }
        }
    }
}