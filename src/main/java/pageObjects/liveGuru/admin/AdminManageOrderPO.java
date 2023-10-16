package pageObjects.liveGuru.admin;

import commons.BasePage;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import pageUIs.AdminManageOrderPageUI;

public class AdminManageOrderPO extends BasePage {
    private WebDriver driver;

    public AdminManageOrderPO(WebDriver driver) {
        this.driver = driver;
    }

    public void selectToFirstCheckboxOrderRow() {
        waitForLoadingMaskUnInvisibility(driver);
        waitForElementClickable(driver, AdminManageOrderPageUI.FIRST_CHECKBOX_ORDER_ROW);
        checkToDefaultCheckboxRadio(driver, AdminManageOrderPageUI.FIRST_CHECKBOX_ORDER_ROW);
    }

    public boolean isInvoicePDFDownloaded(String fileName) {
        return isFileDownloaded(GlobalConstants.DOWNLOAD_FILE_FOLDER, fileName);
    }

    public int getCurrentItemSelected() {
        waitForElementVisibility(driver, AdminManageOrderPageUI.CURRENT_ITEM_SELECTED);
        return Integer.parseInt(getElementText(driver, AdminManageOrderPageUI.CURRENT_ITEM_SELECTED));
    }

    public void clickToSelectVisibleLink() {
        waitForElementClickable(driver, AdminManageOrderPageUI.SELECT_VISIBLE_LINK);
        clickToElement(driver, AdminManageOrderPageUI.SELECT_VISIBLE_LINK);
    }

    public void clickToUnSelectVisibleLink() {
        waitForElementClickable(driver, AdminManageOrderPageUI.UNSELECT_VISIBLE_LINK);
        clickToElement(driver, AdminManageOrderPageUI.UNSELECT_VISIBLE_LINK);

    }
}
