package main.Lab4.TreeNext.MathOperation;

import javafx.util.Pair;
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

public class _NextNode_Minus extends _NextNodeBase implements Interface_MathOperation, Interface_LexType {
    public LexTypeTERMINAL lexTypeTERMINAL;
    public List<NextNode> A_1 = new ArrayList<>();
    public List<NextNode> A_2 = new ArrayList<>();

    public _NextNode_Minus(SavePoint savePoint) {
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


    @Override
    public String calculate(Integer first, Integer second) {
        return  String.valueOf( first - second );
    }

    @Override
    public String calculate(Integer first, Double second) {
        return String.valueOf( (first - second));
    }

    @Override
    public String calculate(Double first, Integer second) {
        return String.valueOf((first - second));
    }

    @Override
    public String calculate(Double first, Double second) {
        return String.valueOf((first - second));
    }
}
