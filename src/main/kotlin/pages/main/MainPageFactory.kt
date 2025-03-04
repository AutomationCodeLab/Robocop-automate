package pages.main

import factory.pagecomponent.IPageComponentFactory
import io.appium.java_client.AppiumDriver

object MainPageFactory : IPageComponentFactory {
    override operator fun invoke(driver: AppiumDriver): MainPage =
        MainPage(driver)
}