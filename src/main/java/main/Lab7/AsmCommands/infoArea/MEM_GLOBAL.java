package main.Lab7.AsmCommands.infoArea;

// Область памяти (MEM) ссылаемся на глобальную переменную
//  [x_1]
public class MEM_GLOBAL implements InfoArea {
    public String globalVariable_str;

    public MEM_GLOBAL(String globalVariable_str) {
        this.globalVariable_str = globalVariable_str;
    }

    @Override
    public String get_STRING() {
        return "[" + globalVariable_str + "]";
    }
}
