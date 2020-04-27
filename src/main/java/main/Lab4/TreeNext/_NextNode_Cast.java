package main.Lab4.TreeNext;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;

@Getter
@Setter
public class _NextNode_Cast extends _NextNodeBase{
    LexTypeTERMINAL castTo_lexTypeTERMINAL;

    public _NextNode_Cast(LexTypeTERMINAL castTo_lexTypeTERMINAL) {
        this.castTo_lexTypeTERMINAL = castTo_lexTypeTERMINAL;
    }
}
