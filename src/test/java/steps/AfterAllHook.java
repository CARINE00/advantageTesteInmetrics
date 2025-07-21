package steps;

import io.cucumber.java.AfterAll;
import utils.DatabaseHelper;

public class AfterAllHook {

    @AfterAll
    public static void salvarRelatorioFinal() {
        DatabaseHelper.insertReportFromFile("target/cucumber-reports/Cucumber.html");
    }
}
