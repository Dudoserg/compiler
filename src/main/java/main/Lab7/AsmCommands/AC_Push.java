package main.Lab7.AsmCommands;

import javafx.util.Pair;
import main.Lab7.Asm;
import main.Lab7.AsmCommands.infoArea.*;

import java.util.ArrayList;
import java.util.List;

public class AC_Push extends _AsmCommand {
    InfoArea first;     // ПРИЕМНИК

    // Возможные пары приемник/источник
    static List<InfoAreaType> rules = new ArrayList<InfoAreaType>() {
        {
            this.add(REG.type);
            this.add(MEM_LOCAL.type);
            this.add(MEM_GLOBAL.type);
            this.add(IMM.type);
        }
    };


    public AC_Push(InfoArea first) throws Exception {
        if (!check_OK(first, rules)) {
            exception("Wrong infoArea for PUSH command!", first);
        }
        this.first = first;
    }


    @Override
    public String get_STRING() throws Exception {
        String result = Asm.LEVEL_1_INDENT +
                "push" +
                " " +
                first.get_STRING();
        return result;
    }
}
