package steps;

import io.cucumber.java.pt.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.ApiHelper;
import utils.DriverFactory;
import utils.Usuario;

public class LoginSteps {

    WebDriver driver = DriverFactory.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    Usuario usuario = ApiHelper.gerarUsuarioViaApi();

    @Dado("que o usuário esta logado")
    public void usuarioLogado() {
        loginPage.acessarTelaLogin();
        loginPage.preencherUsuario(usuario.getLogin());
        loginPage.preencherSenha(usuario.getSenha());
        loginPage.clicarEmLogin();
    }

    @Quando("o sistema deve autenticar o usuário")
    public void validarLoginComSucesso() {
        loginPage.validarLogin();
    }

    @Então("exibir o nome do usuário logado no topo da página")
    public void exibirUsuarioLogado() {
        loginPage.exibirUsuarioLogado(usuario.getLogin());
    }
}
