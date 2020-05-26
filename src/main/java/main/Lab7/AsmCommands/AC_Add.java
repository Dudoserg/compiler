package main.Lab7.AsmCommands;

import javafx.util.Pair;
import main.Lab7.Asm;
import main.Lab7.AsmCommands.infoArea.*;
import main.Lab7.PoolRegister;
import main.Lab7.Register;

import java.util.ArrayList;
import java.util.List;

public class AC_Add extends _AsmCommand {
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

    public AC_Add(InfoArea first, InfoArea second, PoolRegister poolRegister, List<_AsmCommand> asmComandList) throws Exception {
//        //    REG,  MEM_GLOBAL
//        //    REG,  MEM_LOCAL
//        //    REG,  IMM
//        //    REG,  REG
//        if (first instanceof REG) {
//            if (second instanceof MEM_GLOBAL)
//                System.out.print("");
//            else if (second instanceof MEM_LOCAL)
//                System.out.print("");
//            else if (second instanceof IMM)
//                System.out.print("");
//            else if (second instanceof REG)
//                System.out.print("");
//            else
//                exception(first, second);
//        }
//        //    MEM_GLOBAL,  REG
//        //    MEM_GLOBAL,  IMM
//        else if (first instanceof MEM_GLOBAL) {
//            if (second instanceof REG)
//                System.out.print("");
//            else if (second instanceof IMM)
//                System.out.print("");
//            else
//                exception(first, second);
//        }
//        //    MEM_LOCAL,  REG
//        //    MEM_LOCAL,  IMM
//        else if (first instanceof MEM_LOCAL) {
//            if (second instanceof REG)
//                System.out.print("");
//            else if (second instanceof IMM)
//                System.out.print("");
//            else {
//                exception(first, second);
//            }
//        } else if (first instanceof IMM) {
//            if (second instanceof MEM_GLOBAL)
//                exception(first, second);
//
//            else if (second instanceof MEM_LOCAL)
//                exception(first, second);
//
//            // IMM IMM
//            else if (second instanceof IMM){
//                exception(first, second);
//                // Берем из пула регистр ( если свободных нет, то ошибка)
//                // грузим в регистр первое слагаемое, и все чики пуки
//            }
//
//            else if (second instanceof REG)
//                exception(first, second);
//
//            else
//                exception(first, second);
//        }

        if (!check_OK(first, second)) {
            // надо сделать REG XXX, если сюда зашли, явно что первый не регистр
            // Получаем свободный регистр
            final Register freeRegister = poolRegister.getFree();
            // Очищаем его
            AC_Xor ac_xor = new AC_Xor(new REG(freeRegister), new REG(freeRegister));
            asmComandList.add(ac_xor);
            String str_ac_xor = ac_xor.get_STRING() + "\n";
            System.out.print(str_ac_xor);
            // Помещаем в него первый операнд
            AC_Mov ac_mov = new AC_Mov(new REG(freeRegister), first);
            asmComandList.add(ac_mov);
            String str_ac_mov = ac_mov.get_STRING() + "\n";
            System.out.print(str_ac_mov);
            // теперь в этот регистр прибавляем второй операнд

            InfoArea firstChange = new REG(freeRegister);
            first = firstChange;
            // Еще раз чекнем, если что то не то - значит что то не то)00
            if (!check_OK(first, second))
                exception("Wrong infoArea for ADD command!", first, second);
        }
        this.first = first;
        this.second = second;
    }


    @Override
    public String get_STRING() throws Exception {
        String stringBuilder = Asm.LEVEL_1_INDENT +
                "add" +
                " " +
                first.get_STRING() +
                ", " +
                second.get_STRING();
        return stringBuilder;
    }
}