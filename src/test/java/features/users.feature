Feature: Reqres API Testing with Rest Assured and Cucumber

  Scenario: Get user details
    Given the API is available
    When I send a GET request to "/api/users/2"
    Then the response status code should be 200
    And the response should contain user details

  Scenario: Create a new user
    Given the API is available
    When I send a POST request to "/api/users" with the following data:
      | name  | job  |
      | John  | QA   |
    Then the response status code should be 201
    And the response should contain "name" as "John"
    And the response should contain "job" as "QA"

  Scenario: Update user details
    Given the API is available
    When I send a PUT request to "/api/users/2" with the following data:
      | name  | job       |
      | John  | Manager   |
    Then the response status code should be 200
    And the response should contain "name" as "John"
    And the response should contain "job" as "Manager"

  Scenario: Delete a user
    Given the API is available
    When I send a DELETE request to "/api/users/2"
    Then the response status code should be 204
