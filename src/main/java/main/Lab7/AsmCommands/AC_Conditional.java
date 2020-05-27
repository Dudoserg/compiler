package main.Lab7.AsmCommands;


import main.Lab7.Asm;

public class AC_Conditional extends _AsmCommand {
    public Conditionals type;
    public String metka;

    public AC_Conditional(Conditionals type, String metka) {
        this.type = type;
        this.metka = metka;
    }

    public String get_STRING(){
        String result = Asm.LEVEL_2_INDENT + type.toString() + " " + metka;
        return result;
    }
}
