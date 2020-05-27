package main.Lab7.AsmCommands;

import main.Lab7.Asm;

public class AC_Ret extends _AsmCommand {

    public String get_STRING(){
        String result = Asm.LEVEL_2_INDENT + "ret;";
        return result;
    }
}
