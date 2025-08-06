package stepDefnition;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;
    String baseUrl;

    @Before
    public void setup() {
        // Set the ChromeDriver path to the chromedriver in the current project directory
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
        // Load base.url from config.properties
        try (java.io.InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            java.util.Properties prop = new java.util.Properties();
            if (input != null) {
                prop.load(input);
                baseUrl = prop.getProperty("base.url");
                if (baseUrl == null || baseUrl.isEmpty()) {
                    throw new RuntimeException("base.url property is missing in config.properties");
                }
            } else {
                throw new RuntimeException("config.properties file not found in resources");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load base.url from config.properties: " + e.getMessage(), e);
        }
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Given("I am on the Hudl login page")
    public void i_am_on_the_hudl_login_page() {
        driver.get(baseUrl + "/login");
        loginPage = new LoginPage(driver);
    }

    @When("I enter valid credentials")
    public void i_enter_valid_credentials() {
        String email = System.getenv("HUDL_EMAIL");
        String password = System.getenv("HUDL_PASSWORD");
        if (email == null || password == null) {
            throw new RuntimeException("Environment variables HUDL_EMAIL and HUDL_PASSWORD must be set.");
        }
        loginPage.enterEmail(email);
        loginPage.clickContinue();
        loginPage.enterPassword(password);
    }

    @When("I enter invalid credentials")
    public void i_enter_invalid_credentials() {
        loginPage.enterEmail("invalid@example.com");
        loginPage.clickContinue();
        loginPage.enterPassword("wrongpass");
    }

    @And("I click the login button")
    public void i_click_the_login_button() {
        loginPage.clickLogin();
    }

    @Then("I should be redirected to the dashboard")
    public void i_should_be_redirected_to_the_dashboard() {
        // Debug: print current URL and page title
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Page Title: " + driver.getTitle());
        try {
            org.openqa.selenium.TakesScreenshot ts = (org.openqa.selenium.TakesScreenshot) driver;
            java.nio.file.Path screenshot = java.nio.file.Paths.get("dashboard_debug.png");
            java.nio.file.Files.write(screenshot, ts.getScreenshotAs(org.openqa.selenium.OutputType.BYTES));
            System.out.println("Screenshot saved to: " + screenshot.toAbsolutePath());
        } catch (Exception e) {
            System.out.println("Could not take screenshot: " + e.getMessage());
        }
        assertTrue(loginPage.isDashboardVisible());
    }

    @Then("I should see a login error message")
    public void i_should_see_a_login_error_message() {
        assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }
    // --- Additional Scenarios ---
    @When("I leave email and password blank")
    public void i_leave_email_and_password_blank() {
        // Do not enter anything, just click Continue
        loginPage.clickContinue();
    }

    @When("I enter a valid email and leave password blank")
    public void i_enter_valid_email_and_leave_password_blank() {
        String email = System.getenv("HUDL_EMAIL");
        if (email == null) {
            throw new RuntimeException("Environment variable HUDL_EMAIL must be set.");
        }
        loginPage.enterEmail(email);
        loginPage.clickContinue();
        // Re-initialize loginPage to avoid stale element reference after page update
        loginPage = new LoginPage(driver);
        // Do not enter password, just click Login
        loginPage.clickLogin();
    }

    @When("I leave email blank and enter a password")
    public void i_leave_email_blank_and_enter_a_password() {
        // Do not enter email, just click Continue
        loginPage.clickContinue();
        // Do not enter password, as password field will not be visible
    }

    @When("I enter an invalid email format and a password")
    public void i_enter_invalid_email_format_and_a_password() {
        loginPage.enterEmail("invalidemail"); // not a valid email format
        loginPage.clickContinue();
        // Do not enter password, as password field will not be visible
    }

    @When("I click the Forgot Password link")
    public void i_click_the_forgot_password_link() {
        // Try clicking Continue if Forgot Password is not visible
        try {
            loginPage.clickForgotPassword();
        } catch (Exception e) {
            // If not found, click Continue and try again
            try { loginPage.clickContinue(); } catch (Exception ignored) {}
            loginPage.clickForgotPassword();
        }
    }


    @When("I enter valid email without password")
    public void i_enter_valid_email_without_pasword() {
        String email = System.getenv("HUDL_EMAIL");
        String password = System.getenv("HUDL_PASSWORD");
        if (email == null || password == null) {
            throw new RuntimeException("Environment variables HUDL_EMAIL and HUDL_PASSWORD must be set.");
        }
        loginPage.enterEmail(email);
        loginPage.clickContinue();
    }

    @Then("I should see a required fields error message")
    public void i_should_see_a_required_fields_error_message() {
        assertTrue(loginPage.isRequiredFieldsErrorDisplayed());
    }

    @Then("I should see a password required error message")
    public void i_should_see_a_password_required_error_message() {
        assertTrue(loginPage.isPasswordRequiredErrorDisplayed());
    }

    @Then("I should see an email required error message")
    public void i_should_see_an_email_required_error_message() {
        assertTrue(loginPage.isEmailRequiredErrorDisplayed());
    }

    @Then("I should see an invalid email format error message")
    public void i_should_see_an_invalid_email_format_error_message() {
        assertTrue(loginPage.isInvalidEmailFormatErrorDisplayed());
    }

    @Then("I should see the password reset page")
    public void i_should_see_the_password_reset_page() {
        assertTrue(loginPage.isPasswordResetPageVisible());
    }
}
