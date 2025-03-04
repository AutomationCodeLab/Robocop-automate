package util

import io.appium.java_client.AppiumDriver
import io.appium.java_client.AppiumFluentWait
import org.openqa.selenium.By
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import java.time.Duration
import kotlin.jvm.Throws

class Wait(driver: AppiumDriver) : AppiumFluentWait<AppiumDriver>(driver) {
    @Throws(TimeoutException::class)
    fun untilVisible(
        by: By,
        timeout: Duration = Duration.ofSeconds(5),
        polling: Duration = Duration.ofMillis(200)
    ): WebElement {
        timeoutWithPolling(timeout, polling)
        return until(ExpectedConditions.visibilityOfElementLocated(by))
    }

    @Throws(TimeoutException::class)
    fun untilVisible(
        element: WebElement,
        timeout: Duration = Duration.ofSeconds(5),
        polling: Duration = Duration.ofMillis(200)
    ): WebElement {
        timeoutWithPolling(timeout, polling)
        return until(ExpectedConditions.visibilityOf(element))
    }

    private fun timeoutWithPolling(timeout: Duration, polling: Duration) {
        withTimeout(timeout)
        pollingEvery(polling)
    }
}