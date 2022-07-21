import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.font.TextLayout;
import java.awt.BasicStroke;

import javax.imageio.ImageIO;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Enum.Cores;

public class FabricaDeFigurinhas {

    int altura;
    int larguraOriginal;
    int novaAltura;
    String stickerText;
    String nomeArquivo;

    public void cria(InputStream inputStream, String nomeArquivo, String Rating) throws Exception {

        double rating = Double.parseDouble(Rating);


        // leitura da imagem
        BufferedImage imagemOriginal = ImageIO.read(inputStream);
        Image imagemMenor = imagemOriginal.getScaledInstance(300, 400, Image.SCALE_SMOOTH);
        
        altura = imagemMenor.getHeight(null);
        larguraOriginal = imagemMenor.getWidth(null);
        novaAltura = altura + 100;
        this.stickerText = getStickerText(rating);
        this.nomeArquivo = nomeArquivo;
        drawSticker(imagemMenor);

    }
    
    // cria nova imagem em memoria com transparencia e com tamanho novo

    private void drawSticker(Image imagemMenor) {
        BufferedImage novaImagem = new BufferedImage(larguraOriginal, novaAltura, BufferedImage.TRANSLUCENT);
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemMenor, 0, 0, null);        
        drawStickerText(graphics);
        writeImage(novaImagem);

    }

    private void drawStickerText(Graphics2D graphics) {
        // Configura a fonte
        int increaseHeight = novaAltura - altura;
        int fontSize = (int) Math.floor(increaseHeight * 0.7 )-9;
        Font font = new Font("Comic Sans", Font.BOLD, fontSize);
        graphics.setFont(font);


        // Calculando a posição do texto no sticker
        Rectangle2D fontBounds = font.getStringBounds(stickerText, graphics.getFontRenderContext());
        int StartDrawX = (altura / 10) - (int) Math.floor(fontBounds.getWidth() / 9);
        int StartDrawY = (int) (novaAltura- ( fontBounds.getHeight() - increaseHeight) - 50);

        TextLayout textLayout = new TextLayout(stickerText, graphics.getFont(), graphics.getFontRenderContext());
        Shape shape = textLayout.getOutline(null);
        graphics.setStroke(new BasicStroke(fontSize * 0.20f));
        graphics.translate(StartDrawX, StartDrawY);
        graphics.setColor(Color.RED);
        graphics.draw(shape);
        graphics.setColor(Color.YELLOW);
        graphics.fill(shape);

    }

    private String getStickerText(Double rating) {
        if (rating > 8) {
            return "TopZera";
        } else if (rating > 7) {
            return "Legal";
        } else {
            return "Mééé!";
        }
    }

    // Gravação da nova imagem em memoria
    private void writeImage(BufferedImage novaImagem) {
        try {
            String tratedStickerName = nomeArquivo.replace(":", "");
            File newSticker = new File("stickers/" + tratedStickerName + ".png");
            newSticker.mkdirs();
            ImageIO.write(novaImagem, "png", newSticker);
        } catch (IOException e) {
            System.out.println("Não foi possível salvar a imagem " + nomeArquivo + ".png em disco\n" + e.getMessage());
        }
    }

}
