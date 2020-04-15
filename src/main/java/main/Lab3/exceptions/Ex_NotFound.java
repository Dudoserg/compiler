package main.Lab3.exceptions;

import main.Lab3.LLK;
import main.SavePoint;

public class Ex_NotFound extends Exception {
    public SavePoint savePoint;
    public Ex_NotFound(String lexem) {
        super("Неизвестный идентификатор !" + "    " + "find не нашел " + lexem);
        savePoint = LLK.savePointCurrent;
    }
}
