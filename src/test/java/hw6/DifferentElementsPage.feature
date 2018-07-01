Feature: Home Page and Different Elements Page

  Scenario: Home Page and Different Elements Page layout
    Given I am on Home Page
    Then Page Title is Home Page

    When I login as PITER_CHAILOVSKII user
    Then Correct username of PITER_CHAILOVSKII is shown
    And Home Page contains correct elements
    And Service Menu is available and contains correct items

    When Different Elements page is open from Header menu
    Then Different Elements page is displayed
    And Page Title is Different Element
    And Different Elements page contains correct elements
    And Different Elements page contains side sections

    When I select Water checkbox
    And I select Wind checkbox
    Then Logs contain entry: Element Water set to true
    And Logs contain entry: Element Wind set to true

    When Radiobutton Selen is selected
    Then Logs contain entry: Radiobutton Selen is selected

    When Dropdown Yellow is chosen
    Then Logs contain entry: Yellow is chosen

    When I unselect Water checkbox
    And I unselect Wind checkbox
    Then Logs contain entry: Element Water set to false
    And Logs contain entry: Element Wind set to false
