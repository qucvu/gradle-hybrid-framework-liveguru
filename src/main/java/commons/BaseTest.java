package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class BaseTest {
    private WebDriver driver;

    protected final Logger log;

    protected BaseTest() {
        log = LogManager.getLogger(getClass());
    }


    public WebDriver getDriverInstance() {
        return this.driver;
    }

    protected WebDriver getBrowserDriver(String browserName) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList) {
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
                System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.PROJECT_PATH + "\\browserLogs\\Firefox.log");
                System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

                driver = new FirefoxDriver();
                break;
            case H_FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("-headless");
                options.addArguments("window-size=1920x1080");
                driver = new FirefoxDriver(options);
                break;
            case CHROME:
                WebDriverManager.chromedriver().setup();
                System.setProperty("webdriver.chrome.args", "--disable-logging");
                System.setProperty("webdriver.chrome.silentOutput", "true");
                File file = new File(GlobalConstants.PROJECT_PATH + "\\BrowserExtensions\\extension_2_0_13_0.crx");
                ChromeOptions optionsChrome = new ChromeOptions();
                optionsChrome.setAcceptInsecureCerts(true);
                optionsChrome.addExtensions(file);
                optionsChrome.addArguments("--lang=vi");
                optionsChrome.addArguments("--disable-notifications");
                optionsChrome.setExperimentalOption("useAutomationExtension", false);
                optionsChrome.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                driver = new ChromeDriver(optionsChrome);
                break;
            case SAFARI:
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                break;
            case H_CHROME:
                WebDriverManager.chromedriver().setup();
                optionsChrome = new ChromeOptions();
                optionsChrome.addArguments("-headless");
                optionsChrome.addArguments("window-size=1920x1080");
                driver = new ChromeDriver(optionsChrome);
                break;
            case IE:
                WebDriverManager.iedriver().arch32().setup();
                driver = new InternetExplorerDriver();
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case COCCOC:
                WebDriverManager.chromedriver().driverVersion("109.0.5414.25").setup();
                ChromeOptions optionsCocCoc = new ChromeOptions();
                if (GlobalConstants.OS_NAME.contains("windows")) {
                    optionsCocCoc.setBinary("C:\\Program Files (x86)\\CocCoc\\Browser\\Application\\browser.exe");
                } else {
                    optionsCocCoc.setBinary("...");

                }
                driver = new ChromeDriver(optionsCocCoc);
                break;
            default:
                throw new RuntimeException("Browser name invalid");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);

        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String url) {
        switch (browserName) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
                System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.PROJECT_PATH + "\\browserLogs\\Firefox.log");
                FirefoxProfile profile = new FirefoxProfile();
                // Save files to a specific folder
                profile.setPreference("browser.download.folderList", 2);
                profile.setPreference("browser.download.dir", GlobalConstants.DOWNLOAD_FILE_FOLDER);
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
                // disable open files in a new tab
                profile.setPreference("browser.helperApps.alwaysAsk.force", false);
                profile.setPreference("browser.download.manager.showWhenStarting", false);
                profile.setPreference("browser.download.panel.shown", false);
                profile.setPreference("pdfjs.disabled", true);
                FirefoxOptions fireFoxoptions = new FirefoxOptions();
                fireFoxoptions.setProfile(profile);
                driver = new FirefoxDriver(fireFoxoptions);
                break;
            case "h_firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions hFirefoxOptions = new FirefoxOptions();
                hFirefoxOptions.addArguments("-headless");
                hFirefoxOptions.addArguments("window-size=1920x1080");
                driver = new FirefoxDriver(hFirefoxOptions);
                break;
            case "chrome":
//			WebDriverManager.chromedriver().setup();
                System.setProperty("webdriver.chrome.driver", GlobalConstants.PROJECT_PATH + "browserDrivers\\chromedriver_116.exe");
                driver = new ChromeDriver();
                break;
            case "h_chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions optionsChrome = new ChromeOptions();
                optionsChrome.addArguments("-headless");
                optionsChrome.addArguments("window-size=1920x1080");
                driver = new ChromeDriver(optionsChrome);
                break;
            case "ie":
                WebDriverManager.iedriver().arch32().setup();
                driver = new InternetExplorerDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "coccoc":
                WebDriverManager.chromedriver().driverVersion("109.0.5414.25").setup();
                ChromeOptions optionsCocCoc = new ChromeOptions();
                if (GlobalConstants.OS_NAME.contains("windows")) {
                    optionsCocCoc.setBinary("C:\\Program Files (x86)\\CocCoc\\Browser\\Application\\browser.exe");
                } else {
                    optionsCocCoc.setBinary("...");

                }
                driver = new ChromeDriver(optionsCocCoc);
                break;
            default:
                throw new RuntimeException("Browser name invalid");
        }
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);

        return driver;
    }


    protected int generateRandomNumber() {
        return new Random().nextInt(99999);
    }

    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
            log.info(" -------------------------- PASSED -------------------------- ");

        } catch (Throwable e) {
            log.info(" -------------------------- FAILED -------------------------- ");
            pass = false;

            // Add lỗi vào ReportNG
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            log.info(" -------------------------- FAILED -------------------------- ");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            pass = false;
            log.info(" -------------------------- FAILED -------------------------- ");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }


    protected void showBrowserConsoleLogs(WebDriver driver) {
        if (driver.toString().contains("chrome")) {
            log.info("chrome console log");
            LogEntries logs = driver.manage().logs().get("browser");
            List<LogEntry> logList = logs.getAll();
            for (LogEntry logging : logList) {
                log.info("--------" + logging.getLevel().toString() + "---------- \n" + logging.getMessage());
            }
        }
    }

    protected void closeBrowserDriver() {
        String cmd = null;
        try {
            String osName = GlobalConstants.OS_NAME;
            log.info("OS name = " + osName);

            String driverInstanceName = driver.toString().toLowerCase();
            log.info("Driver instance name = " + driverInstanceName);

            String browserDriverName;

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("internetexplorer")) {
                browserDriverName = "IEDriverServer";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else if (driverInstanceName.contains("opera")) {
                browserDriverName = "operadriver";
            } else {
                browserDriverName = "safaridriver";
            }

            if (osName.contains("Windows")) {
                // cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
                cmd = "taskkill /f /im " + browserDriverName + ".exe /T";

            } else {
                cmd = "pkill " + browserDriverName;
            }

            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected String getCurrentDateTimeUTC() {
        DateTime now = new DateTime(DateTimeZone.UTC);
        return now.toString();
    }


    protected String getCurrentDate() {
        DateTime nowUTC = new DateTime(DateTimeZone.UTC);
        int day = nowUTC.getDayOfMonth();
        if (day < 10) {
            return "0" + day;

        }
        return String.valueOf(day);
    }

    protected String getCurrentMonth() {
        DateTime now = new DateTime(DateTimeZone.UTC);
        return now.toString("MM");

    }


    protected String getCurrentYear() {
        DateTime now = new DateTime(DateTimeZone.UTC);
        return String.valueOf(now.getYear());
    }


}
