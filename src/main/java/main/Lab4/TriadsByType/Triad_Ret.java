package main.Lab4.TriadsByType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab4.TriadType;

@Getter
@Setter
public class Triad_Ret extends _Triad_Base {
    public Triad_Ret() {
        this.triadType = TriadType.RET;
    }
}
