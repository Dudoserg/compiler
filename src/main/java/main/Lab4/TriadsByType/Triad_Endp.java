package main.Lab4.TriadsByType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab4.TriadType;

@Getter
@Setter
public class Triad_Endp extends _Triad_Base {
    public Triad_Endp() {
        this.triadType = TriadType.ENDP;
    }
}
