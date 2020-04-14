package main.Lab3.createLLK;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.algoritm_1and2.maga.Elem;
import main.algoritm_1and2.maga.RightPart;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TableElem {
    Elem left;
    Elem right;
    List<RightPart> rightPartList;

    public TableElem(Elem left, Elem right, List<RightPart> rightPartList) {
        this.left = left;
        this.right = right;
        this.rightPartList = rightPartList;
    }
}
