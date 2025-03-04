package factory.driver

import io.appium.java_client.AppiumDriver

interface IDriverFactory {
    operator fun invoke(testName: String): AppiumDriver
}