package lesson2;

import base.lesson2.TestBaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;

public class TestWithAnnotations extends TestBaseClass {

    /*private WebDriver driver;
    will be issues with parallel launch
    */
    private ChromeOptions options;

    @BeforeClass
    public void beforeClass() {
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", "target");
        options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        // for example path to .exe with driver
    }

    @BeforeMethod
    public void beforeMethod() {
        /*examples - System.currentTimeMillis();
        what to do with browser
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    */}

    @AfterMethod
    public void afterMethod() {
        /*to measure diff between times
        driver.close();
    */}

    @AfterClass
    public void afterClass() {

    }

    //@Test(invocationCount = 3, threadPoolSize = 3)
    @Test(timeOut = 1000) // used not so often
    public void seleniumTest() {

        WebDriver driver = new ChromeDriver(); // for parallel runs
        driver.manage().window().maximize();
        driver.navigate().to("https://epam.github.io/JDI");
        Assert.assertEquals(driver.getTitle(), "Home Page");

        driver.findElement(By.cssSelector(".profile-photo")).click();
        driver.findElement(By.id("Name")).sendKeys("epam");
        driver.findElement(By.id("Password")).sendKeys("1234");
        driver.findElement(By.cssSelector(".login button[type = 'submit']")).click();
        driver.close();
    }

}



