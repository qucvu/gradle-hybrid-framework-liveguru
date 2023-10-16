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
import pageObjects.liveGuru.user.UserAdvancedSearchPO;
import pageObjects.liveGuru.user.UserHomePO;
import pageObjects.liveGuru.user.UserProductCategoryPO;
import reportConfig.ExtentTestManager;
import utilities.Enviroment;

import java.lang.reflect.Method;

public class User_04_Search extends BaseTest {

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        minLowPriceRange = 0;
        maxLowPriceRange = 150;
        minHighPriceRange = 151;
        maxHighPriceRange = 1000;
        String environmentName = System.getProperty("environment");
        ConfigFactory.setProperty("env", environmentName);
        Enviroment env = ConfigFactory.create(Enviroment.class);

        log.info("Pre conditions - Step 01: Open EndUser LiveGuru Site");
        driver = getBrowserDriver(browserName, env.endUserUrl());
        homePage = PageGeneratorManager.getUserHomePage(driver);

        log.info("Pre conditions - Step 02: Click to 'Advanced Search' link at the footer");
        homePage.clickToDynamicFooterLinkByText(driver, "Advanced Search");
        advancedSearchPage = PageGeneratorManager.getUserAdvancedSearchPage(driver);
    }


    @Test
    public void Search_01_Search_Range_0_To_150(Method method) {
        ExtentTestManager.startTest(method.getName(), "Search_01_Search_Range_0_To_150");
        ExtentTestManager.getTest().log(Status.INFO, "Search Low Range - Step 01: Enter to 'From Price' with value: " + minLowPriceRange);
        advancedSearchPage.inputToTextboxById(driver, "price", String.format("%.0f", minLowPriceRange));

        ExtentTestManager.getTest().log(Status.INFO, "Search Low Range - Step 02: Enter to 'From Price' with value: " + maxLowPriceRange);
        advancedSearchPage.inputToTextboxById(driver, "price_to", String.format("%.0f", maxLowPriceRange));

        ExtentTestManager.getTest().log(Status.INFO, "Search Low Range - Step 03: Click to 'Search' button");
        productCategoryPage = advancedSearchPage.clickToSearchButton();

        ExtentTestManager.getTest().log(Status.INFO, "Search Low Range - Step 04: Verify all product are displayed with the price from " + minLowPriceRange + " to " + maxLowPriceRange);
        verifyTrue(productCategoryPage.areAllProductDisplayedWithinPriceRange(minLowPriceRange, maxLowPriceRange));

        ExtentTestManager.getTest().log(Status.INFO, "Search Low Range - Step 05: Crawl data search into the 'dataRecord' folder");
        productCategoryPage.crawlProductDataByPriceRange(minLowPriceRange, maxLowPriceRange);
    }

    @Test
    public void Search_02_Search_Range_151_To_1000(Method method) {
        ExtentTestManager.startTest(method.getName(), "Search_02_Search_Range_151_To_1000");
        ExtentTestManager.getTest().log(Status.INFO, "Search High Range - Step 01: Click to 'Advanced Search' link at the footer");
        homePage.clickToDynamicFooterLinkByText(driver, "Advanced Search");
        advancedSearchPage = PageGeneratorManager.getUserAdvancedSearchPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Search High Range - Step 02: Enter to 'From Price' with value: " + minHighPriceRange);
        advancedSearchPage.inputToTextboxById(driver, "price", String.format("%.0f", minHighPriceRange));

        ExtentTestManager.getTest().log(Status.INFO, "Search High Range - Step 03: Enter to 'From Price' with value: " + maxHighPriceRange);
        advancedSearchPage.inputToTextboxById(driver, "price_to", String.format("%.0f", maxHighPriceRange));

        ExtentTestManager.getTest().log(Status.INFO, "Search High Range - Step 04: Click to 'Search' button");
        productCategoryPage = advancedSearchPage.clickToSearchButton();

        ExtentTestManager.getTest().log(Status.INFO, "Search High Range - Step 05: Verify all product are displayed with the price from " + minLowPriceRange + " to " + minHighPriceRange);
        verifyTrue(productCategoryPage.areAllProductDisplayedWithinPriceRange(minHighPriceRange, maxHighPriceRange));

        ExtentTestManager.getTest().log(Status.INFO, "Search High Range - Step 06: Crawl data search into the 'dataRecord' folder");
        productCategoryPage.crawlProductDataByPriceRange(minHighPriceRange, maxHighPriceRange);
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }

    private WebDriver driver;
    private UserHomePO homePage;
    private UserAdvancedSearchPO advancedSearchPage;
    private UserProductCategoryPO productCategoryPage;
    private float minLowPriceRange, maxLowPriceRange;
    private float minHighPriceRange, maxHighPriceRange;
}
