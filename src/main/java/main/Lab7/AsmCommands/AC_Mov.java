package main.Lab7.AsmCommands;

import main.Lab7.AsmCommands.infoArea.InfoArea;

public class AC_Mov extends _AsmCommand {
    public InfoArea first;     // ПРИЕМНИК
    public InfoArea second;    // ИСТОЧНИК

    public AC_Mov(InfoArea first, InfoArea second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String get_STRING() throws Exception {
        String stringBuilder = "    " +
                "mov" +
                " " +
                first.get_STRING() +
                ", " +
                second.get_STRING();
        return stringBuilder;
    }
}
