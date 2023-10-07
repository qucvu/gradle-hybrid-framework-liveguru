package pageUIs.liveGuru.user;

public class UserShoppingCartPageUI {
    public final static String ADD_TO_CART_SUCCESS_MESSAGE_BY_PRODUCT_NAME = "xpath=//ul[@class='messages']//li/span[text()='%s was added to your shopping cart.']";
    public final static String APPLY_DISCOUNT_BUTTON = "css=button[title=Apply]";
    public final static String CANCEL_DISCOUNT_BUTTON = "css=button[title=Cancel]";
    public final static String DISCOUNT_PRICE_AT_TOTAL_TABLE = "xpath=//table[@id='shopping-cart-totals-table']//td[contains(text(), 'Discount (GURU50)')]//following-sibling::td/span[text()='-$%s']";
    public final static String GRAND_TOTAL_PRICE = "css=table#shopping-cart-totals-table > tfoot span.price";
    public final static String QUANTITY_TEXTBOX_BY_PRODUCT_NAME = "xpath=//a[text()='%s']/ancestor::td/following-sibling::td/input[@title='Qty']";
    public final static String UPDATE_BUTTON_BY_PRODUCT_NAME = "xpath=//a[text()='%s']/ancestor::td/following-sibling::td/button[@title='Update']";
    public final static String MESSAGE_UNDER_PAGE_TITLE = "xpath=//ul[@class='messages']//span[text()='%s']";
    public final static String MAXIMUM_QUANTITY_MESSAGE_BY_PRODUCT_NAME = "xpath=//a[text()='%s']/parent::h2/following-sibling::p[contains(@class, 'item-msg') and contains(text(),'The maximum quantity allowed for purchase is 500')]";
    public final static String EMPTY_CART_BUTTON = "css=button.btn-empty";
    public final static String SHOPPING_CART_EMPTY_TITLE = "xpath=//div[@class='page-title']/h1[text()='Shopping Cart is Empty']";
    public final static String SHOPPING_CART_EMPTY_DESCRIPTION = "xpath=//div[@class='cart-empty' and contains(., 'You have no items in your shopping cart.')]";
    public final static String ESTIMATE_BUTTON = "css=button[title='Estimate']";
    public final static String SHIPPING_COST_ESTIMATION = "css=label[for='s_method_flatrate_flatrate']>span.price";
    public final static String FIXED_SHIPPING_COST_RADIO = "id=s_method_flatrate_flatrate";
    public final static String UPDATE_TOTAL_BUTTON = "css=button[title='Update Total']";
    public final static String SHIPPING_COST_AT_CART_TOTAL_DISPLAY = "XPATH=//td[contains(text(), 'Shipping & Handling (Flat Rate - Fixed)')]/following-sibling::td/span[@class='price' and text()='%s']";
    public final static String PROCEED_TO_CHECKOUT_BUTTON = "CSS=li.method-checkout-cart-methods-onepage-bottom button[title='Proceed to Checkout']";


}
