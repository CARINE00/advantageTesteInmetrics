
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
├── src/
│   ├── java/
│   │   ├── pages/             # Page Objects com elementos e ações das telas
│   │   └── utils/             # DriverFactory, geração de logs, usuários pela API
│   │ 
│   └── test/
│       ├── java/
│       │   ├── steps/          # Steps do Cucumber com lógica de teste
│       │   └── runners/        # Runner de testes (JUnit + Cucumber)
│       └── resourses/          # Arquivos .feature em Gherkin
│
├── target/
│   └── allure-results/         # Resultados de execução do Allure
│
├── logs/                       # Resultados de logs
│
├── pom.xml                     # Gerenciamento de dependências Maven
├── .gitignore                  # Arquivos ignorados pelo Git
└── README.md                   # Documentação do projeto
```

---

## 🧪 Funcionalidades Testadas

- Criação de usuário via API `/register`
- Login de usuário no sistema
- Fluxo de checkout com adição de produtos no carrinho
- Validação de resumo da compra e finalização

---

## ⚙️ Execução dos Testes

### 1. Rodar testes com Maven:

```bash
  mvn clean test
```

### 2. Gerar relatório Allure:

```bash
  allure serve target/allure-results
```

> Isso abrirá o relatório automaticamente no navegador com todos os cenários, status, evidências e detalhes de execução.

---

## 📌 Observações

- O projeto utiliza o padrão Page Object para manter organização e facilitar manutenção.
- A massa de dados para login é gerada via API `/register`.
- As execuções são registradas em banco de dados SQLite.
- Os logs são gerados automaticamente com Log4j2.
- O projeto pode ser facilmente integrado com pipelines CI/CD (Jenkins, GitHub Actions, etc.).

---
