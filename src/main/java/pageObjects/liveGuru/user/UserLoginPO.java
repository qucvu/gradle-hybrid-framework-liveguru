package pageObjects.liveGuru.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.liveGuru.user.UserLoginPageUI;

public class UserLoginPO extends BasePage {
    private WebDriver driver;

    public UserLoginPO(WebDriver driver) {
        this.driver = driver;
    }


    public UserRegisterPO clickToCreateAnAccountButton() {
        waitForElementClickable(driver, UserLoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
        clickToElement(driver, UserLoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
        return PageGeneratorManager.getUserRegisterPage(driver);
    }

    public UserAccountPO clickToLoginButton() {
        waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getUserAccountPage(driver);
    }

    public String getErrorMessageUnderPageTitle() {
        waitForElementVisibility(driver, UserLoginPageUI.ERROR_MESSAGE_UNDER_PAGE_TITLE);
        return getElementText(driver, UserLoginPageUI.ERROR_MESSAGE_UNDER_PAGE_TITLE);
    }
}
