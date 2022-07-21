import java.io.FileInputStream;
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
import java.util.Properties;
import java.util.Scanner;

import Enum.Cores;
import Enum.UrlEnum;

public class App {
    public static void main(String[] args) throws Exception {
        
        Properties config = getProp();
        

        //Input category
        Scanner scanner = new Scanner(System.in);
        List<String> categories = List.of("MostPopularMovies", "Top250Movies", "MostPopularTVs", "Top250TVs","MostPopularIntheWeek","MostPopularDaily");
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
        List<String>apImdb =List.of("MostPopularIntheWeek", "MostPopularDaily");
        if(!apImdb.contains(category)){
            url =UrlEnum.API_MOCK_MOVIES.getDescricao() + category;
        }
        if(category.contentEquals("MostPopularIntheWeek")){           
        //Acessar o ENUM ApiUrl e pegar a URL com a informação que queremos
        url = UrlEnum.WEEK_TRENDING_TOP.getDescricao() + config.get("key");
        }
        if(category .contentEquals("MostPopularDaily")){
            url = UrlEnum.DAILY_TRENDING_TOP.getDescricao() + config.get("key");
        }
        
        var client = HttpClient.newHttpClient();        
        //Criamos o URI usando a var url e salvamos na variável request
        URI endereco = URI.create(url);
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // exibir e manipular os dados

        var geradora = new FabricaDeFigurinhas();
        String urlImagem = "";
        String titulo = "";
        String Rating = "";

        if(apImdb.contains(category)){
        //extrai so os dados que interessam ( titulo, poster, Classificacao)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        for (Map<String,String> filme : listaDeFilmes) {

            urlImagem =(UrlEnum.URL_COMP.getDescricao() + filme.get("backdrop_path"));
            titulo = filme.get("title");
            Rating = filme.get("vote_average");
        
            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo;
            geradora.cria(inputStream, nomeArquivo, Rating);

            System.out.println(Cores.CYAN+"Título: " + Cores.FIM +Cores.BLUE+ titulo +Cores.FIM);
            System.out.println(Cores.MAGENTA +"1mPoster: " +Cores.FIM + urlImagem);
            Double RatingDouble = Double.parseDouble(Rating);
            long roundedRating = Math.round(RatingDouble);
            System.out.println(Cores.CYAN + "Classificação: " + Cores.FIM + Rating);
            for (int i = 0; i < roundedRating; i++) {
                System.out.print("\u2b50");
            }
            System.out.println();     
        }
    }else{
        //extrai so os dados que interessam ( titulo, poster, Classificacao)
        var parser = new JsonParserToMock();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        for (Map<String, String> filme : listaDeFilmes) {

            urlImagem = filme.get("image");
            titulo = filme.get("title");
            Rating = filme.get("imDbRating");
        
            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo;
            geradora.cria(inputStream, nomeArquivo, Rating);

           System.out.println(Cores.CYAN + "Titulo: " + Cores.FIM + Cores.GREEN + titulo + Cores.FIM);
           System.out.println(Cores.MAGENTA + "Capa: " + Cores.FIM + urlImagem );

            Double RatingDouble = Double.parseDouble(Rating);
            long roundedRating = Math.round(RatingDouble);
            System.out.println(Cores.CYAN +"Classificação: " + Cores.FIM + Rating);
            for (int i = 0; i < roundedRating; i++) {
                System.out.print("\u2b50");
            }
           System.out.println("");
        }
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
