package com.liveGuru.admin;

import com.aventstack.extentreports.Status;
import com.liveGuru.common.Common_01_Login_Admin;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.AdminReviewDetailPO;
import pageObjects.liveGuru.admin.AdminManageCustomerPO;
import pageObjects.liveGuru.admin.AdminManageOrderPO;
import pageObjects.liveGuru.admin.AdminManageReviewPO;
import pageObjects.liveGuru.user.UserHomePO;
import pageObjects.liveGuru.user.UserProductCategoryPO;
import pageObjects.liveGuru.user.UserProductDetailPO;
import pageObjects.liveGuru.user.UserProductReviewPO;
import reportConfig.ExtentTestManager;
import utilities.Enviroment;

import java.lang.reflect.Method;

public class Admin_02_Invoice_Review_Checkbox extends BaseTest {

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        String envName = System.getProperty("environment");
        ConfigFactory.setProperty("env", envName);
        Enviroment env = ConfigFactory.create(Enviroment.class);
        endUserUrl = env.endUserUrl();
        adminUrl = env.adminUrl();
        fileNameInvoice = String.format("invoice%s-%s-%s", getCurrentYear(), getCurrentMonth(), getCurrentDate());
        productReview = "Samsung LCD";
        thoughtReview = "This product is goood,Can be used for a long time";
        summaryReview = "Good";
        nicknameReview = "Geni" + getCurrentDateTimeUTC();
        qualityRateReview = "3";

        log.info("Pre conditions - Step 01: Open Admin Url Site");
        driver = getBrowserDriver(browserName, adminUrl);
        adminManageCustomerPage = PageGeneratorManager.getAdminManageCustomerPage(driver);

        log.info("Pre conditions - Step 02: Set cookies and reload the page");
        adminManageCustomerPage.setCookies(driver, Common_01_Login_Admin.loggedCookies);
        adminManageCustomerPage.refreshCurrentPage(driver);

        log.info("Pre conditions - Step 03: Close the 'Incoming Message' popup if show");
        adminManageCustomerPage.closeTheIncomingMessagePopupAdminPage(driver);

