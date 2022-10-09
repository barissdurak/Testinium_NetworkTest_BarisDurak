package ProjectDocuments.pages;

import ProjectDocuments.driver.Driver;
import ProjectDocuments.methods.Methods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ProductPage extends Methods {
    private static final Logger logger = LogManager.getLogger(Driver.class);

    public ProductPage(WebDriver driver) {
    }

    //Daha fazla goster butonuna kadar ekrani asagiya kaydirir.
    public void scrollPage() {
        scrollElement(By.xpath("(//*[@class=\"product__price -actual\"])[56]"));
        waitBySeconds(3);
        clickElement(By.xpath("//*[@class=\"button -secondary -sm relative\"]"));
    }

    //Indirimli ilk urune gelene kadar ekrani yukari kaydirir ve ardindan rastgele bedenini sepete atar.
    //Sepete atmadan once atilacak urunun fiyatini ve bedenini degiskenin icine kaydeder.
    //Sepete gider ve burada onceki sayfadaki fiyat ve beden degelerini kiyaslar bu sayede dogru fiyatta gelip gelmediginin kontrolunu yapar.
    public void FirstProductClick() {
        //Ilk indirimli urune kadar yukariya kaydirir.
        waitBySeconds(7);
        scrollElementIfNeeded(By.xpath("(//*[@class=\"product__price -actual\"])[1]"));
        waitBySeconds(7);
        hoverElement(By.xpath("(//*[@class=\"product__price -actual\"])[1]"));
        waitBySeconds(7);

        //Sepete atilacak urunun fiyat ve bedenini degiskenlere atar.
        WebElement Urunfiyat = findElementWait(By.xpath("(//*[@class=\"product__price -actual\"])[1]"));
        String message = Urunfiyat.getText();

        WebElement Urunbeden = findElementWait(By.xpath("(//*[@class=\"radio-box__label \"])[1]"));
        String message2 = Urunbeden.getText();

        //Urunu sepete atar
        clickElement(By.xpath("(//*[@class=\"radio-box__label \"])[1]"));
        waitBySeconds(5);
        clickElement(By.xpath("//*[@class=\"button -primary header__basket--checkout header__basketModal -checkout\"]"));
        waitBySeconds(5);

        //Sepetteki urunun fiyat ve bedenini onceki sayfadan kaydedilen degiskenler ile kiyaslar.
        WebElement element = findElementWait(By.xpath("//*[@class=\"cartItem__price -sale\"]"));
        String message3 = element.getText();
        Assert.assertEquals(message, message3);
        waitBySeconds(5);

        WebElement element2 = findElementWait(By.xpath("(//*[@class=\"cartItem__attrValue\"])[1]"));
        String message4 = element2.getText();
        Assert.assertEquals(message2, message4);
        System.out.println("Urune ait beden ve fiyat bilgisinin sepette dogru geldigi kontrol edildi.");
    }

    //Network logosuna tiklar.
    public void clickMainLogo() {
        clickElement(By.xpath("//*[@class=\"img-fluid\"]"));
    }

    //Sepet logosuna tiklar.
    public void GoBasket() {
        clickElement(By.xpath("(//*[@class=\"header__icon -shoppingBag\"])[1]"));
    }

    //Urunu sepetten kaldirir ve arindan sepetin bos oldugunu kontrol eder.
    public void ClearBasket() {
        clickElement(By.xpath("//*[@class=\"header__basketProductRemove\"]"));
        clickElement(By.xpath("//*[@class=\"btn -black o-removeCartModal__button\"]"));
        waitBySeconds(2);

        clickElement(By.xpath("(//*[@class=\"header__icon -shoppingBag\"])[1]"));
        WebElement element = findElementWait(By.xpath("//span[text()=\"Sepetiniz Henüz Boş\"]"));
        String message = element.getText();
        Assertions.assertEquals("Sepetiniz Henüz Boş", message);
        System.out.println("Sepetin bos oldugu goruntulendi.");
    }

    //Urunun indirimli fiyati ve gercek fiyatini karsilastirir. Ardindan devam et butonuna tiklar.
    //Bunun icin string olarak aldigi fiyati integer yapar ve buyuklugu aritmetik olarak kiyaslar.
    //Not: Delayin fazla kullanilmasinin sebebi bilgisayar ve internetin yavasligindan dolayi.
    public void PriceComporasing() {
        waitBySeconds(7);
        waitBySeconds(7);
        WebElement element = findElementWait(By.xpath("//*[@class=\"cartItem__price -sale\"]"));
        String lowervalue = element.getText();

        WebElement element2 = findElementWait(By.xpath("(//*[@class=\"summaryItem__value\"])[1]"));
        String actualValue = element2.getText();

        String newActualValue = actualValue.substring(3, 5);
        String newLowervalue = lowervalue.substring(3, 5);
        waitBySeconds(7);
        int intactualValue = Integer.parseInt(newActualValue);
        int intlowervalue = Integer.parseInt(newLowervalue);

        Assert.assertTrue(intactualValue > intlowervalue);
        System.out.println("Indirimsiz fiyatin indirimli fiyattan buyuk oldugu kontrol edildi.");
        waitBySeconds(7);
        waitBySeconds(7);

        clickElement(By.xpath("//*[@class=\"continueButton n-button large block text-center -primary\"]"));
        waitBySeconds(2);
    }
}
