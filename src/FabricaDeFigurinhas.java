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

public class FabricaDeFigurinhas {

    int altura;
    int larguraOriginal;
    int novaAltura;
    String stickerText;
    String nomeArquivo;

    public void cria(InputStream inputStream, String nomeArquivo, String Rating) throws Exception {

        // leitura da imagem
        BufferedImage imagemOriginal = ImageIO.read(inputStream);
        altura = imagemOriginal.getHeight();
        double rating = Double.parseDouble(Rating);
        larguraOriginal = imagemOriginal.getWidth();
        novaAltura = altura + 200;
        this.stickerText = getStickerText(rating);
        this.nomeArquivo = nomeArquivo;
        Redimensiona(imagemOriginal);
        drawSticker(imagemOriginal);
        

    }

    public void Redimensiona(BufferedImage imagemOriginal) {

    Image image = imagemOriginal.getScaledInstance(300, 100, Image.SCALE_DEFAULT);
    writeImage((BufferedImage) image);
    }
    // cria nova imagem em memoria com transparencia e com tamanho novo

    private void drawSticker(BufferedImage imagemOriginal) {
        BufferedImage novaImagem = new BufferedImage(larguraOriginal, novaAltura, BufferedImage.TRANSLUCENT);
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);
        drawStickerText(graphics);
        writeImage(novaImagem);

    }

    private void drawStickerText(Graphics2D graphics) {
        // Configura a fonte
        int increaseHeight = novaAltura - altura;
        int fontSize = (int) Math.floor(increaseHeight * 0.9);
        Font font = new java.awt.Font("Comic Sans", java.awt.Font.BOLD, fontSize);
        graphics.setFont(font);

        Rectangle2D fontBounds = font.getStringBounds(stickerText, graphics.getFontRenderContext());
        int StartDrawX = (altura / 2) - (int) Math.floor(fontBounds.getWidth() / 2);
        int StartDrawY = (int) (novaAltura - (fontBounds.getHeight() - increaseHeight));

        // Calculando a posição do texto no sticker
        TextLayout textLayout = new TextLayout(stickerText, graphics.getFont(), graphics.getFontRenderContext());
        Shape shape = textLayout.getOutline(null);
        graphics.setStroke(new BasicStroke(fontSize * 0.15f));
        graphics.translate(StartDrawX, StartDrawY);
        graphics.setColor(Color.BLACK);
        graphics.draw(shape);
        graphics.setColor(Color.YELLOW);
        graphics.fill(shape);

    }

    private String getStickerText(Double rating) {
        if (rating > 8) {
            return "O MELHOR";
        } else if (rating > 7) {
            return "TA VALENDO";
        } else {
            return "MÉÉÉÉÉÉÉ";
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
