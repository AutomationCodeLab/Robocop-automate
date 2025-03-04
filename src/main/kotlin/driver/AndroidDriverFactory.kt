package driver

import config.Configuration
import config.ConfigurationFactory
import factory.driver.IDriverFactory
import io.appium.java_client.AppiumClientConfig
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import io.appium.java_client.remote.options.BaseOptions
import io.appium.java_client.remote.options.SupportsDeviceNameOption
import org.openqa.selenium.UsernameAndPassword
import java.net.URI
import java.time.Duration

class AndroidDriverFactory : IDriverFactory {
    private val appiumClientConfig =
        AppiumClientConfig.defaultConfig().readTimeout(Duration.ofMinutes(1))
    private val configuration = ConfigurationFactory()()

    override fun invoke(testName: String): AppiumDriver =
        when (configuration.client) {
            is Configuration.Client.Local -> createLocalDriver()
            is Configuration.Client.Remote -> createRemoteDriver(testName)
        }

    private fun createLocalDriver(): AndroidDriver =
        AndroidDriver(
            appiumClientConfig.baseUri(
                URI("${configuration.server!!.host}:${configuration.server.port}")
            ), capabilities
        )

    private fun createRemoteDriver(testName: String): AndroidDriver {
        val robocopClientConfig = configuration.client as Configuration.Client.Remote
        return AndroidDriver(
            appiumClientConfig.authenticateAs(
                UsernameAndPassword(
                    robocopClientConfig.userName,
                    robocopClientConfig.accessKey
                )
            )
                .baseUri(URI("https://${robocopClientConfig.userName}:${robocopClientConfig.accessKey}@hub.browserstack.com/wd/hub")),
            browserStackCapabilities(capabilities, testName)
        )
    }

    private val capabilities = UiAutomator2Options().apply {
        setApp(configuration.client.app)
    }

    private fun <T> browserStackCapabilities(
        options: T,
        testName: String
    ) where T : BaseOptions<T>, T : SupportsDeviceNameOption<T> = options.apply {
        val robocopClientConfig = configuration.client as Configuration.Client.Remote
        val capabilities = mapOf(
            "buildName" to robocopClientConfig.buildName,
            "deviceName" to robocopClientConfig.device.name,
            "osVersion" to robocopClientConfig.device.osVersion,
            "projectName" to robocopClientConfig.device.platform,
            "sessionName" to testName,
            "userName" to robocopClientConfig.userName,
            "accessKey" to robocopClientConfig.accessKey,
            "debug" to true,
            "networkLogs" to false,
            "appiumVersion" to "2.0.1"
        )
        setCapability("bstack:options", capabilities)
    }
}