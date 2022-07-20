package Enum;
public enum UrlEnum {

    // api THE MOVIEDB

    WEEK_TRENDING_TOP("https://api.themoviedb.org/3/trending/movie/week?api_key="),   
    DAILY_TRENDING_TOP("https://api.themoviedb.org/3/trending/movie/day?api_key="),
    
    // API MOCK TOP CATEGORIAS
    API_MOCK_MOVIES("https://api.mocki.io/v2/549a5d8b/"),
//    TOP_250_MOVIES("https://api.mocki.io/v2/549a5d8b/"),
//    TOP_POPULAR_MOVIES("https://api.mocki.io/v2/549a5d8b/"),
//    TOP_MOST_POPULAR_TV("https://api.mocki.io/v2/549a5d8b/"),


    //COMPLEMENTO DE URL DA IMAGEM
    IMAGE_COMPLEMENT("https://image.tmdb.org/t/p/w500");

    private String descricao;

    UrlEnum (String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    

}
