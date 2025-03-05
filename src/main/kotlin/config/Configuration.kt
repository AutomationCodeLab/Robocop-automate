package config

data class Configuration(val client: Client, val server: Server? = null) {
    sealed class Client(
        open val app: String,
        open val device: Device
    ) {
        data class Local(
            override val app: String,
            override val device: Device
        ) : Client(app, device)

        data class Remote(
            val userName: String,
            val accessKey: String,
            val buildName: String,
            override val app: String,
            override val device: Device
        ) : Client(app, device)

        data class Device(
            val platform: String,
            val name: String? = null,
            val platformVersion: String? = null,
            val udid: String? = null
        )
    }

    data class Server(
        val host: String,
        val port: Int
    )
}