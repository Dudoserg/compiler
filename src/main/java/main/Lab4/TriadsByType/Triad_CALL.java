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
public class Triad_CALL extends _Triad_Base {

    public NextNode node_callFunc;
    public String lexemStr;
    public LexTypeTERMINAL lexTypeTERMINAL;

    public Triad_CALL(NextNode node_callFunc, String lexemStr,LexTypeTERMINAL lexTypeTERMINAL) {
        this.triadType = TriadType.CALL;
        this.node_callFunc = node_callFunc;
        this.lexemStr = lexemStr;
        this.lexTypeTERMINAL = lexTypeTERMINAL;
    }
}
