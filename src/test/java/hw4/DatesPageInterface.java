package hw4;

import PageObjects.hw4.DatesPage;
import PageObjects.hw4.HomePage;
import base.hw4.SelenideTestBase;
import enums.hw4.Users;
import org.testng.annotations.Test;

public class DatesPageInterface extends SelenideTestBase {

    private HomePage homePage;
    private DatesPage datesPage;

    @Test
    public void datesPageTest() {
        // 1 - open web page in browser and check page title
        homePage = new HomePage();
        homePage.openPage();
        homePage.checkPageTitle();

        // 2 - perform login
        homePage.login(Users.PITER_CHAILOVSKII);
        homePage.checkSingedInUsername(Users.PITER_CHAILOVSKII);

        // 3 - open Dates page
        datesPage = homePage.openDatesPage();
        datesPage.checkPageTitle();

        // 4 - Set sliders from 0 to 100
        datesPage.setLeftSlider(0);
        datesPage.setRightSlider(100);
        datesPage.checkLeftSliderValueIs(0);
        datesPage.checkRightSliderValueIs(100);

        // 5 - Set sliders from 0 to 0
        datesPage.setLeftSlider(0);
        datesPage.setRightSlider(0);
        datesPage.checkLeftSliderValueIs(0);
        datesPage.checkRightSliderValueIs(0);

        // 6 - Set sliders from 100 to 100
        datesPage.setRightSlider(100);
        datesPage.setLeftSlider(100);
        datesPage.checkLeftSliderValueIs(100);
        datesPage.checkRightSliderValueIs(100);

        // 7 - Set sliders from 30 to 70
        datesPage.setLeftSlider(30);
        datesPage.setRightSlider(70);
        datesPage.checkLeftSliderValueIs(30);
        datesPage.checkRightSliderValueIs(70);

    }
}
