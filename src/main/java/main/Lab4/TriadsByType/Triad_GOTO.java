package main.Lab4.TriadsByType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab4.TreeNext.NextNode;
import main.Lab4.Triad;
import main.Lab4.TriadType;

@Getter
@Setter
public class Triad_GOTO extends _Triad_Base {
    public Triad jumpTo;
    public int jumpTo_index;
    public Triad triadIf;

    public Triad_GOTO( Triad triadIf) {
        this.triadType = TriadType.GOTO;
        this.triadIf = triadIf;
    }
}
