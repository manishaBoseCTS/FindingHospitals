Feature: Verify hospital search functionality

  Scenario: Load hospital search data and retrieve matching hospital names
    Given User is on the Home Page
    And User loads hospital search data from Excel
    When User enters city from sheet TC001
    And User enters search place as the search value from TC001
    Then User retrieves the total number of results
    And User fetches hospital names open at open time with rating
    And User writes hospital names to Excel
