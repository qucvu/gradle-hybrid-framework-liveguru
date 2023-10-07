package pageUIs.liveGuru.user;

public class UserMyWishlistPageUI {
    public final static String ADD_TO_WISHLIST_SUCCESS_MESSAGE = "XPATH=//div[@class='my-wishlist']//li[@class='success-msg' and contains (., '%s has been added to your wishlist')]";
    public final static String SHARE_WISHLIST_BUTTON = "css=button[title='Share Wishlist']";
    public final static String EMAIL_SHARE_WISHLIST_TEXTAREA = "id=email_address";
    public final static String MESSAGE_SHARE_WISHLIST_TEXTAREA = "id=message";
    public final static String SHARE_WISHLIST_SUCCESS_MESSAGE = "xpath=//div[@class='my-wishlist']//li[@class='success-msg']//span[ text()= 'Your Wishlist has been shared.']";
    public final static String WISHLIST_ITEM_QUANTITY = "CSS=table#wishlist-table>tbody>tr[id*=item]";
    public final static String PRODUCT_NAME_AT_WISHLIST_INFO = "CSS=table#wishlist-table td.customer-wishlist-item-info>h3.product-name>a[title='%S']";
    public final static String ADD_TO_CART_BUTTON_BY_PRODUCT_NAME = "xpath=//a[@title='%s']/parent::td/following-sibling::td//button[@title='Add to Cart']";

}
