package main.Lab7.AsmCommands;

import main.Lab4.TreeNext.NextNode;
import main.Lab7.Asm;

public class AC_Metka extends _AsmCommand {
    public String metka;

    public AC_Metka(String metka) {
        this.metka = metka;
    }


    public String get_STRING(){
        String result = Asm.LEVEL_1_INDENT + metka + ":";
        return result;
    }
}
