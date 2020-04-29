package main.Lab4.TreeNext;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;
import main.SavePoint;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class _NextNode_Plus extends _NextNodeBase{
    public LexTypeTERMINAL lexTypeTERMINAL;
    public List<NextNode> A_1 = new ArrayList<>();
    public List<NextNode> A_2 = new ArrayList<>();

    public _NextNode_Plus(SavePoint savePoint) {
        super(savePoint);
    }
}
