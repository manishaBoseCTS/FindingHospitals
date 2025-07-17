Feature: Search for nearby doctors

  Scenario: Search doctors by location and type from Excel
    Given User navigates to the application
    When User searches for doctors using data from sheet "TC002"
    Then Doctor names should be saved to sheet "TC002_Output"
