package pages

import io.appium.java_client.AppiumDriver
import pages.main.MainPageFactory

class Pages(driver: AppiumDriver)  {
    val main = MainPageFactory(driver)
}