package ProjectDocuments.pages;

import ProjectDocuments.driver.Driver;
import ProjectDocuments.methods.Methods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoginPage extends Methods {
    private static final Logger logger = LogManager.getLogger(Driver.class);

    public LoginPage(WebDriver driver) {

    }

    // csv dosyasi icinden email ve sifreyi alarak uye girisi yapar.
    public void login() {
        waitBySeconds(2);
        String path = "src\\ids.csv";
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
//                System.out.println("mail: " + values[0] + " //// sifre: " + values[1]);
                WebElement eposta = Driver.driver.findElement(new By.ByCssSelector(" input#n-input-email.input.js-trim"));
                waitBySeconds(2);
                eposta.click();
                eposta.sendKeys(values[0]);

                WebElement sifre = Driver.driver.findElement(new By.ByCssSelector(" input#n-input-password.input"));
                waitBySeconds(2);
                sifre.click();
                sifre.sendKeys(values[1]);

                waitBySeconds(2);
                WebElement girisyap = Driver.driver.findElement(new By.ByCssSelector(" button.n-button.large.block.text-center.-primary"));
                girisyap.click();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






