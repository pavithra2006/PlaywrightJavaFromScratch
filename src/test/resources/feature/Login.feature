Feature: Login

  Scenario: Valid Login

    When User logs in with username "Admin" and password "admin123"

    Then Dashboard should be displayed