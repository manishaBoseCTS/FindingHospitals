Feature: Search and store health articles

  Scenario: Search for health articles and save title-author pairs
    Given User is on the Home Page for article search
    And User loads article search term from Excel sheet TC005
    When User clicks on "Read Articles" link
    And User switches to the newly opened article window
    And User searches for the term from sheet TC005
    Then User extracts article titles and authors
    And User writes extracted data to Excel sheet TC005_Output
