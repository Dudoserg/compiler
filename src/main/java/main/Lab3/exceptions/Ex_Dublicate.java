package main.Lab3.exceptions;

import main.Lab3.LLK;
import main.SavePoint;

public class Ex_Dublicate extends Exception {
    public SavePoint savePoint;
    public Ex_Dublicate(String lexem) {
        super("Нашли дубликат: " + lexem);
        savePoint = LLK.savePointCurrent;
    }
}
