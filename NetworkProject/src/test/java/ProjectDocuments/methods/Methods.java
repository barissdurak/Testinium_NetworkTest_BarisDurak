package ProjectDocuments.methods;

import ProjectDocuments.driver.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class Methods {
    private static final Logger logger = LogManager.getLogger(Methods.class);

    WebDriver driver;
    FluentWait<WebDriver> fluentWait;
    JavascriptExecutor jsDriver;

    public Methods() {

        this.driver = Driver.driver;
        jsDriver = (JavascriptExecutor) driver;
        fluentWait = setFluentWait(30);
    }

    public FluentWait<WebDriver> setFluentWait(long timeout) {

        return new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NoSuchElementException.class);
    }

    public WebElement findElement(By by) {

        return driver.findElement(by);
    }

    public WebElement findElementWait(By by) {

        return fluentWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void clickElement(By by) {

        findElementWait(by).click();
    }

    public void sendKeys(By by, String text) {

        findElementWait(by).sendKeys(text);
    }

    public String getText(By by) {

        return findElementWait(by).getText();
    }

    public String getAttribute(By by, String attribute) {

        return findElementWait(by).getAttribute(attribute);
    }

    public void hoverElement(By by) {

        WebElement webElement = findElementWait(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).build().perform();
        logger.info(by.toString() + "elementine hover islemi yapıldı ");
    }

    public void scrollElement(By by) {
        WebElement webElement = findElementWait(by);
        jsDriver.executeScript("arguments[0].scrollIntoView()", webElement);

    }

    public void scrollElementIfNeeded(By by) {
        WebElement webElement = findElementWait(by);
        jsDriver.executeScript("arguments[0].scrollIntoViewIfNeeded();", webElement);
    }

    public void waitByMiliSeconds(long miliseconds) {
        try {
            Thread.sleep(miliseconds);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (Math.floorMod(miliseconds, 1000) != 0)
            logger.info(miliseconds + " milisaniye bekklendi ");

    }

    public void waitBySeconds(long Seconds) {
        waitByMiliSeconds(1000);
        logger.info(Seconds + " saniye beklendi ");
    }

}
