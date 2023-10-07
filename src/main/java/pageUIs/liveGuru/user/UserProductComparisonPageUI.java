package pageUIs.liveGuru.user;

public class UserProductComparisonPageUI {
    public final static String COMPARE_PRODUCT_PAGE_TITLE = "xpath=//div[contains(@class, 'page-title')]/h1[text()='Compare Products']";
    public final static String PRODUCT_NAME_COMPARISON = "CSS=h2.product-name>a[title='%s']";
    public final static String PRODUCT_IMAGE_COMPARISON_BY_PRODUCT_NAME = "xpath=//a[@title='%s']/parent::h2/preceding-sibling::a/img[contains(@src, '%s')]";
    public final static String PRODUCT_PRICE_COMPARISON_BY_PRODUCT_NAME = "xpath=//a[@title='%s']/parent::h2/following-sibling::div[@class='price-box']//span[@class='price' and text()='%s']";
    public final static String PRODUCT_SKU_COMPARISON_BY_PRODUCT_NAME = "xpath=//a[@title='%s']/ancestor::tbody/following-sibling::tbody//div[@class='std' and contains(text(),'%s')]";

}