        log.info("Pre conditions - Step 04: Verify the 'Manage Customers' title is displayed");
        verifyTrue(adminManageCustomerPage.isManageCustomersTitleDisplayed());

    }

    @Test
    public void TC_01_Invoice(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_01_Invoice");
        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 01: Hover to 'Sales' link at header");
        adminManageCustomerPage.hoverToDynamicHeaderLinkByNameAdminPage(driver, "Sales");

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 02: Click to 'Orders' link at header");
        adminManageCustomerPage.clickToDynamicHeaderLinkByNameAdminPage(driver, "Orders");
        adminManageOrderPage = PageGeneratorManager.getAdminManageOrderPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 03: Select to 'Filter status' dropdown with value 'Canceled'");
        adminManageOrderPage.selectToDefaultDropdownById(driver, "sales_order_grid_filter_status", "Canceled");

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 04: Click to 'Search' button'");
        adminManageOrderPage.clickToSearchButtonAdminPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 05: Select to checkbox row of the first order");
        adminManageOrderPage.selectToFirstCheckboxOrderRow();

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 06: Select to 'Action' dropdown with value 'Print Invoices'");
        adminManageOrderPage.selectToDefaultDropdownById(driver, "sales_order_grid_massaction-select", "Print Invoices");

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 07: Click to 'Submit' button");
        adminManageOrderPage.clickToSubmitButtonAdminPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 08: Verify the error message 'There are no printable documents related to selected orders.' is displayed");
        verifyTrue(adminManageOrderPage.isMessageDisplayedAboveHeaderAdminPage(driver, "There are no printable documents related to selected orders."));

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 09: Select to 'Filter status' dropdown with value 'Complete'");
        adminManageOrderPage.selectToDefaultDropdownById(driver, "sales_order_grid_filter_status", "Complete");

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 10: Click to 'Search' button'");
        adminManageOrderPage.clickToSearchButtonAdminPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 11: Select to checkbox row of the first order");
        adminManageOrderPage.selectToFirstCheckboxOrderRow();

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 12: Select to 'Action' dropdown with value 'Print Invoices'");
        adminManageOrderPage.selectToDefaultDropdownById(driver, "sales_order_grid_massaction-select", "Print Invoices");

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 13: Click to 'Submit' button");
        adminManageOrderPage.clickToSubmitButtonAdminPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 14: Verify the invoice is downloaded");
        verifyTrue(adminManageOrderPage.isInvoicePDFDownloaded(fileNameInvoice));
    }

    @Test
    public void TC_02_Review_Product(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_02_Review_Product");
        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 01: Open EndUser Url Site");
        adminManageCustomerPage.openPageUrl(driver, endUserUrl);
        userHomePage = PageGeneratorManager.getUserHomePage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 02: Click to 'TV' link at the header navigation");
        userProductCategoryPage = userHomePage.clickToTVLinkAtHeaderNav(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 03: Open Product Details page of the product: " + productReview);
        userProductDetailPage = userProductCategoryPage.openProductDetailPageByProductName(productReview);

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 04: Click to 'Add Your Review' link");
        userProductReviewPage = userProductDetailPage.clickToAddYourReviewLink();

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 05: Enter to 'Thought' Textarea with value: " + thoughtReview);
        userProductReviewPage.enterToThoughtTextarea(thoughtReview);

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 06: Enter to 'Your Review' with value: " + summaryReview);
        userProductReviewPage.inputToTextboxById(driver, "summary_field", summaryReview);

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 07: Enter to 'Your nickname' with value" + nicknameReview);
        userProductReviewPage.inputToTextboxById(driver, "nickname_field", nicknameReview);

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 08: Check to quality radio of value: " + qualityRateReview + "/5");
        userProductReviewPage.checkToQualityRadio(qualityRateReview);

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 09: Click to 'Submit Review' button");
        userProductReviewPage.clickToSubmitReviewButton();

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 10: Verify the 'Review accepted' message is displayed");
        verifyTrue(userProductReviewPage.isMessageUnderBreadcrumbDisplayed("Your review has been accepted for moderation."));

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 11: Open Admin Url Site");
        userProductReviewPage.openPageUrl(driver, adminUrl);
        adminManageCustomerPage = PageGeneratorManager.getAdminManageCustomerPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 12: Hover to 'Catalog' link at header");
        adminManageCustomerPage.hoverToDynamicHeaderLinkByNameAdminPage(driver, "Catalog");

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 13: Hover to 'Reviews and Ratings' link at header");
        adminManageCustomerPage.hoverToDynamicHeaderLinkByNameAdminPage(driver, "Reviews and Ratings");

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 14: Hover to 'Customer Reviews' link at header");
        adminManageCustomerPage.hoverToDynamicHeaderLinkByNameAdminPage(driver, "Customer Reviews");

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 15: Click to 'Pending Reviews' link at header");
        adminManageCustomerPage.clickToDynamicHeaderLinkByNameAdminPage(driver, "Pending Reviews");
        adminManageReviewPage = PageGeneratorManager.getAdminManageReviewPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 16: Verify the review is displayed at Admin Reviews Page");
        verifyTrue(adminManageReviewPage.isTitleDisplayedByNicknameGridTable(nicknameReview, summaryReview));
        verifyTrue(adminManageReviewPage.isReviewDisplayedByNicknameGridTable(nicknameReview, thoughtReview));


        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 17: Click Sort 'id' cell ascending");
        adminManageReviewPage.clickToSortDESCByTitle(driver, "ID");

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 18: Click to 'Review' row by nickname: " + nicknameReview);
        adminReviewDetailPage = adminManageReviewPage.clickToEditLinkByNickname(nicknameReview);

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 19: Select to 'Status' dropdown with value 'Approved'");
        adminReviewDetailPage.selectToDefaultDropdownById(driver, "status_id", "Approved");

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 20: CLick to 'Save Review' button");
        adminManageReviewPage = adminReviewDetailPage.clickToSaveReviewButton();

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 21: Verify the message 'The review has been saved.' is displayed above 'Pending Reviews' title");
        verifyTrue(adminManageReviewPage.isMessageDisplayedAboveHeaderAdminPage(driver, "The review has been saved."));

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 22: Open EndUser Url Site");
        adminManageReviewPage.openPageUrl(driver, endUserUrl);
        userHomePage = PageGeneratorManager.getUserHomePage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 23: Click to 'TV' link at the header navigation");
        userProductCategoryPage = userHomePage.clickToTVLinkAtHeaderNav(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 24: Open Product Details page of the product: " + productReview);
        userProductDetailPage = userProductCategoryPage.openProductDetailPageByProductName(productReview);

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 25: Click to the number of 'Reviews' link");
        userProductDetailPage.clickToQuantityReviewsLink();

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 26: Verify the review is displayed at box reviews");
        verifyTrue(userProductDetailPage.isSummaryReviewDisplayedByNickname(nicknameReview, summaryReview));
        verifyTrue(userProductDetailPage.isThoughtReviewDisplayedByNickname(nicknameReview, thoughtReview));
        verifyTrue(userProductDetailPage.isRatingReviewDisplayedByNickname(nicknameReview, qualityRateReview));
    }

    @Test
    public void TC_03_Checkbox_Functionality(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_03_Checkbox_Functionality");
        ExtentTestManager.getTest().log(Status.INFO, "Checkbox - Step 01: Open Admin URL Site");
        userProductDetailPage.openPageUrl(driver, adminUrl);
        adminManageCustomerPage = PageGeneratorManager.getAdminManageCustomerPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Checkbox - Step 02: Hover to 'Sales' link at header");
        adminManageCustomerPage.hoverToDynamicHeaderLinkByNameAdminPage(driver, "Sales");

        ExtentTestManager.getTest().log(Status.INFO, "Checkbox - Step 03: Click to 'Orders' link at header");
        adminManageCustomerPage.clickToDynamicHeaderLinkByNameAdminPage(driver, "Orders");
        adminManageOrderPage = PageGeneratorManager.getAdminManageOrderPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Checkbox - Step 04: Click to 'Select Visible' link");
        adminManageOrderPage.clickToSelectVisibleLink();

        ExtentTestManager.getTest().log(Status.INFO, "Checkbox - Step 05: Verify the current item selected is '1'");
        verifyEquals(adminManageOrderPage.getCurrentItemSelected(), 1);

        ExtentTestManager.getTest().log(Status.INFO, "Checkbox - Step 06: Verify the checkbox rows are selected with quantity '1'");
        verifyEquals(adminManageOrderPage.getQuantityItemSelectedCheckboxAdminPage(driver), 1);

        ExtentTestManager.getTest().log(Status.INFO, "Checkbox - Step 07: Click to 'Unselect Visible' link");
        adminManageOrderPage.clickToUnSelectVisibleLink();

        ExtentTestManager.getTest().log(Status.INFO, "Checkbox - Step 08: Verify the current item selected is '0'");
        verifyEquals(adminManageOrderPage.getCurrentItemSelected(), 0);

        ExtentTestManager.getTest().log(Status.INFO, "Checkbox - Step 09: Verify no checkbox rows are selected");
        verifyEquals(adminManageOrderPage.getQuantityItemSelectedCheckboxAdminPage(driver), 0);

    }


    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }

    private WebDriver driver;
    private String endUserUrl, adminUrl;
    private String fileNameInvoice, productReview, thoughtReview, summaryReview, nicknameReview, qualityRateReview;
    private AdminManageCustomerPO adminManageCustomerPage;
    private AdminManageOrderPO adminManageOrderPage;
    private UserHomePO userHomePage;
    private UserProductCategoryPO userProductCategoryPage;
    private UserProductDetailPO userProductDetailPage;
    private UserProductReviewPO userProductReviewPage;
    private AdminManageReviewPO adminManageReviewPage;
    private AdminReviewDetailPO adminReviewDetailPage;


}
