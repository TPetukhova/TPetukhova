package lesson2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestWithParameter {

        @DataProvider
        public Object[][] userData() {
            return new Object[][] {
                    {"epam", "1234"},
                    {"epam2", "1234"},
                    {"epam2", "1234"}
            };
        }

        @Test(dataProvider = "userData")
        public void simpleTest(String name, String password) {

            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.navigate().to("https://epam.github.io/JDI");
            Assert.assertEquals(driver.getTitle(), "Home Page");

            driver.findElement(By.cssSelector(".profile-photo")).click();
            driver.findElement(By.id("Name")).sendKeys(name);
            driver.findElement(By.id("Password")).sendKeys(password);
            driver.findElement(By.cssSelector(".login button[type = 'submit']")).click();
            Assert.assertEquals(driver.findElement(By.cssSelector(".profile-photo span")).getText(), "PITER CHAILOVSKII");

            driver.close();

        }
}
