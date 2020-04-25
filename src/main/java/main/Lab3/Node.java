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
    public LexTypeTERMINAL getLexTypeTERMINAL() throws Exception {
        switch (this.nodeType){
            case TYPE_DOUBLE: return LexTypeTERMINAL._DOUBLE;
            case TYPE_INTEGER: return LexTypeTERMINAL._INT;
            default:{
                throw new Exception("getLexTypeTERMINAL error");
            }
        }
    }
    
    public Node left;
    public Node right;
    public Node parent;

    public NodeType nodeType;
    public LexTypeTERMINAL returnType = null;  // Если этот узел функция, то тут тип возвращаемого результата
    public int countParams = 0;                // Если этот узел функция, то тут количество параметров функции

    public String lexem;
    public int id;

    public Node() {
        this.id = Node.count++;
    }
}
