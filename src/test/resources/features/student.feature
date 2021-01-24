## mvn clean verify -Dtestsuite="StudentRunner" -Dcucumber.options="src/test/resources/features" -Dparallel.tests=1
Feature: Testing different request on the student application

  @feature=Login
  Scenario: Check if the student application can be accessed by users
    When User sends a Get request to the list endpoint, they must get back a valid status 200

  @SMOKE
  Scenario Outline: Create a new student & verify if the student is added
    When I create a new student by providing the information firstName <firstName> lastName <lastName> email <email> programme <programme> courses <courses>
    Then I verify that the student with <email> is created

    Examples:
      | firstName | lastName | email                                     | programme        | courses |
      | Declan    | Smith    | nnon.ante.bibendum@risusDonecegestas.edu  | Computer Science | Java    |
      | Mark      | Taylor   | nnon2.ante.bibendum@risusDonecegestas.edu | Computer Science | Java    |