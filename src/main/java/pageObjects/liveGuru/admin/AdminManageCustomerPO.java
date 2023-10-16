package pageObjects.liveGuru.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.liveGuru.admin.AdminManageCustomerPageUI;
import pageUIs.liveGuru.user.LiveGuruBasePageUI;

public class AdminManageCustomerPO extends BasePage {
    private WebDriver driver;

    public AdminManageCustomerPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isManageCustomersTitleDisplayed() {
        waitForElementClickable(driver, AdminManageCustomerPageUI.MANAGE_CUSTOMER_TITLE);
        return isElementDisplayed(driver, AdminManageCustomerPageUI.MANAGE_CUSTOMER_TITLE);
    }

    public boolean isEmailDisplayedAtEmailColumnCustomerTable(String email) {
        int emailIndexColumn = getElementsSize(driver, LiveGuruBasePageUI.INDEX_COLUMN_BY_COLUMN_NAME, "Email") + 1;
        waitForElementVisibility(driver, AdminManageCustomerPageUI.CELL_DATA_BY_COLUMN_INDEX_AND_TEXT, String.valueOf(emailIndexColumn), email);
        return isElementDisplayed(driver, AdminManageCustomerPageUI.CELL_DATA_BY_COLUMN_INDEX_AND_TEXT, String.valueOf(emailIndexColumn), email);
    }

    public boolean isFullNameDisplayedAtNameColumnCustomerTableByEmail(String email, String fullName) {
        waitForElementVisibility(driver, AdminManageCustomerPageUI.FULL_NAME_CELL_BY_EMAIL, email, fullName);
        return isElementDisplayed(driver, AdminManageCustomerPageUI.FULL_NAME_CELL_BY_EMAIL, email, fullName);
    }

    public void checkToCheckboxAtCustomerRowByEmail(String email) {
        waitForElementClickable(driver, AdminManageCustomerPageUI.CHECKBOX_CUSTOMER_ROW_BY_EMAIL, email);
        checkToDefaultCheckboxRadio(driver, AdminManageCustomerPageUI.CHECKBOX_CUSTOMER_ROW_BY_EMAIL, email);
    }

    public int getCurrentItemSelected() {
        waitForElementVisibility(driver, AdminManageCustomerPageUI.CURRENT_ITEM_SELECTED);
        return Integer.parseInt(getElementText(driver, AdminManageCustomerPageUI.CURRENT_ITEM_SELECTED));
    }


    public void acceptTheDeleteAlert() {
        acceptAlert(driver);
    }


    public boolean isFullNameUndisplayedAtNameColumnCustomerTableByEmail(String email, String fullName) {
        return isElementUndisplayed(driver, AdminManageCustomerPageUI.FULL_NAME_CELL_BY_EMAIL, email, fullName);
    }
}
