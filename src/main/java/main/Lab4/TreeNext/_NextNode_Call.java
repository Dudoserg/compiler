package main.Lab4.TreeNext;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;
import main.SavePoint;

@Getter
@Setter

public class _NextNode_Call extends _NextNodeBase {
    public NextNode func;

    public _NextNode_Call(NextNode func,  SavePoint savePoint) {
        super(savePoint);
        this.func = func;
    }

    public _NextNode_Call(SavePoint savePoint) {
        super(savePoint);
    }
}
