package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.liveGuru.user.*;

public class PageGeneratorManager {
    public static UserHomePO getUserHomePage(WebDriver driver) {
        return new UserHomePO(driver);
    }

    public static UserLoginPO getUserLoginPage(WebDriver driver) {
        return new UserLoginPO(driver);
    }

    public static UserRegisterPO getUserRegisterPage(WebDriver driver) {
        return new UserRegisterPO(driver);
    }

    public static UserAccountPO getUserMyAccountPage(WebDriver driver) {
        return new UserAccountPO(driver);
    }

    public static UserProductCategoryPO getUserProductCategoryPage(WebDriver driver) {
        return new UserProductCategoryPO(driver);
    }

    public static UserProductDetailPO getUserProductDetailPage(WebDriver driver) {
        return new UserProductDetailPO(driver);
    }

    public static UserShoppingCartPO getUserShoppingCartPage(WebDriver driver) {
        return new UserShoppingCartPO(driver);
    }

    public static UserProductComparisonPO getUserProductComparisonPage(WebDriver driver) {
        return new UserProductComparisonPO(driver);
    }

    public static UserMyWishlistPO getUserMyWishlistPage(WebDriver driver) {
        return new UserMyWishlistPO(driver);
    }

    public static UserProductReviewPO getUserProductReviewPage(WebDriver driver) {
        return new UserProductReviewPO(driver);
    }

    public static UserCheckoutPO getUserCheckoutPage(WebDriver driver) {
        return new UserCheckoutPO(driver);
    }

    public static UserAdvancedSearchPO getUserAdvancedSearchPage(WebDriver driver) {
        return new UserAdvancedSearchPO(driver);
    }
}
