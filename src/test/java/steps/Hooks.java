package steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.ApiHelper;
import utils.DatabaseHelper;
import utils.DriverFactory;
import utils.Usuario;

import java.nio.file.Files;
import java.nio.file.Paths;

import static utils.DriverFactory.getDriver;

public class Hooks {
    private static boolean isLogged = false;
    private static Usuario usuario; // <-- reutilizável
    private WebDriver driver;

    @Before
    public void setUp(Scenario scenario) {
        DatabaseHelper.initializeDatabase();
        driver = DriverFactory.getDriver();
        driver.get("https://advantageonlineshopping.com/#/");


        boolean precisaLogin = scenario.getSourceTagNames().stream()
                .anyMatch(tag -> tag.equalsIgnoreCase("@login"));

        if (precisaLogin) {
            if (usuario == null) {
                usuario = ApiHelper.gerarUsuarioViaApi();
                if (usuario == null || usuario.getLogin() == null || usuario.getSenha() == null) {
                    throw new IllegalStateException("Usuário gerado está inválido.");
                }
            }

            // Verifica se o usuário já está logado pelo nome de usuário exibido na tela
            boolean usuarioLogado = false;
            try {
                String nomeExibido = driver.findElement(By.id("menuUserLink")).getText();
                usuarioLogado = nomeExibido.equalsIgnoreCase(usuario.getLogin());
            } catch (Exception e) {
                // Elemento não encontrado ou texto diferente => não logado
                usuarioLogado = false;
            }

            if (!usuarioLogado) {
                LoginPage loginPage = new LoginPage(driver);
                loginPage.realizarLogin(usuario.getLogin(), usuario.getSenha());
            }
        }

    }

    @After
    public void tearDown(Scenario scenario) {
        //Gravar informação no banco de dados
        String status = scenario.isFailed() ? "FAILED" : "PASSED";
        DatabaseHelper.insertExecution(scenario.getName(), status);

        // Tirar print
        byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "evidência");

        //Fechar navegador
        DriverFactory.quitDriver();
    }

}
