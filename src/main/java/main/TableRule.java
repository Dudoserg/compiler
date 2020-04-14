package main;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.util.Pair;
import main.createLLK.ReadLLK;

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
        ////////////// <программа> int
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ПРОГРАММА, "ПРОГРАММА"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОДНО_ОПИСАНИЕ, "ОДНО_ОПИСАНИЕ"));
        tmpCell.rules.add(tmpRule);

//        tmpRule = new Rule();
//        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "ФУНКЦИЯ"));
//        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ПРОГРАММА, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <программа> double
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ПРОГРАММА, "ПРОГРАММА"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОДНО_ОПИСАНИЕ, "ОДНО_ОПИСАНИЕ"));
        tmpCell.rules.add(tmpRule);

//        tmpRule = new Rule();
//        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "ФУНКЦИЯ"));
//        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ПРОГРАММА, Scaner._DOUBLE), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <программа> const
        tmpCell = new TableCell();


        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ПРОГРАММА, "ПРОГРАММА"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОДНО_ОПИСАНИЕ, "ОДНО_ОПИСАНИЕ"));
        tmpCell.rules.add(tmpRule);
//        tmpRule = new Rule();
//        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "ФУНКЦИЯ"));
//        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ПРОГРАММА, Scaner._CONST), tmpCell);
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
        ////////////// <одно_описание> int
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОБЪЯВЛЕНИЕ_ПЕРЕМННЫХ, "ОБЪЯВЛЕНИЕ_ПЕРЕМННЫХ"));
        tmpCell.rules.add(tmpRule);

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ФУНКЦИЯ, "ФУНКЦИЯ"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ОДНО_ОПИСАНИЕ, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <одно_описание> double
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОБЪЯВЛЕНИЕ_ПЕРЕМННЫХ, "ОБЪЯВЛЕНИЕ_ПЕРЕМННЫХ"));
        tmpCell.rules.add(tmpRule);

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ФУНКЦИЯ, "ФУНКЦИЯ"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ОДНО_ОПИСАНИЕ, Scaner._DOUBLE), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <объявление_переменных> int
        tmpCell = new TableCell();

        tmpRule = new Rule();
        //tmpRule.symbols.add(new OneSymbol(true, Scaner._SEMICOLON, ";")); //изменил
        tmpRule.symbols.add(new OneSymbol(true, Scaner._SEMICOLON, ";"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._СПИСОК_ПЕРЕМННЫХ, "СПИСОК_ПЕРЕМННЫХКЦИЯ"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._ТИП, "ТИП"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ОБЪЯВЛЕНИЕ_ПЕРЕМННЫХ, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <объявление_переменных> double
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._SEMICOLON, ";"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._СПИСОК_ПЕРЕМННЫХ, "СПИСОК_ПЕРЕМННЫХКЦИЯ"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._ТИП, "ТИП"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ОБЪЯВЛЕНИЕ_ПЕРЕМННЫХ, Scaner._DOUBLE), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <функция> int
        tmpCell = new TableCell();

        tmpRule = new Rule();
//        tmpRule.symbols.add(new OneSymbol(false, LLK._L, "СОСТАВНОЙ_ОПЕРАТОР"));
//        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
//        tmpRule.symbols.add(new OneSymbol(false, LLK._K, "СПИСОК_ПАРАМЕТРОВ"));
//        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_OPEN, "("));
//        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
//        tmpRule.symbols.add(new OneSymbol(false, LLK._F, "ТИП"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._W13, "w13"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_OPEN, "("));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._ТИП, "ТИП"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ФУНКЦИЯ, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <функция> double
        tmpCell = new TableCell();

        tmpRule = new Rule();
//        tmpRule.symbols.add(new OneSymbol(false, LLK._L, "СОСТАВНОЙ_ОПЕРАТОР"));
//        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
//        tmpRule.symbols.add(new OneSymbol(false, LLK._K, "СПИСОК_ПАРАМЕТРОВ"));
//        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_OPEN, "("));
//        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
//        tmpRule.symbols.add(new OneSymbol(false, LLK._F, "ТИП"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._W13, "w13"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_OPEN, "("));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._ТИП, "ТИП"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ФУНКЦИЯ, Scaner._DOUBLE), tmpCell);
/////////////////////////////////////////////////////////////////////////////
        //// <W13> int
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._СОСТАВНОЙ_ОПЕРАТОР, "СОСТАВНОЙ_ОПЕРАТОР"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._СПИСОК_ПАРАМЕТРОВ, "СПИСОК_ПАРАМЕТРОВ"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._W13, Scaner._INT), tmpCell);
/////////////////////////////////////////////////////////////////////////////
        //// <W13> double
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._СОСТАВНОЙ_ОПЕРАТОР, "СОСТАВНОЙ_ОПЕРАТОР"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._СПИСОК_ПАРАМЕТРОВ, "СПИСОК_ПАРАМЕТРОВ"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._W13, Scaner._DOUBLE), tmpCell);
/////////////////////////////////////////////////////////////////////////////
        //// <W13> )
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._СОСТАВНОЙ_ОПЕРАТОР, "СОСТАВНОЙ_ОПЕРАТОР"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._W13, Scaner._PARENTHESIS_CLOSE), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <тип> int
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._INT, "int"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ТИП, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <тип> double
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._DOUBLE, "double"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ТИП, Scaner._DOUBLE), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <СПИСОК_ПАРАМЕТРОВ> int
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W4, "W4"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._ТИП, "ТИП"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._СПИСОК_ПАРАМЕТРОВ, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <СПИСОК_ПАРАМЕТРОВ> double
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W4, "W4"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._ТИП, "ТИП"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._СПИСОК_ПАРАМЕТРОВ, Scaner._DOUBLE), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <ОПЕРАТОРЫ_И_ОПИСАНИЯ> int
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОПЕРАТОРЫ_И_ОПИСАНИЯ, "ОПЕРАТОРЫ_И_ОПИСАНИЯ"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._1_ОПЕРАТОР, "1_ОПЕРАТОР"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ОПЕРАТОРЫ_И_ОПИСАНИЯ, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <ОПЕРАТОРЫ_И_ОПИСАНИЯ> double
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОПЕРАТОРЫ_И_ОПИСАНИЯ, "ОПЕРАТОРЫ_И_ОПИСАНИЯ"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._1_ОПЕРАТОР, "1_ОПЕРАТОР"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ОПЕРАТОРЫ_И_ОПИСАНИЯ, Scaner._DOUBLE), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <1_ОПЕРАТОР> int
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОБЪЯВЛЕНИЕ_ПЕРЕМННЫХ, "ОБЪЯВЛЕНИЕ_ПЕРЕМННЫХ"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._1_ОПЕРАТОР, Scaner._INT), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <1_ОПЕРАТОР> double
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОБЪЯВЛЕНИЕ_ПЕРЕМННЫХ, "ОБЪЯВЛЕНИЕ_ПЕРЕМННЫХ"));
        ;
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._1_ОПЕРАТОР, Scaner._DOUBLE), tmpCell);
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
        ////////////// <одно_описание> const
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОБЪЯВЛЕНИЕ_КОНСТАНТ, "ОБЪЯВЛЕНИЕ_КОНСТАНТ"));
        ;
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ОДНО_ОПИСАНИЕ, Scaner._CONST), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <объявление_констант> const
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._SEMICOLON, ";"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._СПИСОК_КОНСТАНТ, "СПИСОК_КОНСТАНТ"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._ТИП, "ТИП"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._CONST, "const"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ОБЪЯВЛЕНИЕ_КОНСТАНТ, Scaner._CONST), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <ОПЕРАТОРЫ_И_ОПИСАНИЯ> const
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОПЕРАТОРЫ_И_ОПИСАНИЯ, "ОПЕРАТОРЫ_И_ОПИСАНИЯ"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._1_ОПЕРАТОР, "1_ОПЕРАТОР"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ОПЕРАТОРЫ_И_ОПИСАНИЯ, Scaner._CONST), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <1_ОПЕРАТОР> const
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОБЪЯВЛЕНИЕ_КОНСТАНТ, "ОБЪЯВЛЕНИЕ_КОНСТАНТ"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._1_ОПЕРАТОР, Scaner._CONST), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <w7> const
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._W7, Scaner._CONST), tmpCell);

        ////////////////////////////////////////////////////////////////////////////
        ////////////// <w7> }
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._W7, Scaner._BRACE_CLOSE), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <A6> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
        tmpCell.rules.add(tmpRule);

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ВЫЗОВ_ФУНКЦИИ, "ВЫЗОВ_ФУНКЦИИ"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._A6, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <СПИСОК_ПЕРЕМННЫХ> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W2, "W2"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._ПЕРЕМЕННАЯ, "ПЕРЕМЕННАЯ"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._СПИСОК_ПЕРЕМННЫХ, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <ПЕРЕМЕННАЯ> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W3, "W3"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ПЕРЕМЕННАЯ, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <ОПЕРАТОРЫ_И_ОПИСАНИЯ> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОПЕРАТОРЫ_И_ОПИСАНИЯ, "ОПЕРАТОРЫ_И_ОПИСАНИЯ"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._1_ОПЕРАТОР, "1_ОПЕРАТОР"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ОПЕРАТОРЫ_И_ОПИСАНИЯ, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <1_ОПЕРАТОР> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОПЕРАТОР, "ОПЕРАТОР"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._1_ОПЕРАТОР, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <ОПЕРАТОР> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ПРИСВАИВАНИЕ, "ПРИСВАИВАНИЕ"));
        tmpCell.rules.add(tmpRule);

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._SEMICOLON, ";"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._ВЫЗОВ_ФУНКЦИИ, "ВЫЗОВ_ФУНКЦИИ"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ОПЕРАТОР, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <ПРИСВАИВАНИЕ> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._SEMICOLON, ";"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ASSIGN, "="));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ПРИСВАИВАНИЕ, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <ВЫЗОВ_ФУНКЦИИ> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W8, "W8"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_OPEN, "("));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ВЫЗОВ_ФУНКЦИИ, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <СПИСОК_КОНСТАНТ> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W6, "W6"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ASSIGN, "="));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ID, "id"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._СПИСОК_КОНСТАНТ, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <ПАРАМЕТРЫ> id
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W9, "W9"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ПАРАМЕТРЫ, Scaner._ID), tmpCell);

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
        tmpRule.symbols.add(new OneSymbol(false, LLK._ПАРАМЕТРЫ, "ПАРАМЕТРЫ"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._W8, Scaner._ID), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <СОСТАВНОЙ_ОПЕРАТОР> {
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._BRACE_CLOSE, "}"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОПЕРАТОРЫ_И_ОПИСАНИЯ, "ОПЕРАТОРЫ_И_ОПИСАНИЯ"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._BRACE_OPEN, "{"));

        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._СОСТАВНОЙ_ОПЕРАТОР, Scaner._BRACE_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <ОПЕРАТОРЫ_И_ОПИСАНИЯ> {
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОПЕРАТОРЫ_И_ОПИСАНИЯ, "ОПЕРАТОРЫ_И_ОПИСАНИЯ"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._1_ОПЕРАТОР, "1_ОПЕРАТОР"));

        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ОПЕРАТОРЫ_И_ОПИСАНИЯ, Scaner._BRACE_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <1_ОПЕРАТОР> {
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОПЕРАТОР, "ОПЕРАТОР"));

        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._1_ОПЕРАТОР, Scaner._BRACE_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <ОПЕРАТОР> {
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._СОСТАВНОЙ_ОПЕРАТОР, "СОСТАВНОЙ_ОПЕРАТОР"));

        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ОПЕРАТОР, Scaner._BRACE_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <W7> {
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));

        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._W7, Scaner._BRACE_OPEN), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <ОПЕРАТОРЫ_И_ОПИСАНИЯ> }
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));

        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ОПЕРАТОРЫ_И_ОПИСАНИЯ, Scaner._BRACE_CLOSE), tmpCell);
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
        ////////////// <ПАРАМЕТРЫ> (
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W9, "W9"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ПАРАМЕТРЫ, Scaner._PARENTHESIS_OPEN), tmpCell);

