package com.example.testingframework.pages

import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until

class HomePage(private val device: UiDevice) {

    private val menuButton = By.desc("test-Menu")
    private val productsTitle = By.text("PRODUCTS")
    private val logoutButton = By.desc("test-LOGOUT")
    private val cartButton = By.desc("test-Cart")
    private val firstItemAddButton = By.desc("test-ADD TO CART")

    fun addItemToCart() {
        val element = device.wait(Until.findObject(firstItemAddButton), 5000)
        element?.click() ?: throw RuntimeException("Add to cart button not found")
    }

    fun goToCart() {
        val element = device.findObject(cartButton)
        element?.click() ?: throw RuntimeException("Cart button not found")
    }

    fun logout() {
        openMenu()
        val element = device.wait(Until.findObject(logoutButton), 5000)
        element?.click() ?: throw RuntimeException("Logout button not found in menu")
    }

    fun openMenu() {
        val element = device.findObject(menuButton)
        element?.click() ?: throw RuntimeException("Menu button not found")
    }

    fun isOnHomePage(): Boolean {
        return device.wait(Until.hasObject(productsTitle), 5000)
    }
}
