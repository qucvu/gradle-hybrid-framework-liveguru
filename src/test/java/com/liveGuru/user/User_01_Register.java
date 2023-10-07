package com.liveGuru.user;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.liveGuru.user.UserAccountPO;
import pageObjects.liveGuru.user.UserHomePO;
import pageObjects.liveGuru.user.UserLoginPO;
import pageObjects.liveGuru.user.UserRegisterPO;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;
import utilities.Enviroment;

import java.lang.reflect.Method;

public class User_01_Register extends BaseTest {

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        data = DataHelper.getDataHelper();
        firstName = data.getFirstName();
        lastName = data.getLastName();
        email = data.getEmail();
        password = "123456";
        confirmPassword = "123456";

        String environmentName = System.getProperty("environment");
        ConfigFactory.setProperty("env", environmentName);
        Enviroment env = ConfigFactory.create(Enviroment.class);

        log.info("Pre conditions - Step 01: Open EndUser site");
        driver = getBrowserDriver(browserName, env.endUserUrl());
        homePage = PageGeneratorManager.getUserHomePage(driver);

        log.info("Pre conditions - Step 02: Click to 'My Account' link at the footer");
        homePage.clickToFooterLinkByText(driver, "My Account");
        loginPage = PageGeneratorManager.getUserLoginPage(driver);

        log.info("Pre conditions - Step 03: Click to 'Create an Account' button");
        registerPage = loginPage.clickToCreateAnAccountButton();

    }


    @Test
    public void Register_01_Register_Success(Method method) {
        ExtentTestManager.startTest(method.getName(), "Register_01_Register_Success");
        ExtentTestManager.getTest().log(Status.INFO, "Register Success - Step 01: Input to first name textbox with value: " + firstName);
        registerPage.inputToTextboxById(driver, "firstname", firstName);

        ExtentTestManager.getTest().log(Status.INFO, "Register Success - Step 02: Input to Last Name textbox with value: " + lastName);
        registerPage.inputToTextboxById(driver, "lastname", lastName);

        ExtentTestManager.getTest().log(Status.INFO, "Register Success - Step 03: Input to email textbox with value: " + email);
        registerPage.inputToTextboxById(driver, "email_address", email);

        ExtentTestManager.getTest().log(Status.INFO, "Register Success - Step 04: Input to password textbox with value: " + password);
        registerPage.inputToTextboxById(driver, "password", password);

        ExtentTestManager.getTest().log(Status.INFO, "Register Success - Step 05: Input to 'confirm password' textbox with value: " + confirmPassword);
        registerPage.inputToTextboxById(driver, "confirmation", confirmPassword);

        ExtentTestManager.getTest().log(Status.INFO, "Register Success - Step 06: Click to 'Register' button");
        myAccountPage = registerPage.clickToRegisterButton();

        ExtentTestManager.getTest().log(Status.INFO, "Register Success - Step 07: Verify the 'register successfully' message is displayed at the mainPage");
        verifyEquals(myAccountPage.getCurrentMessageUnderPageTitle(), "Thank you for registering with Main Website Store.");
    }

    @Test
    public void Register_02_Correct_Register_Data(Method method) {
        ExtentTestManager.startTest(method.getName(), "Register_02_Correct_Register_Data");
        ExtentTestManager.getTest().log(Status.INFO, "Correct register data - Step 01: Refresh the User My account Page");
        myAccountPage.refreshCurrentPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Correct register data - Step 02: Click to 'Account Information' link at the left sidebar");
        myAccountPage.clickToDynamicLinkOnTheLeftSidebar(driver, "Account Information");

        ExtentTestManager.getTest().log(Status.INFO, "Correct register data - Step 03: Verify the 'ACCOUNT INFORMATION' is the current navigate link");
        verifyTrue(myAccountPage.isCurrentActiveLinkAtAccountPageByText(driver, "Account Information"));

        ExtentTestManager.getTest().log(Status.INFO, "Correct register data - Step 03: Verify the first name textbox is displayed with value: " + firstName);
        verifyEquals(myAccountPage.getValueTextboxById(driver, "firstname"), firstName);

        ExtentTestManager.getTest().log(Status.INFO, "Correct register data - Step 04: Verify the last name textbox is displayed with value: " + lastName);
        verifyEquals(myAccountPage.getValueTextboxById(driver, "lastname"), lastName);

        ExtentTestManager.getTest().log(Status.INFO, "Correct register data - Step 03: Verify the email textbox is displayed with value: " + email);
        verifyEquals(myAccountPage.getValueTextboxById(driver, "email"), email);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }

    private WebDriver driver;
    private String confirmPassword;

    public static String email, password, firstName, lastName;
    private DataHelper data;
    private UserHomePO homePage;
    private UserLoginPO loginPage;
    private UserRegisterPO registerPage;
    private UserAccountPO myAccountPage;

}
