package com.example.testingframework.pages

import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until

class CheckoutPage(private val device: UiDevice) {

    // Selectors for Checkout Flow
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
        // Swipe up to see the finish button if needed
        device.findObject(finishButton).click()
    }

    fun isOrderComplete(): Boolean {
        return device.wait(Until.hasObject(confirmationMessage), 5000)
    }
}
