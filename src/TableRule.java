import javafx.util.Pair;

import java.util.HashMap;

public class TableRule {
	public TableRule() {
	}

	public static HashMap<Pair<Integer, Integer>, TableCell> initTable() {
		HashMap<Pair<Integer, Integer>, TableCell> table = new HashMap<>();
		table = new HashMap<>();
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

		table.put(new Pair<>(LLK._A, Scaner._INT), tmpCell);
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

		table.put(new Pair<>(LLK._A, Scaner._DOUBLE), tmpCell);
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

		table.put(new Pair<>(LLK._A, Scaner._CONST), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <A1> id
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R1, "R1"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A2, "A2"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A1, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <A1> (
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R1, "R1"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A2, "A2"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A1, Scaner._PARENTHESIS_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <A2> id
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R2, "R2"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A3, "A3"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A2, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <A2> (
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R2, "R2"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A3, "A3"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A2, Scaner._PARENTHESIS_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <A3> id
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R3, "R3"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A4, "A4"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A3, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <A3> (
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R3, "R3"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A4, "A4"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A3, Scaner._PARENTHESIS_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <A4> id
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R4, "R4"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A5, "A5"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A4, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <A4> (
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R4, "R4"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A5, "A5"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A4, Scaner._PARENTHESIS_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <A5> id
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R5, "R5"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A6, "A6"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A5, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <A5> (
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R5, "R5"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A6, "A6"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A5, Scaner._PARENTHESIS_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <B> int
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._C, "C"));
		tmpCell.rules.add(tmpRule);

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._E, "E"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._B, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <B> double
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._C, "C"));
		tmpCell.rules.add(tmpRule);

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._E, "E"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._B, Scaner._DOUBLE), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <C> int
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(true, Scaner._SEMICOLON, ";"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._G, "G"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._F, "F"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._C, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <C> double
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(true, Scaner._SEMICOLON, ";"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._G, "G"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._F, "F"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._C, Scaner._DOUBLE), tmpCell);
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

		table.put(new Pair<>(LLK._E, Scaner._INT), tmpCell);
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

		table.put(new Pair<>(LLK._E, Scaner._DOUBLE), tmpCell);
/////////////////////////////////////////////////////////////////////////////
		//// <W13> int
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._L, "L"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._K, "K"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W13, Scaner._INT), tmpCell);
/////////////////////////////////////////////////////////////////////////////
		//// <W13> double
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._L, "L"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._K, "K"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W13, Scaner._DOUBLE), tmpCell);
/////////////////////////////////////////////////////////////////////////////
		//// <W13> )
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._L, "L"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W13, Scaner._PARENTHESIS_CLOSE), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <F> int
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(true, Scaner._INT, "int"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._F, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <F> double
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(true, Scaner._DOUBLE, "double"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._F, Scaner._DOUBLE), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <K> int
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._W4, "W4"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._F, "F"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._K, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <K> double
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._W4, "W4"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._F, "F"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._K, Scaner._DOUBLE), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <M> int
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._M, "M"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._N, "N"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._M, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <M> double
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._M, "M"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._N, "N"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._M, Scaner._DOUBLE), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <N> int
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._C, "C"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._N, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <N> double
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._C, "C"));
		;
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._N, Scaner._DOUBLE), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <W7> int
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "epsilon,"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W7, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <W7> double
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "epsilon,"));
		;
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W7, Scaner._DOUBLE), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <B> const
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._D, "D"));
		;
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._B, Scaner._CONST), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <D> const
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(true, Scaner._SEMICOLON, ";"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._R, "R"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._F, "F"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._CONST, "const"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._D, Scaner._CONST), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <M> const
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._M, "M"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._N, "N"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._M, Scaner._CONST), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <N> const
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._D, "D"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._N, Scaner._CONST), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <w7> const
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W7, Scaner._CONST), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <A6> id
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
		tmpCell.rules.add(tmpRule);

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._Q, "Q"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A6, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <G> id
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._W2, "W2"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._H, "H"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._G, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <H> id
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._W3, "W3"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._H, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <M> id
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._M, "M"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._N, "N"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._M, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <N> id
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._O, "O"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._N, Scaner._ID), tmpCell);
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

		table.put(new Pair<>(LLK._O, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <P> id
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(true, Scaner._SEMICOLON, ";"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._ASSIGN, "="));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._P, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <Q> id
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._W8, "W8"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_OPEN, "("));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._Q, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R> id
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._W6, "W6"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._ASSIGN, "="));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <T> id
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._W9, "W9"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._T, Scaner._ID), tmpCell);

