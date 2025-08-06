package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import pages.ElementsPage;


public class LoginPage {
    
WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        WebElement emailElem = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(ElementsPage.EMAIL_FIELD));
        emailElem.clear();
        emailElem.sendKeys(email);
    }

    public void clickContinue() {
        WebElement continueBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(ElementsPage.CONTINUE_BUTTON));
        continueBtn.click();
    }

    public void enterPassword(String password) {
        WebElement passElem = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(ElementsPage.PASSWORD_FIELD));
        passElem.clear();
        passElem.sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(ElementsPage.LOGIN_BUTTON).click();
    }

    public boolean isDashboardVisible() {
        return driver.getCurrentUrl().contains("home");
    }

    public boolean isErrorMessageDisplayed() {
        // Wait for error message to be visible (if present)
        try {
            WebElement errorElem = new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOfElementLocated(ElementsPage.ERROR_MESSAGE));
            return errorElem.isDisplayed();
        } catch (Exception e) {
            System.out.println("[DEBUG] Error message not found. Page source:\n" + driver.getPageSource());
            return false;
        }
    }
    public void clickForgotPassword() {
        WebElement forgotLink = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(ElementsPage.FORGOT_PASSWORD_LINK));
        forgotLink.click();
    }

    public boolean isRequiredFieldsErrorDisplayed() {
        try {
            WebElement errorElem = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(ElementsPage.REQUIRED_FIELDS_ERROR));
            return errorElem.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPasswordRequiredErrorDisplayed() {
        try {
            WebElement errorElem = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(ElementsPage.PASSWORD_REQUIRED_ERROR));
            return errorElem.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEmailRequiredErrorDisplayed() {
        try {
            WebElement errorElem = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(ElementsPage.EMAIL_REQUIRED_ERROR));
            return errorElem.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isInvalidEmailFormatErrorDisplayed() {
        try {
            WebElement errorElem = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(ElementsPage.INVALID_EMAIL_FORMAT_ERROR));
            return errorElem.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPasswordResetPageVisible() {
        try {
            WebElement header = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(ElementsPage.PASSWORD_RESET_HEADER));
            return header.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}


    
    
