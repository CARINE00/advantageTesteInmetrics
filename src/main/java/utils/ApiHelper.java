package utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;

import com.github.javafaker.Faker;
public class ApiHelper {
    private static final Faker faker = new Faker(new Locale("pt-BR"));


    public static Usuario gerarUsuarioViaApi() {

        String endpoint = "https://www.advantageonlineshopping.com/accountservice/accountrest/api/v1/register";
        String firstName = "Teste";
        String lastName = "QA";
        String password = "Senha123";
        String loginName = (firstName + lastName + faker.number().digits(3)).toLowerCase();
        String email = loginName + "@email.com";


        String payload = String.format("""
           {
              "firstName": "%s",
              "lastName": "%s",
              "address": "Lugar Teste",
              "email": "%s",
              "loginName": "%s",
              "password": "%s"
            }
        """, firstName, lastName, email, loginName, password);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endpoint))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200 || response.statusCode() == 201) {
                return new Usuario(loginName, password);
            } else {
                throw new RuntimeException("Erro ao registrar usuário via API: " + response.body());
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro na requisição de criação de usuário via API", e);
        }
    }
}
