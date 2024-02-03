package com.example.signupmainak

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.signupmainak.navigation.Destination
import com.example.signupmainak.ui.ConfirmationScreen
import com.example.signupmainak.ui.SignUpScreen
import com.example.signupmainak.utils.Constants.Companion.REQUEST_IMAGE_CAPTURE
import android.Manifest
import androidx.compose.runtime.CompositionLocalProvider
import com.example.signupmainak.ui.LocalSignUpViewModel
import com.example.signupmainak.utils.PhotoCaptureHandler
import com.example.signupmainak.viewmodel.SignUpViewModel

class MainActivity : ComponentActivity(), PhotoCaptureHandler {
    // Define the CAMERA_PERMISSION_CODE
    private val CAMERA_PERMISSION_CODE = 100
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = Destination.SignUp.route) {
                composable(Destination.SignUp.route) {
                    SignUpScreen(
                        viewModel = viewModel,
                        photoCaptureHandler = this@MainActivity,
                        navController = navController
                    )
                }
                composable(Destination.Confirmation.route) { backStackEntry ->
                    val firstName = backStackEntry.arguments?.getString("firstname") ?: ""
                    val email = backStackEntry.arguments?.getString("email") ?: ""
                    val website = backStackEntry.arguments?.getString("website") ?: ""

                    CompositionLocalProvider(LocalSignUpViewModel provides viewModel) {
                        ConfirmationScreen(
                            firstName = firstName, email = email, website = website
                        )
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            // Set the captured image bitmap to the box
            viewModel.setCapturedImage(imageBitmap)
        }
    }

    override fun launchCamera() {
        // Request CAMERA permission before launching the camera
        requestCameraPermission()
    }

    private fun requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE
            )
        } else {
            // Permission already granted, launch camera
            launchCameraInternal()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, launch camera
                launchCameraInternal()
            } else {
                // Permission denied
                // Handle permission denied scenario
            }
        }
    }

    // Function to launch the camera after permission is granted
    private fun launchCameraInternal() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

}