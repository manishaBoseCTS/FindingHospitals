# Hospital Finder Automation Project

## Overview
The goal of this hackathon was to automate key user interactions on the **Policybazaar website** using a scalable and modular framework. The project showcases a combination of BDD principles, robust data-driven testing, and end-to-end UI validations across several features of the site.

---

## Technologies and Tools

- **BDD & Gherkin**: Feature files written using Gherkin syntax to describe behaviors in a human-readable format.
- **Cucumber**: Integrated for parsing feature files and linking them with test definitions.
- **Selenium WebDriver**: For browser automation including scrolling, clicking, hovering, and tab management.
- **TestNG**: For test execution, reporting, and assertion control.
- **Apache POI**: Used to implement Excel-based data-driven testing.
- **XML Configuration**: Employed to externalize environment-specific data and parameters.

---

## Test Modules and Scenarios

### 1. **Hospital Search Functionality**
- Validates hospital search in Bangalore
- Filters results by:
  - 24/7 availability
  - Parking facility
  - Rating above 3.5
- Writes hospital names to Excel for reporting

### 2. **Doctor Search**
- Searches doctors based on location and specialization
- Data sourced from Excel (Sheet: `TC002`)
- Stores doctor names in `TC002_Output`

### 3. **Video Consultation Pricing**
- Extracts available consultation types
- Retrieves and stores corresponding price details

### 4. **User Login Authentication**
- Performs login using multiple credentials
- Input data driven from Excel sheet

### 5. **Health Article Search**
- Searches articles using keywords from Excel (`TC005`)
- Captures article titles and authors
- Saves results in `TC005_Output`

### 6. **Social Media Link Verification**
- Verifies if footer social media links (Facebook, LinkedIn, YouTube, GitHub) open correctly
- Captures screenshots for each link

### 7. **Corporate Wellness Demo Request**
- Navigates to corporate section
- Fills and submits demo form using XML data
- Captures CAPTCHA screen post-submission

### 8. **Book Tests – TopCities Extraction**
- Authenticates user login
- Fetches TopCities list from Book Tests section
- Stores cities in Excel sheet `TC008_Output`

---

## Implementation Details

### Framework Structure
- **Modular Page Object Model (POM)** for UI elements.
- **Reusable Utility Classes** for WebDriver setup, actions, waits, and data handling.
- **Custom Assertion Wrappers** integrated into step definitions.
- XPath locators refined using dynamic strategies, including:
  - Ancestor traversal
  - Attribute-based matching
  - Conditional logic via `and`/`or`

### Data Sources
- **Excel Sheets**: Used for input combinations in calculators and dynamic field validations.
- **XML Files**: Capture environment details, URLs, and credentials.

### Test Execution & Reporting
- **TestNG XML Suite** executes targeted regression tests.
- Console-based reporting integrated for visibility.
- Test status logs captured via configured listeners.

---

## Contributors
- Manisha  
- Naitik  
- Eskiha  
- Ayush  
- Tamana

---

## Outcome

- Reduced manual effort with full functional coverage.
- Scalable and maintainable automation suite designed for real-time regression.

