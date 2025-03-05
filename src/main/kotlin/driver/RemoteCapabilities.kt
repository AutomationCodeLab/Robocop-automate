package driver

import config.Configuration
import io.appium.java_client.remote.options.BaseOptions
import io.appium.java_client.remote.options.SupportsDeviceNameOption

internal fun <T> browserStackCapabilities(
    options: T,
    testName: String,
    configuration: Configuration.Client.Remote
) where T : BaseOptions<T>, T : SupportsDeviceNameOption<T> = options.apply {
    val capabilities = mapOf(
        "buildName" to configuration.buildName,
        "deviceName" to configuration.device.name,
        "platformVersion" to configuration.device.platformVersion,
        "projectName" to configuration.device.platform,
        "sessionName" to testName,
        "userName" to configuration.userName,
        "accessKey" to configuration.accessKey,
        "debug" to true,
        "networkLogs" to false,
        "appiumVersion" to "2.0.1"
    )
    setCapability("bstack:options", capabilities)
}