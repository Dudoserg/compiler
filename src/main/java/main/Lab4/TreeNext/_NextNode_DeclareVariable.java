package main.Lab4.TreeNext;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;
import main.SavePoint;

import java.util.Objects;

@Getter
@Setter


public class _NextNode_DeclareVariable extends _NextNodeBase {
    public LexTypeTERMINAL lexTypeTERMINAL;
    public String lexem;


    public _NextNode_DeclareVariable(SavePoint savePoint) {
        super(savePoint);
    }

    public _NextNode_DeclareVariable(SavePoint savePoint, LexTypeTERMINAL lexTypeTERMINAL, String lexem) {
        super(savePoint);
        this.lexTypeTERMINAL = lexTypeTERMINAL;
        this.lexem = lexem;
    }
}
