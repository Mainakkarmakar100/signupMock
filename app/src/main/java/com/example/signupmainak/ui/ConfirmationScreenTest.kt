package com.example.signupmainak.ui

import org.junit.Rule
import org.junit.Test

class ConfirmationScreenTest {

    // Create an instance of ComposeTestRule
    /*@get:Rule
    val composeTestRule = createComposeRule()*/

    // Write your test cases here
    @Test
    fun testConfirmationScreen() {
        // Your test logic goes here
        val firstName = "John"
        val email = "john@example.com"
        val website = "example.com"

        /*composeTestRule.setContent {
            // Set up your composable for testing
            ConfirmationScreen(
                firstName = firstName,
                email = email,
                website = website
            )
        }*/

        // Example test case
        /*composeTestRule.onNodeWithText("Hello, John").assertExists()
        composeTestRule.onNodeWithText("john@example.com").assertExists()
        composeTestRule.onNodeWithText("example.com").assertExists()*/
    }
}