package pageObjects.liveGuru.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.liveGuru.user.UserProductComparisonPageUI;

public class UserProductComparisonPO extends BasePage {
    private WebDriver driver;

    public UserProductComparisonPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isTitleCompareProductDisplayed() {
        waitForElementVisibility(driver, UserProductComparisonPageUI.COMPARE_PRODUCT_PAGE_TITLE);
        return isElementDisplayed(driver, UserProductComparisonPageUI.COMPARE_PRODUCT_PAGE_TITLE);
    }

    public boolean isProductCompareNameDisplayed(String productName) {
        waitForElementVisibility(driver, UserProductComparisonPageUI.PRODUCT_NAME_COMPARISON, productName);
        return isElementDisplayed(driver, UserProductComparisonPageUI.PRODUCT_NAME_COMPARISON, productName);
    }

    public boolean isProductComparePriceDisplayed(String productName, String productPrice) {
        waitForElementVisibility(driver, UserProductComparisonPageUI.PRODUCT_PRICE_COMPARISON_BY_PRODUCT_NAME, productName, productPrice);
        return isElementDisplayed(driver, UserProductComparisonPageUI.PRODUCT_PRICE_COMPARISON_BY_PRODUCT_NAME, productName, productPrice);

    }

    public boolean isProductCompareImageDisplayed(String productName, String productImageLink) {
        waitForElementVisibility(driver, UserProductComparisonPageUI.PRODUCT_IMAGE_COMPARISON_BY_PRODUCT_NAME, productName, productImageLink);
        return isElementDisplayed(driver, UserProductComparisonPageUI.PRODUCT_IMAGE_COMPARISON_BY_PRODUCT_NAME, productName, productImageLink);
    }

    public boolean isProductCompareSKUDisplayed(String productName, String productSKU) {
        waitForElementVisibility(driver, UserProductComparisonPageUI.PRODUCT_SKU_COMPARISON_BY_PRODUCT_NAME, productName, productSKU);
        return isElementDisplayed(driver, UserProductComparisonPageUI.PRODUCT_SKU_COMPARISON_BY_PRODUCT_NAME, productName, productSKU);
    }


}
