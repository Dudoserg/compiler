package main.Lab7.AsmCommands.infoArea;

import main.Lab7.Register;

// Регистр общего назначения (REG)
public class REG implements InfoArea {
    public static InfoAreaType type = InfoAreaType.REG;
    public Register register;

    public REG(Register register) {
        this.register = register;
    }

    @Override
    public String get_STRING() throws Exception {
        return register.name;
    }

    @Override
    public InfoAreaType getType() {
        return REG.type;
    }
}
