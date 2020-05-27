package main.Lab7.AsmCommands;

import main.Lab7.Asm;
import main.Lab7.AsmCommands.infoArea.*;
import main.Lab7.PoolRegister;
import main.Lab7.Register;

import java.util.ArrayList;
import java.util.List;

public class AC_Push extends _AsmCommand {
    InfoArea first;     // ПРИЕМНИК

    // Возможные пары приемник/источник
    static List<InfoAreaType> rules = new ArrayList<InfoAreaType>() {
        {
            this.add(REG.type);
//            this.add(MEM_LOCAL.type);
//            this.add(MEM_GLOBAL.type);
            this.add(IMM.type);
        }
    };


    public AC_Push(InfoArea area,
                   PoolRegister poolRegister, List<_AsmCommand> asmCommandList) throws Exception {

        if (!check_OK(area, rules)) {
            if (area instanceof MEM_LOCAL) {
                // Перемещаем нажу память в регистр
                final Register freeRegister = poolRegister.getFree();
                REG freeREG = new REG(freeRegister);
                AC_Mov ac_mov = new AC_Mov(freeREG, area, poolRegister, asmCommandList);
                super.addCommand(ac_mov, asmCommandList);
                // Теперь пушим наш регистр
                area = freeREG;
                // освобождаем регистр
                poolRegister.release(freeRegister);
            }
        }

        if (!check_OK(area, rules)) {
            exception("Wrong infoArea for PUSH command!", area);

        }
        this.first = area;
    }


    @Override
    public String get_STRING() throws Exception {
        String result = Asm.LEVEL_2_INDENT +
                "push" +
                " " +
                first.get_STRING();
        return result;
    }
}
