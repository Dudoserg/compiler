package main.Lab4.TreeNext;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;
import main.Lab4.TreeNext.MathOperation.Interface_LexType;
import main.SavePoint;

@Getter
@Setter
public class _NextNode_Cast extends _NextNodeBase implements Interface_LexType {
    LexTypeTERMINAL castTo_lexTypeTERMINAL;

    public _NextNode_Cast(LexTypeTERMINAL castTo_lexTypeTERMINAL, SavePoint savePoint) {
        super(savePoint);
        this.castTo_lexTypeTERMINAL = castTo_lexTypeTERMINAL;
    }

    @Override
    public LexTypeTERMINAL getType() {
        return castTo_lexTypeTERMINAL;
    }
}
