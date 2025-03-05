package factory.driver

import driver.DriverModel

interface IDriverFactory {
    operator fun invoke(testName: String): DriverModel
}