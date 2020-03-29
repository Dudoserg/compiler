package main;

import java.util.ArrayList;


/**
 * Ячейка таблицы
 */
public class TableCell {

    /**
     * Массив с правилами ( т.к. их может быть несколько)
     */
    ArrayList<Rule> rules;

    public TableCell() {
        this.rules = new ArrayList<>();
    }
}
