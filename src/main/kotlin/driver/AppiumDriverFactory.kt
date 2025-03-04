package driver

import factory.pagecomponent.IDriverFactory
import io.appium.java_client.AppiumClientConfig
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import java.net.URI
import java.time.Duration

class AppiumDriverFactory : IDriverFactory {
    private val clientConfig = AppiumClientConfig.defaultConfig().readTimeout(Duration.ofMinutes(1))
    override fun invoke(testName: String): AppiumDriver {
        return AndroidDriver(clientConfig.baseUri(URI("http://127.0.0.1:4728")), capabilities)
    }

    private val capabilities = UiAutomator2Options().apply {
        setApp("/Users/alfredafutu/development/Robocop/robocop.apk")
    }
}