package com.example.testingframework.base

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.example.testingframework.pages.HomePage
import org.junit.After
import org.junit.Before

open class BaseTest {

    lateinit var device: UiDevice
    private val packageName = "com.swaglabsmobileapp"

    @Before
    fun setUp() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        device = UiDevice.getInstance(instrumentation)

        // Force stop the app to ensure a fresh start
        device.executeShellCommand("am force-stop $packageName")

        // Launch the app
        device.executeShellCommand("monkey -p $packageName -c android.intent.category.LAUNCHER 1")

        // Wait for the app to appear in the foreground
        device.waitForWindowUpdate(packageName, 5000)
        
        // Give the app some extra time to settle
        Thread.sleep(3000)

        // Only logout if we are actually logged in
        logoutIfLoggedIn()
    }

    private fun logoutIfLoggedIn() {
        val homePage = HomePage(device)
        // Check if we are on the products page
        if (homePage.isOnHomePage()) {
            homePage.logout()
        }
    }

    @After
    fun tearDown() {
        // Press home to exit the app
        device.pressHome()
    }
}
