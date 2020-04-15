package main.Lab3;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;
import main.Lab3.Node.NodeType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;


@NoArgsConstructor
public class Semantic {
    private boolean flag_Decl;
    private LexTypeTERMINAL dataType;

    private Node root = new Node();
    private LexTypeTERMINAL savedType;

    {
        root.nodeType = NodeType.TYPE_BLACK;
    }

    private Node current = root;
    private Node k;
    private Node find_last;
    private Node savedVariable;

    private Stack<NodeType> stackType = new Stack<>();

    public void startDecl(LexTypeTERMINAL dataType) throws Exception {
        if (dataType != LexTypeTERMINAL._INT && dataType != LexTypeTERMINAL._DOUBLE)
            throw new Exception("dataType != LexTypeTERMINAL._INT || dataType != LexTypeTERMINAL._DOUBLE");
        this.dataType = dataType;
        this.flag_Decl = true;
    }


    public void endDecl() {
        this.flag_Decl = false;
    }


    public void setIdent(String lexem) throws Exception {
        // Проверить дублирование

        // занести идентификатор в таблицу с типом dataType;
        Node node = new Node();
        switch (dataType) {
            case _INT: {
                node.nodeType = NodeType.TYPE_INTEGER;
                node.lexem = lexem;
                break;
            }
            case _DOUBLE: {
                node.nodeType = NodeType.TYPE_DOUBLE;
                node.lexem = lexem;
                break;
            }
        }
        if (checkDublicate(node)) {
            // нашли дубликат, ошибка
            throw new Exception("Нашли дубликат: " + node.lexem);
        }
        current.left = node;
        node.parent = current;
        current = node;
    }

    private boolean checkDublicateFunc(Node node) throws Exception {
        if (node.nodeType != NodeType.TYPE_FUNC)
            throw new Exception("checkDublicateFunc, вы проверяете не функцию!");
        Node checking = current.parent;
        do {
            if (checking.nodeType == node.nodeType && checking.lexem.equals(node.lexem))
                return true;
            if (checking.parent == null)            // Дошли до корня
                return false;
            checking = checking.parent;
        } while (checking.parent != null);
        return false;
    }

    private boolean checkDublicate(Node node) {
        Node checking = current;
        do {
            if (checking.nodeType == node.nodeType && checking.lexem.equals(node.lexem))
                return true;
            if ((checking.nodeType == NodeType.TYPE_INTEGER || checking.nodeType == NodeType.TYPE_DOUBLE) &&
                    (node.nodeType == NodeType.TYPE_INTEGER || node.nodeType == NodeType.TYPE_DOUBLE)
                    && checking.lexem.equals(node.lexem))
                return true;
            if (checking.nodeType == NodeType.TYPE_BLACK &&
                    checking.parent != null &&
                    checking.parent.right == checking &&
                    checking.parent.nodeType == NodeType.TYPE_BLACK)            // Дошли до черной вершины символизирующей новый уровень
                return false;
            if (checking.parent == null)            // Дошли до корня
                return false;
            if (checking.parent.left == null)        // текущий элемент это начало уровня
                return false;
            if (checking.parent.right == checking)        // текущий элемент это начало уровня
                return false;
            if (checking.parent.left != checking)    // -__-
                return false;
            checking = checking.parent;
        } while (true);
    }


    public void startLevel() {

        {
            Node node = new Node();
            node.nodeType = NodeType.TYPE_BLACK;

            this.current.left = node;
            node.parent = this.current;
            this.current = node;
        }

        {
            Node node = new Node();
            node.nodeType = NodeType.TYPE_BLACK;

            this.current.right = node;
            node.parent = this.current;
            this.current = node;
        }

    }


    public void endLevel() {

        Node checking = this.current;

        do {
            //TODO и ели функция
            // Нашли черную вершину
            if (checking.nodeType == NodeType.TYPE_BLACK &&
                    checking.parent.right == checking &&
                    checking.parent.nodeType == NodeType.TYPE_BLACK
            ) {

                // делаем текущей вершиной, родителя черной вершины
                this.current = checking.parent;
                break;
            }

            checking = checking.parent;
        } while (true);

    }

    public void startFunc(String lexem) throws Exception {

        Node node = new Node();
        node.lexem = lexem;
        node.nodeType = NodeType.TYPE_FUNC;
        node.returnType = this.dataType;
        this.k = node;

        addToCurrentTo_Left(node);

        //TODO проверить на дублирование айдишника
        if (checkDublicateFunc(node)) {
            throw new Exception("дублирование функции " + node.lexem);
        }
//        {
//            Node black = new Node();
//            black.lexem = lexem;
//            black.nodeType = Node.NodeType.TYPE_BLACK;
//            addToCurrentTo_Left(black);
//        }

    }


    public void newBlack() {
        Node node = new Node(); // разделяем параметры функции и локальные переменные
        node.nodeType = NodeType.TYPE_BLACK;
        addToCurrentTo_Left(node);
    }

    private void addToCurrentTo_Left(Node node) {
        this.current.left = node;
        node.parent = this.current;
        this.current = node;
    }

    private void addToCurrentTo_Right(Node node) {
        this.current.right = node;
        node.parent = this.current;
        this.current = node;
    }

    // увеличиваем число параметров у функции (вершина с указателем «k»)
    public void plusParam() {
        this.k.countParams++;
    }

    public void saveType(LexTypeTERMINAL lexTypeTERMINAL) {
        this.savedType = lexTypeTERMINAL;
    }


