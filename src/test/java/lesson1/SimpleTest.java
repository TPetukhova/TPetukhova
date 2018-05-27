package lesson1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class SimpleTest {

    @Test
    public void simpleSeleniumTest() {

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", "target");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://jdi-framework.github.io/tests/index.htm");
        Assert.assertEquals(driver.getTitle(), "Index Page");
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector(".fa.fa-user")).click();
        driver.findElement(By.id("Login")).sendKeys("epam");
        driver.findElement(By.id("Password")).sendKeys("1234");
        driver.findElement(By.cssSelector(".fa-sign-in")).click();

        WebElement element = driver.findElement(By.cssSelector(".profile-photo span"));
        Assert.assertTrue(element.isDisplayed());
        String text = element.getText();
        Assert.assertEquals(text, "PITER CHAILOVSKII");
        driver.close();

    }

}
