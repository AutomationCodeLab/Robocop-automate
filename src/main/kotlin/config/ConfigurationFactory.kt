package config

import factory.configuration.IConfigurationFactory
import io.github.cdimascio.dotenv.Dotenv

class ConfigurationFactory : IConfigurationFactory {
    override fun invoke(): Configuration {
        val dotenv = Dotenv.configure().ignoreIfMissing().load()
        val run = dotenv.get(EnvironmentConstants.RUN)
        val app = dotenv.get(EnvironmentConstants.APP)
        val device = Configuration.Client.Device(
            platform = dotenv.get(EnvironmentConstants.PLATFORM),
            name = dotenv.get(EnvironmentConstants.DEVICE_NAME),
            platformVersion = dotenv.get(EnvironmentConstants.PLATFORM_VERSION),
            udid = dotenv.get(EnvironmentConstants.UDID)
        )
        return if (run == "LOCAL") {
            Configuration(
                client = Configuration.Client.Local(
                    app = app,
                    device = device
                ),
                server = Configuration.Server(
                    host = dotenv.get(EnvironmentConstants.SERVER_HOST),
                    port = dotenv.get(EnvironmentConstants.SERVER_PORT ).toInt()
                )
            )
        } else {
            Configuration(
                Configuration.Client.Remote(
                    userName = dotenv.get(EnvironmentConstants.APP_AUTOMATE_USER),
                    accessKey = dotenv.get(EnvironmentConstants.APP_AUTOMATE_KEY),
                    buildName = dotenv.get(EnvironmentConstants.BUILD_NAME),
                    app = app,
                    device = device
                )
            )
        }
    }
}

private object EnvironmentConstants {
    const val RUN = "RUN"
    const val APP = "APP"
    const val APP_AUTOMATE_USER = "APP_AUTOMATE_USER"
    const val APP_AUTOMATE_KEY = "APP_AUTOMATE_KEY"
    const val BUILD_NAME = "BUILD_NAME"
    const val DEVICE_NAME = "DEVICE_NAME"
    const val PLATFORM = "PLATFORM"
    const val PLATFORM_VERSION = "PLATFORM_VERSION"
    const val UDID = "UDID"
    const val SERVER_HOST = "SERVER_HOST"
    const val SERVER_PORT = "SERVER_PORT"
}