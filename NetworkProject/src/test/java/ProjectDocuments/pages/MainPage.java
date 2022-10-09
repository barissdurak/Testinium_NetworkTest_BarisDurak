package ProjectDocuments.pages;

import ProjectDocuments.driver.Driver;
import ProjectDocuments.methods.Methods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MainPage extends Methods {
    private static final Logger logger = LogManager.getLogger(Driver.class);

    public MainPage(WebDriver driver) {

    }

    //Cerezleri kabul et tusuna basar. Ardindan search kismina ceket yazar ve aratir.
    public void sendkeysObject() {
        WebElement cerezlerikabulet = Driver.driver.findElement(By.cssSelector("button#onetrust-accept-btn-handler"));
        cerezlerikabulet.click();
        waitBySeconds(2);
        WebElement search = Driver.driver.findElement(By.xpath("//*[@class=\"o-header__searchInput--input form-control header__searchInput header__searchAreaInput\"]"));
        search.click();
        search.sendKeys("ceket");
        search.sendKeys(Keys.ENTER);

    }

    //URL karsilastirmasi yapar.
    public void CheckUrl() {
        String URL = Driver.driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://www.network.com.tr/");
        System.out.println("Network urlnin dogrulugu onaylanmistir.");
    }

    //Daha fazla goster butonuna basildiktan sonra URLnin degisip degismedigini kontrol eder.
    public void CheckUrlShowMoreDetails() {
        waitBySeconds(3);
        String URL = Driver.driver.getCurrentUrl();
        waitBySeconds(3);
        Assert.assertEquals(URL, "https://www.network.com.tr/search?searchKey=ceket&page=2");
        System.out.println("2. sayfaya gecildigi url degisikligi ile dogrulandi.");
    }
}
