package main.Lab4.TreeNext.MathOperation;

import javafx.util.Pair;
import main.Lab2.LexTypeTERMINAL;

public interface Interface_MathOperation {
    public void setType(LexTypeTERMINAL lexTypeTERMINAL);
    public LexTypeTERMINAL getType( );

    public String calculate(Integer first, Integer second);
    public String calculate(Integer first, Double second);
    public String calculate(Double first, Integer second);
    public String calculate(Double first, Double second);
}
