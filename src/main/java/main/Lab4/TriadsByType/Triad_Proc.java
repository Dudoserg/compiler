package main.Lab4.TriadsByType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab3.Node;
import main.Lab4.TriadType;

@Getter
@Setter
public class Triad_Proc extends _Triad_Base{
    String funcId;
    Node node;

    public Triad_Proc() {
        this.triadType = TriadType.PROC;
    }

    public Triad_Proc(String funcId, Node node) {
        this.triadType = TriadType.PROC;
        this.funcId = funcId;
        this.node = node;
    }
}
