import java.io.PrintStream;
import java.util.Collections;
import java.util.Map;

public class MoviePrinter {
    
    private PrintStream print;

    private String prefixoTitulo = "+++++++";

    public MoviePrinter(){
        this.print = System.out;
    }

    public void quebraLinha() {
        print.println();
    }

    public void titulo(String nome) {
        String limiteCabecalho = prefixoTitulo+String.join("", Collections.nCopies(nome.length(), "+"))+prefixoTitulo;
        print.println(limiteCabecalho);
        print.println(prefixoTitulo+nome+prefixoTitulo);
        print.println(limiteCabecalho);
    }

    public void imprimirNaLinha(String texto) {
        print.print(texto);
    }

    public void imprimirDados(Map<String, String> filme) {
        print.println(filme.get("title"));
        print.println(filme.get("image"));
        print.println(filme.get("imDbRating"));
    }

}
