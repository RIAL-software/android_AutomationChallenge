# Swag Labs Mobile Automation Framework

This is a professional mobile testing framework built with **Kotlin** and **UI Automator** to automate the **Swag Labs** (Sauce Labs) mobile application.

## 🚀 Overview
Since the target application is built with **React Native**, this framework utilizes UI Automator's ability to interact with cross-platform elements using `content-description` (mapping to React Native's `testID`).

### Tech Stack
- **Language:** Kotlin
- **Tool:** UI Automator (for black-box testing)
- **Architecture:** Page Object Model (POM)
- **Test Runner:** JUnit 4 / AndroidJUnitRunner

## 📁 Project Structure
- **`com.example.testingframework.base`**: Contains `BaseTest.kt`, which manages app launch, device initialization, and session cleanup (automatic logout).
- **`com.example.testingframework.pages`**: Page Objects containing element selectors and reusable actions for:
    - `LoginPage`
    - `HomePage` (Products list)
    - `CheckoutPage` (Full purchase flow)
- **`com.example.testingframework.tests`**: Test suites covering:
    - Successful Login & Logout
    - End-to-end Purchase (Checkout flow)

## 🛠 Setup & Prerequisites
1. **Target App**: Ensure the Swag Labs app (`com.swaglabsmobileapp`) is installed on your Android device or emulator.
2. **Android Studio**: Recommended IDE for running and developing tests.
3. **SDK Tools**: Make sure the Android SDK is correctly configured in your environment.

## 🏃 Running the Tests
You can run the tests directly from Android Studio or via terminal using Gradle:

```bash
# Run all instrumented tests
./gradlew connectedAndroidTest
```

## 🔍 Key Features
- **Session Management**: The framework automatically detects if a user is logged in and performs a logout before and after each test to ensure a clean state.
- **Robust Selectors**: Uses `By.desc()` to target elements reliably across different screen sizes.
- **Wait Strategies**: Implements `device.wait(Until.findObject(...))` to handle asynchronous loading common in React Native apps.

---
*Created by Rick Flores*
