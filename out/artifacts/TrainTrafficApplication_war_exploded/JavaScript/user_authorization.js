import checkedInput, {dataChecking} from "./modules/checkInput.js";

window.onload = function () {
    userIdentification();
}
function userIdentification() {
    let identificationForm = document.forms["identification_form"];
    let password = identificationForm.elements["password"];
    let passwordChecked = false;
    let passRegExp = /[A-Za-z0-9]{6,}/g;
    password.addEventListener("blur",function () {
        passwordChecked = dataChecking(password.value, passRegExp);
        checkedInput(password, passwordChecked);
    });
    document.getElementById('submit').addEventListener("click",function () {
        if(passwordChecked){
            identificationForm.submit();
        }
    });

}
