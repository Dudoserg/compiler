package main.Lab7.AsmCommands;

import main.Lab7.Asm;
import main.Lab7.AsmCommands.infoArea.IMM;
import main.Lab7.AsmCommands.infoArea.InfoArea;
import main.Lab7.AsmCommands.infoArea.REG;

public class AC_Pop extends _AsmCommand {
    InfoArea first;     // ПРИЕМНИК


    public AC_Pop(REG reg) {
        this.first = reg;
    }

    public AC_Pop(IMM imm) {
        this.first = imm;
    }

    @Override
    public String get_STRING() throws Exception {
        String result = Asm.LEVEL_1_INDENT +
                "pop" +
                " " +
                first.get_STRING();
        return result;
    }
}
