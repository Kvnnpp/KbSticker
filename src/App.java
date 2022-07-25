import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import endpoint.Cores;
import endpoint.UrlEnum;
import extratores.ExtratorDeConteudoDaNasa;
import extratores.ExtratorDeConteudoDoIMDB;
import model.Conteudo;
import service.ClienteHttp;
import service.GeradoraDeFigurinhas;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Properties config = getProp();
        

        //Input category
        Scanner scanner = new Scanner(System.in);
        List<String> categories = List.of("nasa","MostPopularMovies", "Top250Movies", "MostPopularTVs", "Top250TVs");
        System.out.println("Selecione uma categoria entre: " + String.join(", ", categories));
        String category= scanner.nextLine();
        // Validacao de categoria
        while(!categories.contains(category)){
            System.out.println("Categoria é inválida");

            category= scanner.nextLine();
        }

        System.out.println("Categoria selecionada: " + category);

        scanner.close();

        var url = "";
        List<String>apImdb =List.of("nasa");

        if(!apImdb.contains(category)){
            url =UrlEnum.API_MOCK_MOVIES.getDescricao() + category;
        }

        if(category.contentEquals("nasa")){
            url = UrlEnum.API_NASA.getDescricao();
        }
        
        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados

        // ExtratorDeConteudoDaNasa extrator = new ExtratorDeConteudoDaNasa();
        // List<Conteudo> conteudos = extrator.extraiConteudos(json);

        ExtratorDeConteudoDoIMDB extrator = new ExtratorDeConteudoDoIMDB();
        List<Conteudo> conteudos = extrator.extraiConteudos(json);
        var gera = new GeradoraDeFigurinhas();

        for (int i = 0; i<7; i++){

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = conteudo.titulo();
            String rating = conteudo.rating();

            gera.gera(inputStream,nomeArquivo,rating);

           System.out.println(Cores.CYAN + "Titulo: " + Cores.FIM + Cores.GREEN + conteudo.titulo() + Cores.FIM);
           System.out.println(Cores.MAGENTA + "Capa: " + Cores.FIM + conteudo.urlImagem() );

            Double RatingDouble = Double.parseDouble(rating);
            long roundedRating = Math.round(RatingDouble);
            System.out.println(Cores.CYAN +"Classificação: " + Cores.FIM + conteudo.rating());
            for (int estrelas = 0; estrelas < roundedRating; estrelas++) {
                System.out.print(Cores.STAR);
            }
           System.out.println("");
        }
    }

    

    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream(
                "./properties/api.key");
        props.load(file);
        return props;
    }
}
