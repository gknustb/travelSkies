const inputBox = document.querySelector('input[name="travelName"]');
const checkBox = document.querySelector('input[name="checkBox"]');

let string = "";
checkBox.addEventListener('change', function () {
  if (this.checked === false) {
    inputBox.disabled = false;
    inputBox.value = string;
  }
  else {
    inputBox.disabled = true;
    string = inputBox.value;
    inputBox.value = "test";
  }
})
