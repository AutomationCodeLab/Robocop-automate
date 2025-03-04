package factory.pagecomponent

import io.appium.java_client.AppiumDriver
import pages.PageComponent

interface IPageComponentFactory {
    operator fun invoke(driver: AppiumDriver): PageComponent
}