    public void createGraphViz() {
        try (FileWriter writer = new FileWriter("result.gv", false)) {
            // запись всей строки
            String text = "Hello Gold!";
            writer.write("graph binary {\n" +
                    "\tdpi=\"400\";\n"
            );

            reucrsion(this.root, writer);


            writer.write("\n}\n");
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void reucrsion(Node node, FileWriter writer) throws IOException {
        Node left = node.left;
        Node right = node.right;

        if (node.nodeType == NodeType.TYPE_BLACK) {
            writer.write("v" + node.id + "[style=filled, fillcolor=grey]" + "\n");
            writer.write("v" + node.id + "[label=\"" + " #" + node.id + "\"]" + "\n");
        } else if (node.nodeType == NodeType.TYPE_FUNC) {
            String type = node.returnType == LexTypeTERMINAL._INT ? "int" : "double";
            writer.write("v" + node.id + "[style=filled, fillcolor=red]" + "\n");
            writer.write("v" + node.id + "[label=\"" + node.lexem + "(" + node.countParams + ")" + " #" + node.id + "\"]" + "\n");
            writer.write("v" + node.id + "[xlabel=\"" + type + " " + "\"]" + "\n");
        } else if (node.nodeType == NodeType.TYPE_INTEGER || node.nodeType == NodeType.TYPE_DOUBLE) {
            String type = node.nodeType == NodeType.TYPE_INTEGER ? "int" : "double";
            writer.write("v" + node.id + "[label=\"" + node.lexem + " #" + node.id + "\"]" + "\n");
            writer.write("v" + node.id + "[xlabel=\"" + type + "\"]" + "\n");
        }

        writer.write("v" + node.id + "center" + "[style=invis, width=0, label=\"\"];" + "\n");
        writer.write("v" + node.id + " -- " + "v" + node.id + "center" + "" + "\n");


        List<String> rankSame = new ArrayList<>();

        if (left != null) {
            writer.write("v" + node.id + " -- " + "v" + left.id + "\n");
            rankSame.add("v" + left.id);
        } else {
            writer.write("v" + node.id + "notVisibleL" + "[style=invis]" + "\n");
            writer.write("v" + node.id + " -- " + "v" + node.id + "notVisibleL" + "" + "\n");
            rankSame.add("v" + node.id + "notVisibleL");
        }

        rankSame.add("v" + node.id + "center");


        if (right != null) {
            writer.write("v" + node.id + " -- " + "v" + right.id + "\n");
            rankSame.add("v" + right.id);
        } else {
            writer.write("v" + node.id + "notVisibleR" + "[style=invis]" + "\n");
            writer.write("v" + node.id + " -- " + "v" + node.id + "notVisibleR" + "" + "\n");
            rankSame.add("v" + node.id + "notVisibleR");
        }
        writer.write("{\n\trank=same " +
                rankSame.stream().collect(Collectors.joining(" -- ")) +
                "  [style=invis]\n} \n");

        if (left != null)
            reucrsion(left, writer);
        if (right != null)
            reucrsion(right, writer);

    }


    public void drawTree() throws IOException {
        if (System.getProperty("os.name").equals("Linux")) {
            Runtime.getRuntime().exec("dot result.gv -Tpng -o result.jpg");

        } else {
            Runtime.getRuntime().exec("cmd /c dot result.gv -Tpng -o result.jpg");
        }
    }


    public void push_t(LexTypeTERMINAL next) {
        if (next == LexTypeTERMINAL._INT || next == LexTypeTERMINAL._TYPE_INT_8 ||
                next == LexTypeTERMINAL._TYPE_INT_10 || next == LexTypeTERMINAL._TYPE_INT_16)
            this.stackType.push(NodeType.TYPE_INTEGER);
        if (next == LexTypeTERMINAL._DOUBLE)
            this.stackType.push(NodeType.TYPE_DOUBLE);
        if (next == LexTypeTERMINAL._TYPE_CHAR)
            this.stackType.push(NodeType.TYPE_CHAR);
    }

    public Node find(String lexemToStr) throws Exception {
        Node node = this.current;
        do {
            if (node.lexem.equals(lexemToStr)) {
                find_last = node;
                return node;
            }
            node = node.parent;
            if (node == null) {
                find_last = node;
                throw new Exception("find не нашел " + lexemToStr);
            }

        } while (true);
    }

    public void saveVariable() {
        this.savedVariable = find_last;
    }

    public void matchLeft() throws Exception {
        if (this.stackType.size() == 0)
            throw new Exception("stack size = 0");
        NodeType rightType = this.stackType.pop();
        Node left = this.savedVariable;

        switch (left.nodeType){
            case TYPE_INTEGER:{
                switch (rightType){
                    // int = int
                    case TYPE_INTEGER:{
                        break;
                    }
                    // int = double
                    case TYPE_DOUBLE:{
                        break;
                    }
                    default:{
                        throw new Exception("matchLeft default error");
                    }
                }
                break;
            }
            case TYPE_DOUBLE:{
                switch (rightType){
                    // double = int
                    case TYPE_INTEGER:{
                        break;
                    }
                    // double = double
                    case TYPE_DOUBLE:{
                        break;
                    }
                    default:{
                        throw new Exception("matchLeft default error");
                    }
                }
                break;
            }
            default:{
                throw new Exception("matchLeft default error");
            }
        }

    }

    public void match() {
        NodeType rightType = this.stackType.pop();
        NodeType leftType = this.stackType.pop();
        System.out.println();
    }
}
