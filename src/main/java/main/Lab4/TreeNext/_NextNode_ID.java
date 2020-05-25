package main.Lab4.TreeNext;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;
import main.Lab3.Node;
import main.Lab4.TreeNext.Const.Interface_Const;
import main.Lab4.TreeNext.MathOperation.Interface_LexType;
import main.SavePoint;

import java.util.Objects;

@Getter
@Setter
public class _NextNode_ID extends _NextNodeBase implements Interface_LexType, Interface_Const {
    public LexTypeTERMINAL lexTypeTERMINAL;
    public String lexem;
    public NextNode nextNode;

    public _NextNode_ID(LexTypeTERMINAL lexTypeTERMINAL, String lexem, NextNode nextNode, SavePoint savePoint) {
        super(savePoint);
        this.lexTypeTERMINAL = lexTypeTERMINAL;
        this.lexem = lexem;
        this.nextNode = nextNode;
    }

    @Override
    public LexTypeTERMINAL getType() {
        return lexTypeTERMINAL;
    }


    @Override
    public void setLexem_(String lexem) {
        this.lexem = lexem;
    }

    @Override
    public String getLexem_() {
        return this.lexem;
    }
}
