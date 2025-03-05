package core

import driver.AndroidDriverFactory
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestStatusReportExtension::class)
open class AutomationTest(testName: String) {
    internal val driverModel = AndroidDriverFactory()(testName)
}