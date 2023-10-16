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
import pageObjects.liveGuru.admin.AdminManageCustomerPO;
import pageObjects.liveGuru.admin.AdminManageReviewPO;
import pageObjects.liveGuru.user.*;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;
import utilities.Enviroment;

import java.lang.reflect.Method;

public class Admin_01_CRUD_User extends BaseTest {

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        String environmentName = System.getProperty("environment");
        ConfigFactory.setProperty("env", environmentName);
        Enviroment env = ConfigFactory.create(Enviroment.class);

        data = DataHelper.getDataHelper();
        firstName = data.getFirstName();
        lastName = data.getLastName();
        email = data.getEmail();
        password = "123456";
        confirmPassword = "123456";
        fullName = String.format("%s %s", firstName, lastName);

        firstNameEdit = firstName + "edit";
        lastNameEdit = lastName + "edit";
        emailEdit = "edit" + email;

        productReview = "Samsung LCD";
        thoughtReview = "This product is goood,Can be used for a long time";
        summaryReview = "Good";
        nicknameReview = "Geni";
        qualityRateReview = "3";

        endUserUrl = env.endUserUrl();
        adminUrl = env.adminUrl();

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
    public void CRUD_01_Create_Account(Method method) {
        ExtentTestManager.startTest(method.getName(), "Admin_01_Create_Account");
        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 01: Open EndUser site");
        adminManageCustomerPage.openPageUrl(driver, endUserUrl);
        userHomePage = PageGeneratorManager.getUserHomePage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 02: Click to 'My Account' link at the footer");
        userHomePage.clickToDynamicFooterLinkByText(driver, "My Account");
        userLoginPage = PageGeneratorManager.getUserLoginPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 03: Click to 'Create an Account' button");
        userRegisterPage = userLoginPage.clickToCreateAnAccountButton();

        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 04: Input to first name textbox with value: " + firstName);
        userRegisterPage.inputToTextboxById(driver, "firstname", firstName);

        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 05: Input to Last Name textbox with value: " + lastName);
        userRegisterPage.inputToTextboxById(driver, "lastname", lastName);

        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 06: Input to email textbox with value: " + email);
        userRegisterPage.inputToTextboxById(driver, "email_address", email);

        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 07: Input to password textbox with value: " + password);
        userRegisterPage.inputToTextboxById(driver, "password", password);

        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 08: Input to 'confirm password' textbox with value: " + confirmPassword);
        userRegisterPage.inputToTextboxById(driver, "confirmation", confirmPassword);

        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 09: Click to 'Register' button");
        userAccountPage = userRegisterPage.clickToRegisterButton();

        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 10: Verify the 'register successfully' message is displayed at the mainPage");
        verifyEquals(userAccountPage.getCurrentMessageUnderPageTitle(), "Thank you for registering with Main Website Store.");

        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 11: Open Admin Url Site");
        userAccountPage.openPageUrl(driver, adminUrl);
        adminManageCustomerPage = PageGeneratorManager.getAdminManageCustomerPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 12: Verify the account has been shown at the admin page");
        verifyTrue(adminManageCustomerPage.isEmailDisplayedAtEmailColumnCustomerTable(email));
        verifyTrue(adminManageCustomerPage.isFullNameDisplayedAtNameColumnCustomerTableByEmail(email, fullName));

    }

