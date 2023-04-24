Feature: API_Automation

  Scenario Outline: User get the data
    Given Launch the browser and navigate to the website
    And User fetch the data from <url>
    Then User verify the <statusCode>

    Examples: 
      | url        | statusCode |
      | /api/users |        200 |
      
  Scenario Outline: User get the data for list users
    Given User get the data from <url> for list users
    Then Verify the <statusCode> and body content for list users

    Examples: 
      | url          | statusCode |
      | /api/unknown |        200 |

  Scenario: User create new id with unique name and job name
    Given User is on the base uri
    When User submit create post request with unique name and job name
    Then API returns the response with status code as 201
    And new id is created in the system
    And Browser closed
