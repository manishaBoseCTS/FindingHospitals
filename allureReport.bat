@echo off
echo Generating Allure report...
cd C:\Users\2403730\eclipse-workspace\FindingHospitals-main\allureReport.bat
allure generate allure-results --clean -o allure-report && allure open allure-report
pause