    @Test
    public void CRUD_02_Update_Account(Method method) {
        ExtentTestManager.startTest(method.getName(), "Admin_02_Update_Account");
        ExtentTestManager.getTest().log(Status.INFO, "Update Account - Step 01: Open EndUser site");
        adminManageCustomerPage.openPageUrl(driver, endUserUrl);
        userHomePage = PageGeneratorManager.getUserHomePage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Update Account - Step 02: Click to 'Account' header at End user site");
        userHomePage.clickToAccountLinkHeader(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Update Account - Step 03: Click to 'My Account' link at Account header");
        userAccountPage = userHomePage.clickToMyAccountLinkAtAccountLinkHeader(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Update Account - Step 04: Click to 'Account Information' at the left sidebar");
        userAccountPage.clickToDynamicLinkOnTheLeftSidebar(driver, "Account Information");

        ExtentTestManager.getTest().log(Status.INFO, "Update Account - Step 05: Enter to 'First Name' textbox with value: " + firstNameEdit);
        userAccountPage.inputToTextboxById(driver, "firstname", firstNameEdit);

        ExtentTestManager.getTest().log(Status.INFO, "Update Account - Step 06: Enter to 'Last Name' textbox with value: " + lastNameEdit);
        userAccountPage.inputToTextboxById(driver, "lastname", lastNameEdit);

        ExtentTestManager.getTest().log(Status.INFO, "Update Account - Step 07: Enter to 'Email' textbox with value: " + emailEdit);
        userAccountPage.inputToTextboxById(driver, "email", emailEdit);

        ExtentTestManager.getTest().log(Status.INFO, "Update Account - Step 08: Enter to 'Current password' textbox with value: " + password);
        userAccountPage.inputToTextboxById(driver, "current_password", password);

        ExtentTestManager.getTest().log(Status.INFO, "Update Account - Step 09: Click to 'Save' button");
        userAccountPage.clickToSaveButton();

        ExtentTestManager.getTest().log(Status.INFO, "Update Account - Step 10: Verify the 'Account information has been saved' message is displayed");
        verifyEquals(userAccountPage.getCurrentMessageUnderPageTitle(), "The account information has been saved.");

        ExtentTestManager.getTest().log(Status.INFO, "Update Account - Step 11: Open Admin Url Site");
        userAccountPage.openPageUrl(driver, adminUrl);
        adminManageCustomerPage = PageGeneratorManager.getAdminManageCustomerPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Update Account - Step 12: Verify the account has been updated at the admin page");
        verifyTrue(adminManageCustomerPage.isEmailDisplayedAtEmailColumnCustomerTable(emailEdit));
        verifyTrue(adminManageCustomerPage.isFullNameDisplayedAtNameColumnCustomerTableByEmail(emailEdit, String.format("%s %s", firstNameEdit, lastNameEdit)));

    }

    @Test
    public void CRUD_03_Add_Review(Method method) {
        ExtentTestManager.startTest(method.getName(), "Admin_03_Add_Review");
        ExtentTestManager.getTest().log(Status.INFO, "Add Review - Step 01: Open EndUser Url Site");
        adminManageCustomerPage.openPageUrl(driver, endUserUrl);
        userHomePage = PageGeneratorManager.getUserHomePage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Add Review - Step 02: Click to 'TV' link at the header navigation");
        userProductCategoryPage = userHomePage.clickToTVLinkAtHeaderNav(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Add Review - Step 03: Open Product Details page of the product: " + productReview);
        userProductDetailPage = userProductCategoryPage.openProductDetailPageByProductName(productReview);

        ExtentTestManager.getTest().log(Status.INFO, "Add Review - Step 04: Click to 'Add Your Review' link");
        userProductReviewPage = userProductDetailPage.clickToAddYourReviewLink();

        ExtentTestManager.getTest().log(Status.INFO, "Add Review - Step 05: Enter to 'Thought' Textarea with value: " + thoughtReview);
        userProductReviewPage.enterToThoughtTextarea(thoughtReview);

        ExtentTestManager.getTest().log(Status.INFO, "Add Review - Step 06: Enter to 'Your Review' with value: " + summaryReview);
        userProductReviewPage.inputToTextboxById(driver, "summary_field", summaryReview);

        ExtentTestManager.getTest().log(Status.INFO, "Add Review - Step 07: Enter to 'Your nickname' with value" + nicknameReview);
        userProductReviewPage.inputToTextboxById(driver, "nickname_field", nicknameReview);

        ExtentTestManager.getTest().log(Status.INFO, "Add Review - Step 08: Check to quality radio of value: " + qualityRateReview + "/5");
        userProductReviewPage.checkToQualityRadio(qualityRateReview);

        ExtentTestManager.getTest().log(Status.INFO, "Add Review - Step 09: Click to 'Submit Review' button");
        userProductReviewPage.clickToSubmitReviewButton();

        ExtentTestManager.getTest().log(Status.INFO, "Add Review - Step 10: Verify the 'Review accepted' message is displayed");
        verifyTrue(userProductReviewPage.isMessageUnderBreadcrumbDisplayed("Your review has been accepted for moderation."));

        ExtentTestManager.getTest().log(Status.INFO, "Add Review - Step 11: Open Admin Url Site");
        userProductReviewPage.openPageUrl(driver, adminUrl);
        adminManageCustomerPage = PageGeneratorManager.getAdminManageCustomerPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Add Review - Step 12: Hover to 'Catalog' link at header");
        adminManageCustomerPage.hoverToDynamicHeaderLinkByNameAdminPage(driver, "Catalog");

        ExtentTestManager.getTest().log(Status.INFO, "Add Review - Step 13: Hover to 'Reviews and Ratings' link at header");
        adminManageCustomerPage.hoverToDynamicHeaderLinkByNameAdminPage(driver, "Reviews and Ratings");

        ExtentTestManager.getTest().log(Status.INFO, "Add Review - Step 14: Hover to 'Customer Reviews' link at header");
        adminManageCustomerPage.hoverToDynamicHeaderLinkByNameAdminPage(driver, "Customer Reviews");

        ExtentTestManager.getTest().log(Status.INFO, "Add Review - Step 15: Click to 'All Reviews' link at header");
        adminManageCustomerPage.clickToDynamicHeaderLinkByNameAdminPage(driver, "All Reviews");
        adminCustomerReviewPage = PageGeneratorManager.getAdminManageReviewPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Add Review - Step 16: Verify the review is displayed at Admin Reviews Page");
        verifyTrue(adminCustomerReviewPage.isTitleDisplayedByNicknameGridTable(nicknameReview, summaryReview));
        verifyTrue(adminCustomerReviewPage.isReviewDisplayedByNicknameGridTable(nicknameReview, thoughtReview));


    }

    @Test
    public void CRUD_04_Delete_Account(Method method) {
        ExtentTestManager.startTest(method.getName(), "Admin_04_Delete_Account");
        ExtentTestManager.getTest().log(Status.INFO, "Delete Account - Step 01: Hover to 'Customer' link at header");
        adminCustomerReviewPage.hoverToDynamicHeaderLinkByNameAdminPage(driver, "Customers");

        ExtentTestManager.getTest().log(Status.INFO, "Delete Account - Step 02: Click to 'Manage Customers' link at header");
        adminCustomerReviewPage.clickToDynamicHeaderLinkByNameAdminPage(driver, "Manage Customers");
        adminManageCustomerPage = PageGeneratorManager.getAdminManageCustomerPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Delete Account - Step 03: Select to 'Customer' checkbox by email: " + emailEdit);
        adminManageCustomerPage.checkToCheckboxAtCustomerRowByEmail(emailEdit);

        ExtentTestManager.getTest().log(Status.INFO, "Delete Account - Step 04: Verify the current items selected' text is '1'");
        verifyEquals(adminManageCustomerPage.getCurrentItemSelected(), 1);

        ExtentTestManager.getTest().log(Status.INFO, "Delete Account - Step 05: Select to 'Action' dropdown with value 'Delete'");
        adminManageCustomerPage.selectToDefaultDropdownById(driver, "customerGrid_massaction-select", "Delete");

        ExtentTestManager.getTest().log(Status.INFO, "Delete Account - Step 06: Click to 'Submit' button");
        adminManageCustomerPage.clickToSubmitButtonAdminPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Delete Account - Step 07: Accept the 'Delete' alert");
        adminManageCustomerPage.acceptTheDeleteAlert();

        ExtentTestManager.getTest().log(Status.INFO, "Delete Account - Step 08: Verify the 'record were deleted' message is displayed");
        verifyTrue(adminManageCustomerPage.isMessageDisplayedAboveHeaderAdminPage(driver, "Total of 1 record(s) were deleted."));

        ExtentTestManager.getTest().log(Status.INFO, "Delete Account - Step 09: Verify the account is undisplayed at Customer Table");
        verifyTrue(adminManageCustomerPage.isFullNameUndisplayedAtNameColumnCustomerTableByEmail(emailEdit, String.format("%s %s", firstNameEdit, lastNameEdit)));

        ExtentTestManager.getTest().log(Status.INFO, "Delete Account - Step 10: Open End User Site");
        adminManageCustomerPage.openPageUrl(driver, endUserUrl);
        userHomePage = PageGeneratorManager.getUserHomePage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Delete Account - Step 11: Navigate to User Login Page");
        userHomePage.clickToDynamicFooterLinkByText(driver, "My Account");
        userLoginPage = PageGeneratorManager.getUserLoginPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Delete Account - Step 12: Login with the previous account with email: " + emailEdit + " and password: " + password);
        userLoginPage.inputToTextboxById(driver, "email", emailEdit);
        userLoginPage.inputToTextboxById(driver, "pass", password);
        userLoginPage.clickToLoginButton();

        ExtentTestManager.getTest().log(Status.INFO, "Delete Account - Step 13: Verify the error message 'Invalid login or password.' is displayed");
        verifyEquals(userLoginPage.getErrorMessageUnderPageTitle(), "Invalid login or password.");
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }

    private WebDriver driver;
    private String endUserUrl, adminUrl;
    private DataHelper data;

    private String email, password, firstName, lastName, fullName, confirmPassword;
    private String firstNameEdit, lastNameEdit, emailEdit;
    private String qualityRateReview, nicknameReview, summaryReview, thoughtReview, productReview;

    private AdminManageCustomerPO adminManageCustomerPage;
    private UserHomePO userHomePage;
    private UserLoginPO userLoginPage;
    private UserRegisterPO userRegisterPage;
    private UserAccountPO userAccountPage;
    private AdminManageReviewPO adminCustomerReviewPage;
    private UserProductCategoryPO userProductCategoryPage;
    private UserProductReviewPO userProductReviewPage;
    private UserProductDetailPO userProductDetailPage;


}
