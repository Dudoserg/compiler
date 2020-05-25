package main.Lab4;

import lombok.Getter;
import lombok.Setter;
import main.Lab4.TriadsByType._Triad_Base;


@Getter
@Setter
public class Triad {
    public String operation;
    public String first;
    public String second;

    public _Triad_Base triad_base;


    public Triad(String operation, String first, String second) {
        this.operation = operation;
        this.first = first;
        this.second = second;
    }
}
