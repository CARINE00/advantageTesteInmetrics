package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.ApiHelper;
import utils.DriverFactory;
import utils.Usuario;

import static utils.DriverFactory.getDriver;

public class Hooks {
    private static boolean isLogged = false;
    private static Usuario usuario; // <-- reutilizável
    private WebDriver driver;

    @Before
    public void setUp(Scenario scenario) {
        driver = DriverFactory.getDriver();
        driver.get("https://advantageonlineshopping.com/#/");

        LoginPage loginPage = new LoginPage(driver);
        if (!isLogged && scenario.getSourceTagNames().contains("@login")) {
            if (usuario == null) {
                usuario = ApiHelper.gerarUsuarioViaApi(); // cadastro apenas 1 vez

                loginPage.realizarLogin(usuario.getLogin(), usuario.getSenha());
            }



            isLogged = true;
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "evidência");
        DriverFactory.quitDriver();
    }
}
