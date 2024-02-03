package com.example.signupmainak.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.signupmainak.R
import com.example.signupmainak.navigation.Destination
import com.example.signupmainak.navigation.data.SignUpData
import com.example.signupmainak.utils.PhotoCaptureHandler
import com.example.signupmainak.viewmodel.SignUpViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel,
    photoCaptureHandler: PhotoCaptureHandler,
    navController: NavHostController
) {
    var signUpData = rememberSaveable { mutableStateOf(SignUpData("", "")) }
    val capturedImage = viewModel.capturedImage.value
    val context = LocalContext.current
    
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Header
                Text(
                    text = stringResource(id = R.string.profile_creation),
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Help text
                Text(
                    text = stringResource(id = R.string.help_text),
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Profile photo section
                Box(
                    modifier = Modifier
                        .height(400.dp)
                        .width(200.dp)
                        .align(Alignment.CenterHorizontally)
                        .aspectRatio(1f)
                        .background(Color.LightGray)
                        .clickable { photoCaptureHandler.launchCamera() }
                ) {
                    // Placeholder text or icon for the profile photo
                    Text(
                        text = stringResource(id = R.string.tap_to_add_avatar),
                        style = TextStyle(fontSize = 16.sp),
                        modifier = Modifier.align(Alignment.Center)
                    )
                    capturedImage?.let { bitmap ->
                        // Display the captured image bitmap in the box
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = "Captured Image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }

                // Form fields (Use string resources for labels)
                OutlinedTextField(
                    value = viewModel.firstName.value,
                    onValueChange = { viewModel.onFirstNameChange(it) },
                    label = { Text(text = stringResource(id = R.string.first_name)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    value = viewModel.email.value,
                    onValueChange = { viewModel.onEmailChange(it) },
                    label = { Text(text = stringResource(id = R.string.email_address)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                // Password field
                OutlinedTextField(
                    value = viewModel.password.value,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    label = { Text(text = stringResource(id = R.string.password)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                // Personal website field (omitted for brevity)
                OutlinedTextField(
                    value = viewModel.website.value,
                    onValueChange = { viewModel.onWebsiteChange(it) },
                    label = { Text(text = stringResource(id = R.string.website)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                // Submit button
                Button(
                    onClick = {

                            if (viewModel.validateFields()) {
                                signUpData.value =
                                    SignUpData(viewModel.firstName.value, viewModel.email.value)
                                navController.navigate(Destination.Confirmation.createRoute
                                    (viewModel.firstName.value, viewModel.email.value, viewModel.website.value))
                            } else {
                                Toast
                                    .makeText(context, "Please fill mandatory details", Toast.LENGTH_SHORT)
                                    .show()
                            }
                    },
                    modifier = Modifier
                        .align(Alignment.End)
                        .height(40.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp), // Adjust the corner radius as needed
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.orange), // Change the background color
                        contentColor = Color.White // Change the text color
                    )
                ) {
                    Text(text = stringResource(id = R.string.submit))
                }
            }
        }
}