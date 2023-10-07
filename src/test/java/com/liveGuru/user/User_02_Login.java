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
import reportConfig.ExtentTestManager;
import utilities.Enviroment;

import java.lang.reflect.Method;

public class User_02_Login extends BaseTest {

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        email = User_01_Register.email;
        password = User_01_Register.password;
        fullName = String.format("%s %s", User_01_Register.firstName, User_01_Register.lastName);
        String environmentName = System.getProperty("environment");
        ConfigFactory.setProperty("env", environmentName);
        Enviroment env = ConfigFactory.create(Enviroment.class);
        log.info("Pre conditions - Step 01: Open EndUser LiveGuru site");
        driver = getBrowserDriver(browserName, env.endUserUrl());
        homePage = PageGeneratorManager.getUserHomePage(driver);

    }


    @Test
    public void Login_01_Login_Success(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login_01_Login_Success");
        ExtentTestManager.getTest().log(Status.INFO, "Login Success - Step 01: Click to 'Account' link at the header");
        homePage.clickToAccountLinkHeader(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Login Success - Step 02: Click to 'Log In' link at the 'Account' header");
        loginPage = homePage.clickToLoginLinkAtAccountLinkHeader(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Login Success - Step 03: Enter to email textbox with value: " + email);
        loginPage.inputToTextboxById(driver, "email", email);

        ExtentTestManager.getTest().log(Status.INFO, "Login Success - Step 04: Enter to password textbox with value: " + password);
        loginPage.inputToTextboxById(driver, "pass", password);

        ExtentTestManager.getTest().log(Status.INFO, "Login Success - Step 05: Click to 'Login' button");
        myAccountPage = loginPage.clickToLoginButton();

        ExtentTestManager.getTest().log(Status.INFO, "Login Success - Step 06: Veifry the 'welcome' message is displayed for fullname: " + fullName + " at Dashboard Tab");
        verifyEquals(myAccountPage.getCurrentTabTitle(), "MY DASHBOARD");
        verifyTrue(myAccountPage.isWelcomeMessageIsDisplayedAtMyDashboard(fullName));

    }

    @Test

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }

    private WebDriver driver;
    private String email, password, fullName;
    private UserHomePO homePage;
    private UserLoginPO loginPage;
    private UserAccountPO myAccountPage;
}
