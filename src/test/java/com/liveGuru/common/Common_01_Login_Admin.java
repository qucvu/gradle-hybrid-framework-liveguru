package com.liveGuru.common;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageObjects.liveGuru.admin.AdminLoginPO;
import pageObjects.liveGuru.admin.AdminManageCustomerPO;
import utilities.Enviroment;

import java.util.Set;

public class Common_01_Login_Admin extends BaseTest {

    @Parameters("browser")
    @BeforeTest
    public void beforeTest(String browserName) {
        userName = "user01";
        password = "guru99com";
        ConfigFactory.setProperty("env", "production");
        Enviroment env = ConfigFactory.create(Enviroment.class);

        log.info("Login - Step 01: Open Admin Url Site");
        driver = getBrowserDriver(browserName, env.adminUrl());
        adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

        log.info("Login - Step 02: Enter to User Name textbox with value: " + userName);
        adminLoginPage.inputToTextboxById(driver, "username", userName);

        log.info("Login - Step 03: Enter to Password textbox with value: " + password);
        adminLoginPage.inputToTextboxById(driver, "login", password);

        log.info("Login - Step 04: Click to 'Login' button");
        adminManageCustomerPage = adminLoginPage.clickToLoginButton();

        log.info("Login - Step 05: Close the 'InComing Message' popup");
        adminManageCustomerPage.closeTheIncomingMessagePopupAdminPage(driver);

        log.info("Login - Step 06: Verify the 'Manage Customers' title is displayed");
        verifyTrue(adminManageCustomerPage.isManageCustomersTitleDisplayed());

        log.info("Login - Step 07: Get all cookies from the current account");
        loggedCookies = adminManageCustomerPage.getAllCookies(driver);
        driver.quit();
    }

    private WebDriver driver;
    public static Set<Cookie> loggedCookies;
    private AdminLoginPO adminLoginPage;
    private AdminManageCustomerPO adminManageCustomerPage;
    private String userName, password;
}
