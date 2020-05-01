package main.Lab4.TreeNext.MathOperation;

import lombok.Getter;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;
import main.Lab4.TreeNext.NextNode;
import main.Lab4.TreeNext._NextNodeBase;
import main.SavePoint;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class _NextNode_Div extends _NextNodeBase implements Interface_MathOperation, Interface_LexType {
    public LexTypeTERMINAL lexTypeTERMINAL;
    public List<NextNode> A_1 = new ArrayList<>();
    public List<NextNode> A_2 = new ArrayList<>();

    public _NextNode_Div(SavePoint savePoint) {
        super(savePoint);
    }

    @Override
    public void setType(LexTypeTERMINAL lexTypeTERMINAL) {
        this.lexTypeTERMINAL = lexTypeTERMINAL;
    }

    @Override
    public LexTypeTERMINAL getType() {
        return lexTypeTERMINAL;
    }
}
