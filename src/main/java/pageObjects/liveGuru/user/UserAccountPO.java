package pageObjects.liveGuru.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.liveGuru.user.UserAccountPageUI;

public class UserAccountPO extends BasePage {
    private WebDriver driver;

    public UserAccountPO(WebDriver driver) {
        this.driver = driver;
    }


    public String getCurrentMessageUnderPageTitle() {
        waitForElementVisibility(driver, UserAccountPageUI.CURRENT_MESSAGE_UNDER_PAGE_TITLE);
        return getElementText(driver, UserAccountPageUI.CURRENT_MESSAGE_UNDER_PAGE_TITLE);
    }


    public boolean isWelcomeMessageIsDisplayedAtMyDashboard(String fullName) {
        waitForElementVisibility(driver, UserAccountPageUI.WELCOME_HEADER_MESSAGE_BY_FULL_NAME, fullName);
        return isElementDisplayed(driver, UserAccountPageUI.WELCOME_HEADER_MESSAGE_BY_FULL_NAME, fullName);
    }

    public String getCurrentTabTitle() {
        waitForElementVisibility(driver, UserAccountPageUI.CURRENT_TAB_TITLE);
        return getElementText(driver, UserAccountPageUI.CURRENT_TAB_TITLE);
    }

}
