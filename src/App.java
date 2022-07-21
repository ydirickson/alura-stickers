import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {

    private LeitorProperties leitorProps;

    private MoviePrinter moviePrinter;

    private JsonParser jsonParser;

    private GeradorStickers geradorStickers;

    private IMDBCliente imdbCliente;

    private List<Map<String, String>> endpoints;

    public App() throws Exception {
        this.leitorProps = new LeitorProperties();
        this.imdbCliente = new IMDBCliente(this.leitorProps.getApiKey());
        this.moviePrinter = new MoviePrinter(this.leitorProps.getClassificar());
        this.jsonParser = new JsonParser();
        this.geradorStickers = new GeradorStickers();
        this.endpoints = this.leitorProps.getEndpoints();
    }

    public static void main(String[] args) throws Exception {
        // Criando aplicação e todos as classes básicas do App
        App app = new App();
        app.executar();
    }

    private void executar() throws Exception {
        try(Scanner scanner = new Scanner(System.in);){
            this.moviePrinter.setScanner(scanner);
            this.moviePrinter.imprimirTituloApp();
            for (Map<String,String> endpoint : endpoints) {
                String nome = endpoint.get("nome");
                String url = endpoint.get("url");
                String alturl = endpoint.get("alturl");
                Integer limite = Integer.parseInt(endpoint.get("limite"));

                String json = imdbCliente.obterJson(url, alturl);
                if(json != null){
                    this.moviePrinter.imprimirTituloLista(nome);
                    List<Map<String, String>> lista = this.jsonParser.parseJackson(json, limite);
                    for (Map<String,String> filme : lista) {
                        String nomeArquivo = filme.get("title")+".png";
                        this.moviePrinter.imprimirDados(filme);
                        this.geradorStickers.criar(filme.get("image"), nomeArquivo);
                        this.moviePrinter.imprimirFigurinhaCriada(nomeArquivo);
                    }
                }
            }
        }
    }

}
