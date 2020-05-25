package main.Lab4.TriadsByType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab3.Node;
import main.Lab4.TreeNext.NextNode;
import main.Lab4.TriadType;

@Getter
@Setter
public class Triad_Proc extends _Triad_Base{
    public String funcId;
    public NextNode node;

    public Triad_Proc() {
        this.triadType = TriadType.PROC;
    }

    public Triad_Proc(String funcId, NextNode node) {
        this.triadType = TriadType.PROC;
        this.funcId = funcId;
        this.node = node;
    }
}
