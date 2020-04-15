package main.Lab3;

import main.Lab2.LexTypeTERMINAL;

public class Node {
    static int count = 0;

    enum NodeType{
        TYPE_FUNC ,
        TYPE_DOUBLE,
        TYPE_INTEGER,
        TYPE_UNKNOWN,
        TYPE_BLACK,
        TYPE_CHAR,
        TYPE_BOOLEAN;
    }
    
    public Node left;
    public Node right;
    public Node parent;

    NodeType nodeType;
    LexTypeTERMINAL returnType = null;  // Если этот узел функция, то тут тип возвращаемого результата
    int countParams = 0;                // Если этот узел функция, то тут количество параметров функции

    String lexem;
    int id;

    public Node() {
        this.id = Node.count++;
    }
}
