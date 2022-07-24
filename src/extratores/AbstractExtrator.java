package extratores;
import java.util.List;

import model.Conteudo;

public interface AbstractExtrator {

    public List<Conteudo> extraiConteudos(String json);
}
