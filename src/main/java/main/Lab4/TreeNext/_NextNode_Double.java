package main.Lab4.TreeNext;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;

@Getter
@Setter
@NoArgsConstructor
public class _NextNode_Double extends _NextNodeBase {
    public LexTypeTERMINAL lexTypeTERMINAL;
    public String lexem;

    public _NextNode_Double(LexTypeTERMINAL lexTypeTERMINAL, String lexem) {
        this.lexTypeTERMINAL = lexTypeTERMINAL;
        this.lexem = lexem;
    }


}
