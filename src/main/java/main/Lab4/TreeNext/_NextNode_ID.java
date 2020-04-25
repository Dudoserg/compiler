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
public class _NextNode_ID extends _NextNodeBase{
    public LexTypeTERMINAL lexTypeTERMINAL;
    public String lexem;
    public NextNode nextNode;

    public _NextNode_ID(LexTypeTERMINAL lexTypeTERMINAL, String lexem, NextNode nextNode) {
        this.lexTypeTERMINAL = lexTypeTERMINAL;
        this.lexem = lexem;
        this.nextNode = nextNode;
    }


}
