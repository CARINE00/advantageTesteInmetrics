echo "🧪 Gerando e abrindo relatório Allure..."
allure generate target/allure-results -o target/allure-report --clean
allure open target/allure-report

#@echo off
#echo 🧪 Gerando e abrindo relatório Allure...
#allure generate target\allure-results -o target\allure-report --clean
#allure open target\allure-report
#pause