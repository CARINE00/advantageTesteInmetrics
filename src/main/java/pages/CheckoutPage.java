package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ApiHelper;
import utils.Usuario;


import java.lang.String;
import java.time.Duration;


public class CheckoutPage {

    static WebDriver driver;
    static WebDriverWait wait;
    Usuario usuario = ApiHelper.gerarUsuarioViaApi();

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    public void adicionarProduto(String nomeProduto) throws InterruptedException {

        wait.until(driver -> driver.findElement(By.linkText(nomeProduto))).click();

    }
    public void clicarPorName(String name) {

        wait.until(driver -> driver.findElement(By.name(name))).click();
    }
    public void verificarCarrinho() throws InterruptedException {
        Thread.sleep(3000);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loader")));

        wait.until(driver -> driver.findElement(By.id("menuCart"))).click();
    }

    public void finalizarCompra() throws InterruptedException {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loader")));

        // Confirmar método de pagamento (ex: "Safepay")
        wait.until(driver -> driver.findElement(By.name("safepay"))).click();
        WebElement preencherUsername = wait.until(ExpectedConditions.elementToBeClickable(By.name("safepay_username")));
        preencherUsername.sendKeys(usuario.getLogin());
        WebElement preencherPassword = wait.until(ExpectedConditions.elementToBeClickable(By.name("safepay_password")));
        preencherPassword.sendKeys("Teste123");

    }


    public static void validarMensagem(String mensagem) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + mensagem + "')]")));
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + mensagem + "')]")));
        Assert.assertTrue("Mensagem não visível na página.", msg.isDisplayed());

    }

    public void selecionarProdutoPorNome(String nomeProduto) {

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'" + nomeProduto + "')]"))).click();
    }


    public void clicarComId(String id) throws InterruptedException {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loader")));
       Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).click();
    }
    public void clicarComXpath(String xpath) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loader")));
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[contains(text(),'" + xpath + "')]"))));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'" + xpath + "')]"))).click();
    }


    public boolean resumoPedido() {
        WebElement produto = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".productName")));
        WebElement quantidade = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".quantity")));
        WebElement total = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".price")));

        return produto.isDisplayed() && quantidade.isDisplayed() && total.isDisplayed();
    }

    public void finalizarCompraSemDadosdoCartao() throws InterruptedException {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loader")));

        // Confirmar método de pagamento (ex: "Safepay")
        wait.until(driver -> driver.findElement(By.name("safepay"))).click();
        WebElement preencherUsername = wait.until(ExpectedConditions.elementToBeClickable(By.name("safepay_username")));
        preencherUsername.sendKeys(Keys.TAB);
        WebElement preencherPassword = wait.until(ExpectedConditions.elementToBeClickable(By.name("safepay_password")));
        preencherPassword.sendKeys(Keys.TAB);

    }


    public void selecionarMetodoPagamento(String metodo) {
        WebElement radioBtn = driver.findElement(By.xpath("//*[contains(@name,"+ metodo +")]"));
        radioBtn.click();

    }

    public static void validarOpcoesPagamento(String mensagem) {
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@name," + mensagem + ")]")));
        Assert.assertTrue("Mensagem não visível na página.", msg.isDisplayed());

    }
}
