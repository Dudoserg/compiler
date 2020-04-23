package main.Lab4.TriadsByType;

import lombok.Getter;
import lombok.Setter;
import main.Lab3.Node;
import main.Lab4.Triad;
import main.Lab4.TriadType;

@Getter
@Setter
public class Triad_CALL extends _Triad_Base {

    Node node_callFunc;

    public Triad_CALL(Node node_callFunc) {
        this.triadType = TriadType.CALL;
        this.node_callFunc = node_callFunc;
    }
}
