package hw2.ex3;

import base.hw2.BaseClassExercise3;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Exercise3 extends BaseClassExercise3 {

    private WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println(driver.getTitle());
    }

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }

    @Test
    public void loginTest() {

        driver.navigate().to("https://jdi-framework.github.io/tests/index.htm");
        Assert.assertEquals(driver.getTitle(), "Index Page");
        driver.findElement(By.cssSelector(".fa.fa-user")).click();
        driver.findElement(By.id("Login")).sendKeys("epam");
        driver.findElement(By.id("Password")).sendKeys("1234");
        driver.findElement(By.cssSelector(".fa-sign-in")).click();

        WebElement element = driver.findElement(By.cssSelector(".profile-photo span"));
        Assert.assertTrue(element.isDisplayed());
        String text = element.getText();
        Assert.assertEquals(text, "PITER CHAILOVSKII");
    }
}
