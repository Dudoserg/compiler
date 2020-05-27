package main.Lab7.AsmCommands;

import javafx.util.Pair;
import main.Lab7.Asm;
import main.Lab7.AsmCommands.infoArea.*;
import main.Lab7.PoolRegister;

import java.util.ArrayList;
import java.util.List;

public class AC_CMP extends _AsmCommand {
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
//
            //    MEM_GLOBAL,  REG
            this.add(new Pair<InfoAreaType, InfoAreaType>(MEM_GLOBAL.type, REG.type));
            //    MEM_GLOBAL,  IMM
            this.add(new Pair<InfoAreaType, InfoAreaType>(MEM_GLOBAL.type, IMM.type));
//
            //    MEM_LOCAL,  REG
            this.add(new Pair<InfoAreaType, InfoAreaType>(MEM_LOCAL.type, REG.type));
            //    MEM_LOCAL,  IMM
            this.add(new Pair<InfoAreaType, InfoAreaType>(MEM_LOCAL.type, IMM.type));

        }
    };

    public AC_CMP(InfoArea first, InfoArea second,
                  PoolRegister poolRegister, List<_AsmCommand> asmComandList) throws Exception {

        if( first instanceof MEM_LOCAL && second instanceof IMM){
            ((MEM_LOCAL) first).dword = "dword";
        }
        if (!check_OK(first, second, rules)) {
//            // надо сделать REG XXX, если сюда зашли, явно что первый не регистр
//            // Получаем свободный регистр
//            final Register freeRegister = poolRegister.getFree();
//            // Очищаем его
//            AC_Xor ac_xor = new AC_Xor(new REG(freeRegister), new REG(freeRegister));
//            super.addCommand(ac_xor, asmComandList);
//            // Помещаем в него первый операнд
//            AC_Mov ac_mov = new AC_Mov(new REG(freeRegister), first, poolRegister, asmComandList);
//            super.addCommand(ac_mov, asmComandList);
//            // теперь в этот регистр прибавляем второй операнд
//
//            InfoArea firstChange = new REG(freeRegister);
//            first = firstChange;

        }
        // Еще раз чекнем, если что то не то - значит что то не то)00
        if (!check_OK(first, second, rules))
            exception("Wrong infoArea for CMP command!", first, second);
        this.first = first;
        this.second = second;
    }


    @Override
    public String get_STRING() throws Exception {
        String stringBuilder = Asm.LEVEL_2_INDENT +
                "cmp" +
                " " +
                first.get_STRING() +
                ", " +
                second.get_STRING();
        return stringBuilder;
    }
}