////////////////////////////////////////////////////////////////////////////
        ////////////// <W8> (
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._ПАРАМЕТРЫ, "ПАРАМЕТРЫ"));
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
        ////////////// <ОПЕРАТОРЫ_И_ОПИСАНИЯ> ;
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОПЕРАТОРЫ_И_ОПИСАНИЯ, "ОПЕРАТОРЫ_И_ОПИСАНИЯ"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._1_ОПЕРАТОР, "1_ОПЕРАТОР"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ОПЕРАТОРЫ_И_ОПИСАНИЯ, Scaner._SEMICOLON), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <1_ОПЕРАТОР> ;
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОПЕРАТОР, "ОПЕРАТОР"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._1_ОПЕРАТОР, Scaner._SEMICOLON), tmpCell);
////////////////////////////////////////////////////////////////////////////
        ////////////// <ОПЕРАТОР> ;
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._SEMICOLON, ";"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ОПЕРАТОР, Scaner._SEMICOLON), tmpCell);
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
        tmpRule.symbols.add(new OneSymbol(false, LLK._СПИСОК_ПЕРЕМННЫХ, "СПИСОК_ПЕРЕМННЫХКЦИЯ"));
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
        tmpRule.symbols.add(new OneSymbol(false, LLK._ТИП, "ТИП"));
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
        tmpRule.symbols.add(new OneSymbol(false, LLK._КОНСТАНТА, "S"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._A6, Scaner._TYPE_INT_8), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <A6> const10
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._КОНСТАНТА, "S"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._A6, Scaner._TYPE_INT_10), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <A6> const16
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._КОНСТАНТА, "S"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._A6, Scaner._TYPE_INT_16), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <A6> const S
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._КОНСТАНТА, "S"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._A6, Scaner._TYPE_CHAR), tmpCell);


        //////////////////////////////////////////////////////////////////////////
        ////////////// <КОНСТАНТА> const8
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._TYPE_INT_8, "_TYPE_INT_8"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._КОНСТАНТА, Scaner._TYPE_INT_8), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <КОНСТАНТА> const10
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._TYPE_INT_10, "_TYPE_INT_10"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._КОНСТАНТА, Scaner._TYPE_INT_10), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <КОНСТАНТА> const16
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._TYPE_INT_16, "_TYPE_INT_16"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._КОНСТАНТА, Scaner._TYPE_INT_16), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <КОНСТАНТА> const S
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._TYPE_CHAR, "_TYPE_CHAR"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._КОНСТАНТА, Scaner._TYPE_CHAR), tmpCell);


        //////////////////////////////////////////////////////////////////////////
        ////////////// <ПАРАМЕТРЫ> const8
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W9, "W9"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ПАРАМЕТРЫ, Scaner._TYPE_INT_8), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <ПАРАМЕТРЫ> const10
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W9, "W9"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ПАРАМЕТРЫ, Scaner._TYPE_INT_10), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <ПАРАМЕТРЫ> const16
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W9, "W9"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ПАРАМЕТРЫ, Scaner._TYPE_INT_16), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <ПАРАМЕТРЫ> const S
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W9, "W9"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ПАРАМЕТРЫ, Scaner._TYPE_CHAR), tmpCell);


        //////////////////////////////////////////////////////////////////////////
        ////////////// <W8> const8
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._ПАРАМЕТРЫ, "ПАРАМЕТРЫ"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._W8, Scaner._TYPE_INT_8), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <W8> const10
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._ПАРАМЕТРЫ, "ПАРАМЕТРЫ"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._W8, Scaner._TYPE_INT_10), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <W8> const16
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._ПАРАМЕТРЫ, "ПАРАМЕТРЫ"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._W8, Scaner._TYPE_INT_16), tmpCell);
//////////////////////////////////////////////////////////////////////////
        ////////////// <W8> const S
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._ПАРАМЕТРЫ, "ПАРАМЕТРЫ"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._W8, Scaner._TYPE_CHAR), tmpCell);


        //////////////////////////////////////////////////////////////////////////
        ////////////// <if> if
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._W7, "W7"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОПЕРАТОР, "ОПЕРАТОР"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_CLOSE, ")"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._A1, "A1"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._PARENTHESIS_OPEN, "("));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._IF, "if"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._if, Scaner._IF), tmpCell);
        //////////////////////////////////////////////////////////////////////////
        ////////////// <ОПЕРАТОРЫ_И_ОПИСАНИЯ> if
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОПЕРАТОРЫ_И_ОПИСАНИЯ, "ОПЕРАТОРЫ_И_ОПИСАНИЯ"));
        tmpRule.symbols.add(new OneSymbol(false, LLK._1_ОПЕРАТОР, "1_ОПЕРАТОР"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ОПЕРАТОРЫ_И_ОПИСАНИЯ, Scaner._IF), tmpCell);
        //////////////////////////////////////////////////////////////////////////
        ////////////// <1_ОПЕРАТОР> if
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОПЕРАТОР, "ОПЕРАТОР"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._1_ОПЕРАТОР, Scaner._IF), tmpCell);
        //////////////////////////////////////////////////////////////////////////
        ////////////// <ОПЕРАТОР> if
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._if, "<if>"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ОПЕРАТОР, Scaner._IF), tmpCell);
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
        tmpRule.symbols.add(new OneSymbol(false, LLK._ОПЕРАТОР, "ОПЕРАТОР"));
        tmpRule.symbols.add(new OneSymbol(true, Scaner._ELSE, "else"));
        tmpCell.rules.add(tmpRule);

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._W7, Scaner._ELSE), tmpCell);
        //////////////////////////////////////////////////////////////////////////
        ////////////// <программа> #
        tmpCell = new TableCell();

        tmpRule = new Rule();
        tmpRule.symbols.add(new OneSymbol(false, LLK._epsilon, "_epsilon"));
        tmpCell.rules.add(tmpRule);

        table.put(new Pair<>(LLK._ПРОГРАММА, Scaner._END), tmpCell);
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

        table.put(new Pair<>(LLK._ПРОГРАММА, Scaner._END), tmpCell);
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
    public static HashMap<Pair<Integer, Integer>, TableCell> initTable_fromFile() throws JsonProcessingException {
        ReadLLK readLLK = new ReadLLK();

        HashMap<Pair<Integer, Integer>, TableCell> table = new HashMap<>();
        table = new HashMap<>();
        TableCell tmpCell;
        Rule tmpRule;
////////////////////////////////////////////////////////////////////////////
        ////////////// <программа> int
        tmpCell = new TableCell();


        return table;
    }

}
