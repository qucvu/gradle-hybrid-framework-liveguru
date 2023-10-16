package pageUIs.liveGuru.user;

public class UserProductDetailPageUI {
    public final static String CURRENT_REGULAR_PRODUCT_PRICE = "CSS=span.regular-price>span.price";
    public final static String ADD_TO_CART_BUTTON = "css=button.btn-cart";
    public final static String ADD_YOUR_REVIEW_LINK = "CSS=p.rating-links>a[href*=review-form]";
    public final static String QUANTITY_REVIEWS_LINK = "xpath=//a[contains(text(), 'Review(s)')]";
    public final static String SUMMARY_REVIEW_BY_NICKNAME = "XPATH=//span[@class='review-meta' and contains(text(), 'Review by %s')]/parent::dd/preceding-sibling::dt//a[contains(text(), '%s')]";
    public final static String THOUGHT_REVIEW_BY_NICKNAME = "xpath=//span[@class='review-meta' and contains(text(), 'Review by %s')]/parent::dd[contains(text(), '%s')]";
    public final static String QUANTITY_RATE_VIEW_BY_NICKNAME = "xpath=//span[@class='review-meta' and contains(text(), 'Review by %s')]/preceding-sibling::table//div[@class='rating']";

}
