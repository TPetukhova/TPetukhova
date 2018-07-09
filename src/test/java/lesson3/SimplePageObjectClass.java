package lesson3;

import pageObjects.lesson3.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

public class SimplePageObjectClass {

    private ChromeOptions options;
    private HomePage homePage;
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", "target");
        options = new ChromeOptions();
        options.addArguments("prefs", "chromePrefs");

        driver = new ChromeDriver();
        homePage = PageFactory.initElements(driver, HomePage.class);
        driver.manage().window().maximize();
    }

    @Test
    public void SimplePageObjectTest() {
        homePage.open(driver);
        homePage.checkHomePageTitle(driver);
        homePage.login("epam","1234");
        Assert.assertEquals(driver.findElement(By.cssSelector(".profile-photo span")).getText(), "PITER CHAILOVSKII");
        driver.close();
    }
}
