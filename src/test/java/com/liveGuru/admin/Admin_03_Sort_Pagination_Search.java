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
import pageObjects.liveGuru.admin.AdminManageInvoicePO;
import pageObjects.liveGuru.admin.AdminManageOrderPO;
import pageObjects.liveGuru.user.UserAccountPO;
import pageObjects.liveGuru.user.UserHomePO;
import pageObjects.liveGuru.user.UserLoginPO;
import pageObjects.liveGuru.user.UserRegisterPO;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;
import utilities.Enviroment;

import java.lang.reflect.Method;

public class Admin_03_Sort_Pagination_Search extends BaseTest {

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        String environmentName = System.getProperty("environment");
        ConfigFactory.setProperty("env", environmentName);
        Enviroment env = ConfigFactory.create(Enviroment.class);
        endUserUrl = env.endUserUrl();
        adminUrl = env.adminUrl();

        data = DataHelper.getDataHelper();
        firstName = data.getFirstName();
        lastName = data.getLastName();
        email = data.getEmail();
        password = "123456";
        confirmPassword = "123456";
        fullName = String.format("%s %s", firstName, lastName);

        log.info("Pre conditions - Step 01: Open Admin Url Site");
        driver = getBrowserDriver(browserName, adminUrl);
        adminManageCustomerPage = PageGeneratorManager.getAdminManageCustomerPage(driver);

        log.info("Pre conditions - Step 02: Set cookies and reload the page");
        adminManageCustomerPage.setCookies(driver, Common_01_Login_Admin.loggedCookies);
        adminManageCustomerPage.refreshCurrentPage(driver);

