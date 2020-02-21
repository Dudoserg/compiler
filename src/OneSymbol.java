import java.lang.reflect.Array;
import java.util.ArrayList;

public class OneSymbol {
    public boolean terminal;
    public Integer typ;
    public String lexString;

    public OneSymbol(boolean terminal, Integer typ, String lexString) {
        this.terminal = terminal;
        this.typ = typ;
        this.lexString = lexString;
    }
}
