package main.Lab4.TriadsByType;

import lombok.Getter;
import lombok.Setter;
import main.Lab4.Triad;
import main.Lab4.TriadType;

@Getter
@Setter
public class Triad_PUSH extends _Triad_Base {
    // Если кладем триаду (тут может быть вычисление математической операции, вызов функции)
    public int index;
    public Triad triad;


    // Если кладем идентификатор переменно
    public String lexemStr = null;
    public String constantStr = null;


    public Triad_PUSH() {
        this.triadType = TriadType.PUSH;
    }
}
