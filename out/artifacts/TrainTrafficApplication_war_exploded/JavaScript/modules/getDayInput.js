/**
 * function checkDayTable(dayPlace) this is a function that inserts the text of the name of the
 * day of the week in label and attaches to checkbox for further selection by the user
 * @param dayPlace is a table for placing checkbox
 */
export default function checkDayTable(dayPlace) {
    for(let i=0;i<7;i++){
        let inputDay = document.createElement('input');
        inputDay.type = "radio";
        inputDay.name = "day";
        inputDay.id = i+1;
        let dayLabel = document.createElement('p');
        switch (i) {
            case 0: dayLabel.innerText = "Monday";
                break;
            case 1: dayLabel.innerText = "Tuesday";
                break;
            case 2: dayLabel.innerText = "Wednesday";
                break;
            case 3: dayLabel.innerText = "Thursday";
                break;
            case 4: dayLabel.innerText = "Friday";
                break;
            case 5: dayLabel.innerText = "Saturday";
                break;
            case 6: dayLabel.innerText = "Sunday";
                break;
        }
        dayPlace.appendChild(dayLabel);
        dayPlace.appendChild(inputDay);
    }
}