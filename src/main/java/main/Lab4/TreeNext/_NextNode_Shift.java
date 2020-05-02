package main.Lab4.TreeNext;

import lombok.Getter;
import lombok.Setter;
import main.SavePoint;

@Getter
@Setter

public class _NextNode_Shift extends _NextNodeBase{
    public int degree;
    public boolean isLeft = true;
    public _NextNode_Shift(int degree,boolean isLeft, SavePoint savePoint) {
        super(savePoint);
        this.isLeft = isLeft;
        this.degree = degree;
    }
}
