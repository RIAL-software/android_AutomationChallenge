package com.example.testingframework.tests

import com.example.testingframework.base.BaseTest
import com.example.testingframework.pages.LoginPage
import com.example.testingframework.pages.HomePage
import com.example.testingframework.data.TestData
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

        loginPage.login(TestData.VALID_USER, TestData.VALID_PASSWORD)

        val errorMessage = loginPage.getErrorMessageText()
        assertFalse("Unexpected error detected: '$errorMessage'", loginPage.isErrorVisible())
        assertTrue("Products screen not detected after login", homePage.isOnHomePage())
    }

    @Test
    fun successfulLogout() {
        val loginPage = LoginPage(device)
        val homePage = HomePage(device)

        loginPage.login(TestData.VALID_USER, TestData.VALID_PASSWORD)
        assertTrue("User should be logged in", homePage.isOnHomePage())

        homePage.logout()
        assertTrue("User should return to Login after logout", loginPage.isOnLoginPage())
    }

    @Test
    fun loginWithInvalidPassword() {
        val loginPage = LoginPage(device)
        
        loginPage.login(TestData.VALID_USER, TestData.INVALID_PASSWORD)

        assertTrue("Error message should be visible with invalid password", 
            loginPage.isErrorVisible())
        
        val errorText = loginPage.getErrorMessageText()
        assertTrue("Error message is not as expected: '$errorText'", 
            errorText.contains("Username and password do not match", ignoreCase = true))
    }

    @Test
    fun loginWithLockedOutUser() {
        val loginPage = LoginPage(device)

        loginPage.login(TestData.LOCKED_OUT_USER, TestData.VALID_PASSWORD)

        assertTrue("Error message should be visible for locked out user", 
            loginPage.isErrorVisible())

        val errorText = loginPage.getErrorMessageText()
        assertTrue("Error message is not as expected: '$errorText'",
            errorText.contains("Sorry, this user has been locked out", ignoreCase = true))
    }
}
