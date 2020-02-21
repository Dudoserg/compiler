import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.stream.Collectors;

public class LLK {

    private static final int PRINTLOGS = 0;

    private static final Integer _A = 100;
    private static final Integer _A1 = 101;
    private static final Integer _A2 = 102;
    private static final Integer _A3 = 103;
    private static final Integer _A4 = 104;
    private static final Integer _A5 = 105;
    private static final Integer _A6 = 106;
    private static final Integer _B = 107;
    private static final Integer _C = 108;
    private static final Integer _D = 109;
    private static final Integer _E = 110;
    private static final Integer _F = 111;
    private static final Integer _G = 112;
    private static final Integer _H = 113;
    private static final Integer _if = 114;
    private static final Integer _K = 115;
    private static final Integer _L = 116;
    private static final Integer _M = 117;
    private static final Integer _N = 118;
    private static final Integer _O = 119;
    private static final Integer _P = 120;
    private static final Integer _Q = 121;
    private static final Integer _R = 122;
    private static final Integer _R1 = 123;
    private static final Integer _R2 = 124;
    private static final Integer _R3 = 125;
    private static final Integer _R4 = 126;
    private static final Integer _R5 = 127;
    private static final Integer _S = 128;
    private static final Integer _T = 129;
    private static final Integer _W2 = 130;
    private static final Integer _W3 = 131;
    private static final Integer _W4 = 132;
    private static final Integer _W5 = 133;
    private static final Integer _W6 = 134;
    private static final Integer _W7 = 135;
    private static final Integer _W8 = 136;
    private static final Integer _W9 = 137;
    private static final Integer _W13 = 138;
    private static final Integer _epsilon = 199;




    private HashMap<Pair<Integer, Integer>,TableCell > table;

    //private ArrayList<OneSymbol> magazin;
    private Stack<OneSymbol> magazin;

    private OneSymbol up;

    private Scaner scaner;
    private boolean flag = true;

    private int countError = 0;

    private void initTable(){
        this.table = new HashMap<>();
        TableCell tmpCell;
        Rule tmpRule;
////////////////////////////////////////////////////////////////////////////
        ////////////// <A> int
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._A, "A"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._B, "B"));
        tmpCell.rules.add(tmpRule);

//        tmpRule = new Rule();
//        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "e"));
//        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <A> double
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._A, "A"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._B, "B"));
        tmpCell.rules.add(tmpRule);

//        tmpRule = new Rule();
//        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "e"));
//        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A, Scaner._DOUBLE), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <A> const
        tmpCell = new TableCell();


        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._A, "A"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._B, "B"));
        tmpCell.rules.add(tmpRule);
//        tmpRule = new Rule();
//        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "e"));
//        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A, Scaner._CONST), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <A1> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R1, "R1"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A2, "A2"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A1, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <A1> (
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R1, "R1"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A2, "A2"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A1, Scaner._PARENTHESIS_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <A2> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R2, "R2"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A3, "A3"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A2, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <A2> (
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R2, "R2"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A3, "A3"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A2, Scaner._PARENTHESIS_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <A3> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R3, "R3"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A4, "A4"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A3, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <A3> (
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R3, "R3"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A4, "A4"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A3, Scaner._PARENTHESIS_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <A4> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R4, "R4"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A5, "A5"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A4, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <A4> (
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R4, "R4"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A5, "A5"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A4, Scaner._PARENTHESIS_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <A5> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R5, "R5"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A6, "A6"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A5, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <A5> (
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R5, "R5"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A6, "A6"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A5, Scaner._PARENTHESIS_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <B> int
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._C, "C"));
        tmpCell.rules.add(tmpRule);

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._E, "E"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._B, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <B> double
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._C, "C"));
        tmpCell.rules.add(tmpRule);

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._E, "E"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._B, Scaner._DOUBLE), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <C> int
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._SEMICOLON, ";"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._G, "G"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._F, "F"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._C, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <C> double
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._SEMICOLON, ";"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._G, "G"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._F, "F"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._C, Scaner._DOUBLE), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <E> int
        tmpCell = new TableCell();

        tmpRule = new Rule();
