package main.Lab3.exceptions;

import main.Lab3.LLK;
import main.SavePoint;

public class Ex_Dublicate_Func extends Exception {
    public SavePoint savePoint;
    public Ex_Dublicate_Func(String lexem) {
        super("Повторное объявление идентификатора(функции): " + lexem);
        savePoint = LLK.savePointCurrent;
    }
}
