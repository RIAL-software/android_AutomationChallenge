package com.example.testingframework.pages

import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector

class CheckoutPage(private val device: UiDevice) {

    // Selectors
    private val checkoutButton = By.desc("test-CHECKOUT")
    private val firstNameField = By.desc("test-First Name")
    private val lastNameField = By.desc("test-Last Name")
    private val zipCodeField = By.desc("test-Zip/Postal Code")
    private val continueButton = By.desc("test-CONTINUE")
    private val finishButton = By.desc("test-FINISH")
    private val confirmationMessage = By.text("THANK YOU FOR YOU ORDER")

    fun proceedToCheckout() {
        val element = device.wait(Until.findObject(checkoutButton), 5000)
        element?.click() ?: throw RuntimeException("Checkout button not found")
    }

    fun fillUserInformation(firstName: String, lastName: String, zip: String) {
        device.wait(Until.findObject(firstNameField), 5000)?.setText(firstName)
        device.findObject(lastNameField).setText(lastName)
        device.findObject(zipCodeField).setText(zip)
        device.findObject(continueButton).click()
    }

    fun finishCheckout() {
        // En la página de Overview, el botón FINISH suele estar abajo.
        // Hacemos scroll hasta encontrarlo.
        val scrollable = UiScrollable(UiSelector().scrollable(true))
        
        // Buscamos el objeto con descripción "test-FINISH" haciendo scroll
        try {
            scrollable.scrollTextIntoView("FINISH") // Intenta por texto
        } catch (e: Exception) {
            // Si falla por texto, intentamos scroll manual o simplemente buscamos el objeto
        }

        // Esperamos a que el botón sea visible tras el scroll
        val element = device.wait(Until.findObject(finishButton), 5000)
        
        if (element != null) {
            element.click()
        } else {
            // Intento desesperado: scroll vertical simple
            device.swipe(500, 1500, 500, 500, 20)
            device.findObject(finishButton)?.click() ?: throw RuntimeException("Finish button not found even after scroll")
        }
    }

    fun isOrderComplete(): Boolean {
        return device.wait(Until.hasObject(confirmationMessage), 10000)
    }
}
