import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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
        new String[] { "Top 250 Filmes", "https://imdb-api.com/en/API/Top250Movies/", "https://alura-filmes.herokuapp.com/conteudos" },
        new String[] { "Filmes Mais Populares", "https://imdb-api.com/en/API/MostPopularMovies/", "https://api.mocki.io/v2/549a5d8b/MostPopularMovies"},
        new String[] { "Séries de TV Mais Populares", "https://imdb-api.com/en/API/MostPopularTVs", "https://api.mocki.io/v2/549a5d8b/MostPopularTVs"},
        new String[] { "Top 250 TV Séries", "https://imdb-api.com/en/API/Top250TVs", "https://api.mocki.io/v2/549a5d8b/Top250TVs"}
    };

    public static void main(String[] args) throws Exception {
        // Criando o impressor de Filmes e Séries
        MoviePrinter printer = new MoviePrinter();

        // Obtendo API Key do IMDB do Ambiente
        String apiKey = System.getenv("IMDB_API_KEY");
        if(apiKey == null || "".equals(apiKey.trim())){
            throw new IllegalArgumentException("Não foi encontrada a variável de ambiente 'IMDB_API_KEY', verifique!");
        }

        // Inicializando o Parser e o Scanner e iterando nos endpoints
        var parser = new JsonParser();
        var geradora = new GeradorStickers();
        try(Scanner scanner = new Scanner(System.in);){

            for(String[] endpoint : ENPOINTS){
                String nome = endpoint[0];
                
                String json = App.obterJson(endpoint);
                if(json != null){
                    List<Map<String, String>> listaDeFilmes = parser.parseJackson(json);
                    // Exibir e manipular os dados.
                    printer.titulo(nome);
                    int limit = App.lerLimite(listaDeFilmes.size(), scanner, printer);
                    printer.quebraLinha();
                    int count = 1;
                    for (Map<String,String> filme : listaDeFilmes) {
                        InputStream is = new URL(filme.get("image")).openStream();
                        String nomeArquivo = filme.get("title")+".png";

                        printer.imprimirDados(filme);
                        printer.imprimirNaLinha("Digite a sua classificação: ");
                        String classificacao = scanner.next();
                        printer.imprimirClassificacao(classificacao);
                        printer.quebraLinha();
                        
                        printer.imprimirNaLinha("Gerando a figurinha do filme!");
                        geradora.criar(is, nomeArquivo);

                        count++;
                        if(count > limit){
                            break;
                        }
                    }
                }
            }
        }
    }

    private static int lerLimite(int size, Scanner scanner, MoviePrinter printer) {
        int limit = 0;
        printer.imprimirNaLinha("Escolha quantos mostrar (vazio para o total de " + size + "): ");
        while(limit == 0){
            String entrada = scanner.nextLine();
            try {
                limit = entrada != null && !"".equals(entrada) ? Integer.parseInt(entrada) : size;
            } catch(Exception e){
                printer.imprimirNaLinha("Entrada não corresponde a um número inteiro, tente novamente: ");
            }
        }
        return limit;
    }

    public static String obterJson(String[] dadosEndpoint) throws IOException, InterruptedException {
        String url = dadosEndpoint[1];
        String altUrl = dadosEndpoint[2];

        String json = App.buscarDados(url);
        if(json == null && altUrl != null){
            json = App.buscarDados(altUrl);
        }
        return json;
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
