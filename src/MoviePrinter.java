import java.io.PrintStream;
import java.util.Collections;
import java.util.Map;

public class MoviePrinter {

    private static final String FECHAR_UNICODE = "\u001b[m";
    private static final String VERMELHO_BRANCO_UNICODE = "\u001b[1m\u001b[37m\u001b[41m";
    
    private PrintStream print;

    private String prefixoTitulo = "+++++++";

    public MoviePrinter(){
        this.print = System.out;
    }

    public void quebraLinha() {
        print.println();
    }

    public void titulo(String nome) {
        String limiteCabecalho = VERMELHO_BRANCO_UNICODE +
            prefixoTitulo+String.join("", Collections.nCopies(nome.length(), "+"))+prefixoTitulo+
            FECHAR_UNICODE;
        String titulo = VERMELHO_BRANCO_UNICODE +
                            prefixoTitulo + nome + prefixoTitulo +
                        FECHAR_UNICODE;
        
        print.println(limiteCabecalho);
        print.println(titulo);
        print.println(limiteCabecalho);
    }

    public void imprimirNaLinha(String texto) {
        print.print(VERMELHO_BRANCO_UNICODE+texto+FECHAR_UNICODE);
    }

    public void imprimirDados(Map<String, String> filme) {
        double classificacao = Double.parseDouble(filme.get("imDbRating"));
        String classString = String.join("", Collections.nCopies(((int) classificacao), "⭐"));
        
        print.println("\u001b[1m Título: \u001b[m\u001b[37;1m\u001b[44;1m "+filme.get("title")+" \u001b[m");
        print.println("\u001b[1m Poster: \u001b[m\u001b[37;1m\u001b[42;1m "+filme.get("image")+" \u001b[m");
        print.print("\u001b[1m Classificação: \u001b[m\u001b[37;1m\u001b[46;1m " + classificacao + " \u001b[m ");
        print.println(classString);
        print.println();
    }

}
