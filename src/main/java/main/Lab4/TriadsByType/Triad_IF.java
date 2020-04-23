package main.Lab4.TriadsByType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab4.Triad;
import main.Lab4.TriadType;

@Getter
@Setter
public class Triad_IF extends _Triad_Base {
    public Triad isTrue;
    public int isTrue_index;

    public Triad isFalse;
    public int isFalse_index;

    public Triad_IF() {
        this.triadType = TriadType.IF;
    }
}
