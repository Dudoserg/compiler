package main.Lab3;

import javafx.util.Pair;
import lombok.NoArgsConstructor;
import main.Lab2.LexTypeTERMINAL;
import main.Lab3.Node.NodeType;
import main.Lab3.exceptions.Ex_Dublicate;
import main.Lab3.exceptions.Ex_Dublicate_Func;
import main.Lab3.exceptions.Ex_NotFound;
import main.Lab3.exceptions.Ex_Signature;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;


public class Semantic {
    private boolean devMode;

    private boolean flag_Decl;
    public LexTypeTERMINAL dataType;

    private Node root = new Node();
    private LexTypeTERMINAL savedType;

    {
        root.nodeType = NodeType.TYPE_BLACK;
    }

    private Node current = root;
    private Node k;
    private Node find_last;
    private Node savedVariable;

    private Stack<Pair<NodeType, String>> stackType = new Stack<>();
    private int parameter_counting;

    public Semantic(boolean devMode) {
        this.devMode = devMode;
    }

    // Запомнить тип в глобальную переменную dataType, установить флаг описания данных
    public void startDecl(LexTypeTERMINAL dataType) throws Exception {
        if (dataType != LexTypeTERMINAL._INT && dataType != LexTypeTERMINAL._DOUBLE)
            throw new Exception("dataType != LexTypeTERMINAL._INT || dataType != LexTypeTERMINAL._DOUBLE");
        this.dataType = dataType;
        this.flag_Decl = true;
    }

    // сбросить флаг описания данных
    public void endDecl() {
        this.flag_Decl = false;
    }

