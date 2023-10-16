package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageObjects.liveGuru.admin.AdminManageReviewPO;
import pageUIs.liveGuru.admin.AdminReviewDetailPageUI;

public class AdminReviewDetailPO extends BasePage {
    private WebDriver driver;

    public AdminReviewDetailPO(WebDriver driver) {
        this.driver = driver;
    }

    public AdminManageReviewPO clickToSaveReviewButton() {
        waitForElementClickable(driver, AdminReviewDetailPageUI.SAVE_REVIEW_BUTTON);
        clickToElement(driver, AdminReviewDetailPageUI.SAVE_REVIEW_BUTTON);
        return PageGeneratorManager.getAdminManageReviewPage(driver);
    }
}
