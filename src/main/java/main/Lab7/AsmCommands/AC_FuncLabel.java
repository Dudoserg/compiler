package main.Lab7.AsmCommands;

import main.Lab4.TreeNext.NextNode;

public class AC_FuncLabel extends _AsmCommand {
    public String lexem_str;
    public NextNode func;

    public AC_FuncLabel(String lexem_str, NextNode func) {
        this.lexem_str = lexem_str;
        this.func = func;
    }


    public String get_STRING(){
        String result = lexem_str + ":";
        return result;
    }
}
