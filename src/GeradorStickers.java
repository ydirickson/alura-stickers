import static java.awt.Transparency.TRANSLUCENT;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import javax.imageio.ImageIO;

public class GeradorStickers {

        public void criar(String url, String nomeArquivo) throws Exception {
            String[] partes = url.split("@\\.");

            URI uri = URI.create(partes[0]+"@..jpg");
            this.criar(uri.toURL().openStream(), nomeArquivo);
        }

        public void criar(InputStream isImagem, String nomeArquivo) throws Exception{
            // Leitura de imagem
            BufferedImage imgOriginal = ImageIO.read(isImagem);

            // Criar nova imagem em memória com transparência e tamanho novo
            int largura = imgOriginal.getWidth();
            int altura = imgOriginal.getHeight();
            int novaAltura = altura + 200;
            BufferedImage imgNova = new BufferedImage(largura, novaAltura, TRANSLUCENT);

            // Copiar a imagem original para nova imagem (em memória)
            Graphics2D graphics = (Graphics2D) imgNova.getGraphics();
            graphics.drawImage(imgOriginal, 0, 0, null);
            
            // configurar a fonte
            Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
            graphics.setFont(fonte);
            graphics.setColor(Color.YELLOW);

            // escrever uma frase na nova imagem
            graphics.drawString("TOPZERA", 100, altura + 100);
            
            // escrever a nova imagem em um arquivo
            File saida = new File("saida", nomeArquivo);
            saida.getParentFile().mkdirs();
            ImageIO.write(imgNova, "png", saida);
        }

        public static void main(String[] args) throws Exception {
            InputStream is = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg").openStream();
            GeradorStickers gerador = new GeradorStickers();
            gerador.criar(is, "figurinha.png");
        }
    
}
