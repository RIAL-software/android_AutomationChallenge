package com.example.testingframework.pages

import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until

class LoginPage(private val device: UiDevice) {

    // Selectors for Swag Labs (React Native)
    val usernameField = By.desc("test-Username")
    private val passwordField = By.desc("test-Password")
    private val loginButton = By.desc("test-LOGIN")
    private val errorContainer = By.desc("test-Error Container")

    fun isErrorVisible(): Boolean {
        return device.wait(Until.hasObject(errorContainer), 2000)
    }

    fun isOnLoginPage(): Boolean {
        return device.wait(Until.hasObject(usernameField), 5000)
    }

    fun enterUsername(username: String) {
        val element = device.wait(Until.findObject(usernameField), 10000)
        element?.setText(username) ?: throw RuntimeException("Username field not found")
    }

    fun enterPassword(password: String) {
        val element = device.findObject(passwordField)
        element?.setText(password) ?: throw RuntimeException("Password field not found")
    }

    fun tapLogin() {
        val element = device.findObject(loginButton)
        element?.click() ?: throw RuntimeException("Login button not found")
    }

    fun login(username: String, password: String) {
        enterUsername(username)
        enterPassword(password)
        tapLogin()
    }
}
