package com.liveGuru.user;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

public class Register extends BaseTest {
    private WebDriver driver;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        driver.get("https://google.com");

    }


    @Test
    public void Register_01_Register_Success(Method method) {
        ExtentTestManager.startTest(method.getName(), "Register_01_Register_Success");
        ExtentTestManager.getTest().log(Status.INFO, "Register Success - Step 01: ");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }
}
