package hw3;

import PageObjects.hw3.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static enums.hw3.Users.PITER_CHAILOVSKII;

public class Exercise1 {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @AfterMethod
    public void afterMethod(){
        driver.close();
    }

    @Test
    public void JDIWebPageTest() {

        // 2 - Open test site by URL
        homePage.open();

        // 3 - Assert Browser title
        homePage.checkTitle();

        // 4 - Perform Login
        homePage.login(PITER_CHAILOVSKII.login,PITER_CHAILOVSKII.password);

        // 5 - Assert User name in the left-top side of screen that user logged in
        homePage.checkSingedInUsername(PITER_CHAILOVSKII.name);

        // 6 - Assert Browser title
        homePage.checkTitle();

        // 7 - Assert that there are 4 items on the header section are displayed and they have proper text
        homePage.checkMenuItemsText(Arrays.asList("HOME", "CONTACT FORM", "SERVICE","METALS & COLORS"));

        // 8 - Assert there are 4 images on the Index Page and they are displayed
        homePage.checkImagesDisplayed();

        // 9 - Assert that there are 4 texts on the Index Page under icons and they have proper text
        homePage.checkTextItems(Arrays.asList("To include good practices\n" + "and ideas from successful\n" +
                        "EPAM project", "To be flexible and\n" + "customizable","To be multiplatform",
                "Already have good base\n" + "(about 20 internal and\n" +
                        "some external projects),\n" + "wish to get more…"));

        // 10 - Assert the text of the main headers
        homePage.checkShortHeaderText("EPAM FRAMEWORK WISHES…");
        homePage.checkLongHeaderText("LOREM IPSUM DOLOR SIT AMET, " +
                "CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. " +
                "UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI " +
                "UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT " +
                "IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.");

        // 11 - Assert the text of the sub header
        homePage.checkSubHeaderText("JDI GITHUB");

        // 12 - Assert that JDI Github is a link and has a proper URL
        homePage.checkSubHeaderLink("https://github.com/epam/JDI");

        // 13 - Assert that there is Left Section
        homePage.checkLeftSectionShown();

        // 14 - Assert that there is Footer
        homePage.checkFooterShown();

    }

}
