package pageObjects.liveGuru.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.liveGuru.user.UserMyWishlistPageUI;

public class UserMyWishlistPO extends BasePage {
    private WebDriver driver;

    public UserMyWishlistPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAddToWishlistSuccessMessageDisplayedByProductName(String productName) {
        waitForElementVisibility(driver, UserMyWishlistPageUI.ADD_TO_WISHLIST_SUCCESS_MESSAGE, productName);
        return isElementDisplayed(driver, UserMyWishlistPageUI.ADD_TO_WISHLIST_SUCCESS_MESSAGE, productName);
    }

    public void clickToShareWishlistbutton() {
        waitForElementClickable(driver, UserMyWishlistPageUI.SHARE_WISHLIST_BUTTON);
        clickToElement(driver, UserMyWishlistPageUI.SHARE_WISHLIST_BUTTON);

    }

    public void enterToEmailTextarea(String email) {
        waitForElementVisibility(driver, UserMyWishlistPageUI.EMAIL_SHARE_WISHLIST_TEXTAREA);
        sendkeyToElement(driver, UserMyWishlistPageUI.EMAIL_SHARE_WISHLIST_TEXTAREA, email);
    }

    public void enterToMessageTextarea(String message) {
        waitForElementVisibility(driver, UserMyWishlistPageUI.MESSAGE_SHARE_WISHLIST_TEXTAREA);
        sendkeyToElement(driver, UserMyWishlistPageUI.MESSAGE_SHARE_WISHLIST_TEXTAREA, message);

    }

    public boolean isProductNameDisplayedAtWishlistTable(String productName) {
        waitForElementVisibility(driver, UserMyWishlistPageUI.PRODUCT_NAME_AT_WISHLIST_INFO, productName);
        return isElementDisplayed(driver, UserMyWishlistPageUI.PRODUCT_NAME_AT_WISHLIST_INFO, productName);
    }

    public int getCurrentWishlistItem() {
        waitForElementVisibility(driver, UserMyWishlistPageUI.WISHLIST_ITEM_QUANTITY);
        return getElementsSize(driver, UserMyWishlistPageUI.WISHLIST_ITEM_QUANTITY);
    }

    public boolean isShareWishlistSuccessMessageDisplayed() {
        waitForElementVisibility(driver, UserMyWishlistPageUI.SHARE_WISHLIST_SUCCESS_MESSAGE);
        return isElementDisplayed(driver, UserMyWishlistPageUI.SHARE_WISHLIST_SUCCESS_MESSAGE);
    }

    public UserShoppingCartPO clickToAddToCartButtonByProductName(String productName) {
        waitForElementClickable(driver, UserMyWishlistPageUI.ADD_TO_CART_BUTTON_BY_PRODUCT_NAME, productName);
        clickToElement(driver, UserMyWishlistPageUI.ADD_TO_CART_BUTTON_BY_PRODUCT_NAME, productName);
        return PageGeneratorManager.getUserShoppingCartPage(driver);
    }
}
