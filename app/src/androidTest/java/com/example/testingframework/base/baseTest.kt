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

        // Launch app
        device.executeShellCommand(
            "monkey -p $packageName -c android.intent.category.LAUNCHER 1"
        )

        // Wait for app to load
        Thread.sleep(3000)

        // Ensure we are logged out before each test starts
        logoutIfLoggedIn()
    }

    private fun logoutIfLoggedIn() {
        val homePage = HomePage(device)
        // If we see the products title, it means we are logged in
        if (homePage.isOnHomePage()) {
            homePage.logout()
        }
    }

    @After
    fun tearDown() {
        // Ensure we logout after the test finishes to leave the app in a clean state
        logoutIfLoggedIn()
        device.pressHome()
    }
}
