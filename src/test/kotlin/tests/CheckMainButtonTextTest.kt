package tests

import driver.AppiumDriverFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import pages.Pages

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CheckMainButtonTextTest {
    private val driver = AppiumDriverFactory()(this::class.simpleName!!)

    @Nested
    @DisplayName("Given I am on the main screen")
    inner class MainScreen {
        private val pages = Pages(driver)
        private val mainPage = pages.main

        @Test
        fun `Then I should see the button in default state`() {
            Assertions.assertTrue(mainPage.isDefaultButtonDisplayed)
        }

        @Nested
        @DisplayName("When I tap on the button in the default state")
        inner class MainButtonTap {

            @BeforeEach
            fun setup() {
                mainPage.tapButtonInDefaultState()
            }

            @Test
            fun `Then I should see the button in the tapped state`() {
                Assertions.assertTrue(mainPage.isTappedButtonDisplayed)
            }
        }
    }
}