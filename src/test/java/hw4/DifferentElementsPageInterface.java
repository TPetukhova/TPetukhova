package hw4;

import PageObjects.hw4.DifferentElementsPage;
import PageObjects.hw4.HomePage;
import base.hw4.SelenideTestBase;
import enums.hw4.Colors;
import enums.hw4.Elements;
import enums.hw4.Metals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static enums.hw4.Users.PITER_CHAILOVSKII;

public class DifferentElementsPageInterface extends SelenideTestBase {

    private HomePage homePage;
    private DifferentElementsPage differentElementsPage;

    @BeforeMethod
    public void beforeMethod() {
        homePage = new HomePage();
    }

    @Test
    public void HomePageAndDifferentElementsLayoutsTest() {
        // 1 - open web page in browser and check page title
        homePage.openPage();
        homePage.checkPageTitle();

        // 2 - perform login
        homePage.login(PITER_CHAILOVSKII);
        homePage.checkSingedInUsername(PITER_CHAILOVSKII);

        // 3 - check images and text on the page
        homePage.checkImagesDisplayed();
        homePage.checkTextItems();
        homePage.checkLongHeaderText();
        homePage.checkShortHeaderText();

        // 4 - check Service menu on the header
        homePage.checkServiceMenuDropdownValuesHeader();

        // 5 - check Service menu on the Left Panel
        homePage.checkServiceMenuDropdownValuesLeftSection();

        // 6 - open Different Elements page
        differentElementsPage = homePage.openDifferentElementsPage();
        differentElementsPage.checkPageTitle();

        // 7 - check Checkboxes
        differentElementsPage.checkCheckboxes();

        // 8 - check radio buttons
        differentElementsPage.checkRadioButtons();

        // 9 - check dropdown values and buttons
        differentElementsPage.checkDropdown();
        differentElementsPage.checkButtons();

        // 10 - check right and left sections
        differentElementsPage.checkRightSection();
        differentElementsPage.checkLeftSection();

        // 11 - select Water and Wind checkboxes, check logs
        differentElementsPage.selectCheckbox(Elements.Water);
        differentElementsPage.selectCheckbox(Elements.Wind);
        differentElementsPage.checkLogs(Elements.Water, true);
        differentElementsPage.checkLogs(Elements.Wind, true);

        // 12 - select radio buttons, check logs
        differentElementsPage.selectRadioButton(Metals.Selen);
        differentElementsPage.checkLogs(Metals.Selen);

        // 13 - select value in dropdown
        differentElementsPage.selectDropdown(Colors.Yellow);
        differentElementsPage.checkLogs(Colors.Yellow);

        // 14 - undo selection of checkboxes, check logs
        differentElementsPage.unSelectCheckbox(Elements.Water);
        differentElementsPage.unSelectCheckbox(Elements.Wind);
        differentElementsPage.checkLogs(Elements.Water, false);
        differentElementsPage.checkLogs(Elements.Wind, false);

    }
}
