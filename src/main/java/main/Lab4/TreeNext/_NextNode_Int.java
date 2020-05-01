package main.Lab4.TreeNext;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;
import main.Lab4.TreeNext.MathOperation.Interface_LexType;
import main.SavePoint;

@Getter
@Setter

public class _NextNode_Int extends _NextNodeBase implements Interface_LexType {
    public LexTypeTERMINAL lexTypeTERMINAL;
    public String lexem;

    public _NextNode_Int(LexTypeTERMINAL lexTypeTERMINAL, String lexem,  SavePoint savePoint) {
        super(savePoint);
        this.lexTypeTERMINAL = lexTypeTERMINAL;
        this.lexem = lexem;
    }


    @Override
    public LexTypeTERMINAL getType() {
        return lexTypeTERMINAL;
    }
}
