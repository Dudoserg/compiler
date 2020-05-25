package main.Lab7.AsmCommands.infoArea;


// Непосредственное значение (например, число) (IMM)
public class IMM implements InfoArea {
    public String value_str;
    public Integer value_int;

    public IMM(String value_str, Integer value_int) {
        this.value_str = value_str;
        this.value_int = value_int;
    }
    public IMM(Integer value_int) {
        this.value_int = value_int;
        this.value_str = String.valueOf(value_int);
    }

    @Override
    public String get_STRING() {
        return value_str;
    }
}
