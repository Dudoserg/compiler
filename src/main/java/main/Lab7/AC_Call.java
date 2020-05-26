package main.Lab7;

import main.Lab4.TreeNext.NextNode;
import main.Lab7.AsmCommands._AsmCommand;

public class AC_Call extends _AsmCommand {
    NextNode node_callFunc;
    String lexemStr;
    public AC_Call(String lexemStr, NextNode node_callFunc) {
        this.lexemStr = lexemStr;
        this.node_callFunc = node_callFunc;
    }

    @Override
    public String get_STRING() throws Exception {
        String result = Asm.LEVEL_1_INDENT +
                "call" +
                "  " +
                this.lexemStr;
        return result;
    }
}
