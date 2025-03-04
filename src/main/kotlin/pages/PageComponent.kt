package pages

import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.support.PageFactory
import java.time.Duration

abstract class PageComponent(protected val driver: AppiumDriver) {
    init {
        PageFactory.initElements(AppiumFieldDecorator(driver, Duration.ofSeconds(2)), this)
    }
}