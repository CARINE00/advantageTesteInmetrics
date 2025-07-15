package steps;

import io.cucumber.java.pt.*;
import org.openqa.selenium.WebDriver;
import pages.CheckoutPage;
import utils.DriverFactory;
import utils.LoggerUtil;
import org.apache.logging.log4j.Logger;


public class CheckoutSteps {

    WebDriver driver = DriverFactory.getDriver();
    CheckoutPage checkoutPage = new CheckoutPage(driver);
    Logger log = LoggerUtil.getLogger(CheckoutSteps.class);


    @Quando("pesquisa por {string}")
    public void pesquisaProduto(String termo) {
        log.info("Pesquisando por produto: {}", termo);
        checkoutPage.selecionarProdutoPorNome(termo);
    }

    @E("adiciona o produto ao carrinho {string}")
    public void adicionaAoCarrinho(String nome) throws InterruptedException {
        log.info("Adicionando produto ao carrinho: {}", nome);
        checkoutPage.selecionarProdutoPorNome(nome);
        checkoutPage.clicarPorName("save_to_cart");
    }

    @E("acessa o carrinho")
    public void verificaCarrinho() throws InterruptedException {
        log.info("Acessando o carrinho de compras.");
        checkoutPage.verificarCarrinho();
    }

    @Quando("finaliza a compra")
    public void finalizaCompra() throws InterruptedException {
        log.info("Iniciando processo de finalização da compra.");
        checkoutPage.clicarComId("checkOutButton");
        checkoutPage.clicarComId("next_btn");
        checkoutPage.finalizarCompra();
        checkoutPage.clicarComId("pay_now_btn_SAFEPAY");

    }

    @Entao("o sistema deve finalizar a compra com sucesso")
    public void validarCompraComSucesso() {
        log.info("Validando mensagem de sucesso da compra.");
        CheckoutPage.validarMensagem("Thank you for buying with Advantage");
    }


    @Entao("o sistema deve exibir uma mensagem informando que o carrinho está vazio")
    public void validarCarrinhoVazio() {
        log.info("Verificando se o carrinho está vazio.");
        CheckoutPage.validarMensagem("Your shopping cart is empty");
    }

    @Entao("o sistema deve exibir o resumo do pedido com produto, quantidade e valor total")
    public void validarResumoDoPedido() {
        log.info("Verificando resumo do pedido.");

        checkoutPage.resumoPedido();
    }

    @Entao("o sistema deve solicitar que o usuário realize login antes de continuar")
    public void validarLoginObrigatorio() throws InterruptedException {
        log.info("Verificando login obrigatorio");
        checkoutPage.clicarComId("checkOutButton");
        CheckoutPage.validarMensagem("Already have an account?");
    }

    @Quando("tenta finalizar a compra sem preencher os campos obrigatórios de pagamento")
    public void finalizarSemPreencherPagamento() throws InterruptedException {
        log.info("Verificando campos de pagamento vazios.");
        checkoutPage.clicarComId("checkOutButton");
        checkoutPage.clicarComId("next_btn");
        checkoutPage.finalizarCompraSemDadosdoCartao();
    }

    @Entao("o sistema deve exibir mensagens de erro nos campos obrigatórios")
    public void validarErrosCamposPagamento() {
        log.info("Verificando mensagem de erro nos campos de pagamento.");
        CheckoutPage.validarMensagem("SafePay username field is required");
        CheckoutPage.validarMensagem("SafePay password field is required");
    }
    @Quando("acessa a etapa de pagamento")
    public void acessaEtapaPagamento() throws InterruptedException {

        checkoutPage.clicarComId("checkOutButton");
        checkoutPage.clicarComId("next_btn");

    }
    @Entao("o sistema deve exibir as opções disponíveis")
    public void exibirOpçõesDisponíveis() {
        log.info("Verificando opcoes de pagamento disponiveis.");
       CheckoutPage.validarOpcoesPagamento("safepay");
       CheckoutPage.validarOpcoesPagamento("masterCredit");


    }
    @Entao("permitir a seleção de um dos métodos")
    public void permitirSeleçãoMétodos() {
        log.info("Verificando seleção de metodos.");
        checkoutPage.selecionarMetodoPagamento("masterCredit");

    }
    @E("deslogo do site")
    public void deslogo_do_site() throws InterruptedException {
        log.info("Verificando logout");
        checkoutPage.clicarComId("menuUserLink");
        checkoutPage.clicarComXpath("Sign out");
    }

}



