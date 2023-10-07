package pageObjects.liveGuru.user;

import commons.BasePage;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.liveGuru.user.UserProductCategoryPageUI;
import utilities.ClassHelper;

import java.util.ArrayList;
import java.util.List;

public class UserProductCategoryPO extends BasePage {
    private WebDriver driver;

    public UserProductCategoryPO(WebDriver driver) {
        this.driver = driver;
    }

    public Float getProductCostByProductName(String productName) {
        waitForElementVisibility(driver, UserProductCategoryPageUI.PRICE_BY_PRODUCT_NAME, productName);
        String productPriceText = getElementText(driver, UserProductCategoryPageUI.PRICE_BY_PRODUCT_NAME, productName);
        return Float.parseFloat(productPriceText.substring(1));
    }

    public UserProductDetailPO openProductDetailPageByProductName(String productName) {
        waitForElementClickable(driver, UserProductCategoryPageUI.PRODUCT_NAME_LINK_IN_PRODUCT_INFO, productName);
        clickToElement(driver, UserProductCategoryPageUI.PRODUCT_NAME_LINK_IN_PRODUCT_INFO, productName);
        return PageGeneratorManager.getUserProductDetailPage(driver);
    }

    public void clickToAddToCompareLinkByProductName(String productName) {
        waitForElementClickable(driver, UserProductCategoryPageUI.ADD_TO_COMPARE_LINK_BY_PRODUCT_NAME, productName);
        clickToElement(driver, UserProductCategoryPageUI.ADD_TO_COMPARE_LINK_BY_PRODUCT_NAME, productName);
    }

    public boolean isAddProductToComparisonMessageDisplayed(String productName) {
        waitForElementVisibility(driver, UserProductCategoryPageUI.ADD_PRODUCT_TO_COMPARISON_SUCCESS_MESSAGE, productName);
        return isElementDisplayed(driver, UserProductCategoryPageUI.ADD_PRODUCT_TO_COMPARISON_SUCCESS_MESSAGE, productName);
    }

    public UserProductComparisonPO clickToCompareButton() {
        waitForElementClickable(driver, UserProductCategoryPageUI.COMPARE_BUTTON);
        clickToElement(driver, UserProductCategoryPageUI.COMPARE_BUTTON);
        return PageGeneratorManager.getUserProductComparisonPage(driver);

    }

    public UserMyWishlistPO clickToAddToAddToWishlistLinkByProductName(String productName) {
        waitForElementClickable(driver, UserProductCategoryPageUI.ADD_TO_WISHLIST_LINK_BY_PRODUCT_NAME, productName);
        clickToElement(driver, UserProductCategoryPageUI.ADD_TO_WISHLIST_LINK_BY_PRODUCT_NAME, productName);
        return PageGeneratorManager.getUserMyWishlistPage(driver);
    }

    public boolean areAllProductDisplayedWithinPriceRange(float minPrice, float maxPrice) {
        waitForAllElementVisibility(driver, UserProductCategoryPageUI.PRODUCT_PRICE_AT_PRODUCT_LIST);
        List<WebElement> priceList = getListElements(driver, UserProductCategoryPageUI.PRODUCT_PRICE_AT_PRODUCT_LIST);
        for (WebElement priceElement : priceList) {
            float price = Float.parseFloat(priceElement.getText().substring(1));
            if (price > maxPrice && price < minPrice) {
                return false;
            }
        }
        return true;
    }

    public void crawlProductDataByPriceRange(float minPrice, float maxPrice) {
        List<WebElement> productNameList = getListElements(driver, UserProductCategoryPageUI.PRODUCT_NAME_AT_PRODUCT_LIST);
        List<WebElement> priceList = getListElements(driver, UserProductCategoryPageUI.PRODUCT_PRICE_AT_PRODUCT_LIST);
        List<ClassHelper.Product> productList = new ArrayList<ClassHelper.Product>();
        String productName;
        String price;
        for (int i = 0; i < priceList.size(); i++) {
            productName = productNameList.get(i).getText();
            price = priceList.get(i).getText();
            productList.add(new ClassHelper.Product(productName, price));
        }
        String fileName = "Product_List_From_" + String.format("%.0f", minPrice) + "_To_" + String.format("%.0f", maxPrice);
        deleteFileByFilePath(GlobalConstants.DATA_RECORD + fileName + ".txt");
        for (ClassHelper.Product product : productList) {
            String data = String.format(product.getProductName() + ";" + product.getPrice());
            writeDataIntoDataRecordByFileName(data, fileName);
        }

    }
}
