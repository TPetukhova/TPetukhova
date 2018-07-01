Feature: User Table Page

  Scenario: User Table Page test
    Given I am on Home Page
    And I am logged in as Piter Chailovskii
    When I open User Table page through the header menu Service -> User Table
    Then I am on User Table page
    And User Table page is active
    And User Table's page interface contains correct elements

    When I check Number and User columns of Users table
    Then User table contains correct values for numbers and users
      | Number | User             |
      | 1      | Roman            |
      | 2      | Sergey Ivan       |
      | 3      | Vladzimir        |
      | 4      | Helen Bennett     |
      | 5      | Yoshi Tannamuri  |
      | 6      | Giovanni Rovelli |

    When I check Description columns of User table
    Then All cells of 'Description' column contain text
      | Number | Description                  |
      | 1      | Lorem ipsum                  |
      | 2      | Lorem ipsum                  |
      | 3      | Lorem ipsum                  |
      | 4      | Lorem ipsum some description |
      | 5      | Lorem ipsum some description |
      | 6      | Lorem ipsum some description |

    When I set 'vip' status to Sergey Ivan
    Then 'Log' section shows a log row in format: Vip: condition changed to true

    When I click on dropdown in column Type for user Roman
    Then Droplist contains values
      | Dropdown values |
      | Admin           |
      | User            |
      | Manager         |
