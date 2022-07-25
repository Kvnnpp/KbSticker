package model;

import java.time.LocalDate;

public class DataAtual {
    
    static LocalDate hoje = LocalDate.now();
    static LocalDate seteDiasAntes = hoje.minusDays(7);

    
    
    public static LocalDate getHoje() {
        return hoje;
    }

    public static LocalDate getSeteDiasAntes() {
        return seteDiasAntes;
    }

}


