package com.example.testingframework.pages

import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until

class LoginPage(private val device: UiDevice) {

    val usernameField = By.desc("test-Username")
    private val passwordField = By.desc("test-Password")
    private val loginButton = By.desc("test-LOGIN")
    private val errorMessage = By.desc("test-Error message")

    fun isErrorVisible(): Boolean {
        // En Swag Labs, el objeto de error puede existir pero estar vacío.
        // Verificamos si existe y si tiene algún texto dentro.
        val errorObj = device.findObject(errorMessage)
        return errorObj != null && !errorObj.text.isNullOrBlank()
    }

    fun getErrorMessageText(): String {
        return device.findObject(errorMessage)?.text ?: ""
    }

    fun isOnLoginPage(): Boolean {
        return device.wait(Until.hasObject(usernameField), 10000)
    }

    fun enterUsername(username: String) {
        val element = device.wait(Until.findObject(usernameField), 5000)
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
        if (!isOnLoginPage()) {
            throw RuntimeException("Login page did not load in time")
        }
        
        // Limpiamos errores previos si los hubiera (re-lanzando la app o interactuando)
        enterUsername(username)
        enterPassword(password)
        tapLogin()
    }
}
