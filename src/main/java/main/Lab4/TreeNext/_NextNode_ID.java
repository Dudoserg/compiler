package main.Lab4.TreeNext;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;
import main.Lab3.Node;
import main.Lab4.TreeNext.MathOperation.Interface_LexType;
import main.SavePoint;

import java.util.Objects;

@Getter
@Setter
public class _NextNode_ID extends _NextNodeBase implements Interface_LexType {
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


}
