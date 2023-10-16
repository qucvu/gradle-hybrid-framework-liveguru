package commons;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.liveGuru.user.*;
import pageUIs.liveGuru.user.LiveGuruBasePageUI;
import pageUIs.liveGuru.user.UserAccountPageUI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class BasePage {
    private final long longTimeout = GlobalConstants.LONG_TIMEOUT;
    private final long shortTimeout = GlobalConstants.SHORT_TIMEOUT;

    public static BasePage getBasePageObject() {
        return new BasePage();
    }


    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public void setCookies(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(1);
    }

    protected String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    protected Alert waitForAlertPresence(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    protected void acceptAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    protected void cancelAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    protected String getAlertText(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    protected void sendKeyToAlert(WebDriver driver, String textValue) {
        waitForAlertPresence(driver).sendKeys(textValue);
    }

    protected String getWindowHandle(WebDriver driver) {
        return driver.getWindowHandle();
    }

    // 2 windows
    protected void switchToWindowById(WebDriver driver, String windowId) {
        Set<String> allWindowIds = driver.getWindowHandles();
        for (String id : allWindowIds) {
            if (!id.equals(windowId)) {
                driver.switchTo().window(id);
            }
        }
    }

    // more than 2
    protected void switchToWindowByTitle(WebDriver driver, String expectedTitlePage) {
        Set<String> allWindowIds = driver.getWindowHandles();
        for (String id : allWindowIds) {
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitlePage)) {
                break;
            }
        }
    }

    protected void closeOtherTabsWithoutParent(WebDriver driver, String parentId) {
        Set<String> allIds = driver.getWindowHandles();
        for (String id : allIds) {
            if (!id.equals(parentId)) {
                driver.switchTo().window(id).close();
            }
        }
        driver.switchTo().window(parentId);
    }

    // locatorType: id=/ css=/ xpath=/ name = /
    private By getByLocator(String locatorType) {
        By by;
        if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
            by = By.id(locatorType.substring(3));
        } else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=") || locatorType.startsWith("Class=")) {
            by = By.className(locatorType.substring(6));
        } else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=")) {
            by = By.name(locatorType.substring(5));

        } else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
            by = By.cssSelector(locatorType.substring(4));

        } else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("XPath=") || locatorType.startsWith("Xpath=")) {
            by = By.xpath(locatorType.substring(6));

        } else {
            throw new RuntimeException("The locator type is not supported!");
        }
        return by;
    }

    private String getDynamicXpath(String locatorType, String... dynamicValues) {
        // if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("XPath=") || locatorType.startsWith("Xpath=")) {
        // locatorType = String.format(locatorType, dynamicValues);
        // } else {
        // throw new RuntimeException("The locator type is only supported for the XPath locator");
        // }
        locatorType = String.format(locatorType, (Object[]) dynamicValues);
        return locatorType;

    }

    public WebElement getWebElement(WebDriver driver, String locatorType) {
        if (driver.toString().contains("internet explorer")) {
            sleepInSecond(3);
        }
        return driver.findElement(getByLocator(locatorType));
    }

    private WebElement getWebElement(WebDriver driver, String locatorType, String... dynamicValues) {
        return driver.findElement(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
    }

    public List<WebElement> getListElements(WebDriver driver, String locatorType) {
        return driver.findElements(getByLocator(locatorType));
    }

    protected List<WebElement> getListElements(WebDriver driver, String locatorType, String... dynamicValues) {
        return driver.findElements(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
    }

    protected void clickToElement(WebDriver driver, String locatorType) {
        if (driver.toString().contains("internet explorer")) {
            sleepInSecond(3);
            clickToElementByJS(driver, locatorType);
        } else {
            getWebElement(driver, locatorType).click();
        }

    }

    protected void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
        this.getWebElement(driver, locatorType, dynamicValues).click();
    }

    protected void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
        WebElement element = getWebElement(driver, locatorType);
        element.clear();
        element.sendKeys(textValue);
    }

    protected void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
        WebElement element = getWebElement(driver, locatorType, dynamicValues);
        element.clear();
        element.sendKeys(textValue);
    }

    protected void clearElement(WebDriver driver, String locatorType) {
        WebElement element = getWebElement(driver, locatorType);
        element.clear();
    }

    protected void clearElement(WebDriver driver, String locatorType, String... dynamicValues) {
        WebElement element = getWebElement(driver, locatorType, dynamicValues);
        element.clear();
    }


    public String getElementText(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).getText();
    }

    protected String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
        return getWebElement(driver, locatorType, dynamicValues).getText();
    }

    protected void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
        Select select = new Select(getWebElement(driver, locatorType));
        select.selectByVisibleText(textItem);
    }

    protected void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... dynamicValues) {
        Select select = new Select(getWebElement(driver, locatorType, dynamicValues));
        select.selectByVisibleText(textItem);
    }

    protected String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType) {
        Select select = new Select(getWebElement(driver, locatorType));
        return select.getFirstSelectedOption().getText();
    }

    protected String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType, String... dynamicValues) {
        Select select = new Select(getWebElement(driver, locatorType, dynamicValues));
        return select.getFirstSelectedOption().getText();
    }

    protected boolean isDropdownMultiple(WebDriver driver, String locatorType) {
        Select select = new Select(getWebElement(driver, locatorType));
        return select.isMultiple();
    }

    protected void selectItemDropdown(WebDriver driver, String parentXpath, String allItemXpath, String expectedTextItem) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        getWebElement(driver, parentXpath).click();
        sleepInSecond(1);
        List<WebElement> speedDropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(allItemXpath)));
        for (WebElement webElement : speedDropdownItems) {
            if (webElement.getText().trim().equals(expectedTextItem)) {
                webElement.click();
                break;
            }
        }
    }

    protected void enterAndSelectItemDropdown(WebDriver driver, String textBoxXpath, String allItemXpath, String expectedTextItem) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, textBoxXpath);
        element.clear();
        element.sendKeys(expectedTextItem);
        sleepInSecond(1);
        List<WebElement> speedDropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(allItemXpath)));
        for (WebElement webElement : speedDropdownItems) {
            if (webElement.getText().trim().equals(expectedTextItem)) {
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", webElement);
                webElement.click();
                break;
            }
        }
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected String getElementAttribute(WebDriver driver, String attributeName, String locatorType) {
        return getWebElement(driver, locatorType).getAttribute(attributeName);
    }

    protected String getElementAttribute(WebDriver driver, String attributeName, String locatorType, String... dynamicValues) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
    }

    protected String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
        return getWebElement(driver, locatorType).getCssValue(propertyName);
    }

    protected String getHexaColorFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    protected int getElementsSize(WebDriver driver, String locatorType) {
        return getListElements(driver, locatorType).size();
    }

    protected int getElementsSize(WebDriver driver, String locatorType, String... dynamicValues) {
        return getListElements(driver, locatorType, dynamicValues).size();
    }

    protected void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
        WebElement element = getWebElement(driver, locatorType);
        if (!element.isSelected()) {
            element.click();
        }
    }

    protected void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType, String... dynamicValues) {
        WebElement element = getWebElement(driver, locatorType, dynamicValues);
        if (!element.isSelected()) {
            element.click();
        }
    }

    protected void unCheckToDefaultCheckbox(WebDriver driver, String locatorType) {
        WebElement element = getWebElement(driver, locatorType);
        if (element.isSelected()) {
            element.click();
        }
    }

    protected void unCheckToDefaultCheckbox(WebDriver driver, String locatorType, String... dynamicValues) {
        WebElement element = getWebElement(driver, locatorType, dynamicValues);
        if (element.isSelected()) {
            element.click();
        }
    }

    protected boolean isElementDisplayed(WebDriver driver, String locatorType) {
        try {
            // displayed - có trong dom: true
            // undisplayed - có trong dom: trả về false
            return getWebElement(driver, locatorType).isDisplayed();
        } catch (NoSuchElementException e) {
            // undisplayed: k có trong dom --> Quá lâu
            return false;
        }
    }

    protected boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
        return getWebElement(driver, locatorType, dynamicValues).isDisplayed();
    }

    protected boolean isElementUndisplayed(WebDriver driver, String locatorType) {
        overrideImplicitTimeout(driver, shortTimeout);
        List<WebElement> elements = getListElements(driver, locatorType);
        overrideImplicitTimeout(driver, longTimeout);

        if (elements.size() == 0)
            return true;
        else if (elements.size() > 0 && !elements.get(0).isDisplayed())
            return true;
        return false;
    }

    protected boolean isElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
        overrideImplicitTimeout(driver, shortTimeout);
        List<WebElement> elements = getListElements(driver, locatorType, dynamicValues);
        overrideImplicitTimeout(driver, longTimeout);

        if (elements.size() == 0)
            return true;
        else if (elements.size() > 0 && !elements.get(0).isDisplayed())
            return true;
        return false;
    }

    protected boolean isElementEnabled(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).isEnabled();
    }

    protected boolean isElementSelected(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).isSelected();
    }

    protected boolean isElementSelected(WebDriver driver, String locatorType, String... dynamicValues) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isSelected();
    }

    protected void switchToFrameIframe(WebDriver driver, String locatorType) {
        driver.switchTo().frame(getWebElement(driver, locatorType));
    }

    protected void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    protected void hoverMouseToElement(WebDriver driver, String locatorType) {
        WebElement element = getWebElement(driver, locatorType);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    protected void hoverMouseToElement(WebDriver driver, String locatorType, String... dynamicValues) {
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    protected void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
        Actions actions = new Actions(driver);
        actions.sendKeys(getWebElement(driver, locatorType), key).build().perform();
    }

    public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
        Actions actions = new Actions(driver);
        actions.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).build().perform();
    }

    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    protected void highlightElement(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, locatorType);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    protected void highlightElement(WebDriver driver, String locatorType, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    protected void clickToElementByJS(WebDriver driver, String locatorType) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locatorType));
    }

    protected void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicValues) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));

    }


    protected void scrollToElement(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
    }

    protected void scrollToElement(WebDriver driver, String locatorType, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
    }


    protected String getElementValueByJS(WebDriver driver, String xpathLocator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        xpathLocator = xpathLocator.substring(6);
        return (String) jsExecutor.executeScript("$(document.evaluate(" + xpathLocator + ", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;).val");
    }

    protected void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
    }

    protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    protected WebElement getShadowDom(WebDriver driver, String locatorType) {
        return (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", getWebElement(driver, locatorType));
    }

    protected String getElementValidationMessage(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
    }

    protected boolean isImageLoaded(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
                getWebElement(driver, locator));
        return status;
    }

    protected boolean isImageLoaded(WebDriver driver, String locator, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
                getWebElement(driver, getDynamicXpath(locator, dynamicValues)));
        return status;
    }

    protected void waitForElementVisibility(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
    }

    protected void waitForElementVisibility(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    protected void waitForElementInvisibility(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    protected void waitForElementInvisibility(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));

    }

    /*
     * wait for element undisplayed in dom and not in dom
     */
    protected void waitForElementUnDisplayed(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
        overrideImplicitTimeout(driver, shortTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
        overrideImplicitTimeout(driver, longTimeout);

    }

    protected void waitForElementUnDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
        overrideImplicitTimeout(driver, shortTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
        overrideImplicitTimeout(driver, longTimeout);
    }

    protected void waitForAllElementVisibility(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));

    }

    protected void waitForAllElementVisibility(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    protected void waitForAllElementInVisibility(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListElements(driver, locatorType)));
    }

    protected void waitForAllElementInVisibility(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListElements(driver, locatorType, dynamicValues)));
    }

    protected void waitForElementClickable(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
    }

    private void overrideImplicitTimeout(WebDriver driver, long timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    protected void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    protected void waitForElementStaleness(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.stalenessOf(getWebElement(driver, locatorType)));
    }

    public void uploadFilesByFileName(WebDriver driver, String locatorType, String... fileNames) {
        String filePath = GlobalConstants.UPLOAD_FILE_FOLDER;
        String fullName = "";
        for (String fileName : fileNames) {
            fullName += filePath + fileName + "\n";
        }
        fullName = fullName.trim();
        getWebElement(driver, getDynamicXpath(locatorType)).sendKeys(fullName);

    }

    public boolean isDataStringSortAsc(WebDriver driver, String locator) {
        List<WebElement> elementList = getListElements(driver, locator);
        ArrayList<String> dataList = new ArrayList<String>();
        for (WebElement element : elementList) {
            dataList.add(element.getText().trim().toLowerCase());
        }
        List<String> sortList = new ArrayList<String>(dataList);
        Collections.sort(sortList);
        return sortList.equals(dataList);
    }

    public boolean isDataStringSortAsc(WebDriver driver, String locator, String... dynamicValues) {
        List<WebElement> elementList = getListElements(driver, locator, dynamicValues);
        ArrayList<String> dataList = new ArrayList<String>();
        for (WebElement element : elementList) {
            dataList.add(element.getText().trim().toLowerCase());
        }
        List<String> sortList = new ArrayList<String>(dataList);
        Collections.sort(sortList);
        return sortList.equals(dataList);
    }

    public boolean isDataStringSortDesc(WebDriver driver, String locator) {
        List<String> dataList = new ArrayList<String>();
        List<WebElement> elementList = getListElements(driver, locator);
        for (WebElement element : elementList) {
            dataList.add(element.getText().trim().toLowerCase());
        }
        List<String> sortList = new ArrayList<String>(dataList);

        Collections.sort(sortList);
        Collections.reverse(sortList);
        return sortList.equals(dataList);
    }

    public boolean isDataStringSortDesc(WebDriver driver, String locator, String... dynamicValues) {
        List<String> dataList = new ArrayList<String>();
        List<WebElement> elementList = getListElements(driver, locator, dynamicValues);
        for (WebElement element : elementList) {
            dataList.add(element.getText().trim().toLowerCase());
        }
        List<String> sortList = new ArrayList<String>(dataList);

        Collections.sort(sortList);
        Collections.reverse(sortList);
        return sortList.equals(dataList);
    }

    public boolean isDataNumberSortAsc(WebDriver driver, String locator) {
        List<WebElement> elementList = getListElements(driver, locator);
        ArrayList<Float> dataList = new ArrayList<Float>();
        for (WebElement element : elementList) {
            dataList.add(Float.parseFloat(element.getText().trim()));
        }
        List<Float> sortList = new ArrayList<Float>(dataList);
        Collections.sort(sortList);
        return sortList.equals(dataList);
    }

    public boolean isDataNumberSortAsc(WebDriver driver, String locator, String... dynamicValues) {
        List<WebElement> elementList = getListElements(driver, locator, dynamicValues);
        ArrayList<Long> dataList = new ArrayList<Long>();
        for (WebElement element : elementList) {
            dataList.add(Long.parseLong(element.getText().trim()));
        }
        List<Long> sortList = new ArrayList<Long>(dataList);
        Collections.sort(sortList);
        return sortList.equals(dataList);
    }

    public boolean isDataNumberSortDesc(WebDriver driver, String locator) {
        List<WebElement> elementList = getListElements(driver, locator);
        ArrayList<Float> dataList = new ArrayList<Float>();
        for (WebElement element : elementList) {
            dataList.add(Float.parseFloat(element.getText().trim()));
        }
        List<Float> sortList = new ArrayList<Float>(dataList);
        Collections.sort(sortList);
        Collections.reverse(sortList);
        return sortList.equals(dataList);
    }

    public boolean isDataNumberSortDesc(WebDriver driver, String locator, String... dynamicValues) {
        List<WebElement> elementList = getListElements(driver, locator, dynamicValues);
        ArrayList<Long> dataList = new ArrayList<Long>();
        for (WebElement element : elementList) {
            dataList.add(Long.parseLong(element.getText().trim()));
        }
        List<Long> sortList = new ArrayList<Long>(dataList);
        Collections.sort(sortList);
        Collections.reverse(sortList);
        return sortList.equals(dataList);
    }

    /**
     * @param driver  Webdriver
     * @param option  asc: ascending, desc: descending
     * @param locator locator of off all data
     * @return if sort or not
     */
    public boolean isDataDateSortByOption(WebDriver driver, String option, String locator) {
        List<WebElement> elementList = getListElements(driver, locator);
        ArrayList<Date> dataList = new ArrayList<Date>();
        for (WebElement element : elementList) {
            dataList.add(convertStringToDate(element.getText().trim(), "MMM dd, yyyy h:mm:ss a"));
        }
        List<Date> sortList = new ArrayList<Date>(dataList);
        Collections.sort(sortList);
        if (option.equals("asc")) {
            Collections.reverse(sortList);
        }
        Collections.reverse(sortList);
        return sortList.equals(dataList);
    }

    /**
     * @param driver  Webdriver
     * @param option  asc: ascending, desc: descending
     * @param locator locator of off all data
     * @return if sort or not
     */
    public boolean isDataDateSortByOption(WebDriver driver, String option, String locator, String... dynamicValues) {
        List<WebElement> elementList = getListElements(driver, locator, dynamicValues);
        ArrayList<Date> dataList = new ArrayList<Date>();
        for (WebElement element : elementList) {
            dataList.add(convertStringToDate(element.getText().trim(), "MMM dd, yyyy h:mm:ss a"));
        }
        List<Date> sortList = new ArrayList<Date>(dataList);
        Collections.sort(sortList);
        if (option.equals("asc")) {
            Collections.reverse(sortList);
        }
        Collections.reverse(sortList);
        return sortList.equals(dataList);
    }


    public boolean isDataStringSortDescLamDa(WebDriver driver, String locator) {
        List<WebElement> elementList = getListElements(driver, locator);
        List<String> dataList = elementList.stream().map(n -> n.getText()).collect(Collectors.toList());
        List<String> sortList = new ArrayList<String>(dataList);
        Collections.sort(sortList);
        Collections.reverse(sortList);
        return sortList.equals(dataList);
    }

    public boolean isDataStringSortAscLamDa(WebDriver driver, String locator) {
        List<WebElement> elementList = getListElements(driver, locator);
        List<String> dataList = elementList.stream().map(n -> n.getText()).collect(Collectors.toList());
        List<String> sortList = new ArrayList<String>(dataList);
        Collections.sort(sortList);
        return sortList.equals(dataList);
    }


    /**
     * Enter to dynamic textbox by ID
     *
     * @param driver
     * @param textID the ID of textbox
     * @param value  text value
     */
    public void inputToTextboxById(WebDriver driver, String textID, String value) {
        waitForElementVisibility(driver, LiveGuruBasePageUI.DYNAMIC_TEXTBOX_BY_ID, textID);
        sendkeyToElement(driver, LiveGuruBasePageUI.DYNAMIC_TEXTBOX_BY_ID, value, textID);
    }

    /**
     * Click to the link at the footer by text link
     *
     * @param driver
     * @param textLink The text link onUI
     */
    public void clickToDynamicFooterLinkByText(WebDriver driver, String textLink) {
        waitForElementClickable(driver, LiveGuruBasePageUI.FOOTER_LINK_BY_TEXT, textLink);
        clickToElement(driver, LiveGuruBasePageUI.FOOTER_LINK_BY_TEXT, textLink);
    }

    protected void deleteFileByFilePath(String filePath) {
        try {
            Files.deleteIfExists(
                    Paths.get(filePath));
        } catch (NoSuchFileException e) {
            System.out.println(
                    "No such file/directory exists");
        } catch (DirectoryNotEmptyException e) {
            System.out.println("Directory is not empty.");
        } catch (IOException e) {
            System.out.println("Invalid permissions.");
        }

    }

    protected void writeDataIntoDataRecordByFileName(String data, String fileName) {
        File file = new File(GlobalConstants.DATA_RECORD + fileName + ".txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file, true);
            fr.write(data);
            fr.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fr != null;
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public String getValueTextboxById(WebDriver driver, String id) {
        waitForElementVisibility(driver, LiveGuruBasePageUI.DYNAMIC_TEXTBOX_BY_ID, id);
        return getElementAttribute(driver, "value", LiveGuruBasePageUI.DYNAMIC_TEXTBOX_BY_ID, id);
    }

    public void clickToAccountLinkHeader(WebDriver driver) {
        waitForElementClickable(driver, LiveGuruBasePageUI.ACCOUNT_LINK_HEADER);
        clickToElement(driver, LiveGuruBasePageUI.ACCOUNT_LINK_HEADER);

    }

    public UserLoginPO clickToLoginLinkAtAccountLinkHeader(WebDriver driver) {
        waitForElementClickable(driver, LiveGuruBasePageUI.LOGIN_LINK_AT_ACCOUNT_HEADER);
        clickToElement(driver, LiveGuruBasePageUI.LOGIN_LINK_AT_ACCOUNT_HEADER);
        return PageGeneratorManager.getUserLoginPage(driver);
    }

    public UserMyWishlistPO clickToMyWishlistLinkAtAccountLinkHeader(WebDriver driver) {
        waitForElementClickable(driver, LiveGuruBasePageUI.MY_WISHLIST_LINK_AT_ACCOUNT_HEADER);
        clickToElement(driver, LiveGuruBasePageUI.MY_WISHLIST_LINK_AT_ACCOUNT_HEADER);
        return PageGeneratorManager.getUserMyWishlistPage(driver);
    }


    public UserProductCategoryPO clickToMobileLinkAtHeaderNav(WebDriver driver) {
        waitForElementClickable(driver, LiveGuruBasePageUI.MOBILE_LINK_AT_NAVIGATION_BAR);
        clickToElement(driver, LiveGuruBasePageUI.MOBILE_LINK_AT_NAVIGATION_BAR);
        return PageGeneratorManager.getUserProductCategoryPage(driver);

    }

    public void clickToDynamicLinkOnTheLeftSidebar(WebDriver driver, String linkText) {
        waitForElementClickable(driver, UserAccountPageUI.DYNAMIC_LINK_AT_LEFT_SIDEBAR, linkText);
        clickToElement(driver, UserAccountPageUI.DYNAMIC_LINK_AT_LEFT_SIDEBAR, linkText);
    }


    public UserProductComparisonPO switchToProductComparisonPage(WebDriver driver) {
        switchToWindowByTitle(driver, LiveGuruBasePageUI.COMPARISON_WINDOW_TITLE);
        return PageGeneratorManager.getUserProductComparisonPage(driver);
    }

    public void closeTheComparisonProductWindow(WebDriver driver) {
        waitForElementClickable(driver, LiveGuruBasePageUI.CLOSE_WINDOW_BUTTON);
        clickToElement(driver, LiveGuruBasePageUI.CLOSE_WINDOW_BUTTON);
        switchToWindowByTitle(driver, LiveGuruBasePageUI.MOBILE_WINDOW_TITLE);
    }

    public boolean isCurrentActiveLinkAtAccountPageByText(WebDriver driver, String linkText) {
        waitForElementVisibility(driver, UserAccountPageUI.CURRENT_ACTIVE_LINK_AT_LEFT_SIDEBAR, linkText);
        return isElementDisplayed(driver, UserAccountPageUI.CURRENT_ACTIVE_LINK_AT_LEFT_SIDEBAR, linkText);
    }

    public UserAccountPO clickToMyAccountLinkAtAccountLinkHeader(WebDriver driver) {
        waitForElementClickable(driver, LiveGuruBasePageUI.MY_ACCOUNT_LINK_AT_ACCOUNT_HEADER);
        clickToElement(driver, LiveGuruBasePageUI.MY_ACCOUNT_LINK_AT_ACCOUNT_HEADER);
        return PageGeneratorManager.getUserAccountPage(driver);
    }

    public UserProductCategoryPO clickToTVLinkAtHeaderNav(WebDriver driver) {
        waitForElementClickable(driver, LiveGuruBasePageUI.TV_LINK_AT_NAVIGATION_BAR);
        clickToElement(driver, LiveGuruBasePageUI.TV_LINK_AT_NAVIGATION_BAR);
        return PageGeneratorManager.getUserProductCategoryPage(driver);

    }

    public void enterToTextboxEmptyValueById(WebDriver driver, String id) {
        waitForAllElementVisibility(driver, LiveGuruBasePageUI.DYNAMIC_TEXTBOX_BY_ID, id);
        clearElement(driver, LiveGuruBasePageUI.DYNAMIC_TEXTBOX_BY_ID, id);
    }

    public void selectToDefaultDropdownById(WebDriver driver, String Id, String textOption) {
        waitForElementClickable(driver, LiveGuruBasePageUI.DYNAMIC_DEFAULT_DROPDOWN_BY_ID, Id);
        selectItemInDefaultDropdown(driver, LiveGuruBasePageUI.DYNAMIC_DEFAULT_DROPDOWN_BY_ID, textOption, Id);
    }

    public void closeTheIncomingMessagePopupAdminPage(WebDriver driver) {
        if (!isElementUndisplayed(driver, LiveGuruBasePageUI.INCOMING_MESSAGE_POPUP_ADMIN_PAGE)) {
            waitForElementClickable(driver, LiveGuruBasePageUI.INCOMING_MESSAGE_POPUP_ADMIN_PAGE);
            clickToElement(driver, LiveGuruBasePageUI.CLOSE_LINK_INCOMING_MESSAGE_POPUP);
        }
    }

    public void hoverToDynamicHeaderLinkByNameAdminPage(WebDriver driver, String name) {
        waitForElementVisibility(driver, LiveGuruBasePageUI.HEADER_LINK_BY_NAME_ADMIN_PAGE, name);
        hoverMouseToElement(driver, LiveGuruBasePageUI.HEADER_LINK_BY_NAME_ADMIN_PAGE, name);
    }

    public void clickToDynamicHeaderLinkByNameAdminPage(WebDriver driver, String name) {
        waitForElementClickable(driver, LiveGuruBasePageUI.HEADER_LINK_BY_NAME_ADMIN_PAGE, name);
        clickToElement(driver, LiveGuruBasePageUI.HEADER_LINK_BY_NAME_ADMIN_PAGE, name);

    }

    public boolean isMessageDisplayedAboveHeaderAdminPage(WebDriver driver, String message) {
        waitForElementVisibility(driver, LiveGuruBasePageUI.TITLE_MESSAGE_ABOVE_HEADER_BY_MESSAGE, message);
        return isElementDisplayed(driver, LiveGuruBasePageUI.TITLE_MESSAGE_ABOVE_HEADER_BY_MESSAGE, message);
    }


    public void clickToSubmitButtonAdminPage(WebDriver driver) {
        waitForElementClickable(driver, LiveGuruBasePageUI.SUBMIT_BUTTON);
        clickToElement(driver, LiveGuruBasePageUI.SUBMIT_BUTTON);
    }


    public void clickToSearchButtonAdminPage(WebDriver driver) {
        waitForLoadingMaskUnInvisibility(driver);
        checkToDefaultCheckboxRadio(driver, LiveGuruBasePageUI.SEARCH_BUTTON);

    }

    public void waitForLoadingMaskUnInvisibility(WebDriver driver) {
        waitForElementInvisibility(driver, LiveGuruBasePageUI.LOADING_MASK);
    }

    public boolean isFileDownloaded(String downloadPath, String fileName) {
        boolean isDownloaded = false;
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();
        for (int i = 0; i < Objects.requireNonNull(dirContents).length; i++) {
            if (dirContents[i].getName().contains(fileName)) {
                waitForFileToDownload(String.valueOf(dirContents[i]), GlobalConstants.LONG_TIMEOUT);
                dirContents[i].delete();
                isDownloaded = true;
            }
        }
        return isDownloaded;
    }

    public boolean waitForFileToDownload(String expectedFullPathName, int maxWaitSeconds) {
        File downloadedFile = new File(expectedFullPathName);
        System.out.println("Download file: " + downloadedFile);
        long startTime = System.currentTimeMillis();

        while (!downloadedFile.exists()) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = TimeUnit.MILLISECONDS.toSeconds(currentTime - startTime);
            if (elapsedTime > maxWaitSeconds) {
                return false;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    private static Date convertStringToDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clickToSortASCByTitle(WebDriver driver, String title) {
        waitForElementVisibility(driver, LiveGuruBasePageUI.SORT_TITLE_BY_TITLE, title);
        String titleAttribute = getElementAttribute(driver, "title", LiveGuruBasePageUI.SORT_TITLE_BY_TITLE, title);
        if (titleAttribute.equals("asc")) {
            waitForElementClickable(driver, LiveGuruBasePageUI.SORT_TITLE_BY_TITLE, title);
            clickToElement(driver, LiveGuruBasePageUI.SORT_TITLE_BY_TITLE, title);
        }
    }

    public void clickToSortDESCByTitle(WebDriver driver, String title) {
        waitForElementVisibility(driver, LiveGuruBasePageUI.SORT_TITLE_BY_TITLE, title);
        String titleAttribute = getElementAttribute(driver, "title", LiveGuruBasePageUI.SORT_TITLE_BY_TITLE, title);
        if (titleAttribute.equals("desc")) {
            waitForElementClickable(driver, LiveGuruBasePageUI.SORT_TITLE_BY_TITLE, title);
            clickToElement(driver, LiveGuruBasePageUI.SORT_TITLE_BY_TITLE, title);
        }

    }

    public int getQuantityItemSelectedCheckboxAdminPage(WebDriver driver) {
        waitForLoadingMaskUnInvisibility(driver);
        waitForAllElementVisibility(driver, LiveGuruBasePageUI.CHECKBOX_ROW_ITEM);
        List<WebElement> checkboxList = getListElements(driver, LiveGuruBasePageUI.CHECKBOX_ROW_ITEM);
        System.out.println("Size:" + checkboxList.size());
        int countCheckboxSelected = 0;
        for (WebElement checkbox : checkboxList) {
            if (checkbox.isSelected()) {
                countCheckboxSelected++;
            }
        }
        return countCheckboxSelected;

    }

    public boolean isDataNumberSortASCByTitleColumn(WebDriver driver, String columnName) {
        int columnIndex = getElementsSize(driver, LiveGuruBasePageUI.INDEX_COLUMN_BY_COLUMN_NAME, columnName) + 1;
        waitForLoadingMaskUnInvisibility(driver);
        waitForAllElementVisibility(driver, LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));
        return isDataNumberSortAsc(driver, LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));
    }

    public boolean isDataNumberSortDESCByTitleColumn(WebDriver driver, String columnName) {
        int columnIndex = getElementsSize(driver, LiveGuruBasePageUI.INDEX_COLUMN_BY_COLUMN_NAME, columnName) + 1;
        waitForLoadingMaskUnInvisibility(driver);
        waitForAllElementVisibility(driver, LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));
        return isDataNumberSortDesc(driver, LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));
    }

    public boolean isDataDateSortByTitleColumnAndOption(WebDriver driver, String columnName, String option) {
        int columnIndex = getElementsSize(driver, LiveGuruBasePageUI.INDEX_COLUMN_BY_COLUMN_NAME, columnName) + 1;
        waitForLoadingMaskUnInvisibility(driver);
        waitForAllElementVisibility(driver, LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));
        if (option.equals("asc")) {
            return isDataDateSortByOption(driver, "asc", LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));
        }
        return isDataDateSortByOption(driver, "desc", LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));
    }


    public boolean isDataStringSortASCByTitleColumn(WebDriver driver, String columnName) {
        int columnIndex = getElementsSize(driver, LiveGuruBasePageUI.INDEX_COLUMN_BY_COLUMN_NAME, columnName) + 1;
        waitForLoadingMaskUnInvisibility(driver);
        waitForAllElementVisibility(driver, LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));
        return isDataStringSortAsc(driver, LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));
    }

    public boolean isDataStringSortDESCByTitleColumn(WebDriver driver, String columnName) {
        int columnIndex = getElementsSize(driver, LiveGuruBasePageUI.INDEX_COLUMN_BY_COLUMN_NAME, columnName) + 1;
        waitForLoadingMaskUnInvisibility(driver);
        waitForAllElementVisibility(driver, LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));
        return isDataStringSortDesc(driver, LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));

    }

    public int getQuantityItemRowDisplayedAdminPage(WebDriver driver) {
        waitForLoadingMaskUnInvisibility(driver);
        waitForAllElementVisibility(driver, LiveGuruBasePageUI.CHECKBOX_ROW_ITEM);
        return getElementsSize(driver, LiveGuruBasePageUI.CHECKBOX_ROW_ITEM);
    }

    public void selectToViewPerPageDropdownAdminPage(WebDriver driver, String textValue) {
        waitForElementClickable(driver, LiveGuruBasePageUI.VIEW_PER_PAGE_DROPDOWN);
        selectItemInDefaultDropdown(driver, LiveGuruBasePageUI.VIEW_PER_PAGE_DROPDOWN, textValue);
    }
}
