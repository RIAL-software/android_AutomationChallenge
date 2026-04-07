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

        // 1. Intentamos el login
        loginPage.login(TestData.VALID_USER, TestData.VALID_PASSWORD)

        // 2. Aserción mejorada: Si falla, nos dirá exactamente QUÉ error encontró
        val errorMessage = loginPage.getErrorMessageText()
        assertFalse("Se detectó un error inesperado: '$errorMessage'", loginPage.isErrorVisible())

        // 3. Verificamos que llegamos a la Home Page
        assertTrue("No se detectó la pantalla de productos tras el login", homePage.isOnHomePage())
    }

    @Test
    fun successfulLogout() {
        val loginPage = LoginPage(device)
        val homePage = HomePage(device)

        loginPage.login(TestData.VALID_USER, TestData.VALID_PASSWORD)
        assertTrue("El usuario debería estar logueado", homePage.isOnHomePage())

        homePage.logout()

        assertTrue("El usuario debería volver al Login tras cerrar sesión", loginPage.isOnLoginPage())
    }
}
