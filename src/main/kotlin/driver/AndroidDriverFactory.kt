package driver

import config.Configuration
import config.ConfigurationFactory
import factory.driver.IDriverFactory
import io.appium.java_client.AppiumClientConfig
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import org.openqa.selenium.UsernameAndPassword
import java.net.URI
import java.time.Duration

class AndroidDriverFactory : IDriverFactory {
    private val appiumClientConfig =
        AppiumClientConfig.defaultConfig().readTimeout(Duration.ofMinutes(5))
    private val configuration = ConfigurationFactory()()

    override fun invoke(testName: String): DriverModel =
        when (configuration.client) {
            is Configuration.Client.Local -> DriverModel(createLocalDriver(), configuration.client)
            is Configuration.Client.Remote -> DriverModel(
                createRemoteDriver(testName),
                configuration.client
            )
        }

    private fun createLocalDriver(): AndroidDriver =
        AndroidDriver(
            appiumClientConfig.baseUri(
                URI("${configuration.server!!.host}:${configuration.server.port}")
            ), capabilities
        )

    private fun createRemoteDriver(testName: String): AndroidDriver {
        val remoteClientConfig = configuration.client as Configuration.Client.Remote
        return AndroidDriver(
            appiumClientConfig.authenticateAs(
                UsernameAndPassword(
                    remoteClientConfig.userName,
                    remoteClientConfig.accessKey
                )
            )
                .baseUri(URI("https://${remoteClientConfig.userName}:${remoteClientConfig.accessKey}@hub.browserstack.com/wd/hub")),
            browserStackCapabilities(capabilities, testName, remoteClientConfig)
        )
    }

    private val capabilities: UiAutomator2Options
        get() = UiAutomator2Options().apply { setApp(configuration.client.app) }
}