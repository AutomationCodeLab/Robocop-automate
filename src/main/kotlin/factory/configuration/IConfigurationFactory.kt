package factory.configuration

import config.Configuration

interface IConfigurationFactory {
    operator fun invoke(): Configuration
}