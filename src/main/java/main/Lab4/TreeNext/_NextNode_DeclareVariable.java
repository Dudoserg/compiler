package main.Lab4.TreeNext;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class _NextNode_DeclareVariable extends _NextNodeBase {
    public LexTypeTERMINAL lexTypeTERMINAL;
    public String lexem;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        _NextNode_DeclareVariable that = (_NextNode_DeclareVariable) o;
        return lexTypeTERMINAL == that.lexTypeTERMINAL &&
                lexem.equals(that.lexem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lexTypeTERMINAL, lexem);
    }
}
