package main.Lab4.TreeNext;

import main.Lab2.LexTypeTERMINAL;
import main.Lab4.TreeNext.MathOperation.Interface_LexType;
import main.SavePoint;

import java.util.ArrayList;
import java.util.List;

public class _NextNode_Func extends _NextNodeBase implements Interface_LexType {
    public String lexem;
    public LexTypeTERMINAL lexTypeTERMINAL;
    public int countParam = 0;


    public int asm_countLocalVariable = 0;
    public List<NextNode> localVariableList = new ArrayList<>();

    public _NextNode_Func(String lexem, LexTypeTERMINAL lexTypeTERMINAL, SavePoint savePoint) {
        super(savePoint);
        this.lexem = lexem;
        this.lexTypeTERMINAL = lexTypeTERMINAL;
    }

    @Override
    public LexTypeTERMINAL getType() {
        return lexTypeTERMINAL;
    }



}
