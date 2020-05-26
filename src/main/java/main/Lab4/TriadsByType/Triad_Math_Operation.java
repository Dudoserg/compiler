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

public class Triad_Math_Operation extends _Triad_Base{

    //public int left_index = Integer.MIN_VALUE;

    public boolean left_isNode  = false;
    public NextNode left_node;                          // Если переменная

    public Triad left_triad;                            // если триада
    public Integer left_triad_index;

    public LexTypeTERMINAL left_lexTypeTERMINAL;        // если константа
    public String left_lexemStr;


    public boolean right_isNode;
    public NextNode right_node;                             // Если переменная
    //public int right_index = Integer.MIN_VALUE;;

    public Triad right_triad;                           // если триада
    public Integer right_triad_index;

    public LexTypeTERMINAL right_lexTypeTERMINAL;       // если константа
    public String right_lexemStr;


    public Triad_Math_Operation() {
        this.triadType = TriadType.MATH_OPERATION;
    }
}
