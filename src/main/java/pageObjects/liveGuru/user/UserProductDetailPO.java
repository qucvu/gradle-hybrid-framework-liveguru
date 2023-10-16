package pageObjects.liveGuru.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.liveGuru.user.UserProductDetailPageUI;

public class UserProductDetailPO extends BasePage {
    private WebDriver driver;

    public UserProductDetailPO(WebDriver driver) {
        this.driver = driver;
    }

    public Float getProductCostByProductName() {
        waitForElementVisibility(driver, UserProductDetailPageUI.CURRENT_REGULAR_PRODUCT_PRICE);
        String productPriceText = getElementText(driver, UserProductDetailPageUI.CURRENT_REGULAR_PRODUCT_PRICE);
        return Float.parseFloat(productPriceText.substring(1));
    }


    public UserShoppingCartPO clickToAddToCartButton() {
        waitForElementClickable(driver, UserProductDetailPageUI.ADD_TO_CART_BUTTON);
        clickToElement(driver, UserProductDetailPageUI.ADD_TO_CART_BUTTON);
        return PageGeneratorManager.getUserShoppingCartPage(driver);
    }

    public UserProductReviewPO clickToAddYourReviewLink() {
        waitForElementClickable(driver, UserProductDetailPageUI.ADD_YOUR_REVIEW_LINK);
        clickToElement(driver, UserProductDetailPageUI.ADD_YOUR_REVIEW_LINK);
        return PageGeneratorManager.getUserProductReviewPage(driver);
    }

    public void clickToQuantityReviewsLink() {
        waitForElementClickable(driver, UserProductDetailPageUI.QUANTITY_REVIEWS_LINK);
        clickToElement(driver, UserProductDetailPageUI.QUANTITY_REVIEWS_LINK);
    }

    public boolean isSummaryReviewDisplayedByNickname(String nickname, String summaryReview) {
        waitForElementVisibility(driver, UserProductDetailPageUI.SUMMARY_REVIEW_BY_NICKNAME, nickname, summaryReview);
        return isElementDisplayed(driver, UserProductDetailPageUI.SUMMARY_REVIEW_BY_NICKNAME, nickname, summaryReview);
    }

    public boolean isThoughtReviewDisplayedByNickname(String nickname, String thoughtReview) {
        waitForElementVisibility(driver, UserProductDetailPageUI.THOUGHT_REVIEW_BY_NICKNAME, nickname, thoughtReview);
        return isElementDisplayed(driver, UserProductDetailPageUI.THOUGHT_REVIEW_BY_NICKNAME, nickname, thoughtReview);
    }

    public boolean isRatingReviewDisplayedByNickname(String nickname, String qualityRateReview) {
        waitForElementVisibility(driver, UserProductDetailPageUI.QUANTITY_RATE_VIEW_BY_NICKNAME, nickname);
        String styleAttribute = getElementAttribute(driver, "style", UserProductDetailPageUI.QUANTITY_RATE_VIEW_BY_NICKNAME, nickname);
        int quantityRate = Integer.parseInt(styleAttribute.substring(7, styleAttribute.indexOf(";") - 1)) / 20;
        return String.valueOf(quantityRate).equals(qualityRateReview);
    }

    public static void main(String[] args) {
        System.out.println("width:6%;".substring(6, "width:60%;".indexOf(";") - 1));

    }
}
