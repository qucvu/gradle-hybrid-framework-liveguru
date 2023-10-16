package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.AdminReviewDetailPO;
import pageObjects.liveGuru.admin.*;
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

    public static UserAccountPO getUserAccountPage(WebDriver driver) {
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

    public static AdminLoginPO getAdminLoginPage(WebDriver driver) {
        return new AdminLoginPO(driver);
    }

    public static AdminManageCustomerPO getAdminManageCustomerPage(WebDriver driver) {
        return new AdminManageCustomerPO(driver);
    }

    public static AdminManageReviewPO getAdminManageReviewPage(WebDriver driver) {
        return new AdminManageReviewPO(driver);
    }

    public static AdminManageOrderPO getAdminManageOrderPage(WebDriver driver) {
        return new AdminManageOrderPO(driver);
    }

    public static AdminReviewDetailPO getAdminReviewDetailPage(WebDriver driver) {

        return new AdminReviewDetailPO(driver);
    }

    public static AdminManageInvoicePO getAdminInvoicePage(WebDriver driver) {
        return new AdminManageInvoicePO(driver);
    }


}
