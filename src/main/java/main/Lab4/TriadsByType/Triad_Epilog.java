package main.Lab4.TriadsByType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab4.TriadType;

@Getter
@Setter
public class Triad_Epilog extends _Triad_Base {
    public Triad_Epilog() {
        this.triadType = TriadType.EPILOG;
    }
}
