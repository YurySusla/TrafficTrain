/**
 * function checkedInput(inputData, test) this is a method that changes the color of input tag and blocks
 * the entry of other data if parameters that do not match the regular expression are entered
 * @param inputData is a variable that stores the input parameter
 * @param test is a variable that stores a regular expression
 */
export default function checkedInput(inputData, test) {
    if (!test) {
        inputData.classList.add('error');
        inputData.focus();
    } else {
        inputData.classList.remove('error');
        inputData.blur();
    }
}

/**
 * function dataChecking(dataField, regularExpression) is a method, the result of comparing
 * an input parameter with a regular expression
 * @param dataField is a verifiable expression
 * @param regularExpression is a regular expression
 * @returns {boolean} result of comparison
 */
export function dataChecking(dataField, regularExpression) {
    let test = regularExpression.test(dataField);
    return test;
}