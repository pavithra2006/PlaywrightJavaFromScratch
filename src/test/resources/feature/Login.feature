Feature: Login

  Scenario: Valid Login

    Given User launches application

    When User enters username "Admin"

    And User enters password "admin123"

    And User clicks login

    Then Dashboard should be displayed