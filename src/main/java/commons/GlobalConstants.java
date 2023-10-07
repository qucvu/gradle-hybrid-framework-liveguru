package commons;

import java.io.File;

public class GlobalConstants {

    public static final String PROJECT_PATH = System.getProperty("user.dir") + File.separator;
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public static final String UPLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
    public static final String DOWNLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "downloadFiles";
    public static final String BROWSER_LOG_FOLDER = PROJECT_PATH + File.separator + "browserLogs";
    public static final String DATA_RECORD = PROJECT_PATH + "dataRecord" + File.separator;
    public static final String EXTENT_PATH_V5 = PROJECT_PATH + File.separator + "ExtentReportV5" + File.separator;

    public static final int LONG_TIMEOUT = 30;
    public static final int SHORT_TIMEOUT = 5;
    public static final int RETRY_TEST_FAIL = 15;

}
