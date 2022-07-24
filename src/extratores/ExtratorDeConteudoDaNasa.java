package extratores;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Conteudo;
import service.JsonParser;

public class ExtratorDeConteudoDaNasa implements AbstractExtrator {

    public List<Conteudo> extraiConteudos(String json) {

        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos =  new ArrayList<>();
        
        // popular a lista de conteudos
        for (Map<String, String> atributos : listaDeAtributos){
            String urlImagem = atributos.get("url");
            String titulo = atributos.get("title");
            String rating ="0";
            if(atributos.get("rating")!= null){
            rating = atributos.get("rating");
            }
            
            var conteudo = new Conteudo(titulo, urlImagem,rating);
            conteudos.add(conteudo);
        }
        return conteudos;
    }
}