        log.info("Pre conditions - Step 03: Verify the 'Manage Customers' title is displayed");
        verifyTrue(adminManageCustomerPage.isManageCustomersTitleDisplayed());

    }

    @Test
    public void TC_01_Sort(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_01_Sort");
        ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 01: Hover to 'Sales' link at header");
        adminManageCustomerPage.hoverToDynamicHeaderLinkByNameAdminPage(driver, "Sales");

        ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 02: Click to 'Invoice' link at header");
        adminManageCustomerPage.clickToDynamicHeaderLinkByNameAdminPage(driver, "Invoices");
        adminManageInvoicePage = PageGeneratorManager.getAdminInvoicePage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 03: Click to 'Invoices' sort title ascending");
        adminManageInvoicePage.clickToSortASCByTitle(driver, "Invoice #");

        ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 04: Verify the column with 'invoice id' sort ascending");
        verifyTrue(adminManageInvoicePage.isDataNumberSortASCByTitleColumn(driver, "Invoice #"));

        ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 05: Click to 'Invoices' sort title descending");
        adminManageInvoicePage.clickToSortDESCByTitle(driver, "Invoice #");

        ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 06: Verify the column with 'invoice id' sort descending");
        verifyTrue(adminManageInvoicePage.isDataNumberSortDESCByTitleColumn(driver, "Invoice #"));

        ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 07: Click to 'Invoice Date' sort title ascending");
        adminManageInvoicePage.clickToSortASCByTitle(driver, "Invoice Date");

        ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 08: Verify the column with 'Invoice Date' sort ascending");
        verifyTrue(adminManageInvoicePage.isDataDateSortByTitleColumnAndOption(driver, "Invoice Date", "asc"));

        ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 09: Click to 'Invoices' sort title descending");
        adminManageInvoicePage.clickToSortDESCByTitle(driver, "Invoice Date");

        ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 10: Verify the column with 'Invoice Date' sort descending");
        verifyTrue(adminManageInvoicePage.isDataDateSortByTitleColumnAndOption(driver, "Invoice Date", "desc"));

        ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 11: Click to 'Bill to Name' sort title ascending");
        adminManageInvoicePage.clickToSortASCByTitle(driver, "Bill to Name");

        ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 12: Verify the column with 'Bill to Name' sort ascending");
        verifyTrue(adminManageInvoicePage.isDataStringSortASCByTitleColumn(driver, "Bill to Name"));

        ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 13: Click to 'Bill to Name' sort title descending");
        adminManageInvoicePage.clickToSortDESCByTitle(driver, "Bill to Name");

        ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 14: Verify the column with 'Bill to Name' sort descending");
        verifyTrue(adminManageInvoicePage.isDataStringSortDESCByTitleColumn(driver, "Bill to Name"));

        ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 11: Click to 'Amount' sort title ascending");
        adminManageInvoicePage.clickToSortASCByTitle(driver, "Amount");

        ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 12: Verify the column with 'Amount' sort ascending");
        verifyTrue(adminManageInvoicePage.isDataAmountColumnSortByOption("asc"));

        ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 13: Click to 'Amount' sort title descending");
        adminManageInvoicePage.clickToSortDESCByTitle(driver, "Amount");

        ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 14: Verify the column with 'Amount' sort descending");
        verifyTrue(adminManageInvoicePage.isDataAmountColumnSortByOption("desc"));
    }

    @Test
    public void TC_02_Pagination(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_02_Pagination");
        ExtentTestManager.getTest().log(Status.INFO, "Pagination - Step 01: Hover to 'Sales' link at header");
        adminManageCustomerPage.hoverToDynamicHeaderLinkByNameAdminPage(driver, "Sales");

        ExtentTestManager.getTest().log(Status.INFO, "Pagination - Step 02: Click to 'Orders' link at header");
        adminManageCustomerPage.clickToDynamicHeaderLinkByNameAdminPage(driver, "Orders");
        adminManageOrderPage = PageGeneratorManager.getAdminManageOrderPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Pagination - Step 03: Reset the filter status");
        adminManageOrderPage.selectToDefaultDropdownById(driver, "sales_order_grid_filter_status", "");
        adminManageOrderPage.clickToSearchButtonAdminPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Pagination - Step 04: Select to 'View per page' dropdown with value '20'");
        adminManageOrderPage.selectToViewPerPageDropdownAdminPage(driver, "20");

        ExtentTestManager.getTest().log(Status.INFO, "Pagination - Step 05: Verify the quantity item is '20'");
        verifyEquals(adminManageOrderPage.getQuantityItemRowDisplayedAdminPage(driver), 20);

        ExtentTestManager.getTest().log(Status.INFO, "Pagination - Step 06: Select to 'View per page' dropdown with value '30'");
        adminManageOrderPage.selectToViewPerPageDropdownAdminPage(driver, "30");

        ExtentTestManager.getTest().log(Status.INFO, "Pagination - Step 07: Verify the quantity item is '30'");
        verifyEquals(adminManageOrderPage.getQuantityItemRowDisplayedAdminPage(driver), 30);

        ExtentTestManager.getTest().log(Status.INFO, "Pagination - Step 08: Select to 'View per page' dropdown with value '50'");
        adminManageOrderPage.selectToViewPerPageDropdownAdminPage(driver, "50");

        ExtentTestManager.getTest().log(Status.INFO, "Pagination - Step 09: Verify the quantity item is '50'");
        verifyEquals(adminManageOrderPage.getQuantityItemRowDisplayedAdminPage(driver), 50);
    }

    @Test
    public void TC_03_Search_Functionality(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_03_Search_Functionality");
        ExtentTestManager.getTest().log(Status.INFO, "Search - Step 01: Open EndUser site");
        adminManageCustomerPage.openPageUrl(driver, endUserUrl);
        userHomePage = PageGeneratorManager.getUserHomePage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Search - Step 02: Click to 'My Account' link at the footer");
        userHomePage.clickToDynamicFooterLinkByText(driver, "My Account");
        userLoginPage = PageGeneratorManager.getUserLoginPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Search - Step 03: Click to 'Create an Account' button");
        userRegisterPage = userLoginPage.clickToCreateAnAccountButton();

        ExtentTestManager.getTest().log(Status.INFO, "Search - Step 04: Input to first name textbox with value: " + firstName);
        userRegisterPage.inputToTextboxById(driver, "firstname", firstName);

        ExtentTestManager.getTest().log(Status.INFO, "Search - Step 05: Input to Last Name textbox with value: " + lastName);
        userRegisterPage.inputToTextboxById(driver, "lastname", lastName);

        ExtentTestManager.getTest().log(Status.INFO, "Search - Step 06: Input to email textbox with value: " + email);
        userRegisterPage.inputToTextboxById(driver, "email_address", email);

        ExtentTestManager.getTest().log(Status.INFO, "Search - Step 07: Input to password textbox with value: " + password);
        userRegisterPage.inputToTextboxById(driver, "password", password);

        ExtentTestManager.getTest().log(Status.INFO, "Search - Step 08: Input to 'confirm password' textbox with value: " + confirmPassword);
        userRegisterPage.inputToTextboxById(driver, "confirmation", confirmPassword);

        ExtentTestManager.getTest().log(Status.INFO, "Search - Step 09: Click to 'Register' button");
        userAccountPage = userRegisterPage.clickToRegisterButton();

        ExtentTestManager.getTest().log(Status.INFO, "Search - Step 10: Verify the 'register successfully' message is displayed at the mainPage");
        verifyEquals(userAccountPage.getCurrentMessageUnderPageTitle(), "Thank you for registering with Main Website Store.");

        ExtentTestManager.getTest().log(Status.INFO, "Search - Step 11: Open Admin Url Site");
        userAccountPage.openPageUrl(driver, adminUrl);
        adminManageCustomerPage = PageGeneratorManager.getAdminManageCustomerPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Search - Step 11: Open Admin Url Site");
        userAccountPage.openPageUrl(driver, adminUrl);
        adminManageCustomerPage = PageGeneratorManager.getAdminManageCustomerPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Search - Step 12: Verify the account has been shown at the admin page");
        verifyTrue(adminManageCustomerPage.isEmailDisplayedAtEmailColumnCustomerTable(email));
        verifyTrue(adminManageCustomerPage.isFullNameDisplayedAtNameColumnCustomerTableByEmail(email, fullName));

        ExtentTestManager.getTest().log(Status.INFO, "Search - Step 13: Enter to 'Filter name' textbox with value: " + fullName);
        adminManageCustomerPage.inputToTextboxById(driver, "customerGrid_filter_name", fullName);

        ExtentTestManager.getTest().log(Status.INFO, "Search - Step 14: Enter to 'Filter email' textbox with value: " + email);
        adminManageCustomerPage.inputToTextboxById(driver, "customerGrid_filter_email", email);

        ExtentTestManager.getTest().log(Status.INFO, "Search - Step 15: Click to 'Search' button");
        adminManageCustomerPage.clickToSearchButtonAdminPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Search - Step 15: Click to 'Search' button");
        adminManageCustomerPage.clickToSearchButtonAdminPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Search - Step 16: Verify the quantity item row is '1'");
        verifyEquals(adminManageOrderPage.getQuantityItemRowDisplayedAdminPage(driver), 1);

        ExtentTestManager.getTest().log(Status.INFO, "Search - Step 17: Verify the account has been shown at the admin page");
        verifyTrue(adminManageCustomerPage.isEmailDisplayedAtEmailColumnCustomerTable(email));
        verifyTrue(adminManageCustomerPage.isFullNameDisplayedAtNameColumnCustomerTableByEmail(email, fullName));

    }


    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }

    private WebDriver driver;
    private String endUserUrl, adminUrl;
    private DataHelper data;

    private String email, password, firstName, lastName, fullName, confirmPassword;

    private AdminManageCustomerPO adminManageCustomerPage;
    private AdminManageOrderPO adminManageOrderPage;
    private AdminManageInvoicePO adminManageInvoicePage;
    private UserHomePO userHomePage;
    private UserLoginPO userLoginPage;
    private UserRegisterPO userRegisterPage;
    private UserAccountPO userAccountPage;

}
