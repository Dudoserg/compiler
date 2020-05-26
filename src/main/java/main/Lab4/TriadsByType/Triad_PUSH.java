package main.Lab4.TriadsByType;

import lombok.Getter;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;
import main.Lab4.TreeNext.NextNode;
import main.Lab4.Triad;
import main.Lab4.TriadType;

@Getter
@Setter
public class Triad_PUSH extends _Triad_Base {


    public boolean isNode  = false;
    public NextNode node;  // Если переменная

    //public int index = Integer.MIN_VALUE;

    public Triad triad;    // если триада
    public Integer triad_index;

    public LexTypeTERMINAL lexTypeTERMINAL;   // если константа
    public String lexemStr;

    public Triad_PUSH() {
        this.triadType = TriadType.PUSH;
    }
}
