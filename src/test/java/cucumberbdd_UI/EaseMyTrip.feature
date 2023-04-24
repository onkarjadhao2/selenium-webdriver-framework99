Feature: Ease My Trip Flight Booking

  Scenario Outline: User Booked One Way Flight
    Given Open the browser for Ease My Trip
    And User is on the Ease my trip website
    When User click on One Way Option
    And User-filled travel details for <Origin> to <Destination>
    And User click on the search button
    And User navigated to search result page
    And User click on book button
    And User navigated to the flight details page
    And User filled the details and click on continue booking
    Then User complete the payment process

    Examples: 
      | Origin | Destination |
      | Mumbai | Delhi       |

  Scenario: User Booked Round Trip Flight
    Given Open the broser for Ease My Trip for Round Trip
    And User is on the Ease My trip website for Round Trip
    When User click on the Round Trip Option
    And User fillup all travel details for Round Trip
    And User click on the Search button for Round Trip
    And User is navigated to search result page for round trip
    And User select the flights and click on book button
    And User filled the details and click on continue booking for round trip
    Then User complete the payment process for round trip
@raw
  Scenario: E-Commerce end-to-end flow
    Given Open the browser for E-commarce site
    And User is on the website
    And User complete the login process
    When User enter the item name and search the product
    And User add the product into the cart
    And fillup all the required address details and payment details
    Then User assert the order details
