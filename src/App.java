import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {

    // Coleção de Endpoints, com os nomes, os endpoints do IMDB originais seguidos dos endpoints alternativos.
    private static final String[][] ENPOINTS = new String[][]{
        // Top 250 Movies
        new String[] { "Top 250 Filmes", "https://imdb-api.com/en/API/Top250Movies/", "https://alura-filmes.herokuapp.com/conteudos" },
        new String[] { "Filmes Mais Populares", "https://imdb-api.com/en/API/MostPopularMovies/", "https://api.mocki.io/v2/549a5d8b/MostPopularMovies"},
        new String[] { "Séries de TV Mais Populares", "https://imdb-api.com/en/API/MostPopularTVs", "https://api.mocki.io/v2/549a5d8b/MostPopularTVs"},
        new String[] { "Top 250 TV Séries", "https://imdb-api.com/en/API/Top250TVs", "https://api.mocki.io/v2/549a5d8b/Top250TVs"}
    };

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String apiKey = System.getenv("IMDB_API_KEY");
        if(apiKey == null || "".equals(apiKey.trim())){
            throw new IllegalArgumentException("Não foi encontrada a variável de ambiente 'IMDB_API_KEY', verifique!");
        }

        var parser = new JsonParser();
        for(String[] endpoint : ENPOINTS){
            String nome = endpoint[0];
            String url = endpoint[1];
            String altUrl = endpoint[2];

            String json = App.buscarDados(url);
            if(json == null && altUrl != null){
                json = App.buscarDados(altUrl);
            }
            int limit = 0;
            if(json != null){
                List<Map<String, String>> listaDeFilmes = parser.parseJackson(json);
                // Exibir e manipular os dados.
                System.out.println();
                System.out.println("+++++++ "+nome+" +++++++");
                System.out.print("Escolha quantos mostrar (vazio para o total de "+listaDeFilmes.size()+"): ");

                while(limit == 0){
                    String entrada = scanner.nextLine();
                    try {
                        limit = entrada != null && !"".equals(entrada) ? Integer.parseInt(entrada) : listaDeFilmes.size();
                    } catch(Exception e){
                        System.out.print("Entrada não corresponde a um número inteiro, tente novamente: ");
                    }
                }

                if(limit == 0){
                    limit = 20;
                }
                System.out.println();
                int count = 1;
                for (Map<String,String> filme : listaDeFilmes) {
                    
                    System.out.println(filme.get("title"));
                    System.out.println(filme.get("image"));
                    System.out.println(filme.get("imDbRating"));
                    System.out.println();

                    count++;
                    if(count > limit){
                        break;
                    }
                }
            }

        }
    }

    public static String buscarDados(String url) throws IOException, InterruptedException{
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
