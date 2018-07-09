package hw4;

import base.hw4.SelenideTestBase;
import enums.hw4.Colors;
import enums.hw4.Elements;
import enums.hw4.Metals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.hw4.DifferentElementsPage;
import pageObjects.hw4.HomePage;

import static com.codeborne.selenide.Selenide.page;
import static enums.hw4.Users.PITER_CHAILOVSKII;

public class DifferentElementsPageInterface extends SelenideTestBase {

    private HomePage homePage;
    private DifferentElementsPage differentElementsPage;

    @BeforeClass
    public void beforeClass() {
        homePage = page(HomePage.class);
    }

    @Test
    public void HomePageAndDifferentElementsLayoutsTest() {

        // 1 - Open test site by URL
        homePage.openPage();

        // 2 - Assert Browser title
        homePage.checkPageTitle();

        // 3 - Perform login
        homePage.login(PITER_CHAILOVSKII);

        // 4 - Assert User name in the left-top side of screen that user is logged in
        homePage.checkSingedInUsername(PITER_CHAILOVSKII);

        // 5 - Check interface on Home page, it contains all needed elements
        homePage.checkImagesDisplayed();
        homePage.checkTextItems();
        homePage.checkLongHeaderText();
        homePage.checkShortHeaderText();

        // 6 - Click on "Service" subcategory in the header and check that dropdown contains options
        homePage.expandServiceMenuDropdown();
        homePage.checkServiceMenuItems();

        // 7 - Click on "Service" subcategory in the left section and check that dropdown contains options
        homePage.expandServiceMenuDropdownLeftSection();
        homePage.checkServiceMenuItemsOnLeftSection();

        // 8 - Open through the header menu Service -> Different Elements page
        differentElementsPage = homePage.openDifferentElementsPage();
        differentElementsPage.checkPageTitle();

        // 9 - Check interface on Different Elements page, it contains all needed elements
        differentElementsPage.checkCheckboxes();
        differentElementsPage.checkRadioButtons();
        differentElementsPage.checkDropdown();
        differentElementsPage.checkButtons();

        // 10 - Assert there is Right Section
        differentElementsPage.checkRightSection();

        // 11 - Assert there is Left Section
        differentElementsPage.checkLeftSection();

        // 12 - Select Checkboxes
        differentElementsPage.setCheckboxState(Elements.Water, true);
        differentElementsPage.setCheckboxState(Elements.Wind, true);

        // 13 - Assert that for each checkbox there is an individual log row and value is corresponded to the value of checkbox
        differentElementsPage.checkLogs(Elements.Water, true);
        differentElementsPage.checkLogs(Elements.Wind, true);

        // 14 - Select radio button
        differentElementsPage.selectRadioButton(Metals.Selen);

        // 15 - Assert that for radio button there is a log row and value is corresponded to the status of button
        differentElementsPage.checkLogs(Metals.Selen);

        // 16 - Select in dropdown
        differentElementsPage.selectDropdown(Colors.Yellow);

        // 17 - Assert that for dropdown there is a log row and value is corresponded to the selected value
        differentElementsPage.checkLogs(Colors.Yellow);

        // 18 - Unselect and assert checkboxes
        differentElementsPage.setCheckboxState(Elements.Water, false);
        differentElementsPage.setCheckboxState(Elements.Wind, false);
        differentElementsPage.checkLogs(Elements.Water, false);
        differentElementsPage.checkLogs(Elements.Wind, false);

    }
}
