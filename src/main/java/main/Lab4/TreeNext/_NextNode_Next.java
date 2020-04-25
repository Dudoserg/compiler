package main.Lab4.TreeNext;

import main.Lab3.Node;

import java.util.Objects;

public class _NextNode_Next extends _NextNodeBase{
    public String lexemStr;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        _NextNode_Next that = (_NextNode_Next) o;
        return Objects.equals(lexemStr, that.lexemStr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lexemStr);
    }
}
