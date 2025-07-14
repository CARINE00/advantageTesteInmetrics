package utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiHelper {

    private static String gerarLoginAleatorio() {
        String letras = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder login = new StringBuilder("user");

        // Letras aleatórias
        for (int i = 0; i < 4; i++) {
            int index = (int) (Math.random() * letras.length());
            login.append(letras.charAt(index));
        }

        // Números aleatórios
        int numeros = (int) (Math.random() * 10000);
        login.append(numeros);

        return login.toString();
    }

    public static Usuario gerarUsuarioViaApi() {
        String endpoint = "https://www.advantageonlineshopping.com/accountservice/accountrest/api/v1/register";

        String loginName = gerarLoginAleatorio();
        String email = loginName + "@email.com";
        String password = "Senha123";
        String name = loginName + "Oi";

        String payload = String.format("""
            {
              "firstName": "%s",
              "lastName" : "Teste",
              "lastName" : "Teste",
              "address" : "Lugar de Teste",
              "email": "%s",
              "loginName": "%s",
              "password": "%s"
            }
        """, name, email, loginName, password);

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
                throw new RuntimeException("Erro ao registrar usuário: " + response.body());
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro na requisição de criação de usuário via API", e);
        }
    }
}
