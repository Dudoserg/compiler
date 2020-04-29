package main.Lab4.TreeNext;

import main.Lab2.LexTypeTERMINAL;
import main.SavePoint;

public class _NextNode_FuncEnd extends _NextNodeBase{
    public NextNode func;

    public _NextNode_FuncEnd(NextNode func, SavePoint savePoint) {
        super(savePoint);
        this.func = func;
    }

    public _NextNode_FuncEnd(SavePoint savePoint) {
        super(savePoint);
    }
}
