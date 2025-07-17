Feature: Submit corporate health demo request form

  Scenario: Fill and submit health and wellness form to capture CAPTCHA screen
    Given User is on the Home Page
    When User clicks the "For Corporates" link
    And User clicks the "For Health and Wellness" link
    And The form page is loaded completely
    And User fills the form with values from XML
    And User submits the demo request
    Then CAPTCHA screen should be displayed
    And A screenshot is captured and saved
