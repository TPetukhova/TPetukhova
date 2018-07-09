package pageObjects.hw6;

import cucumber.api.java.After;

import static com.codeborne.selenide.Selenide.close;

public class Hooks {

        @After
        public void afterScenario(){
            close();
        }

}
