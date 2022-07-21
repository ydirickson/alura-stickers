import java.io.PrintStream;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;

public class MoviePrinter {

    private static final String FECHAR = "\u001b[m";

    private static final String COR_BRANCA = "\u001b[37m";

    private static final String NEGRITO = "\u001b[1m";

    private static final String FUNDO_CIANO = "\u001b[46m";
    private static final String FUNDO_VERMELHO = "\u001b[41m";
    
    private PrintStream print;

    private String prefixoTitulo = "+++++++";

    private Scanner scanner;

    private boolean classificar;

    public MoviePrinter(boolean classificar){
        this.classificar = classificar;
        this.print = System.out;
    }

    public void imprimirTituloApp() {
        this.quebrarLinha();
        this.imprimirln(" ++++++++++++++++++++++++++++++++++++++++++++++++++ ", NEGRITO, COR_BRANCA, FUNDO_CIANO);
        this.imprimirln(" +++++++++++++++ Alura Stickers +++++++++++++++++++ ", NEGRITO, COR_BRANCA, FUNDO_CIANO);
        this.imprimirln(" ++++++++++++++++++++++++++++++++++++++++++++++++++ ", NEGRITO, COR_BRANCA, FUNDO_CIANO);
        this.quebrarLinha();
    }

    public void imprimirDados(Map<String, String> filme) {
        this.imprimirTituloFilme(filme.get("title"));
        this.imprimirClassificacao(filme.get("imDbRating"));
        if(classificar){
            this.lerClassificacao();
        }
        this.imprimir("Convertendo poster em figurinha...");
    }

    public void imprimirFigurinhaCriada(String nomeArquivo) {
        this.print.print('\r');
        this.print.print("Figurinha criada: "+nomeArquivo);
        this.quebrarLinha();
        this.quebrarLinha();
    }

    public void imprimirTituloLista(String nome) {
        this.imprimirln("++++++++ "+nome+" +++++++", NEGRITO);
        this.quebrarLinha();
    }

    private void imprimirTituloFilme(String nome) {
        String limite = prefixoTitulo+String.join("", Collections.nCopies(nome.length(), "+"))+prefixoTitulo;
        String titulo = prefixoTitulo + nome + prefixoTitulo;
        
        this.imprimirln(limite, NEGRITO, COR_BRANCA, FUNDO_VERMELHO);
        this.imprimirln(titulo, NEGRITO, COR_BRANCA, FUNDO_VERMELHO);
        this.imprimirln(limite, NEGRITO, COR_BRANCA, FUNDO_VERMELHO);
    }

    private void imprimirClassificacao(String classificacao) {
        String classString = this.calcularClassificacao(classificacao);
        this.imprimir("Classificação: ", NEGRITO);
        this.imprimir(classString, null, null, FUNDO_CIANO);
        this.quebrarLinha();
    }

    private void lerClassificacao() {
        this.imprimir("Sua Classificação: ", NEGRITO);
        String classificacao = scanner.nextLine();
        //this.print.print('\r');
        String classString = this.calcularClassificacao(classificacao);
        this.imprimir("Sua Classificação: ", NEGRITO);
        this.imprimir(classString, null, null, FUNDO_CIANO);
        this.quebrarLinha();
    }

    private String calcularClassificacao(String classificacao){
        double classDouble = Double.parseDouble(classificacao);
        return String.join("", Collections.nCopies(((int) classDouble), "⭐"))+ "("+classificacao+")";
    }

    private void imprimir(String texto){
        this.imprimir(texto, null, null, null);
    }

    private void imprimir(String texto, String estilo){
        this.imprimir(texto, estilo, null, null);
    }

    private void imprimir(String texto, String estilo, String corFonte, String corFundo) {
        String unicode = this.juntarUnicode(estilo, corFonte, corFundo);
        String fechar = unicode.length() > 0 ? FECHAR : "";
        System.out.print(
            unicode +
            texto +
            fechar
        );
    }

    private void imprimirln(String texto){
        this.imprimirln(texto, null, null, null);
    }

    private void imprimirln(String texto, String estilo){
        this.imprimirln(texto, estilo, null, null);
    }

    private void imprimirln(String texto, String estilo, String corFonte){
        this.imprimirln(texto, estilo, corFonte, null);
    }

    private void imprimirln(String texto, String estilo, String corFonte, String corFundo) {
        this.imprimir(texto, estilo, corFonte, corFundo);
        this.quebrarLinha();
    }

    private void quebrarLinha() {
        this.print.println();
    }

    private String juntarUnicode(String estilo, String corFonte, String corFundo) {
        String unicodes = "";
        if(estilo != null){
            unicodes += estilo;
        }
        if(corFonte != null){
            unicodes += corFonte;
        }
        if(corFundo != null){
            unicodes += corFundo;
        }
        return unicodes;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

}
