package pages;

import baseEntities.BasePage;
import core.BrowsersService;
import core.ReadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import wrappers.Button;
import wrappers.InputField;

public class LoginPage extends BasePage {

    private final static String endpoint = "/index.php?/auth/login";

    private final static By LOGIN_PAGE_TITLE = By.className("loginpage-installationname");
    private final static By EMAIL_FIELD = By.id("name");
    private final static By PASSWORD_FIELD = By.id("password");
    private final static By BUTTON_FIELD = By.id("button_primary");

    public LoginPage(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);
    }

    @Override
    protected void openPage() {
        browsersService.getDriver().get(ReadProperties.getInstance().getURL() + endpoint);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getLoginPageInstallationName().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private WebElement getLoginPageInstallationName() {
        return browsersService.getWaiters().waitForVisibility(LOGIN_PAGE_TITLE);
    }

    private InputField getNameField() {
        return new InputField(browsersService, EMAIL_FIELD);
    }

    private InputField getPasswordField() {
        return new InputField(browsersService, PASSWORD_FIELD);
    }

    private Button getLoginButton() {
        return new Button(browsersService, BUTTON_FIELD);
    }

    public void successfulLogin() {
        inputEmail(ReadProperties.getInstance().getUsername());
        inputPasswordField(ReadProperties.getInstance().getPassword());
        clickButton();
    }

    public LoginPage unsuccessfulLogin(String email, String password) {

        if (email == null & password == null) {
            return this;
        }
        inputEmail(email);
        inputPasswordField(password);
        clickButton();
        return this;
    }

    private void inputEmail(String email) {
        getNameField()
                .sendKeys(email);
    }

    private void inputPasswordField(String password) {
        getPasswordField()
                .sendKeys(password);
    }

    private void clickButton() {
        getLoginButton()
                .click();
    }
}


