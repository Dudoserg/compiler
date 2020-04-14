package main.Lab3;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;


@NoArgsConstructor
public class Semantic {
    private boolean flag_Decl;
    private LexTypeTERMINAL dataType;

    private Node root = new Node();
    private Node current = root;

    public void startDecl(LexTypeTERMINAL dataType) throws Exception {
        if(dataType != LexTypeTERMINAL._INT && dataType != LexTypeTERMINAL._DOUBLE)
            throw new Exception("dataType != LexTypeTERMINAL._INT || dataType != LexTypeTERMINAL._DOUBLE");
        this.dataType = dataType;
        this.flag_Decl = true;
    }
    public void endDecl(){
        this.flag_Decl = false;
    }

    public void setIdent(String lexem) throws Exception {
        // Проверить дублирование

        // занести идентификатор в таблицу с типом dataType;
        Node node = new Node();
        switch (dataType){
            case _INT:{
                node.nodeType = Node.NodeType.TYPE_INTEGER;
                node.lexem = lexem;
                break;
            }
            case _DOUBLE:{
                node.nodeType = Node.NodeType.TYPE_DOUBLE;
                node.lexem = lexem;
                break;
            }
        }
        if( checkDublicate(node)){
            // нашли дубликат, ошибка
            throw new Exception("Нашли дубликат: " + node.lexem);
        }
        current.left = node;
        node.parent = current;
        current = node;
    }


    private boolean checkDublicate(Node node){
        Node checking = current;
        do{
            if( checking.nodeType == node.nodeType && checking.lexem.equals(node.lexem))
                return true;
            if( checking.nodeType == Node.NodeType.TYPE_BLACK)            // Дошли до черной вершины символизирующей новый уровень
                return false;
            if( checking.parent == null)            // Дошли до корня
                return false;
            if(checking.parent.left == null)        // текущий элемент это начало уровня
                return false;
            if(checking.parent.right == checking)        // текущий элемент это начало уровня
                return false;
            if(checking.parent.left != checking)    // -__-
                return false;
            checking = checking.parent;
        }while (true);
    }

    public void startLevel() {
        Node node = new Node();
        node.nodeType = Node.NodeType.TYPE_BLACK;

        this.current.right = node;
        node.parent = this.current;
        this.current = node;
    }

    public void endLevel() {
        Node checking = this.current;

        do{
            // Нашли черную вершину
            if(checking.nodeType == Node.NodeType.TYPE_BLACK){
                // делаем текущей вершиной, родителя черной вершины
                this.current = checking.parent;
                break;
            }
            checking = checking.parent;
        }while (true);

    }
}
