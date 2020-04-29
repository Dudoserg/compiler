package main.Lab4.TreeNext;

import main.Lab2.LexTypeTERMINAL;
import main.SavePoint;

public class _NextNode_Func extends _NextNodeBase{
    public String lexem;
    public LexTypeTERMINAL lexTypeTERMINAL;
    public int countParam = 0;

    public _NextNode_Func(String lexem, LexTypeTERMINAL lexTypeTERMINAL, SavePoint savePoint) {
        super(savePoint);
        this.lexem = lexem;
        this.lexTypeTERMINAL = lexTypeTERMINAL;
    }
}
