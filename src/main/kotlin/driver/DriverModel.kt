package driver

import config.Configuration
import io.appium.java_client.AppiumDriver

data class DriverModel(val appiumDriver: AppiumDriver, val client: Configuration.Client)
