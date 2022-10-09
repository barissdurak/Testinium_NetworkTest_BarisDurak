package ProjectDocuments.test;

import ProjectDocuments.driver.Driver;
import ProjectDocuments.pages.LoginPage;
import ProjectDocuments.pages.MainPage;
import ProjectDocuments.pages.ProductPage;
import org.junit.jupiter.api.Test;

public class NetworkTest extends Driver {

    ProductPage productPage;
    LoginPage loginPage;
    MainPage mainPage;

    @Test
    public void loginTest() {

        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        loginPage = new LoginPage(driver);

        mainPage.CheckUrl();
        mainPage.sendkeysObject();
        productPage.scrollPage();
        mainPage.CheckUrlShowMoreDetails();
        productPage.FirstProductClick();
        productPage.PriceComporasing();
        loginPage.login();
        productPage.clickMainLogo();
        productPage.GoBasket();
        productPage.ClearBasket();

        System.out.println("Network projesi tamamlandi.");

    }
}
