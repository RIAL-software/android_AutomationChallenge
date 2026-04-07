package com.example.testingframework.tests

import com.example.testingframework.base.BaseTest
import com.example.testingframework.pages.LoginPage
import com.example.testingframework.pages.HomePage
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginTest : BaseTest() {

    @Test
    fun successfulLogin() {
        val loginPage = LoginPage(device)
        val homePage = HomePage(device)

        // Login with valid credentials
        loginPage.login("standard_user", "secret_sauce")

        // Assertion: Verify NO error message is displayed
        assertFalse("Unexpected error message displayed", loginPage.isErrorVisible())

        // Assertion: Verify we reached the Products screen
        assertTrue("Failed to reach HomePage after login", homePage.isOnHomePage())
    }

    @Test
    fun successfulLogout() {
        val loginPage = LoginPage(device)
        val homePage = HomePage(device)

        // 1. Ensure we are logged in first
        loginPage.login("standard_user", "secret_sauce")
        assertTrue("User should be on Products page before logout", homePage.isOnHomePage())

        // 2. Perform Logout
        homePage.logout()

        // 3. Assertion: Verify we are back on the login screen
        assertTrue("User should be back on the Login Page after logout", loginPage.isOnLoginPage())
    }
}
