package main.Lab7.AsmCommands;

import javafx.util.Pair;
import main.Lab7.Asm;
import main.Lab7.AsmCommands.infoArea.*;

import java.util.ArrayList;
import java.util.List;

public class AC_Xor extends _AsmCommand {
    public InfoArea first;     // ПРИЕМНИК
    public InfoArea second;    // ИСТОЧНИК

    // Возможные пары приемник/источник
    static List<Pair<InfoAreaType, InfoAreaType>> rules = new ArrayList<Pair<InfoAreaType, InfoAreaType>>() {
        {
//            //    REG,  MEM_GLOBAL
//            this.add(new Pair<InfoAreaType, InfoAreaType>(REG.type, MEM_GLOBAL.type));
//            //    REG,  MEM_LOCAL
//            this.add(new Pair<InfoAreaType, InfoAreaType>(REG.type, MEM_LOCAL.type));
//            //    REG,  IMM
//            this.add(new Pair<InfoAreaType, InfoAreaType>(REG.type, IMM.type));
            //    REG,  REG
            this.add(new Pair<InfoAreaType, InfoAreaType>(REG.type, REG.type));
//
//            //    MEM_GLOBAL,  REG
//            this.add(new Pair<InfoAreaType, InfoAreaType>(MEM_GLOBAL.type, REG.type));
//            //    MEM_GLOBAL,  IMM
//            this.add(new Pair<InfoAreaType, InfoAreaType>(MEM_GLOBAL.type, IMM.type));
//
//            //    MEM_LOCAL,  REG
//            this.add(new Pair<InfoAreaType, InfoAreaType>(MEM_LOCAL.type, REG.type));
//            //    MEM_LOCAL,  IMM
//            this.add(new Pair<InfoAreaType, InfoAreaType>(MEM_LOCAL.type, IMM.type));

        }
    };

    public AC_Xor(InfoArea first, InfoArea second) throws Exception {
        if (!check_OK(first, second, rules))
            exception("Wrong infoArea for XOR command!", first, second);

        this.first = first;
        this.second = second;
    }

    @Override
    public String get_STRING() throws Exception {
        String stringBuilder = Asm.LEVEL_1_INDENT +
                "xor" +
                " " +
                first.get_STRING() +
                ", " +
                second.get_STRING();
        return stringBuilder;
    }
}
