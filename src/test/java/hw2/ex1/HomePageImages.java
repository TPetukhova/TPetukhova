package hw2.ex1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class HomePageImages {

    @DataProvider(parallel = true)
    public Object[][] userData() {
        return new Object[][]{
                {1, "To include good practices\n" +
                        "and ideas from successful\n" + "EPAM project"},
                {2, "To be flexible and\n" +
                        "customizable"},
                {3, "To be multiplatform"},
                {4, "Already have good base\n" +
                        "(about 20 internal and\n" + "some external projects),\n" + "wish to get more…"}};
    }

    // 1 - Create test with Data Provider
    @Test(dataProvider = "userData")
    public void checkPicturesAndTextIndexPage(int index, String expectedText) {
        // 2 - Open site page in the browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://epam.github.io/JDI/index.html");

        // 3 - Verify texts under pictures using provided data
        assertEquals(driver.findElement(By.xpath("(//*[@class='benefit-txt'])[" + index + "]")).getText(), expectedText);
        driver.close();

    }
}
