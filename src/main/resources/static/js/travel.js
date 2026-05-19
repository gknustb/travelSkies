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

document.getElementById('travelForm').addEventListener('submit', async (event) => {
  // 1. Stop the browser from refreshing the page
  event.preventDefault();

  // 2. Scoop up all the data from the form using the 'name' attributes
  const formElement = event.target;
  const formData = new FormData(formElement);

  // 3. Build the JSON payload explicitly so types (like numbers) are correct
  const travelPayload = {
    travelName: formData.get('travelName'),
    startDate: formData.get('startDate'),
    endDate: formData.get('endDate'),
    locationName: formData.get('locationName'),
    // Convert the string inputs from the form into actual decimals for Java
    latitude: parseFloat(formData.get('latitude')),
    longitude: parseFloat(formData.get('longitude'))
  };

  try {
    // 4. Fire the JSON payload to your Tomcat server
    const response = await fetch('/travel/save', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(travelPayload)
    });

    // 5. Handle the backend's response
    if (response.ok) {
      const responseData = await response.json();
      console.log("Success:", responseData);
      // Optionally show a success message on the screen
      // document.getElementById('statusMessage').innerText = "Travel saved successfully!";
      formElement.reset(); // Clear the form fields
    } else {
      console.error("Server returned an error:", response.status);
    }

  } catch (error) {
    console.error("Network error trying to reach Tomcat:", error);
  }
});
