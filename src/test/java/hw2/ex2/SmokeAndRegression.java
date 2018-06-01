package hw2.ex2;

import base.hw2.BaseClassSmokeAndRegression;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeAndRegression extends BaseClassSmokeAndRegression {

    @Test(groups = {"Regression"})
    public void loginTest7() {

        WebDriver driver = new ChromeDriver();
        // 1 - open page in browser and check title
        driver.navigate().to("https://jdi-framework.github.io/tests/index.htm");
        Assert.assertEquals(driver.getTitle(), "Index Page");
        driver.manage().window().maximize();

        // 2 - perform login
        driver.findElement(By.cssSelector(".fa.fa-user")).click();
        driver.findElement(By.id("Login")).sendKeys("epam");
        driver.findElement(By.id("Password")).sendKeys("1234");
        driver.findElement(By.cssSelector(".fa-sign-in")).click();

        // 3 - check the name of signed in user
        WebElement element = driver.findElement(By.cssSelector(".profile-photo span"));
        Assert.assertTrue(element.isDisplayed());
        String text = element.getText();
        Assert.assertEquals(text, "PITER CHAILOVSKII");
        driver.close();
    }

    @Test(groups = {"Smoke"})
    public void loginTest8() {

        WebDriver driver = new ChromeDriver();
        // 1 - open page in browser and check title
        driver.navigate().to("https://jdi-framework.github.io/tests/index.htm");
        Assert.assertEquals(driver.getTitle(), "Index Page");
        driver.manage().window().maximize();

        // 2 - perform login
        driver.findElement(By.cssSelector(".fa.fa-user")).click();
        driver.findElement(By.id("Login")).sendKeys("epam");
        driver.findElement(By.id("Password")).sendKeys("1234");
        driver.findElement(By.cssSelector(".fa-sign-in")).click();

        // 3 - check the name of signed in user
        WebElement element = driver.findElement(By.cssSelector(".profile-photo span"));
        Assert.assertTrue(element.isDisplayed());
        String text = element.getText();
        Assert.assertEquals(text, "PITER CHAILOVSKII");
        driver.close();
    }

    @Test(groups = {"Regression"})
    public void loginTest9() {

        WebDriver driver = new ChromeDriver();
        // 1 - open page in browser and check title
        driver.navigate().to("https://jdi-framework.github.io/tests/index.htm");
        Assert.assertEquals(driver.getTitle(), "Index Page");
        driver.manage().window().maximize();

        // 2 - perform login
        driver.findElement(By.cssSelector(".fa.fa-user")).click();
        driver.findElement(By.id("Login")).sendKeys("epam");
        driver.findElement(By.id("Password")).sendKeys("1234");
        driver.findElement(By.cssSelector(".fa-sign-in")).click();

        // 3 - check the name of signed in user
        WebElement element = driver.findElement(By.cssSelector(".profile-photo span"));
        Assert.assertTrue(element.isDisplayed());
        String text = element.getText();
        Assert.assertEquals(text, "PITER CHAILOVSKII");
        driver.close();
    }
}
