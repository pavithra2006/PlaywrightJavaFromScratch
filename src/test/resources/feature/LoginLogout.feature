Feature: Login

  Scenario: Verify successful Login
    When User logs in with username "Admin" and password "admin123"
    Then Dashboard should be displayed

  Scenario: Verify successful logout
    When User logs in with username "Admin" and password "admin123"
    Then Dashboard should be displayed
    And Verify logout is successful