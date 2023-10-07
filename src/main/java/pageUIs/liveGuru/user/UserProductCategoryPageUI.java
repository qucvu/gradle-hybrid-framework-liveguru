package pageUIs.liveGuru.user;

public class UserProductCategoryPageUI {
    public final static String PRICE_BY_PRODUCT_NAME = "xpath=//a[text()='%s']/parent::h2//following-sibling::div//span[@class='price']";
    public final static String PRODUCT_NAME_LINK_IN_PRODUCT_INFO = "css=div.product-info a[title='%s']";
    public final static String ADD_TO_COMPARE_LINK_BY_PRODUCT_NAME = "xpath=//a[@title='%s']/parent::h2/following-sibling::div//a[@class='link-compare']";
    public final static String ADD_PRODUCT_TO_COMPARISON_SUCCESS_MESSAGE = "xpath=//li[@class='success-msg']//span[text()='The product %s has been added to comparison list.']";
    public final static String COMPARE_BUTTON = "css=button[title=Compare]";
    public final static String ADD_TO_WISHLIST_LINK_BY_PRODUCT_NAME = "xpath=//a[@title='%s']/parent::h2/following-sibling::div//a[@class='link-wishlist']";
    public final static String PRODUCT_NAME_AT_PRODUCT_LIST = "css=div.product-info>h2.product-name>a";
    public final static String PRODUCT_PRICE_AT_PRODUCT_LIST = "css=div.product-info>div.price-box>span.regular-price>span.price,p.special-price >span.price";


}
