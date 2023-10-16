package pageUIs.liveGuru.admin;

public class AdminManageReviewPageUI {
    public final static String TITLE_CELL_BY_NICK_NAME = "xpath=//table[@id='reviwGrid_table']//tbody//td[contains(text(),'%s')]/preceding-sibling::td[contains(text(), '%s')]";

    public final static String REVIEW_DESCRIPTION_CELL_BY_NICK_NAME = "xpath=//table[@id='reviwGrid_table']//tbody//td[contains(text(),'%s')]/following-sibling::td[contains(text(), '%s')]";
    public final static String EDIT_LINK_BY_NICKNAME = "xpath=//td[contains(text(), '%s')]/following-sibling::td/a[text()='Edit']";


}
