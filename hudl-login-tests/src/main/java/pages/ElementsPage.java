package pages;

import org.openqa.selenium.By;

public class ElementsPage {
    public static final By EMAIL_FIELD = By.id("username");
    public static final By CONTINUE_BUTTON = By.cssSelector("button[type='submit']");
    public static final By PASSWORD_FIELD = By.id("password");
    public static final By LOGIN_BUTTON = By.cssSelector("button[type='submit']");
    public static final By ERROR_MESSAGE = By.id("error-element-password");
    public static final By FORGOT_PASSWORD_LINK = By.xpath("//a[text()='Forgot Password']");
    public static final By REQUIRED_FIELDS_ERROR = By.id("error-cs-email-required");
    public static final By PASSWORD_REQUIRED_ERROR = By.id("error-cs-password-required");
    public static final By EMAIL_REQUIRED_ERROR = By.cssSelector("span.ulp-input-error-message[data-error-code='username-required']");
    public static final By INVALID_EMAIL_FORMAT_ERROR = By.id("error-cs-email-invalid");
    public static final By PASSWORD_RESET_HEADER = By.id("aria-description-text");
}
