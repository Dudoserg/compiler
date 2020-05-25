package main.Lab4.TriadsByType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab4.TriadType;

@Getter
@Setter

public class Triad_Prolog extends _Triad_Base{
    public Triad_Prolog() {
        this.triadType = TriadType.PROLOG;

    }
}
