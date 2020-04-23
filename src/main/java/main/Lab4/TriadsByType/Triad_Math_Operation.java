package main.Lab4.TriadsByType;

import lombok.Getter;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;
import main.Lab3.Node;
import main.Lab4.Triad;
import main.Lab4.TriadType;

@Getter
@Setter

public class Triad_Math_Operation extends _Triad_Base{

    public String left_lexemStr;
    public Node left_node;  // Если переменная
    public int left_index = Integer.MIN_VALUE;
    public Triad left_triad;    // если триада
    public LexTypeTERMINAL left_lexTypeTERMINAL;   // если константа

    public String right_lexemStr;

    public Triad right_triad;    // если триада
    public int right_index = Integer.MIN_VALUE;;
    public Node right_node;  // Если переменная
    public LexTypeTERMINAL right_lexTypeTERMINAL;  // если константа


    public Triad_Math_Operation() {
        this.triadType = TriadType.MATH_OPERATION;
    }
}
