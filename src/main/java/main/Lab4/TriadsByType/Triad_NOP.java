package main.Lab4.TriadsByType;

import lombok.Getter;
import lombok.Setter;
import main.Lab4.Triad;
import main.Lab4.TriadType;

@Getter
@Setter
public class Triad_NOP extends _Triad_Base {
    public Triad_NOP() {
        this.triadType = TriadType.NOP;
    }
}
