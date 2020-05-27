package main.Lab7.AsmCommands;

public enum Conditionals {
    JNE("=="),    // использую когда ==
    JE("!="),     // использую когда !=
    JLE(">"),    // использую когда >
    JG("<"),      // использую когда <
    JMP("JMP");

    private String str;

    Conditionals(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public static Conditionals getType(String sign) throws Exception {
        for (Conditionals current : Conditionals.values()) {
            if (current.getStr().equals(sign))
                return current;
        }
        throw new Exception("aadsf90328vfsd");
    }

}
