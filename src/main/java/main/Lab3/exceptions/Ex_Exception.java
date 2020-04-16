package main.Lab3.exceptions;

import main.Lab3.LLK;
import main.SavePoint;

public class Ex_Exception extends Exception {
    public SavePoint savePoint;
    public Ex_Exception(String message) {
        super(message);
        savePoint = LLK.savePointCurrent;
    }
}
