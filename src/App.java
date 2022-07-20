import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

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

        if(category == "MostPopularIntheWeek"){           
        //Acessar o ENUM ApiUrl e pegar a URL com a informação que queremos
        url = UrlEnum.WEEK_TRENDING_TOP.getDescricao() + config.get("key");
        }
        if(category == "MostPopularDaily"){
            url = UrlEnum.WEEK_TRENDING_TOP.getDescricao() + config.get("key");
        }else{
            url =UrlEnum.API_MOCK_MOVIES.getDescricao() + category;
        }
        
        var client = HttpClient.newHttpClient();        
        //Criamos o URI usando a var url e salvamos na variável request
        URI endereco = URI.create(url);
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // exibir e manipular os dados
        if(category == "MostPopularIntheWeek" || category == "MostPopularDaily"){
        //extrai so os dados que interessam ( titulo, poster, Classificacao)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[46;1mTítulo: \u001b[0m " + "\u001b[38;2;255;255;255m \u001b[48;2;42;122;228m" + filme.get("title") +"\u001b[m" );
            System.out.println("\u001b[44;1mPoster: " + "\u001b[0m" + UrlEnum.IMAGE_COMPLEMENT.getDescricao() +filme.get("backdrop_path"));
            String Rating = filme.get("vote_average");
            Double RatingDouble = Double.parseDouble(Rating);
            long roundedRating = Math.round(RatingDouble);
            System.out.println("\u001b[46;1mClassificação: " + "\u001b[0m" + filme.get("vote_average"));
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
           System.out.println(filme.get("title"));
           System.out.println(filme.get("image"));
           String Rating = filme.get("imDbRating");
            Double RatingDouble = Double.parseDouble(Rating);
            long roundedRating = Math.round(RatingDouble);
            System.out.println("\u001b[46;1mClassificação: " + "\u001b[0m" + filme.get("imDbRating"));
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
