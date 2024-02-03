package com.example.signupmainak.ui

import androidx.compose.runtime.compositionLocalOf
import com.example.signupmainak.viewmodel.SignUpViewModel

val LocalSignUpViewModel = compositionLocalOf<SignUpViewModel> {
    error("No SignUpViewModel provided")
}