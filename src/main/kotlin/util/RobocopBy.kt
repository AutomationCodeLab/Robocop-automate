package util

import io.appium.java_client.AppiumBy
import org.openqa.selenium.By

object RobocopBy {
    fun resourceId(id: String): By =
        AppiumBy.androidUIAutomator("""new UiSelector().resourceId("$id")""")
}