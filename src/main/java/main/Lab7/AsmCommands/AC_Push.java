package main.Lab7.AsmCommands;

import main.Lab7.AsmCommands.infoArea.IMM;
import main.Lab7.AsmCommands.infoArea.InfoArea;
import main.Lab7.AsmCommands.infoArea.REG;

public class AC_Push extends _AsmCommand {
    InfoArea first;     // ПРИЕМНИК


    public AC_Push(REG reg) {
        this.first = reg;
    }

    public AC_Push(IMM imm) {
        this.first = imm;
    }

    @Override
    public String get_STRING() throws Exception {
        String result = "    " +
                "push" +
                " " +
                first.get_STRING();
        return result;
    }
}
