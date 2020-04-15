package main.Lab3.exceptions;

import main.Lab3.LLK;
import main.SavePoint;

public class Ex_Signature extends Exception {
    public SavePoint savePoint;
    public Ex_Signature(int want, int real) {
        super("Несовпадение сигнатуры вызываемой функции с фактическим числом параметров:\n" +
                "ожидалось: " + want + "\n" +
                "фактически: " + real + "\n");
        savePoint = LLK.savePointCurrent;
    }
}
