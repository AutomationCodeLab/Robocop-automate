package pages.main

import io.appium.java_client.AppiumDriver
import pages.PageComponent
import util.RobocopBy
import util.Wait

class MainPage(driver: AppiumDriver) : PageComponent(driver) {
    val isDefaultButtonDisplayed: Boolean
        get() = Wait(driver).untilVisible(RobocopBy.resourceId(DEFAULT_BUTTON_ID)).isDisplayed
    val isTappedButtonDisplayed: Boolean
        get() = Wait(driver).untilVisible(
            RobocopBy.resourceId(TAPPED_BUTTON_ID)
        ).isDisplayed

    fun tapButtonInDefaultState() {
        Wait(driver).untilVisible(RobocopBy.resourceId(DEFAULT_BUTTON_ID)).click()
    }

    private companion object {
        const val DEFAULT_BUTTON_ID = "Klick mich_button"
        const val TAPPED_BUTTON_ID = "Ich wurde geklickt!_button"
    }
}