Feature: Social Media Links Verification

  Scenario: Verify all social links open correctly and capture screenshots
    Given User is on the website and scrolls to footer
    Then User verifies and captures the screenshot of "facebook" social link
    
    Then User verifies and captures the screenshot of "linkedin" social link
    Then User verifies and captures the screenshot of "youtube" social link
    Then User verifies and captures the screenshot of "github" social link
