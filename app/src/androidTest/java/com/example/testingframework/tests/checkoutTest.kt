package com.example.testingframework.tests

import com.example.testingframework.base.BaseTest
import com.example.testingframework.pages.LoginPage
import com.example.testingframework.pages.HomePage
import com.example.testingframework.pages.CheckoutPage
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CheckoutTest : BaseTest() {

    @Test
    fun completeCheckoutFlow() {
        val loginPage = LoginPage(device)
        val homePage = HomePage(device)
        val checkoutPage = CheckoutPage(device)

        // 1. Login
        loginPage.login("standard_user", "secret_sauce")
        assertTrue("Failed to reach HomePage", homePage.isOnHomePage())

        // 2. Add item to cart and go to cart
        homePage.addItemToCart()
        homePage.goToCart()

        // 3. Start Checkout
        checkoutPage.proceedToCheckout()

        // 4. Fill Information and Continue
        checkoutPage.fillUserInformation("Rick", "Flores", "12345")

        // 5. Review Overview and Finish
        // In some screens we might need to swipe to see the finish button
        checkoutPage.finishCheckout()

        // 6. Assertion: Verify Success
        assertTrue("Order completion message not displayed", checkoutPage.isOrderComplete())
    }
}
