package main.Lab7.AsmCommands;

import main.Lab7.AsmCommands.infoArea.InfoArea;

public class AC_Sub extends _AsmCommand {
    public InfoArea first;     // ПРИЕМНИК
    public InfoArea second;    // ИСТОЧНИК

    public AC_Sub(InfoArea first, InfoArea second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String get_STRING() throws Exception {
        String stringBuilder = "    " +
                "sub" +
                " " +
                first.get_STRING() +
                ", " +
                second.get_STRING();
        return stringBuilder;
    }
}
