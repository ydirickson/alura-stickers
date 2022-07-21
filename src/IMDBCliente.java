import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class IMDBCliente {

    private String apiKey;

    public IMDBCliente (String apiKey) {
        this.apiKey = apiKey;
    }

    public String obterJson(String url, String altUrl) throws Exception {
        String json = this.buscarDados(url+"/"+this.apiKey);
        if(json == null && altUrl != null){
            json = this.buscarDados(altUrl);
        }
        return json;
    }

    private String buscarDados(String url) throws IOException, InterruptedException {
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        if(response.statusCode() > 400){
            return null;
        }
        return response.body();
    }

}
