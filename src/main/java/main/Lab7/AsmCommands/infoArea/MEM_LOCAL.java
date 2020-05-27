package main.Lab7.AsmCommands.infoArea;

import main.Lab7.Register;

// Область памяти (MEM)
//  [ebp-4]
public class MEM_LOCAL implements InfoArea {
    public static InfoAreaType type = InfoAreaType.MEM_LOCAL;
    // dword
    public String dword = "";
    public Register register;  // ebp
    public Integer shift;      // -4

    public MEM_LOCAL(Register register, Integer shift) {
        this.register = register;
        this.shift = shift;
    }

    @Override
    public String get_STRING() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        if(!dword.equals("")){
            stringBuilder.append(" ");
            stringBuilder.append(dword);
            stringBuilder.append(" ");
        }
        stringBuilder.append("[");
        stringBuilder.append(register.name);
        if (shift < 0)
//            throw new Exception("Ну тут не может быть отрицательное число");
            stringBuilder.append("");
        else
            stringBuilder.append("+");
        stringBuilder.append(shift);
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    @Override
    public InfoAreaType getType() {
        return MEM_LOCAL.type;
    }
}
