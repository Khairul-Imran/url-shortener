# URL Shortener Application

## Table of Contents
- [Description](#description)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Usage](#usage)
- [Customization](#customization)
- [Expiration](#expiration)

## Description
The URL Shortener Application is a web-based tool designed to simplify the sharing of long URLs by generating shorter, easy-to-share links. This project was created to showcase proficiency in backend and frontend development, as well as database management.

## Features
- Shorten long URLs into concise and shareable links.
- Copy button for quick copying of the shortened URL to the clipboard.
- Generate QR codes linked to the shortened URL for easy sharing.
- Download QR code images for offline use.

## Technologies Used
- Backend: Java with Spring Framework
- Frontend: HTML, CSS, JavaScript
- Database: PostgreSQL

## Usage
1. Start the backend server.
2. Open the application in a web browser.
3. Enter the long URL you want to shorten and click the "Shorten" button.
4. Copy the generated short URL to share with others.
5. Optionally, use the QR code feature to generate and download QR codes.

## Customization
- Customize Shortened URLs: Users can provide their own "aliases" to create custom short URLs.
- Automated Alias Generation: If no alias is provided, the application uses "Base 62" hashing for generating unique aliases.

## Expiration
- The application implements an "expiry" feature that removes entries from the database after 15 minutes to optimize database space.


---
