package com.example.signupmainak.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signupmainak.R

@Composable
fun ConfirmationScreen(firstName: String, email: String, website: String) {
    val viewModel = LocalSignUpViewModel.current
    val context = LocalContext.current.resources

    Surface(
        modifier = Modifier.fillMaxSize(), color = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = context.getString(R.string.hello_first_name, firstName), style = TextStyle(
                    fontSize = 28.sp, fontWeight = FontWeight.Bold
                ), modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = context.getString(R.string.portfolio_submitted),
                style = TextStyle(fontSize = 18.sp),
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
            ) {
                // Display the bitmap image if available
                val bitmapImage = viewModel.capturedImage.value
                if (bitmapImage != null) {
                    Image(
                        bitmap = bitmapImage.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }

            website?.let { websiteValue ->
                Text(text = context.getString(R.string.website_label, websiteValue),
                    style = TextStyle(fontSize = 16.sp, color = Color.Blue),
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .clickable { /* Handle website click action */ })
            }

            Text(
                text = context.getString(R.string.name_label, firstName),
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = context.getString(R.string.email_label, email),
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .height(40.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.orange),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = context.getString(R.string.sign_in_button))
                }
            }
        }
    }
}