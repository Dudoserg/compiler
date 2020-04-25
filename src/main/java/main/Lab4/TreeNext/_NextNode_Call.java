package main.Lab4.TreeNext;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;

@Getter
@Setter
@NoArgsConstructor
public class _NextNode_Call extends _NextNodeBase {
    public NextNode func;

    public _NextNode_Call(NextNode func) {
        this.func = func;
    }
}
