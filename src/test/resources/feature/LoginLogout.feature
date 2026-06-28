Feature: Login

  Scenario: Verify successful Login
    When User logs in with username "Admin3" and password "admin123"
    Then Dashboard should be displayed

  Scenario: Verify successful logout
    When User logs in with username "Admin" and password "admin123"
    Then Dashboard should be displayed
    And Verify logout is successful

  Scenario Outline: Verify invalid Login

    When User logs in with username "<username>" and password "<password>"
    Then Verify login fails with toast message "Invalid credentials"
    Examples:
      | username | password |
      | Admin1   | admin123 |
      | Admin2   | admin123 |
      | User1    | pass123  |