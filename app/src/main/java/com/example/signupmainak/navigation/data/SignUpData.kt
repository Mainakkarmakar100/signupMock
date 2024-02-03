package com.example.signupmainak.navigation.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpData(val firstName: String, val email: String) : Parcelable