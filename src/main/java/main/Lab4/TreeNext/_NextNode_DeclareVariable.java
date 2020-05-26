package main.Lab4.TreeNext;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;
import main.Lab4.TreeNext.MathOperation.Interface_LexType;
import main.SavePoint;

import java.util.Objects;

@Getter
@Setter


public class _NextNode_DeclareVariable extends _NextNodeBase implements Interface_LexType {

    public boolean isGlobal = false;
    public boolean isLocal = false;
    public boolean isParam = false;



    //public static int asm_addr_counter = 0;
    public LexTypeTERMINAL lexTypeTERMINAL;
    public String lexem;
    public String currentValue = null;

    public String asm_name;
    public int asm_addr;
    public int asm_len;
    public int asm_index;   // номер  переменной по порядку следвоания
    public _NextNode_DeclareVariable(SavePoint savePoint) {
        super(savePoint);
    }

    public _NextNode_DeclareVariable(SavePoint savePoint, LexTypeTERMINAL lexTypeTERMINAL, String lexem) {
        super(savePoint);
        this.lexTypeTERMINAL = lexTypeTERMINAL;
        this.lexem = lexem;
    }

    @Override
    public LexTypeTERMINAL getType() {
        return lexTypeTERMINAL;
    }
}
