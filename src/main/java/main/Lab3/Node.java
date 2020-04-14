package main.Lab3;

public class Node {
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
    String lexem;
}
