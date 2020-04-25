package main.Lab4.TreeNext;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;
import main.Lab3.Node;

@Getter
@Setter
@NoArgsConstructor
public class _NextNode_Double extends _NextNodeBase {
    public LexTypeTERMINAL next;
    public String lexem;
    public Node node;

    public _NextNode_Double(LexTypeTERMINAL next, String lexem, Node node) {
        this.next = next;
        this.lexem = lexem;
        this.node = node;
    }
}
