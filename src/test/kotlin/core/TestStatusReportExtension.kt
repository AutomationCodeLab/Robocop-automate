package core

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import util.TestReport
import util.TestReport.reportStatus

class TestStatusReportExtension : AfterEachCallback, AfterAllCallback {
    private val failures = mutableListOf<Throwable>()

    override fun afterEach(context: ExtensionContext) {
        val exception = context.executionException
        if (exception.isPresent) {
            val throwable = exception.get()
            failures.add(throwable)
            reportStatus(
                status = TestReport.Status.FAILED,
                throwable = throwable,
                driverModel = context.test.driverModel
            )
        }
    }

    override fun afterAll(context: ExtensionContext) {
        if (failures.isEmpty()) {
            runCatching {
                reportStatus(
                    status = TestReport.Status.PASSED,
                    driverModel = context.test.driverModel
                )
            }
        }
    }
}

private val ExtensionContext.test: AutomationTest
    get() = (parent.get().parent.get().testInstance.get() as AutomationTest)