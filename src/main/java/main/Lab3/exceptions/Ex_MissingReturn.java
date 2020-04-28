package main.Lab3.exceptions;

import main.Lab3.LLK;
import main.Lab4.TreeNext.NextNode;
import main.Lab4.TreeNext._NextNode_Func;
import main.SavePoint;

public class Ex_MissingReturn extends Exception {
    public SavePoint savePoint;
    public Ex_MissingReturn(NextNode func) {
        super("Не все ветки кода возвращают значение в функции " +
                ((_NextNode_Func)func.nodeBase).lexem.toString());
        savePoint = LLK.savePointCurrent;
    }
}
