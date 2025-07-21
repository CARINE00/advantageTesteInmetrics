
# 🧪 Projeto de Automação Web – Advantage Online Shopping

Este projeto foi desenvolvido como parte do **Desafio Automação Web**, com foco em aplicar práticas modernas de automação de testes utilizando Gherkin, Selenium, Cucumber, integração com API, geração de logs, banco de dados e relatórios com Allure.

---

## ✅ Tecnologias e Ferramentas Utilizadas

| Ferramenta         | Versão         | Finalidade                          |
|--------------------|----------------|-------------------------------------|
| Java               | 21             | Linguagem de programação            |
| Maven              | 3.x            | Gerenciador de dependências         |
| Cucumber           | 7.13.0         | Framework BDD                       |
| Selenium WebDriver | 4.19.1         | Automação de browser                |
| JUnit              | 4.13.2         | Execução de testes                  |
| Allure Report      | 2.20.1         | Geração de relatórios               |
| Log4j2             | 2.20.0         | Geração de logs                     |
| WebDriverManager   | 5.7.0          | Gerenciamento automático de drivers |
| SQLite JDBC        | 3.43.2.2       | Registro em banco de dados          |
| Rest Assured       | 5.5.5          | Testes de API (massa de dados)      |
| IntelliJ IDEA      | -              | IDE utilizada                       |
| GitHub             | -              | Versionamento de código             |

---

## 📁 Estrutura do Projeto

```
advantageTesteInmetrics/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pages/           # Page Objects
│   │       └── utils/           # Helpers, Log, DB, API
│   └── test/
│       ├── java/
│       │   ├── steps/           # Step Definitions
│       │   └── runners/         # Test Runner (JUnit + Allure)
│       └── resources/
│           └── features/        # Cenários em Gherkin
│
├── target/                      # Saída dos testes, relatórios, etc.
├── Jenkinsfile                  # Pipeline Jenkins
├── azure-pipelines.yml         # Pipeline Azure DevOps
├── pom.xml                     # Configuração Maven
├── allure.properties           # Configuração Allure (opcional)
└── README.md                   # Este arquivo
```


---
## 🧪 Como Executar os Testes

### 1. Pré-requisitos

- Java 21 instalado
- Maven instalado
- Navegador Chrome instalado
- ### 2. Executar via terminal

``` bash
  mvn clean test
```

### 3. Gerar Relatório Allure

```bash
  mvn  allure:report
allure serve target/allure-results
```
> Isso abrirá o relatório automaticamente no navegador com todos os cenários, status, evidências e detalhes de execução.


---


## 🧪 Funcionalidades Testadas

- Checkout com usuário logado
- Exibição de métodos de pagamento
- Validação de campos obrigatórios
- Geração de massa dinâmica via API `/register`
- Armazenamento de execução e logs no banco SQLite
- Evidências visuais e logs salvos automaticamente
---
## 📊 Relatórios e Logs

- Relatórios gerados:
    - Allure: `target/allure-results`
    - HTML Cucumber: `target/cucumber-reports`
- Logs salvos no banco `executions.db`
- Evidências visuais (prints) capturadas em falhas

---
## ⚙️ CI/CD

Este projeto possui pipeline configurado para:

- **Jenkins:** com `Jenkinsfile` para build, execução e publicação de relatórios
- **Azure DevOps:** com `azure-pipelines.yml`

---

## 📌 Observações

- O projeto utiliza o padrão Page Object para manter organização e facilitar manutenção.
- A massa de dados para login é gerada via API `/register`.
- Separação de responsabilidades: `steps`, `utils`, `pages`
- As execuções são registradas em banco de dados SQLite.
- Os logs são gerados automaticamente com Log4j2.
- O projeto pode ser facilmente integrado com pipelines CI/CD (Jenkins, GitHub Actions, etc.).

---
## 👩‍💻 Desenvolvido por

**Carine R Monte**  
🔗 [LinkedIn](https://www.linkedin.com/in/carine-rodrigues-monte/)  
📧 cariner9@gmail.com  