//        tmpRule.symbols.add(new OneSymbol(false, LLK._L, "L"));
//        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
//        tmpRule.symbols.add(new OneSymbol(false, LLK._K, "K"));
//        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_OPEN, "("));
//        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
//        tmpRule.symbols.add(new OneSymbol(false, LLK._F, "F"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._W13, "w13"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_OPEN, "("));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._F, "F"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._E, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <E> double
        tmpCell = new TableCell();

        tmpRule = new Rule();
//        tmpRule.symbols.add(new OneSymbol(false, LLK._L, "L"));
//        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
//        tmpRule.symbols.add(new OneSymbol(false, LLK._K, "K"));
//        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_OPEN, "("));
//        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
//        tmpRule.symbols.add(new OneSymbol(false, LLK._F, "F"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._W13, "w13"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_OPEN, "("));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._F, "F"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._E, Scaner._DOUBLE), tmpCell);
/////////////////////////////////////////////////////////////////////////////
        //// <W13> int
        tmpCell = new TableCell();

        tmpRule = new Rule();
            tmpRule.symbols.add(new OneSymbol(false, LLK._L, "L"));
            tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
            tmpRule.symbols.add(new OneSymbol(false, LLK._K, "K"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W13, Scaner._INT), tmpCell);
/////////////////////////////////////////////////////////////////////////////
        //// <W13> double
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._L, "L"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._K, "K"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W13, Scaner._DOUBLE), tmpCell);
/////////////////////////////////////////////////////////////////////////////
        //// <W13> )
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._L, "L"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W13, Scaner._PARENTHESIS_CLOSE), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <F> int
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._INT, "int"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._F, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <F> double
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._DOUBLE, "double"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._F, Scaner._DOUBLE), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <K> int
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W4, "W4"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._F, "F"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._K, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <K> double
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W4, "W4"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._F, "F"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._K, Scaner._DOUBLE), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <M> int
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._M, "M"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._N, "N"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._M, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <M> double
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._M, "M"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._N, "N"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._M, Scaner._DOUBLE), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <N> int
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._C, "C"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._N, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <N> double
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._C, "C"));;
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._N, Scaner._DOUBLE), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <W7> int
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "epsilon,"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W7, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <W7> double
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "epsilon,"));;
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W7, Scaner._DOUBLE), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <B> const
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._D, "D"));;
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._B, Scaner._CONST), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <D> const
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._SEMICOLON, ";"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._R, "R"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._F, "F"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._CONST, "const"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._D, Scaner._CONST), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <M> const
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._M, "M"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._N, "N"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._M, Scaner._CONST), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <N> const
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._D, "D"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._N, Scaner._CONST), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <w7> const
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W7, Scaner._CONST), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <A6> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
        tmpCell.rules.add(tmpRule);

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._Q, "Q"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A6, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <G> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W2, "W2"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._H, "H"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._G, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <H> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W3, "W3"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._H, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <M> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._M, "M"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._N, "N"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._M, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <N> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._O, "O"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._N, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <O> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._P, "P"));
        tmpCell.rules.add(tmpRule);

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._SEMICOLON, ";"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._Q, "Q"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._O, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <P> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._SEMICOLON, ";"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ASSIGN, "="));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._P, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <Q> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W8, "W8"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_OPEN, "("));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._Q, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W6, "W6"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ASSIGN, "="));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <T> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W9, "W9"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._T, Scaner._ID), tmpCell);

////////////////////////////////////////////////////////////////////////////
        ////////////// <W7> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W7, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <W8> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._T, "T"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W8, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <L> {
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._BRACE_CLOSE, "}"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._M, "M"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._BRACE_OPEN, "{"));

        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._L, Scaner._BRACE_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <M> {
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._M, "M"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._N, "N"));

        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._M, Scaner._BRACE_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <N> {
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._O, "O"));

        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._N, Scaner._BRACE_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <O> {
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._L, "L"));

        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._O, Scaner._BRACE_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <W7> {
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));

        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W7, Scaner._BRACE_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <M> }
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));

        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._M, Scaner._BRACE_CLOSE), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <A6> (
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_OPEN, "("));

        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A6, Scaner._PARENTHESIS_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <T> (
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W9, "W9"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._T, Scaner._PARENTHESIS_OPEN), tmpCell);

