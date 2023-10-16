package pageObjects.liveGuru.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.liveGuru.user.LiveGuruBasePageUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdminManageInvoicePO extends BasePage {
    private WebDriver driver;

    public AdminManageInvoicePO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDataAmountColumnSortByOption(String option) {
        int columnIndex = getElementsSize(driver, LiveGuruBasePageUI.INDEX_COLUMN_BY_COLUMN_NAME, "Amount") + 1;
        waitForLoadingMaskUnInvisibility(driver);
        List<WebElement> elementList = getListElements(driver, LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));
        ArrayList<Float> dataList = new ArrayList<>();
        for (WebElement element : elementList) {
            dataList.add(Float.parseFloat(element.getText().trim().substring(1).replace(",", "")));
        }
        List<Float> sortList = new ArrayList<Float>(dataList);
        Collections.sort(sortList);
        if (!option.equals("asc")) {
            Collections.reverse(sortList);
        }
        return sortList.equals(dataList);
    }

}
