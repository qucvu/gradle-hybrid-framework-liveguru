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
}
