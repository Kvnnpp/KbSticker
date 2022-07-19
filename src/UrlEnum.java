public enum UrlEnum {
    /**
     * 
     * ENDPOINTS TMDB Retornando o top semanal de files
     */
    WEEK_TRENDING_TOP("https://api.themoviedb.org/3/trending/movie/week?api_key=cb685c99e7eba913da39ef6edf832d53"),   /**
    /**
     * 
     * ENDPOINTS TMDB Retornando o top semanal de files
     */
    DAILY_TRENDING_TOP("https://api.themoviedb.org/3/trending/movie/day?api_key=cb685c99e7eba913da39ef6edf832d53"),
    /**
     * 
     * COMPLEMENTO DE URL para busca de imagem
     */
    IMAGE_COMPLEMENT("https://image.tmdb.org/t/p/w500");

    private String descricao;

    UrlEnum (String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    

}
