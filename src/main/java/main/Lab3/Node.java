package main.Lab3;

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
    String lexem;
    int id;

    public Node() {
        this.id = Node.count++;
    }
}
