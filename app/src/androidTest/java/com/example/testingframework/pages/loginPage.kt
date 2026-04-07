package com.example.testingframework.pages

import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until

class LoginPage(private val device: UiDevice) {

    val usernameField = By.desc("test-Username")
    private val passwordField = By.desc("test-Password")
    private val loginButton = By.desc("test-LOGIN")
    private val errorMessageContainer = By.desc("test-Error message")

    fun isErrorVisible(): Boolean {
        // Wait up to 5 seconds for the error container to appear and have content
        val container = device.wait(Until.findObject(errorMessageContainer), 5000)
        val textObj = container?.findObject(By.clazz("android.widget.TextView"))
        return textObj != null && !textObj.text.isNullOrBlank()
    }

    fun getErrorMessageText(): String {
        val container = device.findObject(errorMessageContainer)
        val textObj = container?.findObject(By.clazz("android.widget.TextView"))
        return textObj?.text ?: ""
    }

    fun isOnLoginPage(): Boolean {
        // Increased timeout to 20 seconds for slower emulators
        return device.wait(Until.hasObject(usernameField), 20000)
    }

    fun enterUsername(username: String) {
        val element = device.wait(Until.findObject(usernameField), 10000)
        element?.setText(username) ?: throw RuntimeException("Username field not found")
    }

    fun enterPassword(password: String) {
        val element = device.wait(Until.findObject(passwordField), 5000)
        element?.setText(password) ?: throw RuntimeException("Password field not found")
    }

    fun tapLogin() {
        val element = device.wait(Until.findObject(loginButton), 5000)
        element?.click() ?: throw RuntimeException("Login button not found")
    }

    fun login(username: String, password: String) {
        if (!isOnLoginPage()) {
            throw RuntimeException("Login page did not load in time")
        }
        enterUsername(username)
        enterPassword(password)
        tapLogin()
    }
}
