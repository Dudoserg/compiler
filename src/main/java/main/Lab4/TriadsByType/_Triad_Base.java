package main.Lab4.TriadsByType;

import lombok.Getter;
import lombok.Setter;
import main.Lab4.TriadType;
import main.Lab7.AsmCommands.infoArea.InfoArea;


public class _Triad_Base {
    public TriadType triadType;

    // Тут мы ссылаемся на место, где хранится результат выполнения триады регистр или область памяти и тд и тп
    public InfoArea result;

}
