document.addEventListener('DOMContentLoaded', () => {
  const shortenForm = document.getElementById('shortenForm');
  const longLinkInput = document.getElementById('longLink');
  const urlValidationMessage = document.getElementById('urlValidationMessage');
  const aliasInput = document.getElementById('alias');
  const errorMessage = document.getElementById('errorMessage');
  const resultContainer = document.getElementById('resultContainer');
  const originalUrl = document.getElementById('originalUrl');
  const shortUrl = document.getElementById('shortUrl');
  const copyButton = document.getElementById('copyButton');
  const notification = document.getElementById('notification');
  const resetButton = document.getElementById('resetButton');
  const generateQRButton = document.getElementById('generateQRButton');
  const qrCodeImage = document.getElementById('qrCodeImage');
  const downloadQRButton = document.getElementById('downloadQRButton');
  // Todo
  const shareButton = document.getElementById('shareButton');

  // Will store the alias or hash here.
  let shortened = '';

  // Function to display error message. Currently not sure what this does.*******
  function displayError(message) {
      errorMessage.textContent = message;
      errorMessage.style.display = 'block';
  }

  // Function to display validation message
  function displayValidationMessage(message) {
    urlValidationMessage.textContent = message;
    urlValidationMessage.style.display = 'block';
  }

  // Function to hide validation message
  function hideValidationMessage() {
    urlValidationMessage.style.display = 'none';
  }

  // For url validation
  longLinkInput.addEventListener('input', () => {
    const inputValue = longLinkInput.value;

    try {
        const url = new URL(inputValue);
        hideValidationMessage();
    } catch (error) {
        displayValidationMessage("Please enter a valid URL.");
    }
});

  // For main form
  shortenForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    // Clears previous results
    resultContainer.style.display = 'none';
    originalUrl.textContent = '';
    shortUrl.textContent = '';

    const longLink = longLinkInput.value;
    const alias = aliasInput.value;

    // Deciding which endpoint to use based on alias presence
    const endpoint = alias ? 'create-alias' : 'create-hash';

    const requestData = {
        alias: alias,
        url: longLink,
    };

    try {
        const response = await fetch(`http://localhost:8080/${endpoint}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(requestData),
        });

        if (!response.ok) {
            console.error('Error:', response.statusText);
            errorMessage.textContent = "This alias is already in use. Please choose another.";
            return;
        }

        const responseData = await response.json();
        shortened = responseData.alias || responseData.hash;
        const shortenedUrl = `http://localhost:8080/${shortened}`;

        // Updating UI
        originalUrl.textContent = longLink;
        shortUrl.textContent = shortenedUrl;
        resultContainer.style.display = 'block';
        errorMessage.textContent = ''; // Clear error message
    } catch (error) {
        console.error('Error:', error);
        errorMessage.textContent = "An error occurred. Please try again.";
    }
  });

  // For copy button
  copyButton.addEventListener('click', () => {
    const shortenedUrlText = shortUrl.textContent;

    // Creating a temporary textarea element to copy text
    const tempTextarea = document.createElement('textarea');
    tempTextarea.value = shortenedUrlText;
    document.body.appendChild(tempTextarea);
    tempTextarea.select();

    try {
      document.execCommand('copy');
      console.log("Text copied: ", shortenedUrlText);

      // Notification
      notification.style.display = 'block';
      setTimeout(() => {
        notification.style.display = 'none';
      }, 2000); // Hides after 2 seconds
    } catch (err) {
      console.error("Unable to copy text: ", err);
    } finally {
      document.body.removeChild(tempTextarea); // For clean up
    }
  })

  // For QR button
  generateQRButton.addEventListener('click', async () => {
    try {
        const response = await fetch(`http://localhost:8080/qr/generate/${shortened}`, {
          method: 'GET',
        });

        if (!response.ok) {
          console.error("Error: ", response.statusText);
          return;
        }

        const qrImageData = await response.blob();
        const qrImageUrl = URL.createObjectURL(qrImageData);

        qrCodeImage.src = qrImageUrl;
        qrCodeImage.style.display = 'block';
        downloadQRButton.style.display = 'block';

    } catch (error) {
        console.error('Error:', error);
    }
  });

  // For download QR button
  downloadQRButton.addEventListener('click', () => {
    if (qrCodeImage.src) {
      const link = document.createElement('a');
      link.href = qrCodeImage.src;
      link.download = 'qr-code.png';
      link.click();
    }
  })


  // For reset button.
  resetButton.addEventListener('click', () => {
    // Clear input fields
    longLinkInput.value = '';
    aliasInput.value = '';

    // Hide result container or error message
    resultContainer.style.display = 'none';
    errorMessage.textContent = '';

    // Reset QR Code
    qrCodeImage.src = '';
    qrCodeImage.style.display = 'none';

    // Reset QR Code download button
    downloadQRButton.style.display = 'none';
  });

});