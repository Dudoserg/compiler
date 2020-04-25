package main.Lab4.TreeNext;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;
import main.Lab3.Node;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class _NextNode_Double extends _NextNodeBase {
    public LexTypeTERMINAL next;
    public String lexem;

    public _NextNode_Double(LexTypeTERMINAL next, String lexem) {
        this.next = next;
        this.lexem = lexem;
    }


}
