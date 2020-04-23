package main.Lab4.TriadsByType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;
import main.Lab3.Node;
import main.Lab4.TriadType;

@Getter
@Setter
public class Triad_Declare_Variable extends _Triad_Base{
    LexTypeTERMINAL lexTypeTERMINAL;
    String variableId;
    Node node;

    public Triad_Declare_Variable() {
        this.triadType = TriadType.DECLARE_VARIABLE;
    }

    public Triad_Declare_Variable(LexTypeTERMINAL lexTypeTERMINAL, String variableId, Node node) {
        this.triadType = TriadType.DECLARE_VARIABLE;
        this.lexTypeTERMINAL = lexTypeTERMINAL;
        this.variableId = variableId;
        this.node = node;
    }
}
