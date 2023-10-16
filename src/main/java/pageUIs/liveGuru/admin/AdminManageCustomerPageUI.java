package pageUIs.liveGuru.admin;

public class AdminManageCustomerPageUI {
    public final static String MANAGE_CUSTOMER_TITLE = "xpath=//div[@id='anchor-content']//h3[contains(@class, 'head-customer') and text()='Manage Customers']";
    public final static String CELL_DATA_BY_COLUMN_INDEX_AND_TEXT = "xpath=//table[@id='customerGrid_table']//tbody//td[%s][contains(text(),'%s')]";

    public final static String FULL_NAME_CELL_BY_EMAIL = "XPATH=//table[@id='customerGrid_table']//tbody//td[contains(text(),'%s')]/preceding-sibling::td[contains(text(), '%s')]";

    public final static String CHECKBOX_CUSTOMER_ROW_BY_EMAIL = "xpath=//td[contains(text(), '%s')]/preceding-sibling::td[@class='a-center ']/input";
    public final static String CURRENT_ITEM_SELECTED = "id=customerGrid_massaction-count";

}
