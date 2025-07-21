@echo off
echo Generating Allure report...
cd /d "%~dp0"
allure generate allure-results --clean -o allure-report && allure open allure-report
pause
