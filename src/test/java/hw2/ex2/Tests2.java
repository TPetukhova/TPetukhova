package hw2.ex2;

import base.hw2.BaseClassExercise2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests2 extends BaseClassExercise2 {

    // 1 - assign test 4 to the REGRESSION group
    @Test(groups = {"Regression"})
    public void simpleTest4() {

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

    // 2 - assign test 5 to the REGRESSION group
    @Test(groups = {"Regression"})
    public void simpleTest5() {

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

    // 3 - assign test 6 to the REGRESSION group
    @Test(groups = {"Regression"})
    public void simpleTest6() {

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
