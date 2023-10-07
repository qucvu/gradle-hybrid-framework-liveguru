package pageUIs.liveGuru.user;

public class UserCheckoutPageUI {
    public final static String CONTINUE_BUTTON_CONTAINER_ID = "CSS=div#%s>button";
    public final static String PAYMENT_METHOD_RADIO = "CSS=input#p_method_checkmo[title='%s']";
    public final static String PLACE_ORDER_BUTTON = "CSS=button[title='Place Order']";
    public final static String ORDER_SUCCESS_MESSAGE = "XPATH=//h1[text()='Your order has been received.']/parent::div/following-sibling::h2[@class='sub-title' and text()='Thank you for your purchase!']";
    public final static String ORDER_NUMBER_GENERATION = "xpath=//p[contains(text(),'Your order # is: ')]//a[contains(@href, 'order_id/')]";

}
