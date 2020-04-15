package main.Lab3;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
public class Semantic {
    private boolean flag_Decl;
    private LexTypeTERMINAL dataType;

    private Node root = new Node();

    {
        root.nodeType = Node.NodeType.TYPE_BLACK;
    }

    private Node current = root;


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
                node.nodeType = Node.NodeType.TYPE_INTEGER;
                node.lexem = lexem;
                break;
            }
            case _DOUBLE: {
                node.nodeType = Node.NodeType.TYPE_DOUBLE;
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


    private boolean checkDublicate(Node node) {
        Node checking = current;
        do {
            if (checking.nodeType == node.nodeType && checking.lexem.equals(node.lexem))
                return true;
            if (checking.nodeType == Node.NodeType.TYPE_BLACK &&
                    checking.parent != null &&
                    checking.parent.right == checking &&
                    checking.parent.nodeType == Node.NodeType.TYPE_BLACK)            // Дошли до черной вершины символизирующей новый уровень
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
            node.nodeType = Node.NodeType.TYPE_BLACK;

            this.current.left = node;
            node.parent = this.current;
            this.current = node;
        }

        {
            Node node = new Node();
            node.nodeType = Node.NodeType.TYPE_BLACK;

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
            if (checking.nodeType == Node.NodeType.TYPE_BLACK &&
                    checking.parent.right == checking &&
                    checking.parent.nodeType == Node.NodeType.TYPE_BLACK
            ) {

                // делаем текущей вершиной, родителя черной вершины
                this.current = checking.parent;
                break;
            }

            checking = checking.parent;
        } while (true);

    }

    public void startFunc(String lexem) {
        {
            Node node = new Node();
            node.lexem = lexem;
            node.nodeType = Node.NodeType.TYPE_FUNC;

            addToCurrentTo_Left(node);
        }
        //TODO проверить на дублирование айдишника

//        {
//            Node black = new Node();
//            black.lexem = lexem;
//            black.nodeType = Node.NodeType.TYPE_BLACK;
//            addToCurrentTo_Left(black);
//        }

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

        if (node.nodeType == Node.NodeType.TYPE_BLACK) {
            writer.write("v" + node.id + "[style=filled, fillcolor=grey]" + "\n");
            writer.write("v" + node.id + "[label=\"" + "[" + node.id + "]" + "\"]" + "\n");
        } else if (node.nodeType == Node.NodeType.TYPE_FUNC) {
            writer.write("v" + node.id + "[style=filled, fillcolor=red]" + "\n");
            writer.write("v" + node.id + "[label=\"" + node.lexem + "[" + node.id + "]" + "\"]" + "\n");
            writer.write("v" + node.id + "[xlabel=\"" + "func" + "\"]" + "\n");
        } else if (node.nodeType == Node.NodeType.TYPE_INTEGER || node.nodeType == Node.NodeType.TYPE_DOUBLE) {
            String type = node.nodeType == Node.NodeType.TYPE_INTEGER ? "int" : "double";
            writer.write("v" + node.id + "[label=\"" + node.lexem + "[" + node.id + "]" + "\"]" + "\n");
            writer.write("v" + node.id + "[xlabel=\"" + type + "\"]" + "\n");
        }

        writer.write("v" + node.id + "center" + "[style=invis, width=0, label=\"\"];" + "\n");
        writer.write("v" + node.id + " -- " + "v" + node.id + "center" + "[style=invis]" + "\n");


        List<String> rankSame = new ArrayList<>();

        if (left != null) {
            writer.write("v" + node.id + " -- " + "v" + left.id + "\n");
            rankSame.add("v" + left.id);
        } else {
            writer.write("v" + node.id + "notVisibleL" + "[style=invis]" + "\n");
            writer.write("v" + node.id + " -- " + "v" + node.id + "notVisibleL" + "[style=invis]" + "\n");
            rankSame.add("v" + node.id + "notVisibleL");
        }

        rankSame.add("v" + node.id + "center");


        if (right != null) {
            writer.write("v" + node.id + " -- " + "v" + right.id + "\n");
            rankSame.add("v" + right.id);
        } else {
            writer.write("v" + node.id + "notVisibleR" + "[style=invis]" + "\n");
            writer.write("v" + node.id + " -- " + "v" + node.id + "notVisibleR" + "[style=invis]" + "\n");
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
}
