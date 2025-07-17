Feature: Fetch and store TopCities from Book Tests section

  Scenario: User logs in and saves TopCities to Excel
    Given User logs into the application with valid credentials
    When User navigates to Book Tests and fetches TopCities
    Then TopCities should be written to Excel sheet "TC008_Output"
