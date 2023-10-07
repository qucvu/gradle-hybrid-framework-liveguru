package pageObjects.liveGuru.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.liveGuru.user.UserShoppingCartPageUI;

public class UserShoppingCartPO extends BasePage {
    private WebDriver driver;

    public UserShoppingCartPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAddToCartSuccessMessageDisplayed(String productName) {
        waitForElementVisibility(driver, UserShoppingCartPageUI.ADD_TO_CART_SUCCESS_MESSAGE_BY_PRODUCT_NAME, productName);
        return isElementDisplayed(driver, UserShoppingCartPageUI.ADD_TO_CART_SUCCESS_MESSAGE_BY_PRODUCT_NAME, productName);
    }

    public void clickToApplyDiscountButton() {
        waitForElementClickable(driver, UserShoppingCartPageUI.APPLY_DISCOUNT_BUTTON);
        clickToElement(driver, UserShoppingCartPageUI.APPLY_DISCOUNT_BUTTON);
    }

    public void clickToCancelDiscountButton() {
        waitForElementClickable(driver, UserShoppingCartPageUI.CANCEL_DISCOUNT_BUTTON);
        clickToElement(driver, UserShoppingCartPageUI.CANCEL_DISCOUNT_BUTTON);

    }


    public boolean isDiscountPriceDisplayed(float discountPrice) {
        String discountPriceText = String.format("%.02f", discountPrice);
        waitForElementVisibility(driver, UserShoppingCartPageUI.DISCOUNT_PRICE_AT_TOTAL_TABLE, discountPriceText);
        return isElementDisplayed(driver, UserShoppingCartPageUI.DISCOUNT_PRICE_AT_TOTAL_TABLE, discountPriceText);
    }


    public float getGrandTotalPrice() {
        waitForElementVisibility(driver, UserShoppingCartPageUI.GRAND_TOTAL_PRICE);
        String totalPriceText = getElementText(driver, UserShoppingCartPageUI.GRAND_TOTAL_PRICE);
        return Float.parseFloat(totalPriceText.substring(1));
    }

    public void enterToQuantityTextboxByProductName(String quanityValue, String productName) {
        waitForElementVisibility(driver, UserShoppingCartPageUI.QUANTITY_TEXTBOX_BY_PRODUCT_NAME, productName);
        sendkeyToElement(driver, UserShoppingCartPageUI.QUANTITY_TEXTBOX_BY_PRODUCT_NAME, quanityValue, productName);
    }

    public void clickToUpdateButtonByProductName(String productName) {
        waitForElementClickable(driver, UserShoppingCartPageUI.UPDATE_BUTTON_BY_PRODUCT_NAME, productName);
        clickToElement(driver, UserShoppingCartPageUI.UPDATE_BUTTON_BY_PRODUCT_NAME, productName);
    }

    public boolean isMessageDisplayedUnderShoppingCartTitle(String message) {
        waitForElementVisibility(driver, UserShoppingCartPageUI.MESSAGE_UNDER_PAGE_TITLE, message);
        return isElementDisplayed(driver, UserShoppingCartPageUI.MESSAGE_UNDER_PAGE_TITLE, message);
    }

    public boolean isMaximumQuantityErrorMessageDisplayedByProductName(String productName) {
        waitForElementVisibility(driver, UserShoppingCartPageUI.MAXIMUM_QUANTITY_MESSAGE_BY_PRODUCT_NAME, productName);
        return isElementDisplayed(driver, UserShoppingCartPageUI.MAXIMUM_QUANTITY_MESSAGE_BY_PRODUCT_NAME, productName);
    }

    public void clickToEmptyCartButton() {
        waitForElementClickable(driver, UserShoppingCartPageUI.EMPTY_CART_BUTTON);
        clickToElement(driver, UserShoppingCartPageUI.EMPTY_CART_BUTTON);

    }

    public boolean isShoppingCartEmptyTitleDisplayed() {
        waitForElementVisibility(driver, UserShoppingCartPageUI.SHOPPING_CART_EMPTY_TITLE);
        return isElementDisplayed(driver, UserShoppingCartPageUI.SHOPPING_CART_EMPTY_TITLE);
    }

    public boolean isShoppingCartEmptyDescriptionDisplayed() {
        waitForElementVisibility(driver, UserShoppingCartPageUI.SHOPPING_CART_EMPTY_DESCRIPTION);
        return isElementDisplayed(driver, UserShoppingCartPageUI.SHOPPING_CART_EMPTY_DESCRIPTION);

    }

    public void clickToEstimateButton() {
        waitForElementClickable(driver, UserShoppingCartPageUI.ESTIMATE_BUTTON);
        clickToElement(driver, UserShoppingCartPageUI.ESTIMATE_BUTTON);
    }

    public String getShippingCostEstimation() {
        waitForElementVisibility(driver, UserShoppingCartPageUI.SHIPPING_COST_ESTIMATION);
        return getElementText(driver, UserShoppingCartPageUI.SHIPPING_COST_ESTIMATION);
    }

    public void checkToFixedShippingCostRadio() {
        waitForElementClickable(driver, UserShoppingCartPageUI.FIXED_SHIPPING_COST_RADIO);
        checkToDefaultCheckboxRadio(driver, UserShoppingCartPageUI.FIXED_SHIPPING_COST_RADIO);
    }

    public void clickToUpdateTotalButton() {
        waitForElementClickable(driver, UserShoppingCartPageUI.UPDATE_TOTAL_BUTTON);
        clickToElement(driver, UserShoppingCartPageUI.UPDATE_TOTAL_BUTTON);

    }

    public boolean isFixedShippingCostDisplayedAtCartTotal(String shippingCost) {
        waitForElementVisibility(driver, UserShoppingCartPageUI.SHIPPING_COST_AT_CART_TOTAL_DISPLAY, shippingCost);
        return isElementDisplayed(driver, UserShoppingCartPageUI.SHIPPING_COST_AT_CART_TOTAL_DISPLAY, shippingCost);
    }

    public String getGrandTotalCost() {
        waitForElementVisibility(driver, UserShoppingCartPageUI.GRAND_TOTAL_PRICE);
        return getElementText(driver, UserShoppingCartPageUI.GRAND_TOTAL_PRICE);
    }

    public UserCheckoutPO clickToProceedToCheckoutButton() {
        waitForElementClickable(driver, UserShoppingCartPageUI.PROCEED_TO_CHECKOUT_BUTTON);
        clickToElement(driver, UserShoppingCartPageUI.PROCEED_TO_CHECKOUT_BUTTON);

        return PageGeneratorManager.getUserCheckoutPage(driver);
    }

}
