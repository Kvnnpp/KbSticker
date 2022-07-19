import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        //Acessar o ENUM ApiUrl e pegar a URL com a informação que queremos
        var url = UrlEnum.WEEK_TRENDING_TOP.getDescricao();


        //Criamos um novo Client
        var client = HttpClient.newHttpClient();
        
        
        //Criamos o URI usando a var url e salvamos na variável request
        URI endereco = URI.create(url);
        var request = HttpRequest.newBuilder(endereco).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //extrai so os dados que interessam ( titulo, poster, Classificacao)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println(filme.get("title"));
            System.out.println(UrlEnum.IMAGE_COMPLEMENT.getDescricao() +filme.get("poster_path"));
            System.out.println(filme.get("vote_average"));
            System.out.println();
            
        }


    }
}