////////////////////////////////////////////////////////////////////////////
		////////////// <W7> id
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W7, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <W8> id
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._T, "T"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W8, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <L> {
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(true, Scaner._BRACE_CLOSE, "}"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._M, "M"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._BRACE_OPEN, "{"));

		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._L, Scaner._BRACE_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <M> {
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._M, "M"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._N, "N"));

		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._M, Scaner._BRACE_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <N> {
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._O, "O"));

		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._N, Scaner._BRACE_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <O> {
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._L, "L"));

		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._O, Scaner._BRACE_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <W7> {
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));

		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W7, Scaner._BRACE_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <M> }
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));

		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._M, Scaner._BRACE_CLOSE), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <A6> (
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_OPEN, "("));

		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A6, Scaner._PARENTHESIS_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <T> (
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._W9, "W9"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._T, Scaner._PARENTHESIS_OPEN), tmpCell);

////////////////////////////////////////////////////////////////////////////
		////////////// <W8> (
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._T, "T"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W8, Scaner._PARENTHESIS_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R1> )
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R1, Scaner._PARENTHESIS_CLOSE), tmpCell);
		////////////// <R2> )
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R2, Scaner._PARENTHESIS_CLOSE), tmpCell);
		////////////// <R3> )
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R3, Scaner._PARENTHESIS_CLOSE), tmpCell);
		////////////// <R4> )
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R4, Scaner._PARENTHESIS_CLOSE), tmpCell);
		////////////// <R5> )
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R5, Scaner._PARENTHESIS_CLOSE), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <W4> )
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W4, Scaner._PARENTHESIS_CLOSE), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <W8> )
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W8, Scaner._PARENTHESIS_CLOSE), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <W9> )
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W9, Scaner._PARENTHESIS_CLOSE), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <M> ;
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._M, "M"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._N, "N"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._M, Scaner._SEMICOLON), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <N> ;
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._O, "O"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._N, Scaner._SEMICOLON), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <O> ;
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(true, Scaner._SEMICOLON, ";"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._O, Scaner._SEMICOLON), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R1> ;
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R1, Scaner._SEMICOLON), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <W2> ;
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W2, Scaner._SEMICOLON), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <W6> ;
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W6, Scaner._SEMICOLON), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <W7> ;
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W7, Scaner._SEMICOLON), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R1> ,
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R1, Scaner._COMMA), tmpCell);
		////////////// <R2> ,
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R2, Scaner._COMMA), tmpCell);
		////////////// <R3> ,
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R3, Scaner._COMMA), tmpCell);
		////////////// <R4> ,
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R4, Scaner._COMMA), tmpCell);
		////////////// <R5> ,
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R5, Scaner._COMMA), tmpCell);

////////////////////////////////////////////////////////////////////////////
		////////////// <W2> ,
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._W2, "W2"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._G, "G"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._COMMA, ","));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W2, Scaner._COMMA), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <W3> ,
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W3, Scaner._COMMA), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <W3> ;
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W3, Scaner._SEMICOLON), tmpCell);
		//////////////////////////////////////////////////////////////////////////
		////////////// <W3> =
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._ASSIGN, "="));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W3, Scaner._ASSIGN), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <W4> ,
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._W4, "W4"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._F, "F"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._COMMA, ","));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W4, Scaner._COMMA), tmpCell);
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

		table.put(new Pair<>(LLK._W6, Scaner._COMMA), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <W9> ,
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._W9, "W9"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._COMMA, ","));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W9, Scaner._COMMA), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R5> *
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R5, "R5"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A6, "A6"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._STAR, "*"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R5, Scaner._STAR), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R5> /
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R5, "R5"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A6, "A6"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._SLASH, "/"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R5, Scaner._SLASH), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R5> %
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R5, "R5"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A6, "A6"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._PERCENT, "%"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R5, Scaner._PERCENT), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R5> +
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R5, Scaner._PLUS), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R5> -
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R5, Scaner._MINUS), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R4> +
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R4, "R4"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A5, "A5"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._PLUS, "+"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R4, Scaner._PLUS), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R4> -
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R4, "R4"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A5, "A5"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._MINUS, "-"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R4, Scaner._MINUS), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R4> >>
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R4, Scaner._SHIFT_RIGHT), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R4> <<
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R4, Scaner._SHIFT_LEFT), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R3> >>
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R3, "R3"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A4, "A4"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._SHIFT_RIGHT, ">>"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R3, Scaner._SHIFT_RIGHT), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R3> <<
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R3, "R3"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A4, "A4"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._SHIFT_LEFT, "<<"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R3, Scaner._SHIFT_LEFT), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R3> >
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R3, Scaner._GREAT), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R3> >=
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R3, Scaner._GREAT_EQUALLY), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R3> <
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R3, Scaner._LESS), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R3> <=
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R3, Scaner._LESS_EQUALLY), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R2> >
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R2, "R2"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A3, "A3"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._GREAT, ">"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R2, Scaner._GREAT), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R2> >=
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R2, "R2"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A3, "A3"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._GREAT_EQUALLY, ">="));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R2, Scaner._GREAT_EQUALLY), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R2> <
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R2, "R2"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A3, "A3"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._LESS, "<"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R2, Scaner._LESS), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R2> <=
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R2, "R2"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A3, "A3"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._LESS_EQUALLY, "<="));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R2, Scaner._LESS_EQUALLY), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R2> ==
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R2, Scaner._EQUALLY), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R2> !=
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R2, Scaner._NOT_EQUALLY), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R1> ==
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R1, "R1"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A2, "A2"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._EQUALLY, "<="));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R1, Scaner._EQUALLY), tmpCell);
////////////////////////////////////////////////////////////////////////////
		////////////// <R1> !=
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R1, "R1"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A2, "A2"));
		tmpRule.symbols.add(new OneSymbol(true, Scaner._NOT_EQUALLY, "<="));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R1, Scaner._NOT_EQUALLY), tmpCell);
