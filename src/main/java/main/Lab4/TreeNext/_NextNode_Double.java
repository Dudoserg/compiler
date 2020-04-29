package main.Lab4.TreeNext;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;
import main.SavePoint;

@Getter
@Setter

public class _NextNode_Double extends _NextNodeBase {
    public LexTypeTERMINAL lexTypeTERMINAL;
    public String lexem;

    public _NextNode_Double(LexTypeTERMINAL lexTypeTERMINAL, String lexem,  SavePoint savePoint) {
        super(savePoint);
        this.lexTypeTERMINAL = lexTypeTERMINAL;
        this.lexem = lexem;
    }

    public _NextNode_Double(SavePoint savePoint) {
        super(savePoint);
    }
}
