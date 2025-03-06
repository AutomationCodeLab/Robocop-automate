# Robocop-automate

This project provides a streamlined approach to automating mobile app testing using BrowserStack. It leverages **Appium** with **JUnit 5** and **Kotlin** to write and execute automated tests. It includes workflows for uploading apps to BrowserStack, running automated tests, and managing the overall testing process.

## Key Features

*   **Automated App Upload:** Automatically uploads your mobile app to BrowserStack for testing.
*   **BrowserStack Test Execution:** Executes automated tests on BrowserStack's real device cloud.
*   **Appium with JUnit 5 and Kotlin:** Uses Appium for mobile automation, JUnit 5 for test structure, and Kotlin for writing tests.
*   **CI/CD Integration:** Designed for seamless integration with CI/CD pipelines.
* **Version Catalog** The project uses a version catalog to manage dependencies.

## Project Structure

The project is structured to facilitate efficient automation workflows. Here are the key files and actions:

### `browserstack-automation.yml`

This file defines the GitHub Actions workflow for running automated tests on BrowserStack. It orchestrates the entire testing process, including:

*   **App Upload:** Uses the `browserstack-app-upload` action to upload the app to BrowserStack.
*   **Test Execution:** Uses the `run-automation` action to execute the automated tests on BrowserStack.
*   **Environment Setup:** Sets up the necessary environment variables and dependencies.
* **Workflow Triggers:** Defines the events that trigger the workflow.

### `browserstack-app-upload` Action

This is a custom GitHub Action located in `.github/actions/browserstack-app-upload`. It's responsible for uploading the mobile app to BrowserStack.

**Key Responsibilities:**

*   **Authentication:** Authenticates with BrowserStack using the provided credentials.
*   **App Upload:** Uploads the specified app file to BrowserStack's servers.
*   **Output:** Provides the `app-url` as an output, which is the URL of the uploaded app on BrowserStack. This URL is used in subsequent steps to run tests against the uploaded app.

### `run-automation` Action

This is another custom GitHub Action located in `.github/actions/run-automation`. It's responsible for running the automated tests on BrowserStack.

**Key Responsibilities:**

*   **Authentication:** Authenticates with BrowserStack using the provided credentials.
*   **Test Execution:** Executes the **Appium** automated tests written in **Kotlin** using **JUnit 5** on BrowserStack.
*   **Reporting:** Provides test results and logs.
* **App URL:** Uses the app url provided by the `browserstack-app-upload` action.

### `automate-app-upload.sh` Script

This shell script is a utility for uploading the app to BrowserStack. It's used by the `browserstack-app-upload` action.

**Key Responsibilities:**

*   **Authentication:** Authenticates with BrowserStack using the provided credentials.
*   **App Upload:** Uploads the specified app file to BrowserStack's servers.
*   **Output:** Prints the `app-url` to standard output.
