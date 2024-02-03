package com.example.signupmainak.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class SignUpViewModel : ViewModel() {
    var firstName: MutableState<String> = mutableStateOf("")
    var email: MutableState<String> = mutableStateOf("")
    var password: MutableState<String> = mutableStateOf("")
    var website: MutableState<String> = mutableStateOf("")
    var capturedImage: MutableState<Bitmap?> = mutableStateOf(null)

    // Function to set the captured image bitmap
    fun setCapturedImage(bitmap: Bitmap) {
        capturedImage.value = bitmap
    }

    // Function to handle changes in the first name field
    fun onFirstNameChange(value: String) {
        firstName.value = value
    }

    // Function to handle changes in the email field
    fun onEmailChange(value: String) {
        email.value = value
    }

    fun onPasswordChange(value: String) {
        password.value = value
    }

    fun onWebsiteChange(value: String) {
        website.value = value
    }

    fun validateFields(): Boolean {
        return firstName.value.isNotEmpty() && email.value.isNotEmpty() && password.value.isNotEmpty() && website.value.isNotEmpty()
    }

}