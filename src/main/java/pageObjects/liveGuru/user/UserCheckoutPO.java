package pageObjects.liveGuru.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.liveGuru.user.UserCheckoutPageUI;

public class UserCheckoutPO extends BasePage {
    private WebDriver driver;

    public UserCheckoutPO(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToContinueButtonByContainerId(WebDriver driver, String containerId) {
        scrollToElement(driver, UserCheckoutPageUI.CONTINUE_BUTTON_CONTAINER_ID, containerId);
        waitForElementClickable(driver, UserCheckoutPageUI.CONTINUE_BUTTON_CONTAINER_ID, containerId);
        clickToElement(driver, UserCheckoutPageUI.CONTINUE_BUTTON_CONTAINER_ID, containerId);
    }

    public void checkToPaymentMethodRadio(String paymentMethod) {
        waitForElementClickable(driver, UserCheckoutPageUI.PAYMENT_METHOD_RADIO, paymentMethod);
        clickToElement(driver, UserCheckoutPageUI.PAYMENT_METHOD_RADIO, paymentMethod);

    }

    public void clickToPlaceOrderButton() {
        waitForElementClickable(driver, UserCheckoutPageUI.PLACE_ORDER_BUTTON);
        clickToElement(driver, UserCheckoutPageUI.PLACE_ORDER_BUTTON);

    }

    public boolean isOrderSuccessMessageDisplayed() {
        waitForElementVisibility(driver, UserCheckoutPageUI.ORDER_SUCCESS_MESSAGE);
        return isElementDisplayed(driver, UserCheckoutPageUI.ORDER_SUCCESS_MESSAGE);
    }

    public boolean isOrderNumberGenerated() {
        waitForElementVisibility(driver, UserCheckoutPageUI.ORDER_NUMBER_GENERATION);
        return isElementDisplayed(driver, UserCheckoutPageUI.ORDER_NUMBER_GENERATION);
    }
}
