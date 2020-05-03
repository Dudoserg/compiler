package main.Lab4.TreeNext;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;
import main.Lab4.TreeNext.MathOperation.Interface_LexType;
import main.SavePoint;

@Getter
@Setter

public class _NextNode_Call extends _NextNodeBase implements Interface_LexType {
    public NextNode func;

    public _NextNode_Call(NextNode func,  SavePoint savePoint) {
        super(savePoint);
        this.func = func;
    }

    public _NextNode_Call(SavePoint savePoint) {
        super(savePoint);
    }

    @Override
    public LexTypeTERMINAL getType() {
        return ((_NextNode_Func)func.nodeBase).lexTypeTERMINAL;
    }
}
