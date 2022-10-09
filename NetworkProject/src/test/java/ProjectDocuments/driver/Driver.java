package ProjectDocuments.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Driver {
    private static final Logger logger = LogManager.getLogger(Driver.class);

    public static WebDriver driver;

    @BeforeAll
    public static void beforeAll() {
        logger.info("** Before All **");

    }


    @BeforeEach
    public void beforeEach() {

        System.setProperty("webdriver.chrome.driver", "src/chromedriver/chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://www.network.com.tr/");
    }

    @AfterEach
    public void afterEach() {

        if (driver != null) {
            // driver.quit();
        }

        logger.info(" ** After **");
    }

    @AfterAll
    public static void afterAll() {
        logger.info(" ** After All **");
    }

}
