package hw4;

import base.hw4.SelenideTestBase;
import enums.hw4.Sliders;
import enums.hw4.Users;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.hw4.DatesPage;
import pageObjects.hw4.HomePage;

import static com.codeborne.selenide.Selenide.page;

public class DatesPageInterface extends SelenideTestBase {

    private HomePage homePage;
    private DatesPage datesPage;

    @BeforeClass
    public void beforeClass() {
        homePage = page(HomePage.class);
    }

    @Test
    public void datesPageTest() {
        // 1 - Open test site by URL
        homePage.openPage();

        // 2 - Assert Browser title
        homePage.checkPageTitle();

        // 3 - Perform login
        homePage.login(Users.PITER_CHAILOVSKII);

        // 4 - Assert username in the left-top side of screen that user is logged in
        homePage.checkSingedInUsername(Users.PITER_CHAILOVSKII);

        // 5 - Open through the header menu Service -> Dates Page
        datesPage = homePage.openDatesPage();
        datesPage.checkPageTitle();

        // 6 - Using drag-and-drop set Range Sliders from 0 to 100
        datesPage.setLeftSlider(0);
        datesPage.setRightSlider(100);

        // 7 - Assert that for "From" and "To" sliders there are logs row with corresponding values
        datesPage.checkLogs(Sliders.From, 0);
        datesPage.checkLogs(Sliders.To, 100);

        // 8 - Using drag-and-drop set Range Sliders from 0 to 0
        datesPage.setLeftSlider(0);
        datesPage.setRightSlider(0);

        // 9 - Assert that for "From" and "To" sliders there are logs row with corresponding values
        datesPage.checkLogs(Sliders.From,0);
        datesPage.checkLogs(Sliders.To,0);

        // 10 - Using drag-and-drop set Range Sliders from 100 to 100
        datesPage.setRightSlider(100);
        datesPage.setLeftSlider(100);


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 11 - Assert that for "From" and "To" sliders there are logs row with corresponding values
        datesPage.checkLogs(Sliders.From,100);
        datesPage.checkLogs(Sliders.To,100);

        // 12 - Using drag-and-drop set Range Sliders from 30 to 70
        datesPage.setLeftSlider(30);
        datesPage.setRightSlider(70);

        // 13 - Assert that for "From" and "To" sliders there are logs row with corresponding values
        datesPage.checkLogs(Sliders.From,30);
        datesPage.checkLogs(Sliders.To,70);

    }
}
