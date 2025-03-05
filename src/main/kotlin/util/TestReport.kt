package util

import com.google.gson.JsonObject
import config.Configuration
import driver.DriverModel
import org.openqa.selenium.JavascriptExecutor

object TestReport {
    enum class Status {
        PASSED,
        FAILED
    }

    fun reportStatus(status: Status, throwable: Throwable? = null, driverModel: DriverModel) {
        if (driverModel.client is Configuration.Client.Local) {
            return
        }
        val executor: JavascriptExecutor = driverModel.appiumDriver
        val executorObject = JsonObject()
        val argumentsObject = JsonObject()
        argumentsObject.addProperty("status", status.name.lowercase())
        throwable?.let {
            argumentsObject.addProperty("reason", it.stackTraceToString())
        }
        executorObject.addProperty("action", "setSessionStatus")
        executorObject.add("arguments", argumentsObject)
        executor.executeScript(String.format("browserstack_executor: %s", executorObject))
    }
}