////////////////////////////////////////////////////////////////////////////
        ////////////// <W8> (
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._T, "T"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W8, Scaner._PARENTHESIS_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R1> )
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R1, Scaner._PARENTHESIS_CLOSE), tmpCell);
        ////////////// <R2> )
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R2, Scaner._PARENTHESIS_CLOSE), tmpCell);
        ////////////// <R3> )
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R3, Scaner._PARENTHESIS_CLOSE), tmpCell);
        ////////////// <R4> )
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R4, Scaner._PARENTHESIS_CLOSE), tmpCell);
        ////////////// <R5> )
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R5, Scaner._PARENTHESIS_CLOSE), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <W4> )
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W4, Scaner._PARENTHESIS_CLOSE), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <W8> )
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W8, Scaner._PARENTHESIS_CLOSE), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <W9> )
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W9, Scaner._PARENTHESIS_CLOSE), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <M> ;
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._M, "M"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._N, "N"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._M, Scaner._SEMICOLON), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <N> ;
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._O, "O"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._N, Scaner._SEMICOLON), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <O> ;
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._SEMICOLON, ";"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._O, Scaner._SEMICOLON), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R1> ;
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R1, Scaner._SEMICOLON), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <W2> ;
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W2, Scaner._SEMICOLON), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <W6> ;
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W6, Scaner._SEMICOLON), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <W7> ;
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W7, Scaner._SEMICOLON), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R1> ,
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R1, Scaner._COMMA), tmpCell);
        ////////////// <R2> ,
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R2, Scaner._COMMA), tmpCell);
        ////////////// <R3> ,
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R3, Scaner._COMMA), tmpCell);
        ////////////// <R4> ,
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R4, Scaner._COMMA), tmpCell);
        ////////////// <R5> ,
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R5, Scaner._COMMA), tmpCell);

////////////////////////////////////////////////////////////////////////////
        ////////////// <W2> ,
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W2, "W2"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._G, "G"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._COMMA, ","));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W2, Scaner._COMMA), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <W3> ,
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W3, Scaner._COMMA), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <W3> ;
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W3, Scaner._SEMICOLON), tmpCell);
        //////////////////////////////////////////////////////////////////////////
        ////////////// <W3> =
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ASSIGN, "="));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W3, Scaner._ASSIGN), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <W4> ,
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W4, "W4"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._F, "F"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._COMMA, ","));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W4, Scaner._COMMA), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <W6> ,
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W6, "W6"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ASSIGN, "="));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._COMMA, ","));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W6, Scaner._COMMA), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <W9> ,
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W9, "W9"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._COMMA, ","));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W9, Scaner._COMMA), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R5> *
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R5, "R5"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A6, "A6"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._STAR    , "*"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R5, Scaner._STAR), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R5> /
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R5, "R5"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A6, "A6"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._SLASH    , "/"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R5, Scaner._SLASH), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R5> %
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R5, "R5"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A6, "A6"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PERCENT    , "%"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R5, Scaner._PERCENT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R5> +
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R5, Scaner._PLUS), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R5> -
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R5, Scaner._MINUS), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R4> +
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R4, "R4"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A5, "A5"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PLUS    , "+"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R4, Scaner._PLUS), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R4> -
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R4, "R4"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A5, "A5"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._MINUS    , "-"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R4, Scaner._MINUS), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R4> >>
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R4, Scaner._SHIFT_RIGHT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R4> <<
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R4, Scaner._SHIFT_LEFT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R3> >>
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R3, "R3"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A4, "A4"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._SHIFT_RIGHT    , ">>"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R3, Scaner._SHIFT_RIGHT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R3> <<
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R3, "R3"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A4, "A4"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._SHIFT_LEFT    , "<<"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R3, Scaner._SHIFT_LEFT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R3> >
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R3, Scaner._GREAT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R3> >=
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R3, Scaner._GREAT_EQUALLY), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R3> <
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R3, Scaner._LESS), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R3> <=
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R3, Scaner._LESS_EQUALLY), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R2> >
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R2, "R2"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A3, "A3"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._GREAT    , ">"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R2, Scaner._GREAT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R2> >=
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R2, "R2"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A3, "A3"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._GREAT_EQUALLY    , ">="));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R2, Scaner._GREAT_EQUALLY), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R2> <
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R2, "R2"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A3, "A3"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._LESS    , "<"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R2, Scaner._LESS), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R2> <=
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R2, "R2"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A3, "A3"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._LESS_EQUALLY    , "<="));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R2, Scaner._LESS_EQUALLY), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R2> ==
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R2, Scaner._EQUALLY), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R2> !=
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R2, Scaner._NOT_EQUALLY), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R1> ==
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R1, "R1"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A2, "A2"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._EQUALLY    , "<="));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R1, Scaner._EQUALLY), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <R1> !=
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R1, "R1"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A2, "A2"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._NOT_EQUALLY    , "<="));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R1, Scaner._NOT_EQUALLY), tmpCell);
//======================================================================================================================
//////////////////////////////////////////////////////////////////////////
        ////////////// <A1> const8
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R1, "R1"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A2, "A2"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A1, Scaner._TYPE_INT_8), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <A1> const10
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R1, "R1"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A2, "A2"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A1, Scaner._TYPE_INT_10), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <A1> const16
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R1, "R1"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A2, "A2"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A1, Scaner._TYPE_INT_16), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <A1> const S
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R1, "R1"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A2, "A2"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A1, Scaner._TYPE_CHAR), tmpCell);


