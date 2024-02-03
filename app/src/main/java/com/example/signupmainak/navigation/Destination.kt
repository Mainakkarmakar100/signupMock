package com.example.signupmainak.navigation

sealed class Destination(val route: String) {
    object SignUp : Destination("signUpScreen")
    object Confirmation : Destination("confirmationScreen/{firstname}/{email}/{website}") {
        fun createRoute(firstname: String?, email: String?, website: String?) =
            "confirmationScreen/${firstname.orEmpty()}/${email.orEmpty()}/${website.orEmpty()}"
}
}
