package hw3;

import PageObjects.hw3.HomePage;
import PageObjects.hw3.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Exercise1 {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
    }

    @AfterMethod
    public void afterMethod(){
        driver.close();
    }

    //1 - Create a new test
    @Test
    public void JDIWebPageTest() {

        // 2 - Open test site by URL
        loginPage.open();

        // 3 - Assert Browser title
        loginPage.checkTitle();

        // 4 - Perform Login
        loginPage.login("epam", "1234");
        homePage = PageFactory.initElements(driver, HomePage.class);

        // 5 - Assert User name in the left-top side of screen that user logged in
        homePage.checkSingedInUsername("PITER CHAILOVSKII");

        // 6 - Assert Browser title
        homePage.checkTitle();

        // 7 - Assert that there are 4 items on the header section are displayed and they have proper text
        homePage.checkMenuItemsText();

        // 8 - Assert there are 4 images on the Index Page and they are displayed
        homePage.checkImagesDisplayed();

        // 9 - Assert that there are 4 texts on the Index Page under icons and they have proper text
        homePage.checkTextItems();

        // 10 - Assert the text of the main headers
        homePage.checkShortHeaderText();
        homePage.checkLongHeaderText();

        // 11 - Assert the text of the sub header
        homePage.checkSubHeaderText();

        // 12 - Assert that JDI Github is a link and has a proper URL
        homePage.checkSubHeaderLink();

        // 13 - Assert that there is Left Section
        homePage.checkLeftSectionShown();

        // 14 - Assert that there is Footer
        homePage.checkFooterShown();

    }

}
