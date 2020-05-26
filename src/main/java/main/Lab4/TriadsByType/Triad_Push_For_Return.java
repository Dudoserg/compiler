package main.Lab4.TriadsByType;

import lombok.Getter;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;
import main.Lab3.Node;
import main.Lab4.TreeNext.NextNode;
import main.Lab4.Triad;
import main.Lab4.TriadType;

@Getter
@Setter
public class Triad_Push_For_Return extends _Triad_Base {
    //public int index = Integer.MIN_VALUE;

    public String lexemStr;
    public NextNode node;  // Если переменная
    public LexTypeTERMINAL lexTypeTERMINAL;   // если константа
    public Triad triad;    // если триада
    public Integer triad_index;

    public Triad_Push_For_Return() {
        this.triadType = TriadType.PUSH_FOR_RETURN;
    }
}
