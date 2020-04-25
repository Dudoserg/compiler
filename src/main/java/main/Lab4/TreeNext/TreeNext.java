package main.Lab4.TreeNext;

import javafx.util.Pair;
import main.Lab2.LexTypeTERMINAL;
import main.Lab3.Node;
import main.Lab3.Semantic;
import main.Lab4.Triads;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TreeNext {
    Semantic semantic;
    Triads triads;
    boolean devMode;

    public TreeNext(Semantic semantic, Triads triads, boolean devMode) {
        this.semantic = semantic;
        this.triads = triads;
        this.devMode = devMode;
    }

    NextNode root;

    {
        root = new NextNode();
        root.nodeBase = new _NextNode_Next();
    }

    NextNode current = root;

    List<NextNode> stack = new ArrayList<>();

    public void startFunc(String lexemToStr) {
//              // новоя вершина - next
//        NextNode nextVertex = new NextNode();
//        nextVertex.nodeBase = new _NextNodeNext();
//
//        // вершина - функция
//        NextNode funcVertex = new NextNode();
//        funcVertex.nodeBase = new _NextNodeFunc(lexemToStr, semantic.getNode_callFunc());
//
//        // вершина - функция становится левым потомком вершины NEXT
//        nextVertex.left = funcVertex;
//        funcVertex.parent = nextVertex;
//
//        // первый некст в функции
//        NextNode firstNextVertex = new NextNode();
//        firstNextVertex.nodeBase = new _NextNodeNext();
//
//        funcVertex.left = firstNextVertex;
//        firstNextVertex.parent = funcVertex;
//
//
//        // новая вершина NEXT становится правым потомком текущей вершины
//        current.right = nextVertex;
//        nextVertex.parent = current;
//
//
//        // текузей вершиной становится первый next в функции
//        this.current = firstNextVertex;

    }

    public void new_variable(LexTypeTERMINAL dataType, String lexem) throws Exception {
        NextNode newNextVertex = new NextNode();
        newNextVertex.nodeBase = new _NextNode_Next();


        NextNode declareVariableVertex = new NextNode();
        _NextNode_DeclareVariable nextNode_declareVariable = new _NextNode_DeclareVariable(semantic.getCurrent());
        declareVariableVertex.nodeBase = nextNode_declareVariable;


        newNextVertex.setLeft(declareVariableVertex);

        NextNode id = new NextNode();
        id.nodeBase =
                new _NextNode_ID(nextNode_declareVariable.node.getLexTypeTERMINAL(),
                        nextNode_declareVariable.node.lexem,
                        nextNode_declareVariable.node);
        this.stack.add(id);

        this.current.setRight(newNextVertex);
        this.current = newNextVertex;
    }

    public void generAssign() {
        NextNode newNextVertex = new NextNode();
        newNextVertex.nodeBase = new _NextNode_Next();

        NextNode assignVertex = new NextNode();
        assignVertex.nodeBase = new _NextNode_Assign();
        assignVertex.setLeft(this.getFromStack(-2));
        assignVertex.setRight(this.getFromStack(-1));

        newNextVertex.setLeft(assignVertex);

        this.current.setRight(newNextVertex);
        this.current = newNextVertex;

    }

    public void generDiv() {
        NextNode devVertex = new NextNode();
        devVertex.nodeBase = new _NextNode_Div();

        devVertex.setLeft(this.getFromStack(-2));
        devVertex.setRight(this.getFromStack(-1));

        this.stack.add(devVertex);
    }

    public void generStar() {
        NextNode starVertex = new NextNode();
        starVertex.nodeBase = new _NextNode_Star();

        starVertex.setLeft(this.getFromStack(-2));
        starVertex.setRight(this.getFromStack(-1));

        this.stack.add(starVertex);
    }

    public void generPlus() {
        NextNode plusVertex = new NextNode();
        plusVertex.nodeBase = new _NextNode_Plus();

        plusVertex.setLeft(this.getFromStack(-2));
        plusVertex.setRight(this.getFromStack(-1));

        this.stack.add(plusVertex);
    }

    public void push(LexTypeTERMINAL next, String lexem) throws Exception {
        if (next == LexTypeTERMINAL._INT || next == LexTypeTERMINAL._TYPE_INT_8 ||
                next == LexTypeTERMINAL._TYPE_INT_10 || next == LexTypeTERMINAL._TYPE_INT_16) {
            NextNode intVertex = new NextNode();
            intVertex.nodeBase = new _NextNode_Int(next, lexem, null);
            this.stack.add(intVertex);
        }

        if (next == LexTypeTERMINAL._DOUBLE) {
            NextNode intVertex = new NextNode();
            intVertex.nodeBase = new _NextNode_Double(next, lexem, null);
            this.stack.add(intVertex);
        }

        if (next == LexTypeTERMINAL._TYPE_CHAR) {
            throw new Exception("class TreeNext, method push, TYPE_CHAR");
        }

        // переменная, ищем ее тип
        if (next == LexTypeTERMINAL._ID) {
            NextNode intVertex = new NextNode();
            intVertex.nodeBase = new _NextNode_ID(next, lexem, semantic.getFind_last());
            this.stack.add(intVertex);
        }
    }

    private NextNode getFromStack(int i) {
        if (i >= 0) {
            NextNode nextNode = this.stack.get(i);
            this.stack.remove(i);
            return nextNode;
        } else {
            NextNode nextNode = this.stack.get(this.stack.size() + i);
            this.stack.remove(this.stack.size() + i);
            return nextNode;
        }
    }

    public void draw() throws IOException {
        // составляем файл
        try (FileWriter writer = new FileWriter("result_tree_next.gv", false)) {
            // запись всей строки
            String text = "Hello Gold!";
            writer.write("graph binary {\n" +
                    "\tdpi=\"400\";\n"
            );

            this.root.print(writer);


            writer.write("\n}\n");
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // рисуем
        if (System.getProperty("os.name").equals("Linux")) {
            Runtime.getRuntime().exec("dot result_tree_next.gv -Tpng -o result_tree_next.jpg");

        } else {
            Runtime.getRuntime().exec("cmd /c dot result_tree_next.gv -Tpng -o result_tree_next.jpg");
        }

    }

}
