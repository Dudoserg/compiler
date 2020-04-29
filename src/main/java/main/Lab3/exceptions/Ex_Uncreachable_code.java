package main.Lab3.exceptions;

import main.Lab3.LLK;
import main.Lab4.TreeNext.NextNode;
import main.Lab4.TreeNext._NextNode_Func;
import main.SavePoint;

public class Ex_Uncreachable_code extends Exception {
    public SavePoint savePoint;
    public Ex_Uncreachable_code(NextNode func, NextNode nextNode) {

        super("Присутствуют недостижимые участки кода " +
                ((_NextNode_Func)func.nodeBase).lexem.toString() + "\n" +
               "недостижимый код находится после символа : " + nextNode.nodeBase.savePoint.getPrintStr() + "\n");
        savePoint = LLK.savePointCurrent;
    }
}
