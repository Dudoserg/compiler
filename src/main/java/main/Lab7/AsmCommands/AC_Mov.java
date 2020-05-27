package main.Lab7.AsmCommands;

import com.sun.org.apache.regexp.internal.RE;
import javafx.util.Pair;
import main.Lab7.Asm;
import main.Lab7.AsmCommands.infoArea.*;
import main.Lab7.PoolRegister;
import main.Lab7.Register;

import java.util.ArrayList;
import java.util.List;

public class AC_Mov extends _AsmCommand {
    public InfoArea first;     // ПРИЕМНИК
    public InfoArea second;    // ИСТОЧНИК

    // Возможные пары приемник/источник
    static List<Pair<InfoAreaType, InfoAreaType>> rules = new ArrayList<Pair<InfoAreaType, InfoAreaType>>() {
        {
            //    REG,  MEM_GLOBAL
            this.add(new Pair<InfoAreaType, InfoAreaType>(REG.type, MEM_GLOBAL.type));
            //    REG,  MEM_LOCAL
            this.add(new Pair<InfoAreaType, InfoAreaType>(REG.type, MEM_LOCAL.type));
            //    REG,  IMM
            this.add(new Pair<InfoAreaType, InfoAreaType>(REG.type, IMM.type));
            //    REG,  REG
            this.add(new Pair<InfoAreaType, InfoAreaType>(REG.type, REG.type));

            //    MEM_GLOBAL,  REG
            this.add(new Pair<InfoAreaType, InfoAreaType>(MEM_GLOBAL.type, REG.type));
            //    MEM_GLOBAL,  IMM
            this.add(new Pair<InfoAreaType, InfoAreaType>(MEM_GLOBAL.type, IMM.type));

            //    MEM_LOCAL,  REG
            this.add(new Pair<InfoAreaType, InfoAreaType>(MEM_LOCAL.type, REG.type));
            //    MEM_LOCAL,  IMM
            this.add(new Pair<InfoAreaType, InfoAreaType>(MEM_LOCAL.type, IMM.type));
        }

    };


    public AC_Mov(InfoArea first, InfoArea second,
                  PoolRegister poolRegister, List<_AsmCommand> asmCommandList) throws Exception {

        if( first instanceof MEM_LOCAL && second instanceof IMM){
            ((MEM_LOCAL) first).dword = "dword";
        }

        if (!check_OK(first, second, rules)) {

            // MEM_LOCAL    // MEM_LOCAL
            if (first instanceof MEM_LOCAL && second instanceof MEM_LOCAL) {
                // вторую область нужно загрузить в регистр
                final Register freeRegister = poolRegister.getFree();
                final REG freeRegisterREG = new REG(freeRegister);
                AC_Mov ac_mov = new AC_Mov(freeRegisterREG, second, poolRegister, asmCommandList);
                super.addCommand(ac_mov, asmCommandList);
                // изменяем второй параметр MOV на наш регистр
                second = freeRegisterREG;
                // освобождаем регистр
                poolRegister.release(freeRegister);

            }
        }
        if (check_OK(first, second, rules)) {
            this.first = first;
            this.second = second;
        } else
            exception("Wrong infoArea for MOV command!", first, second);

    }

    @Override
    public String get_STRING() throws Exception {
        String stringBuilder = Asm.LEVEL_1_INDENT +
                "mov" +
                " " +
                first.get_STRING() +
                ", " +
                second.get_STRING();
        return stringBuilder;
    }
}