//////////////////////////////////////////////////////////////////////////
        ////////////// <A2> const8
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R2, "R2"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A3, "A3"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A2, Scaner._TYPE_INT_8), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <A2> const10
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R2, "R2"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A3, "A3"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A2, Scaner._TYPE_INT_10), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <A2> const16
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R2, "R2"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A3, "A3"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A2, Scaner._TYPE_INT_16), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <A2> const S
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R2, "R2"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A3, "A3"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A2, Scaner._TYPE_CHAR), tmpCell);


//////////////////////////////////////////////////////////////////////////
        ////////////// <A3> const8
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R3, "R3"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A4, "A4"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A3, Scaner._TYPE_INT_8), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <A3> const10
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R3, "R3"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A4, "A4"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A3, Scaner._TYPE_INT_10), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <A3> const16
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R3, "R3"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A4, "A4"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A3, Scaner._TYPE_INT_16), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <A3> const S
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R3, "R3"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A4, "A4"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A3, Scaner._TYPE_CHAR), tmpCell);


        //////////////////////////////////////////////////////////////////////////
        ////////////// <A4> const8
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R4, "R4"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A5, "A5"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A4, Scaner._TYPE_INT_8), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <A4> const10
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R4, "R4"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A5, "A5"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A4, Scaner._TYPE_INT_10), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <A4> const16
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R4, "R4"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A5, "A5"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A4, Scaner._TYPE_INT_16), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <A4> const S
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R4, "R4"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A5, "A5"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A4, Scaner._TYPE_CHAR), tmpCell);


        //////////////////////////////////////////////////////////////////////////
        ////////////// <A5> const8
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R5, "R5"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A6, "A6"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A5, Scaner._TYPE_INT_8), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <A5> const10
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R5, "R5"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A6, "A6"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A5, Scaner._TYPE_INT_10), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <A5> const16
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R5, "R5"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A6, "A6"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A5, Scaner._TYPE_INT_16), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <A5> const S
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._R5, "R5"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A6, "A6"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A5, Scaner._TYPE_CHAR), tmpCell);


        //////////////////////////////////////////////////////////////////////////
        ////////////// <A6> const8
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._S, "S"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A6, Scaner._TYPE_INT_8), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <A6> const10
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._S, "S"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A6, Scaner._TYPE_INT_10), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <A6> const16
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._S, "S"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A6, Scaner._TYPE_INT_16), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <A6> const S
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._S, "S"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A6, Scaner._TYPE_CHAR), tmpCell);


        //////////////////////////////////////////////////////////////////////////
        ////////////// <S> const8
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._TYPE_INT_8, "_TYPE_INT_8"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._S, Scaner._TYPE_INT_8), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <S> const10
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._TYPE_INT_10, "_TYPE_INT_10"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._S, Scaner._TYPE_INT_10), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <S> const16
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._TYPE_INT_16, "_TYPE_INT_16"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._S, Scaner._TYPE_INT_16), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <S> const S
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._TYPE_CHAR, "_TYPE_CHAR"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._S, Scaner._TYPE_CHAR), tmpCell);


        //////////////////////////////////////////////////////////////////////////
        ////////////// <T> const8
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W9, "W9"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._T, Scaner._TYPE_INT_8), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <T> const10
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W9, "W9"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._T, Scaner._TYPE_INT_10), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <T> const16
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W9, "W9"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._T, Scaner._TYPE_INT_16), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <T> const S
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W9, "W9"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._T, Scaner._TYPE_CHAR), tmpCell);



        //////////////////////////////////////////////////////////////////////////
        ////////////// <W8> const8
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._T, "T"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W8, Scaner._TYPE_INT_8), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <W8> const10
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._T, "T"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W8, Scaner._TYPE_INT_10), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <W8> const16
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._T, "T"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W8, Scaner._TYPE_INT_16), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <W8> const S
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._T, "T"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W8, Scaner._TYPE_CHAR), tmpCell);


        //////////////////////////////////////////////////////////////////////////
        ////////////// <if> if
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W7, "W7"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._O, "O"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_OPEN, "("));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._IF, "if"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._if, Scaner._IF), tmpCell);
        //////////////////////////////////////////////////////////////////////////
        ////////////// <M> if
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._M, "M"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._N, "N"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._M, Scaner._IF), tmpCell);
        //////////////////////////////////////////////////////////////////////////
        ////////////// <N> if
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._O, "O"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._N, Scaner._IF), tmpCell);
        //////////////////////////////////////////////////////////////////////////
        ////////////// <O> if
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._if, "<if>"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._O, Scaner._IF), tmpCell);
        //////////////////////////////////////////////////////////////////////////
        ////////////// <W7> if
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W7, Scaner._IF), tmpCell);
        //////////////////////////////////////////////////////////////////////////
        ////////////// <W7> else
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._O, "O"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ELSE, "else"));
        tmpCell.rules.add(tmpRule);

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._W7, Scaner._ELSE), tmpCell);
        //////////////////////////////////////////////////////////////////////////
        ////////////// <A> #
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A, Scaner._END), tmpCell);
//======================================================================================================================
        ////////////// <R2> ;
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R2, Scaner._SEMICOLON), tmpCell);
        ////////////// <R3> ;
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R3, Scaner._SEMICOLON), tmpCell);
        ////////////// <R4> ;
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R4, Scaner._SEMICOLON), tmpCell);
        ////////////// <R5> ;
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R5, Scaner._SEMICOLON), tmpCell);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//  <RA> #
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._A, Scaner._END), tmpCell);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ДЭБИЛЬНЫЙ блок с ешками
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        this.table.put(new Pair<>(LLK._R5, Scaner._SHIFT_RIGHT), tmpCell);
        this.table.put(new Pair<>(LLK._R5, Scaner._SHIFT_LEFT), tmpCell);
        this.table.put(new Pair<>(LLK._R5, Scaner._GREAT), tmpCell);
        this.table.put(new Pair<>(LLK._R5, Scaner._GREAT_EQUALLY), tmpCell);
        this.table.put(new Pair<>(LLK._R5, Scaner._LESS), tmpCell);
        this.table.put(new Pair<>(LLK._R5, Scaner._LESS_EQUALLY), tmpCell);
        this.table.put(new Pair<>(LLK._R5, Scaner._EQUALLY), tmpCell);
        this.table.put(new Pair<>(LLK._R5, Scaner._NOT_EQUALLY), tmpCell);

        this.table.put(new Pair<>(LLK._R4, Scaner._GREAT), tmpCell);
        this.table.put(new Pair<>(LLK._R4, Scaner._GREAT_EQUALLY), tmpCell);
        this.table.put(new Pair<>(LLK._R4, Scaner._LESS), tmpCell);
        this.table.put(new Pair<>(LLK._R4, Scaner._LESS_EQUALLY), tmpCell);
        this.table.put(new Pair<>(LLK._R4, Scaner._EQUALLY), tmpCell);
        this.table.put(new Pair<>(LLK._R4, Scaner._NOT_EQUALLY), tmpCell);

        this.table.put(new Pair<>(LLK._R3, Scaner._EQUALLY), tmpCell);
        this.table.put(new Pair<>(LLK._R3, Scaner._NOT_EQUALLY), tmpCell);

    }

    private void putRule(Stack stack,Rule rule){
        // Кладем все символы правила в СТЕК
        for(OneSymbol oneSymbol: rule.symbols){
            stack.push(oneSymbol);
        }
    }

    private void printStack(Stack<OneSymbol> stack){
        if( PRINTLOGS == 1) {
            for (int i = 0; i < 30; i++)
                System.out.print("=");
            System.out.println("\n");
            ArrayList<OneSymbol> list = new ArrayList<>();
            for (OneSymbol oneSymbol : stack) {
                list.add(0, oneSymbol);
                //System.out.println("<" + oneSymbol.lexString +">");
            }
            for (OneSymbol oneSymbol : list) {
                if (oneSymbol.terminal)
                    System.out.println(oneSymbol.lexString);
                else
                    System.out.println("<" + oneSymbol.lexString + ">");
            }
        }
    }

    private void printLex(ArrayList<Character> lex){
        if( PRINTLOGS == 1){
            System.out.println();
            System.out.print("lex = ");
            for(Character c : lex){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public LLK() throws IOException {
        // Инициализируем таблицу
        this.initTable();

        try {
            this.scaner = new Scaner();
        } catch (IOException e) {
            e.printStackTrace();
        }

  /*      boolean flag = true;
        while (flag){
            ArrayList<Character> lex = new ArrayList<>();
            Integer type;
            type = scaner.next(lex);

            System.out.println("lex = [ " + lex.stream().map(String::valueOf).collect(Collectors.joining()) +
                    " ]\t\ttype = [ " + type + " ]");
            System.out.println();
        }*/

        this.magazin = new Stack<>();

        // Записываем аксиому
        magazin.push(new OneSymbol(true, Scaner._END, "#"));
        magazin.push(new OneSymbol(false, LLK._A, "A"));

        ArrayList<Character> lex = new ArrayList<>();
        Integer type;
        type = scaner.next(lex);


        while( flag){

            this.printStack(this.magazin);
            // Проверить содержимое верхушки магазина
            up = this.magazin.pop();
            this.printLex(lex);
            ////////////////////////////////////////////////////////////////////////////////////////////////////////
            // Случай когда эпсилон
            if( up.typ == LLK._epsilon)
                continue;
            ////////////////////////////////////////////////////////////////////////////////////////////////////////
            // Терминал
            if( up.terminal == true){
                System.out.print("");
                //System.out.println("up.typ = " + up.typ + "\t type = " + type);
                //int sukablydskay =  up.typ - type;
                // Mag == lex
                if( up.typ.intValue() == type.intValue()){
                    System.out.print("");
                    if( type == Scaner._END)
                        flag = false;
                    else
                        type = scaner.next(lex);
                }
                else{
                    scaner.printError("ERR", lex);
                    countError++;
                    throw new NullPointerException("\n=====================================================\n\t\t\t\tОшибка!\n=====================================================");
                }
            }
            // Нетерминал
            else{
                // Анализ клетки управляющей таблицы
                System.out.print("");
                boolean f = table.containsKey(new Pair<>(up.typ, type));
                if( f == true){
                    // В ячейке не пусто
                    // Заменить символ Mag на клетку (up.typ, type)

                    TableCell tableCell = table.get(new Pair<>(up.typ, type));
                    // Если вдруг правил больше, чем 1
                    // То надо бы поглубже глянуть, так сказать
                    // Всего у меня таких 4 случая
                    // Нужно сначала понять, какой из этих случаев рассматривается
                    if( tableCell.rules.size() > 1){
                        // То надо бы поглубже глянуть, так сказать
                        // Всего у меня таких 4 случая
                        // Нужно сначала понять, какой из этих случаев рассматривается

                        int uk = scaner.getUk();
                        SavePoint savePoint = scaner.getSavePoint();
                        int tmpType;
                        ArrayList<Character> tmpLex = new ArrayList<>();

                        // <B> int
                        // <B> double
                        if(up.typ == LLK._B  && (type == Scaner._INT || type == Scaner._DOUBLE)){
                            //int a,
                            //int a;
                            //int a=
                            //int a(        функция

                            tmpType = scaner.next(tmpLex);      // тут должен быть айдишник
                            tmpType = scaner.next(tmpLex);      // тут символ

                            // удаляем верхушку
                            //this.magazin.pop();

                            // Ситуация когда объявляем функцию
                            if ( tmpType == Scaner._PARENTHESIS_OPEN){
                                this.magazin.push(new OneSymbol(false, LLK._E, "E"));
                            }
                            // Ситуация когда объявляем переменную без инициализации
                            // Ситуация когда объявляем переменную с инициализацией
                            else{
                                this.magazin.push(new OneSymbol(false, LLK._C, "C"));
                            }
                        }
                        // <A6> id
                        if(up.typ == LLK._A6 && type == Scaner._ID) {
                            //a(        function
                            //a+-*/...  все символы, кроме скобки это идентификатор

                            tmpType = scaner.next(tmpLex);      // тут должен символ скобки или че другое

                            //Выкинем верхушку стека
                            //this.magazin.pop();

                            // Ситуация когда вызов функции
                            if( tmpType ==  Scaner._PARENTHESIS_OPEN ){
                                this.magazin.push(new OneSymbol(false, LLK._Q, "Q"));
                            }
                            // Ситуация когда идентификатор
                            else{
                                this.magazin.push(new OneSymbol(true, Scaner._ID, "idd"));
                            }
                        }
                        // <W7> else
                        if(up.typ == LLK._W7 && type == Scaner._ELSE){

                            //else...                   // это значит "<O> else"
                            //все другое, нежели else   // это значит "e"
                            //tmpType = scaner.next(tmpLex);

                            //Выкинем верхушку стека
                            //this.magazin.pop();
                            // Ситуация когда идет блок с ELSE
                            tmpType = type;
                            if( tmpType == Scaner._ELSE){
                                this.magazin.push(new OneSymbol(false, LLK._O, "O"));
                                this.magazin.push(new OneSymbol(true, Scaner._ELSE, "else"));
                            }
                            // Ситуация когда ИФ кончился
                            else{
                                this.magazin.push(new OneSymbol(false, LLK._epsilon, "_epsilon"));
                            }
                        }
                        // <O> id
                        if(up.typ == LLK._O && type == Scaner._ID){

                            // =                  // это значит <P>
                            // (   // это значит ";<Q>"
                            tmpType = scaner.next(tmpLex);

                            //Выкинем верхушку стека
                            //this.magazin.pop();
                            // Ситуация когда идет = т.е. присваивание
                            if( tmpType == Scaner._ASSIGN){
                                this.magazin.push(new OneSymbol(false, LLK._P, "P"));
                            }
                            // Ситуация когда ( т.е. вызов функции
                            else{
                                this.magazin.push(new OneSymbol(true, Scaner._SEMICOLON, ";"));
                                this.magazin.push(new OneSymbol(false, LLK._Q, "Q"));
                            }
                        }

                        scaner.setUk(uk);
                        scaner.setSavePoint(savePoint);
                    }
                    // Если правило одно
                    else{
                        // удаляем верхушку
                        //this.magazin.pop();
                        // Просто помещаем правило в стек
                        this.putRule(this.magazin, tableCell.rules.get(0));
                    }
                }
                else{
                    // в Ячейке пусто
                    System.out.print("");
                    this.scaner.printError("erorina", lex);
                    countError++;
                    throw new NullPointerException("\n=====================================================\n\t\t\t\tОшибка!\n=====================================================");
                }
            }
        }
        System.out.println("Проверка завершена без ошибок");
       // System.out.println("Проверка завершена, Количество ошибок = " + countError);
    }



}
