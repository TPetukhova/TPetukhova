package hw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static enums.hw3.Users.PITER_CHAILOVSKII;

public class HomePageInterface {

    private WebDriver driver;
    private PageObjects.hw3.HomePage homePage;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = PageFactory.initElements(driver, PageObjects.hw3.HomePage.class);
    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }

    @Test
    public void homePageInterfaceTest() {

        // 2 - Open test site by URL
        homePage.open(driver);

        // 3 - Assert Browser title
        homePage.checkTitle(driver);

        // 4 - Perform Login
        homePage.login(PITER_CHAILOVSKII);

        // 5 - Assert User name in the left-top side of screen that user logged in
        homePage.checkSingedInUsername(PITER_CHAILOVSKII);

        // 6 - Assert Browser title
        homePage.checkTitle(driver);

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
