Project Description:

This project is a sample Android application developed using Jetpack Compose that facilitates user sign-up and confirmation functionalities. It consists of two main screens: SignUpScreen and ConfirmationScreen, along with supporting ViewModel classes and navigation logic.

SignUpScreen:

Allows users to enter their first name, email, password, and personal website information.
Provides a camera feature to capture a profile photo.
Validates user input fields and navigates to the ConfirmationScreen upon successful submission.
ConfirmationScreen:

Displays the submitted user information including the first name, email, and website.
Shows a profile photo if available.
Provides a sign-in button for further actions.
Components:

ViewModel:

Manages the user input fields and captured image data.
Performs field validations and provides data for screen rendering.
Navigation:

Utilizes Jetpack Navigation for navigating between the SignUpScreen and ConfirmationScreen.
Tests:

Includes unit tests for ViewModel classes.
Due to setup errors, full test coverage could not be achieved. However, sample test cases have been provided to demonstrate the testing approach.
