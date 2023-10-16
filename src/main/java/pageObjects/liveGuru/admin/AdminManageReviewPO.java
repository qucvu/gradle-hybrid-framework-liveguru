package pageObjects.liveGuru.admin;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageObjects.AdminReviewDetailPO;
import pageUIs.liveGuru.admin.AdminManageReviewPageUI;

public class AdminManageReviewPO extends BasePage {
    private WebDriver driver;

    public AdminManageReviewPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isTitleDisplayedByNicknameGridTable(String nickname, String title) {
        waitForElementVisibility(driver, AdminManageReviewPageUI.TITLE_CELL_BY_NICK_NAME, nickname, title);
        return isElementDisplayed(driver, AdminManageReviewPageUI.TITLE_CELL_BY_NICK_NAME, nickname, title);
    }

    public boolean isReviewDisplayedByNicknameGridTable(String nickname, String review) {
        waitForElementVisibility(driver, AdminManageReviewPageUI.REVIEW_DESCRIPTION_CELL_BY_NICK_NAME, nickname, review);
        return isElementDisplayed(driver, AdminManageReviewPageUI.REVIEW_DESCRIPTION_CELL_BY_NICK_NAME, nickname, review);
    }

    public AdminReviewDetailPO clickToEditLinkByNickname(String nickname) {
        waitForElementClickable(driver, AdminManageReviewPageUI.EDIT_LINK_BY_NICKNAME, nickname);
        clickToElement(driver, AdminManageReviewPageUI.EDIT_LINK_BY_NICKNAME, nickname);
        return PageGeneratorManager.getAdminReviewDetailPage(driver);
    }

}
