package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void acessarTelaLogin() {

        wait.until(ExpectedConditions.elementToBeClickable(By.id("menuUser"))).click();
    }

    public void preencherUsuario(String usuario) {
        driver.findElement(By.name("username")).sendKeys(usuario);
    }

    public void preencherSenha(String senha) {
        wait.until(ExpectedConditions.elementToBeClickable(By.name("password"))).sendKeys(senha);
    }

    public void clicarEmLogin() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loader")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("sign_in_btn"))).click();
    }

    public void validarLogin() {

        WebElement iconeUsuario = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menuUserLink")));
        assert iconeUsuario.isDisplayed();

    }

    public void exibirUsuarioLogado( String nomeUsuario){

        WebElement usuarioLogado = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@id='menuUserLink']/span")));
        Assert.assertEquals("Usuário logado diferente do esperado!", nomeUsuario, usuarioLogado.getText());
    }

    public void realizarLogin(String usuario, String senha) {
        acessarTelaLogin();
        preencherUsuario(usuario);
        preencherSenha(senha);
        clicarEmLogin();
        validarLogin();
    }
}