    // заносим идентификатор в таблицу с типом dataType, проверяем дубликаты
    public void setIdent(String lexem) throws Exception {
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
        // Проверить дублирование
        if (checkDublicate(node)) {
            // нашли дубликат, ошибка
            throw new Ex_Dublicate(node.lexem);
        }
        // Запоминаем переменную
        this.savedVariable = node;

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

    // начать новый уровень в дереве.
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

    // вернуться к началу текущего уровня
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

    // создаем вершину – функцию (запоминаем тип возвращаемых данных), проверяем дубликаты.
    // Запоминаем указатель на функцию в переменную «k».
    // сбрасываем счетчик return-ов
    public void startFunc(String lexem) throws Exception {

        Node node = new Node();

        node.lexem = lexem;
        node.nodeType = NodeType.TYPE_FUNC;
        node.returnType = this.dataType;

        addToCurrentTo_Left(node);

        // проверяем дубликаты функции
        if (checkDublicateFunc(node)) {
            throw new Ex_Dublicate_Func(node.lexem);
        }
        // Запоминаем указатель на функцию в переменную «k».
        this.k = node;

//        {
//            Node black = new Node();
//            black.lexem = lexem;
//            black.nodeType = Node.NodeType.TYPE_BLACK;
//            addToCurrentTo_Left(black);
//        }

    }

    // новая черная вершина, необходима, чтобы отделить параметры функции от локальных переменных
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
                    "\tdpi=\"100\";\n"
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

    // Кладем в магазин тип текущей лексемы
    public void push_t(LexTypeTERMINAL next, String lexem) {
        if (next == LexTypeTERMINAL._INT || next == LexTypeTERMINAL._TYPE_INT_8 ||
                next == LexTypeTERMINAL._TYPE_INT_10 || next == LexTypeTERMINAL._TYPE_INT_16) {
            this.stackType.push(new Pair<>(NodeType.TYPE_INTEGER, lexem));
        }

        if (next == LexTypeTERMINAL._DOUBLE) {
            this.stackType.push(new Pair<>(NodeType.TYPE_DOUBLE, lexem));
        }

        if (next == LexTypeTERMINAL._TYPE_CHAR) {
            this.stackType.push(new Pair<>(NodeType.TYPE_CHAR, lexem));
        }

        // переменная, ищем ее тип
        if (next == LexTypeTERMINAL._ID) {
            this.stackType.push(new Pair<>(find_last.nodeType, lexem));
        }

    }

    int countFind = 0;

    //
    public Node find(String lexemToStr) throws Exception {
        countFind++;
        if (devMode)
            System.out.println("countFind = " + countFind);

//        Node node = this.current.parent;
        Node node;
        // Если сейчас объявление переменных, то начинаем искать с предка, чтобы избежать ситуации
        // когда int a = 2 + a ; будет нормой
        if (flag_Decl)
            node = this.current.parent;
        else
            node = this.current;

        do {
            if (node.lexem != null && node.lexem.equals(lexemToStr)) {
                find_last = node;
                return node;
            }
            node = node.parent;
            if (node == null) {
                find_last = node;
                throw new Ex_NotFound(lexemToStr);
            }

        } while (true);
    }

    public void saveVariable() {
        this.savedVariable = find_last;
    }

    public void matchLeft() throws Exception {
        if (this.stackType.size() == 0)
            throw new Exception("stack size = 0");
        Pair<NodeType, String> rightPair = this.stackType.pop();
        NodeType rightType = rightPair.getKey();
        String rightLexem = rightPair.getValue();
        Node left = this.savedVariable;

        switch (left.nodeType) {
            case TYPE_INTEGER: {
                switch (rightType) {
                    // int = int
                    case TYPE_INTEGER: {
                        break;
                    }
                    // int = double
                    case TYPE_DOUBLE: {
                        break;
                    }
                    // int = func()
                    case TYPE_FUNC: {
                        break;
                    }
                    default: {
                        throw new Exception("matchLeft default error");
                    }
                }
                break;
            }
            case TYPE_DOUBLE: {
                switch (rightType) {
                    // double = int
                    case TYPE_INTEGER: {
                        break;
                    }
                    // double = double
                    case TYPE_DOUBLE: {
                        break;
                    }
                    // double = func()
                    case TYPE_FUNC: {
                        break;
                    }
                    default: {
                        throw new Exception("matchLeft default error");
                    }
                }
                break;
            }
            default: {
                throw new Exception("matchLeft default error");
            }
        }

    }

    // TODO желательно бы еще сделать matchCompare, чтобы отдельно разбираться ситуации со сравнением
    public void match() {
        final Pair<NodeType, String> rightPair = this.stackType.pop();
        NodeType rightType = rightPair.getKey();
        String rightLexem = rightPair.getValue();

        final Pair<NodeType, String> leftPair = this.stackType.pop();
        String leftLExem = leftPair.getValue();
        NodeType leftType = leftPair.getKey();
        if (devMode)
            System.out.println("match: " + "(" + leftLExem + ")" + " and " + "(" + rightLexem + ")");
        // TODO ченить пихаем в тип)0
        this.stackType.push(new Pair<>(NodeType.TYPE_DOUBLE, "(" + leftLExem + ")" + " and " + "(" + rightLexem + ")"));
//        System.out.println();
    }

    Node node_callFunc;

    public void callFunc() {
        node_callFunc = this.find_last;
    }

    public void start_parameter_counting() {
        parameter_counting = 0;
    }

    public void plus_parameter_counting() {
        parameter_counting++;
    }

    public void end_parameter_counting() throws Exception {
        // TODO проверяем количество параметров у вызываемой функции
        if (this.node_callFunc.countParams != this.parameter_counting)
            throw new Ex_Signature(this.find_last.countParams, this.parameter_counting);
        System.out.print("");
    }


    public void checkDubl(String lexemToStr) throws Exception {
        Node node = null;
        try {
            node = this.find(lexemToStr);
        } catch (Exception e) {

        }
        if (node != null)
            throw new Ex_Dublicate(lexemToStr);

    }

    public Stack<Pair<NodeType, String>> getStackType() {
        return stackType;
    }

    public void setStackType(Stack<Pair<NodeType, String>> stackType) {
        this.stackType = stackType;
    }

    public Node getCurrent() {
        return current;
    }

    public Node getNode_callFunc() {
        return node_callFunc;
    }

    public Node getFind_last() {
        return find_last;
    }
}
