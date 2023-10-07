package pageObjects.liveGuru.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.liveGuru.user.UserProductReviewPageUI;

public class UserProductReviewPO extends BasePage {
    private WebDriver driver;

    public UserProductReviewPO(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToThoughtTextareaEmptyValue() {
        waitForElementVisibility(driver, UserProductReviewPageUI.THOUGHT_TEXTAREA);
        clearElement(driver, UserProductReviewPageUI.THOUGHT_TEXTAREA);
    }

    public void clickToSubmitReviewButton() {
        waitForElementClickable(driver, UserProductReviewPageUI.SUBMIT_REVIEW_BUTTON);
        clickToElement(driver, UserProductReviewPageUI.SUBMIT_REVIEW_BUTTON);
    }

    public String getErrorMessageUnderQualityRateRadio() {
        waitForElementVisibility(driver, UserProductReviewPageUI.ERROR_MESSAGE_UNDER_QUALITY_RADIO);
        return getElementText(driver, UserProductReviewPageUI.ERROR_MESSAGE_UNDER_QUALITY_RADIO);
    }

    public String getErrorMessageUnderThoughtTextarea() {
        waitForElementVisibility(driver, UserProductReviewPageUI.ERROR_MESSAGE_UNDER_THOUGHT_TEXTAREA);
        return getElementText(driver, UserProductReviewPageUI.ERROR_MESSAGE_UNDER_THOUGHT_TEXTAREA);
    }

    public String getErrorMessageUnderSummaryTextbox() {
        waitForElementVisibility(driver, UserProductReviewPageUI.ERROR_MESSAGE_UNDER_SUMMARY_TEXTBOX);
        return getElementText(driver, UserProductReviewPageUI.ERROR_MESSAGE_UNDER_SUMMARY_TEXTBOX);
    }

    public String getErrorMessageUnderNicknameTextbox() {
        waitForElementVisibility(driver, UserProductReviewPageUI.ERROR_MESSAGE_UNDER_NICKNAME_TEXTBOX);
        return getElementText(driver, UserProductReviewPageUI.ERROR_MESSAGE_UNDER_NICKNAME_TEXTBOX);
    }

    public void enterToThoughtTextarea(String thoughtReview) {
        waitForElementVisibility(driver, UserProductReviewPageUI.THOUGHT_TEXTAREA);
        sendkeyToElement(driver, UserProductReviewPageUI.THOUGHT_TEXTAREA, thoughtReview);
    }

    public void checkToQualityRadio(String qualityRateReview) {
        waitForElementClickable(driver, UserProductReviewPageUI.QUALITY_RADIO_BY_RATE_NUMBER, qualityRateReview);
        checkToDefaultCheckboxRadio(driver, UserProductReviewPageUI.QUALITY_RADIO_BY_RATE_NUMBER, qualityRateReview);
    }

    public boolean isMessageUnderBreadcrumbDisplayed(String message) {
        waitForElementVisibility(driver, UserProductReviewPageUI.MESSAGE_UNDER_BREADCRUMB, message);
        return isElementDisplayed(driver, UserProductReviewPageUI.MESSAGE_UNDER_BREADCRUMB, message);
    }

}
