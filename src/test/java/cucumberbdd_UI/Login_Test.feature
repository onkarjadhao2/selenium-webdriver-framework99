Feature: Login Functionality
  Scenario Outline: Test Login Functionality(Positive)
    Given browser is open for login
    And User is on login page
    When User enters <username> and <password>
    And user clicks in login button
    Then user is navigated to home page

    Examples: 
      | username                  | password  |
      | markwill@gamil.com        | Markwill@1234 |

  Scenario Outline: Test Login Functionality(Negative)
    Given browser is open for invalid user
    And User is on login page for invalid user
    When User filled <username> and <password> for invalid user
    And user clicks in login button for invalid user
    Then user get error

    Examples: 
      | username                | password   |
      | jonhgardner@gamil.com   | john@1234 |
