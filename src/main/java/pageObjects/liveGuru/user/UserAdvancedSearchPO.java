package pageObjects.liveGuru.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.liveGuru.user.UserAdvancedSearchPageUI;

public class UserAdvancedSearchPO extends BasePage {
    private WebDriver driver;

    public UserAdvancedSearchPO(WebDriver driver) {
        this.driver = driver;
    }

    public UserProductCategoryPO clickToSearchButton() {
        waitForElementClickable(driver, UserAdvancedSearchPageUI.SEARCH_BUTTON);
        clickToElement(driver, UserAdvancedSearchPageUI.SEARCH_BUTTON);
        return PageGeneratorManager.getUserProductCategoryPage(driver);
    }
}
