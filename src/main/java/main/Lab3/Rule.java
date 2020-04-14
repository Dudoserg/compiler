package main.Lab3;

import java.util.ArrayList;

public class Rule {

    /**
     * Одно правило из ячейки (состоит из нескольких терминалов\нетерминалов)
     */
    ArrayList<OneSymbol> symbols;

    public Rule() {
        this.symbols = new ArrayList<>();
    }
}