//======================================================================================================================
//////////////////////////////////////////////////////////////////////////
		////////////// <A1> const8
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R1, "R1"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A2, "A2"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A1, Scaner._TYPE_INT_8), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <A1> const10
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R1, "R1"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A2, "A2"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A1, Scaner._TYPE_INT_10), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <A1> const16
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R1, "R1"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A2, "A2"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A1, Scaner._TYPE_INT_16), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <A1> const S
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R1, "R1"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A2, "A2"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A1, Scaner._TYPE_CHAR), tmpCell);


//////////////////////////////////////////////////////////////////////////
		////////////// <A2> const8
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R2, "R2"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A3, "A3"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A2, Scaner._TYPE_INT_8), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <A2> const10
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R2, "R2"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A3, "A3"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A2, Scaner._TYPE_INT_10), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <A2> const16
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R2, "R2"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A3, "A3"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A2, Scaner._TYPE_INT_16), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <A2> const S
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R2, "R2"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A3, "A3"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A2, Scaner._TYPE_CHAR), tmpCell);


//////////////////////////////////////////////////////////////////////////
		////////////// <A3> const8
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R3, "R3"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A4, "A4"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A3, Scaner._TYPE_INT_8), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <A3> const10
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R3, "R3"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A4, "A4"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A3, Scaner._TYPE_INT_10), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <A3> const16
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R3, "R3"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A4, "A4"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A3, Scaner._TYPE_INT_16), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <A3> const S
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R3, "R3"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A4, "A4"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A3, Scaner._TYPE_CHAR), tmpCell);


		//////////////////////////////////////////////////////////////////////////
		////////////// <A4> const8
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R4, "R4"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A5, "A5"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A4, Scaner._TYPE_INT_8), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <A4> const10
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R4, "R4"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A5, "A5"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A4, Scaner._TYPE_INT_10), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <A4> const16
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R4, "R4"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A5, "A5"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A4, Scaner._TYPE_INT_16), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <A4> const S
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R4, "R4"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A5, "A5"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A4, Scaner._TYPE_CHAR), tmpCell);


		//////////////////////////////////////////////////////////////////////////
		////////////// <A5> const8
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R5, "R5"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A6, "A6"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A5, Scaner._TYPE_INT_8), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <A5> const10
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R5, "R5"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A6, "A6"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A5, Scaner._TYPE_INT_10), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <A5> const16
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R5, "R5"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A6, "A6"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A5, Scaner._TYPE_INT_16), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <A5> const S
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._R5, "R5"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A6, "A6"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A5, Scaner._TYPE_CHAR), tmpCell);


		//////////////////////////////////////////////////////////////////////////
		////////////// <A6> const8
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._S, "S"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A6, Scaner._TYPE_INT_8), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <A6> const10
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._S, "S"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A6, Scaner._TYPE_INT_10), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <A6> const16
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._S, "S"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A6, Scaner._TYPE_INT_16), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <A6> const S
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._S, "S"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A6, Scaner._TYPE_CHAR), tmpCell);


		//////////////////////////////////////////////////////////////////////////
		////////////// <S> const8
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(true, Scaner._TYPE_INT_8, "_TYPE_INT_8"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._S, Scaner._TYPE_INT_8), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <S> const10
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(true, Scaner._TYPE_INT_10, "_TYPE_INT_10"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._S, Scaner._TYPE_INT_10), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <S> const16
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(true, Scaner._TYPE_INT_16, "_TYPE_INT_16"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._S, Scaner._TYPE_INT_16), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <S> const S
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(true, Scaner._TYPE_CHAR, "_TYPE_CHAR"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._S, Scaner._TYPE_CHAR), tmpCell);


		//////////////////////////////////////////////////////////////////////////
		////////////// <T> const8
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._W9, "W9"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._T, Scaner._TYPE_INT_8), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <T> const10
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._W9, "W9"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._T, Scaner._TYPE_INT_10), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <T> const16
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._W9, "W9"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._T, Scaner._TYPE_INT_16), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <T> const S
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._W9, "W9"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._T, Scaner._TYPE_CHAR), tmpCell);


		//////////////////////////////////////////////////////////////////////////
		////////////// <W8> const8
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._T, "T"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W8, Scaner._TYPE_INT_8), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <W8> const10
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._T, "T"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W8, Scaner._TYPE_INT_10), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <W8> const16
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._T, "T"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W8, Scaner._TYPE_INT_16), tmpCell);
//////////////////////////////////////////////////////////////////////////
		////////////// <W8> const S
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._T, "T"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W8, Scaner._TYPE_CHAR), tmpCell);


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

		table.put(new Pair<>(LLK._if, Scaner._IF), tmpCell);
		//////////////////////////////////////////////////////////////////////////
		////////////// <M> if
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._M, "M"));
		tmpRule.symbols.add(new OneSymbol(false, LLK._N, "N"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._M, Scaner._IF), tmpCell);
		//////////////////////////////////////////////////////////////////////////
		////////////// <N> if
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._O, "O"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._N, Scaner._IF), tmpCell);
		//////////////////////////////////////////////////////////////////////////
		////////////// <O> if
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._if, "<if>"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._O, Scaner._IF), tmpCell);
		//////////////////////////////////////////////////////////////////////////
		////////////// <W7> if
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._W7, Scaner._IF), tmpCell);
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

		table.put(new Pair<>(LLK._W7, Scaner._ELSE), tmpCell);
		//////////////////////////////////////////////////////////////////////////
		////////////// <A> #
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A, Scaner._END), tmpCell);
//======================================================================================================================
		////////////// <R2> ;
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R2, Scaner._SEMICOLON), tmpCell);
		////////////// <R3> ;
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R3, Scaner._SEMICOLON), tmpCell);
		////////////// <R4> ;
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R4, Scaner._SEMICOLON), tmpCell);
		////////////// <R5> ;
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R5, Scaner._SEMICOLON), tmpCell);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//  <RA> #
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._A, Scaner._END), tmpCell);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// ДЭБИЛЬНЫЙ блок с ешками
		tmpCell = new TableCell();

		tmpRule = new Rule();
		tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
		tmpCell.rules.add(tmpRule);

		table.put(new Pair<>(LLK._R5, Scaner._SHIFT_RIGHT), tmpCell);
		table.put(new Pair<>(LLK._R5, Scaner._SHIFT_LEFT), tmpCell);
		table.put(new Pair<>(LLK._R5, Scaner._GREAT), tmpCell);
		table.put(new Pair<>(LLK._R5, Scaner._GREAT_EQUALLY), tmpCell);
		table.put(new Pair<>(LLK._R5, Scaner._LESS), tmpCell);
		table.put(new Pair<>(LLK._R5, Scaner._LESS_EQUALLY), tmpCell);
		table.put(new Pair<>(LLK._R5, Scaner._EQUALLY), tmpCell);
		table.put(new Pair<>(LLK._R5, Scaner._NOT_EQUALLY), tmpCell);

		table.put(new Pair<>(LLK._R4, Scaner._GREAT), tmpCell);
		table.put(new Pair<>(LLK._R4, Scaner._GREAT_EQUALLY), tmpCell);
		table.put(new Pair<>(LLK._R4, Scaner._LESS), tmpCell);
		table.put(new Pair<>(LLK._R4, Scaner._LESS_EQUALLY), tmpCell);
		table.put(new Pair<>(LLK._R4, Scaner._EQUALLY), tmpCell);
		table.put(new Pair<>(LLK._R4, Scaner._NOT_EQUALLY), tmpCell);

		table.put(new Pair<>(LLK._R3, Scaner._EQUALLY), tmpCell);
		table.put(new Pair<>(LLK._R3, Scaner._NOT_EQUALLY), tmpCell);



		return table;
	}

}
