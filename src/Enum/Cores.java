package Enum;
public enum Cores {


    //Cores de fundo:
    BLUE("\u001b[38;2;255;255;255m \u001b[48;2;42;122;228m"),
    CYAN("\u001b[46;1m"),
    MAGENTA("\u001b[44;1m"),
    FIM("\u001b[0m"), 
    GREEN("\u001b[42;1m"),


    //Cores do Texto:
    GREEN_Text("\u001b[32;1m");

    Cores(String code) {
      this.code = code;
    }

    private final String code;

    @Override
    public String toString() {
      return code;
    }
}
