/**
 * function getDayName(day) is a function that selects the name of the day of the week
 * depending on the input numeric expression
 * @param day is an input numeric expression
 * @returns {any}
 */
export default function getDayName(day) {
    let weekDay = new Array(7);
    weekDay[0] = "Monday";
    weekDay[1] = "Tuesday";
    weekDay[2] = "Wednesday";
    weekDay[3] = "Thursday";
    weekDay[4] = "Friday";
    weekDay[5] = "Saturday";
    weekDay[6] = "Sunday";
    return weekDay[day - 1];
}
