package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import utils.DatabaseHelper;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "html:target/cucumber-reports/cucumber-report.html"

        },
        monochrome = true
)
public class TestRunner {
        @AfterClass
        public static void salvarRelatorioFinal() {
                DatabaseHelper.insertReportFromFile("target/cucumber-reports/cucumber-report.html");
        }
}
