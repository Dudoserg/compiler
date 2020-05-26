package main.Lab4.TreeNext;

import main.Lab2.LexTypeTERMINAL;
import main.Lab3.LLK;
import main.Lab3.Semantic;
import main.Lab3.exceptions.*;
import main.Lab4.Optimizations.Agrigate;
import main.Lab4.Optimizations.CalculateBeforeCompile;
import main.Lab4.Optimizations.Shift;
import main.Lab4.TreeNext.Const._NextNode_Double;
import main.Lab4.TreeNext.Const._NextNode_Int;
import main.Lab4.TreeNext.MathOperation.*;
import main.Lab4.TreeNext.Relations.*;
import main.Lab4.Triads;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class TreeNext {
    Semantic semantic;
    Triads triads;
    boolean devMode;
    private NextNode savedVariable;
    private NextNode find_last;
    private List<NextNode> stack_node_callFunc = new ArrayList<>(0);
    public List<NextNode_Triad> listTriads = new ArrayList<>();

    public TreeNext(Semantic semantic, Triads triads, boolean devMode) {
        this.semantic = semantic;
        this.triads = triads;
        this.devMode = devMode;
    }

    public NextNode root;

    {
        root = new NextNode();
        root.nodeBase = new _NextNode_Next(LLK.savePointCurrent);
    }

    NextNode current = root;
    NextNode k;

    List<NextNode> stack = new ArrayList<>();


    public void startFunc(String lexemToStr) throws Exception {
        // новоя вершина - next
        NextNode nextVertex = new NextNode();
        nextVertex.nodeBase = new _NextNode_Next(LLK.savePointCurrent);


        // вершина - функция
        NextNode funcVertex = new NextNode();
        _NextNode_Func funcNodeBase = new _NextNode_Func(lexemToStr, dataType, LLK.savePointCurrent);
        funcVertex.nodeBase = funcNodeBase;
        k = funcVertex;

        // проверяем дубликаты функции
        if (checkDublicateFunc(funcVertex)) {
            throw new Ex_Dublicate_Func(funcNodeBase.lexem);
        }
        // вершина - функция становится левым потомком вершины NEXT
        nextVertex.setLeft(funcVertex);

        // первый некст в функции
        NextNode firstNextVertex = new NextNode();
        firstNextVertex.nodeBase = new _NextNode_Next(LLK.savePointCurrent);

        funcVertex.setLeft(firstNextVertex);

        // новая вершина NEXT становится правым потомком текущей вершины
        current.setRight(nextVertex);

        // текузей вершиной становится первый next в функции
        this.current = firstNextVertex;


    }

    private boolean checkDublicateFunc(NextNode funcVertex) throws Exception {
        if (!(funcVertex.nodeBase instanceof _NextNode_Func))
            throw new Exception("checkDublicateFunc, вы проверяете не функцию!");

        _NextNode_Func nodeBase = (_NextNode_Func) funcVertex.nodeBase;

        NextNode checkingNext = current;
        do {

            if (checkingNext == null)
                return false;
            NextNode checking = checkingNext.left;
            if (checking == null) {
                // проверяем дальше
                checkingNext = getCheckNext(checkingNext);
                if (checkingNext == null)
                    return false;
                continue;
            }
            if (checking.nodeBase instanceof _NextNode_Func) {
                _NextNode_Func checking_nodeBase = (_NextNode_Func) checking.nodeBase;
                if (nodeBase.lexem.equals(checking_nodeBase.lexem)) {
                    return true;
                }
            }
            if (checking.parent == null)            // Дошли до корня
                return false;
            // если следующий функция
            NextNode checkingOld = checkingNext;
            do {
                checkingOld = checkingNext;
                checkingNext = checkingNext.parent;
                if (checkingNext != checkingOld && checkingNext.nodeBase instanceof _NextNode_Next)
                    break;
            } while (true);
            System.out.print("");

        } while (checkingNext.parent != null);
        return false;
    }

    public void new_variable(LexTypeTERMINAL dataType, String lexem) throws Exception {
//        NextNode newNextVertex = new NextNode();
//        newNextVertex.nodeBase = new _NextNode_Next();
//
//
//        NextNode declareVariableVertex = new NextNode();
//        _NextNode_DeclareVariable nextNode_declareVariable = new _NextNode_DeclareVariable(dataType, lexem);
//        declareVariableVertex.nodeBase = nextNode_declareVariable;
//
//
//        newNextVertex.setLeft(declareVariableVertex);
//
//        NextNode id = new NextNode();
//        id.nodeBase =
//                new _NextNode_ID(dataType, lexem, declareVariableVertex);
//        this.stack.add(id);
//
//        this.current.setRight(newNextVertex);
//        this.current = newNextVertex;
    }

    public void generAssign() throws Exception {
        NextNode newNextVertex = new NextNode();
        newNextVertex.nodeBase = new _NextNode_Next(LLK.savePointCurrent);

        NextNode assignVertex = new NextNode();
        assignVertex.nodeBase = new _NextNode_Assign(LLK.savePointCurrent);

        NextNode left_fromStack = this.getFromStack(-2);
        assignVertex.setLeft(left_fromStack);

        NextNode right_fromStack = this.getFromStack(-1);
        right_fromStack = castToLeft(left_fromStack, right_fromStack);
        assignVertex.setRight(right_fromStack);

        newNextVertex.setLeft(assignVertex);

        this.current.setRight(newNextVertex);
        this.current = newNextVertex;
    }

    private NextNode castToLeft(final NextNode left_fromStack, NextNode right_fromStack) throws Exception {
        LexTypeTERMINAL left_lexTypeTERMINAL = getLexTypeTerminal_inMathOper(left_fromStack);
        LexTypeTERMINAL right_lexTypeTERMINAL = getLexTypeTerminal_inMathOper(right_fromStack);

        // double double
        if (left_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE && right_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE) {
        }
        // int double ПРИВОДИМ Правую К ИНТУ
        else if ((left_lexTypeTERMINAL == LexTypeTERMINAL._INT || left_lexTypeTERMINAL == LexTypeTERMINAL._TYPE_INT_10) &&
                right_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE) {

            NextNode castNode = new NextNode();
            castNode.nodeBase = new _NextNode_Cast(LexTypeTERMINAL._INT, LLK.savePointCurrent);
            castNode.setRight(right_fromStack);
            right_fromStack = castNode;
        }
        //  double int ПРИВОДИМ ПРАВУЮ К ДАБЛУ
        else if (left_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE &&
                (right_lexTypeTERMINAL == LexTypeTERMINAL._INT || right_lexTypeTERMINAL == LexTypeTERMINAL._TYPE_INT_10)) {
            NextNode castNode = new NextNode();
            castNode.nodeBase = new _NextNode_Cast(LexTypeTERMINAL._DOUBLE, LLK.savePointCurrent);
            castNode.setRight(right_fromStack);
            right_fromStack = castNode;
        }
        //  int int
        else if ((left_lexTypeTERMINAL == LexTypeTERMINAL._INT || left_lexTypeTERMINAL == LexTypeTERMINAL._TYPE_INT_10) &&
                (right_lexTypeTERMINAL == LexTypeTERMINAL._INT || right_lexTypeTERMINAL == LexTypeTERMINAL._TYPE_INT_10)) {
        }
        //  double double
        else if (left_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE &&
                right_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE) {
        } else {
            throw new Exception("17ds89+4w4c6hj");
        }
        return right_fromStack;
    }

    private ArrayList<Object> calculateCast(String sign, NextNode left_fromStack, NextNode right_fromStack) throws Exception {
        LexTypeTERMINAL left_lexTypeTERMINAL = getLexTypeTerminal_inMathOper(left_fromStack);
        LexTypeTERMINAL right_lexTypeTERMINAL = getLexTypeTerminal_inMathOper(right_fromStack);


        LexTypeTERMINAL result_lexTypeTERMINAL = null;// если один из оперант дабл, то результат дабл
        // double double
        if (left_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE && right_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE) {
            // результат дабл
            result_lexTypeTERMINAL = LexTypeTERMINAL._DOUBLE;
        }
        // int double ПРИВОДИМ ЛЕВУЮ К ДАБЛУ
        else if ((left_lexTypeTERMINAL == LexTypeTERMINAL._INT || left_lexTypeTERMINAL == LexTypeTERMINAL._TYPE_INT_10) &&
                right_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE) {
            result_lexTypeTERMINAL = LexTypeTERMINAL._DOUBLE;

            NextNode castNode = new NextNode();
            castNode.nodeBase = new _NextNode_Cast(LexTypeTERMINAL._DOUBLE, LLK.savePointCurrent);
            castNode.setRight(left_fromStack);
            left_fromStack = castNode;
        }
        //  double int ПРИВОДИМ ПРАВУЮ К ДАБЛУ
        else if (left_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE &&
                (right_lexTypeTERMINAL == LexTypeTERMINAL._INT || right_lexTypeTERMINAL == LexTypeTERMINAL._TYPE_INT_10)) {
            result_lexTypeTERMINAL = LexTypeTERMINAL._DOUBLE;

            NextNode castNode = new NextNode();
            castNode.nodeBase = new _NextNode_Cast(LexTypeTERMINAL._DOUBLE, LLK.savePointCurrent);
            castNode.setRight(right_fromStack);
            right_fromStack = castNode;
        }
        //  int int
        else if ((left_lexTypeTERMINAL == LexTypeTERMINAL._INT || left_lexTypeTERMINAL == LexTypeTERMINAL._TYPE_INT_10) &&
                (right_lexTypeTERMINAL == LexTypeTERMINAL._INT || right_lexTypeTERMINAL == LexTypeTERMINAL._TYPE_INT_10)) {
            // результат дабл
            if ("div".equals(sign)) {
                result_lexTypeTERMINAL = LexTypeTERMINAL._DOUBLE;
            } else if ("start".equals(sign)) {
                result_lexTypeTERMINAL = LexTypeTERMINAL._INT;
            } else if ("plus".equals(sign)) {
                result_lexTypeTERMINAL = LexTypeTERMINAL._INT;
            } else if ("minus".equals(sign)) {
                result_lexTypeTERMINAL = LexTypeTERMINAL._INT;
            } else {
                System.out.println("not found this sign : " + sign);
                throw new Exception("not found this sign : " + sign);
            }

        }
        //  double double
        else if (left_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE &&
                right_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE) {
            // результат дабл
            result_lexTypeTERMINAL = LexTypeTERMINAL._INT;
        } else {
            throw new Exception("1a14qw820146cd38207d53426hj");
        }
        return new ArrayList<Object>(Arrays.asList(result_lexTypeTERMINAL, left_fromStack, right_fromStack));
    }

    public void generMinus() throws Exception {
        NextNode minusVertex = new NextNode();
        final _NextNode_Minus nodeBase = new _NextNode_Minus(LLK.savePointCurrent);
        minusVertex.nodeBase = nodeBase;

        NextNode left_fromStack = getFromStack(-2);
        LexTypeTERMINAL left_lexTypeTERMINAL = getLexTypeTerminal_inMathOper(left_fromStack);

        NextNode right_fromStack = getFromStack(-1);
        LexTypeTERMINAL right_lexTypeTERMINAL = getLexTypeTerminal_inMathOper(right_fromStack);

        final ArrayList<Object> listObjects = calculateCast("minus", left_fromStack, right_fromStack);
        nodeBase.lexTypeTERMINAL = (LexTypeTERMINAL) listObjects.get(0);
        left_fromStack = (NextNode) listObjects.get(1);
        right_fromStack = (NextNode) listObjects.get(2);

        minusVertex.setLeft(left_fromStack);
        minusVertex.setRight(right_fromStack);

        this.stack.add(minusVertex);
    }

    public void generDiv() throws Exception {
        NextNode divVertex = new NextNode();
        final _NextNode_Div nodeBase = new _NextNode_Div(LLK.savePointCurrent);
        divVertex.nodeBase = nodeBase;

        NextNode left_fromStack = getFromStack(-2);
        LexTypeTERMINAL left_lexTypeTERMINAL = getLexTypeTerminal_inMathOper(left_fromStack);

        NextNode right_fromStack = getFromStack(-1);
        LexTypeTERMINAL right_lexTypeTERMINAL = getLexTypeTerminal_inMathOper(right_fromStack);

        final ArrayList<Object> listObjects = calculateCast("div", left_fromStack, right_fromStack);
        nodeBase.lexTypeTERMINAL = (LexTypeTERMINAL) listObjects.get(0);
        left_fromStack = (NextNode) listObjects.get(1);
        right_fromStack = (NextNode) listObjects.get(2);

        divVertex.setLeft(left_fromStack);
        divVertex.setRight(right_fromStack);

        this.stack.add(divVertex);
    }

    public void generStar() throws Exception {
        NextNode starVertex = new NextNode();
        final _NextNode_Star nodeBase = new _NextNode_Star(LLK.savePointCurrent);
        starVertex.nodeBase = nodeBase;

        NextNode left_fromStack = getFromStack(-2);
        LexTypeTERMINAL left_lexTypeTERMINAL = getLexTypeTerminal_inMathOper(left_fromStack);

        NextNode right_fromStack = getFromStack(-1);
        LexTypeTERMINAL right_lexTypeTERMINAL = getLexTypeTerminal_inMathOper(right_fromStack);

        final ArrayList<Object> listObjects = calculateCast("start", left_fromStack, right_fromStack);
        nodeBase.lexTypeTERMINAL = (LexTypeTERMINAL) listObjects.get(0);
        left_fromStack = (NextNode) listObjects.get(1);
        right_fromStack = (NextNode) listObjects.get(2);

        starVertex.setLeft(left_fromStack);
        starVertex.setRight(right_fromStack);

        this.stack.add(starVertex);
    }

    public void generPlus() throws Exception {
        NextNode plusVertex = new NextNode();
        final _NextNode_Plus nodeBase = new _NextNode_Plus(LLK.savePointCurrent);
        plusVertex.nodeBase = nodeBase;

        NextNode left_fromStack = getFromStack(-2);
        LexTypeTERMINAL left_lexTypeTERMINAL = getLexTypeTerminal_inMathOper(left_fromStack);

        NextNode right_fromStack = getFromStack(-1);
        LexTypeTERMINAL right_lexTypeTERMINAL = getLexTypeTerminal_inMathOper(right_fromStack);

        final ArrayList<Object> listObjects = calculateCast("plus", left_fromStack, right_fromStack);
        nodeBase.lexTypeTERMINAL = (LexTypeTERMINAL) listObjects.get(0);
        left_fromStack = (NextNode) listObjects.get(1);
        right_fromStack = (NextNode) listObjects.get(2);

        plusVertex.setLeft(left_fromStack);
        plusVertex.setRight(right_fromStack);

        this.stack.add(plusVertex);
    }

    // вычисляем результат операции над двумя операндами определенных типов
    @Deprecated
    private LexTypeTERMINAL calculateResultLexTypeTerminal(LexTypeTERMINAL left_lexTypeTERMINAL, LexTypeTERMINAL right_lexTypeTERMINAL)
            throws Exception {
        LexTypeTERMINAL result_lexTypeTERMINAL = null;// если один из оперант дабл, то результат дабл
        if (left_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE && right_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE) {
            // результат дабл
            result_lexTypeTERMINAL = LexTypeTERMINAL._DOUBLE;
        }
        if ((left_lexTypeTERMINAL == LexTypeTERMINAL._INT || left_lexTypeTERMINAL == LexTypeTERMINAL._TYPE_INT_10) &&
                right_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE) {
            // результат дабл
            result_lexTypeTERMINAL = LexTypeTERMINAL._DOUBLE;
        } else if (left_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE &&
                (right_lexTypeTERMINAL == LexTypeTERMINAL._INT || right_lexTypeTERMINAL == LexTypeTERMINAL._TYPE_INT_10)) {
            // результат дабл
            result_lexTypeTERMINAL = LexTypeTERMINAL._DOUBLE;
        } else if ((left_lexTypeTERMINAL == LexTypeTERMINAL._INT || left_lexTypeTERMINAL == LexTypeTERMINAL._TYPE_INT_10) &&
                (right_lexTypeTERMINAL == LexTypeTERMINAL._INT || right_lexTypeTERMINAL == LexTypeTERMINAL._TYPE_INT_10)) {
            // результат дабл
            result_lexTypeTERMINAL = LexTypeTERMINAL._INT;
        } else if (left_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE &&
                right_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE) {
            // результат дабл
            result_lexTypeTERMINAL = LexTypeTERMINAL._INT;
        } else {
            throw new Exception("12820463820742546hj");
        }
        return result_lexTypeTERMINAL;
    }

    // получаем тип операнды из вершины
    static LexTypeTERMINAL getLexTypeTerminal_inMathOper(NextNode fromStack) throws Exception {
        LexTypeTERMINAL lexTypeTERMINAL = null;
        if (fromStack.nodeBase instanceof _NextNode_Int) {
            final _NextNode_Int left_nodeBase = (_NextNode_Int) fromStack.nodeBase;
            lexTypeTERMINAL = left_nodeBase.lexTypeTERMINAL;
        } else if (fromStack.nodeBase instanceof _NextNode_Double) {
            final _NextNode_Double left_nodeBase = (_NextNode_Double) fromStack.nodeBase;
            lexTypeTERMINAL = left_nodeBase.lexTypeTERMINAL;
        } else if (fromStack.nodeBase instanceof _NextNode_ID) {
            final _NextNode_ID nodeBase = (_NextNode_ID) fromStack.nodeBase;
            final NextNode declNode = nodeBase.nextNode;
            final _NextNode_DeclareVariable decl_nodeBase = (_NextNode_DeclareVariable) declNode.nodeBase;
            lexTypeTERMINAL = decl_nodeBase.lexTypeTERMINAL;
        } else if (fromStack.nodeBase instanceof _NextNode_Call) {
            final _NextNode_Call left_nodeBase = (_NextNode_Call) fromStack.nodeBase;
            NextNode callFunc = left_nodeBase.func;
            final _NextNode_Func callFunc_nodeBase = (_NextNode_Func) callFunc.nodeBase;
            lexTypeTERMINAL = callFunc_nodeBase.lexTypeTERMINAL;
        } else if (fromStack.nodeBase instanceof _NextNode_Func) {
            final _NextNode_Func left_nodeBase = (_NextNode_Func) fromStack.nodeBase;
            lexTypeTERMINAL = left_nodeBase.lexTypeTERMINAL;
        } else if (fromStack.nodeBase instanceof _NextNode_Plus) {
            final _NextNode_Plus left_nodeBase = (_NextNode_Plus) fromStack.nodeBase;
            lexTypeTERMINAL = left_nodeBase.lexTypeTERMINAL;
        } else if (fromStack.nodeBase instanceof _NextNode_Minus) {
            final _NextNode_Minus left_nodeBase = (_NextNode_Minus) fromStack.nodeBase;
            lexTypeTERMINAL = left_nodeBase.lexTypeTERMINAL;
        } else if (fromStack.nodeBase instanceof _NextNode_Div) {
            final _NextNode_Div left_nodeBase = (_NextNode_Div) fromStack.nodeBase;
            lexTypeTERMINAL = left_nodeBase.lexTypeTERMINAL;
        } else if (fromStack.nodeBase instanceof _NextNode_Star) {
            final _NextNode_Star left_nodeBase = (_NextNode_Star) fromStack.nodeBase;
            lexTypeTERMINAL = left_nodeBase.lexTypeTERMINAL;
        } else if (fromStack.nodeBase instanceof _NextNode_DeclareVariable) {
            final _NextNode_DeclareVariable left_nodeBase = (_NextNode_DeclareVariable) fromStack.nodeBase;
            lexTypeTERMINAL = left_nodeBase.lexTypeTERMINAL;
        } else {
            throw new Exception("SAhjf78013031z';sw");
        }
        return lexTypeTERMINAL;
    }


    public void triad_push(LexTypeTERMINAL next, String lexem) throws Exception {
        if (next == LexTypeTERMINAL._INT || next == LexTypeTERMINAL._TYPE_INT_8 ||
                next == LexTypeTERMINAL._TYPE_INT_10 || next == LexTypeTERMINAL._TYPE_INT_16) {
            NextNode intVertex = new NextNode();
            intVertex.nodeBase = new _NextNode_Int(next, lexem, LLK.savePointCurrent);
            this.stack.add(intVertex);
        }

        if (next == LexTypeTERMINAL._DOUBLE) {
            NextNode intVertex = new NextNode();
            intVertex.nodeBase = new _NextNode_Double(next, lexem, LLK.savePointCurrent);
            this.stack.add(intVertex);
        }

        if (next == LexTypeTERMINAL._TYPE_CHAR) {
            throw new Exception("class TreeNext, method push, TYPE_CHAR");
        }

        // переменная, ищем ее тип
        if (next == LexTypeTERMINAL._ID) {
            NextNode intVertex = new NextNode();
            intVertex.nodeBase = new _NextNode_ID(next, lexem, this.find_last, LLK.savePointCurrent);
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

    long lastDrawTime = 0;

    private void clearDrawed(NextNode start) {
        start.isDrawed = false;
        if (start.left != null)
            clearDrawed(start.left);
        if (start.right != null)
            clearDrawed(start.right);
    }


    public void draw(NextNode start, NextNode current, String fileName) throws IOException {
        clearDrawed(start);
        if (!LLK.DRAWING)
            return;
        String jpgPath = "деревья/" + fileName;
        String gvPath = "деревья/gv/" + fileName + ".gv";
        try (FileWriter writer = new FileWriter(gvPath, false)) {
            writer.write("graph binary {\n" +
                    "\tdpi=\"75\";\n" + " splines=\"line\"  \n");
            start.print(writer, current);
            writer.write("\n}\n");
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // рисуем
        if (System.getProperty("os.name").equals("Linux")) {
            Runtime.getRuntime().exec("dot " + gvPath + " -Tpng -o " + jpgPath + ".jpg");
        } else {
            Runtime.getRuntime().exec("cmd /c dot " + gvPath + " -Tpng -o " + jpgPath + ".jpg");
        }

    }

    public void draw(NextNode current, String fileName) throws Exception {
        clearDrawed(this.root);
        if (!LLK.DRAWING)
            return;
        String jpgPath = "деревья/" + fileName;
        String gvPath = "деревья/gv/" + fileName + ".gv";
        try (FileWriter writer = new FileWriter(gvPath, false)) {
            // запись всей строки
            String text = "Hello Gold!";
            writer.write("graph binary {\n" +
                    "\tdpi=\"90\";\n" +
                    " splines=\"line\"  \n"
            );

            this.root.print(writer, current);


            writer.write("\n}\n");
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        // рисуем
        if (System.getProperty("os.name").equals("Linux")) {
            Runtime.getRuntime().exec("dot " + gvPath + " -Tpng -o " + jpgPath + ".jpg");
        } else {
            Runtime.getRuntime().exec("cmd /c dot " + gvPath + " -Tpng -o " + jpgPath + ".jpg");
        }
        lastDrawTime = System.currentTimeMillis();

    }

    LexTypeTERMINAL dataType;
    boolean flag_Decl;

    public void startDecl(LexTypeTERMINAL dataType) throws Exception {
        if (dataType != LexTypeTERMINAL._INT && dataType != LexTypeTERMINAL._DOUBLE)
            throw new Exception("dataType != LexTypeTERMINAL._INT || dataType != LexTypeTERMINAL._DOUBLE");
        this.dataType = dataType;
        this.flag_Decl = true;
    }

    public void setIdent(String lexem) throws Exception {
        // занести идентификатор в таблицу с типом dataType;
        NextNode declareVariable = new NextNode();
        _NextNode_DeclareVariable nodeBase = new _NextNode_DeclareVariable(LLK.savePointCurrent);
        declareVariable.nodeBase = nodeBase;

        if (dataType == LexTypeTERMINAL._DOUBLE || dataType == LexTypeTERMINAL._INT) {
            nodeBase.lexTypeTERMINAL = dataType;
            nodeBase.lexem = lexem;
        } else
            throw new Exception("TreeNext setIdent отвалилось");

        // Проверить дублирование
        if (checkDublicate(declareVariable)) {
            // нашли дубликат, ошибка
            throw new Ex_Dublicate(nodeBase.lexem);
        }
        // Запоминаем переменную
        this.savedVariable = declareVariable;

        NextNode nodeNext = new NextNode();
        nodeNext.nodeBase = new _NextNode_Next(LLK.savePointCurrent);
        nodeNext.setLeft(declareVariable);

        current.setRight(nodeNext);
        current = nodeNext;

        System.out.print("");

        // кладем переменную в стек;
        NextNode id = new NextNode();
        id.nodeBase = new _NextNode_ID(dataType, lexem, declareVariable, LLK.savePointCurrent);

        if (dataType == LexTypeTERMINAL._INT) {
        } else if (dataType == LexTypeTERMINAL._DOUBLE) {
        } else throw new Exception("shj918fsj2jjfogyval");

        this.stack.add(id);
        //this.draw(current);
        System.out.print("");

    }

    private boolean checkDublicate(NextNode node) throws Exception {
        NextNode checkingNext = current;
        if (!(node.nodeBase instanceof _NextNode_DeclareVariable))
            throw new Exception(" ты проверяешь на дубликан не переменную");

        _NextNode_DeclareVariable nodeBase = (_NextNode_DeclareVariable) node.nodeBase;
        do {
            if (checkingNext == null)
                return false;

            NextNode checking = checkingNext.left;

            if (checking == null) {
                // проверяем дальше
                checkingNext = getCheckNext(checkingNext);
                if (checkingNext == null)
                    return false;
                continue;
            }
            if (checking.nodeBase instanceof _NextNode_DeclareVariable) {
                _NextNode_DeclareVariable checking_nodeBase = (_NextNode_DeclareVariable) checking.nodeBase;
                if (nodeBase.lexem.equals(checking_nodeBase.lexem)) {
                    return true;
                }
            } else if (checking.nodeBase instanceof _NextNode_Func) {
                _NextNode_Func checking_nodeBase = (_NextNode_Func) checking.nodeBase;
                if (nodeBase.lexem.equals(checking_nodeBase.lexem)) {
                    return true;
                }
            } else {
                // проверяем дальше
                checkingNext = getCheckNext(checkingNext);
                if (checkingNext == null)
                    return false;
                continue;
            }

            if (checking.parent == null)            // Дошли до корня
                return false;
            // если следующий функция
            NextNode checkingOld = checkingNext;
            do {
                checkingOld = checkingNext;
                checkingNext = checkingNext.parent;
                if (checkingNext != checkingOld && checkingNext.nodeBase instanceof _NextNode_Next)
                    break;
                ;
            } while (true);
            System.out.print("");
        } while (true);
    }

    private NextNode getCheckNext(NextNode checkingNext) {
        checkingNext = checkingNext.parent;
        if (checkingNext == null)
            return null;
        // Если ща смотрим вершину - функцию, то берем еще выше
        if (checkingNext.nodeBase instanceof _NextNode_Func)
            checkingNext = checkingNext.parent;
        return checkingNext;
    }

    private boolean checkDublicateInParams(NextNode func, NextNode node) throws Exception {
        NextNode checking = func.right;
        if (!(node.nodeBase instanceof _NextNode_ID))
            throw new Exception(" ты проверяешь на дубликан не переменную");

        _NextNode_ID nodeBase = (_NextNode_ID) node.nodeBase;
        do {
            // просмотрели все параметры
            if (checking == null)
                return false;
            // конвертим к нужному типу, тут точно все айдишники, атк что инстансоф не будем проверять
            _NextNode_ID checking_nodeBase = (_NextNode_ID) checking.nodeBase;
            // если сходится айдишник
            if (nodeBase.lexem.equals(checking_nodeBase.lexem)) {
                return true;
            }

            checking = checking.right;
        } while (true);
    }

    public void callFunc() {
        NextNode node_callFunc = this.find_last;
        this.stack_node_callFunc.add(node_callFunc);
    }

    //    private int parameter_counting;
    // Использую стек, т.к. в параметрах уже вызываемой функции для которой ведется расчет,
    //  может встретиться так же вызываемая функция, и уже для нее надо с нуля считать параметры.
    List<Integer> parameter_counting = new ArrayList<>();

    public void start_parameter_counting() {
        parameter_counting.add(0);
    }

    public void plus_parameter_counting() throws Exception {
        parameter_counting.set(
                parameter_counting.size() - 1,
                parameter_counting.get(parameter_counting.size() - 1) + 1
        );
        final NextNode peek = stack_findLastFunc.peek();
        if (peek.nodeBase instanceof _NextNode_Func) {
            int countParam = ((_NextNode_Func) peek.nodeBase).countParam;
            int countCurrent = parameter_counting.get(parameter_counting.size() - 1);
            if (countCurrent > countParam)
                throw new Ex_Signature(countParam, countCurrent);
        } else {
            throw new Exception("Тут должна быть только функция123");
        }
    }

    public void end_parameter_counting() throws Exception {
        Integer lastParameter_counting = this.parameter_counting.get(this.parameter_counting.size() - 1);
        this.parameter_counting.remove(this.parameter_counting.size() - 1);

        NextNode node_callFunc = stack_node_callFunc.get(stack_node_callFunc.size() - 1);
        stack_node_callFunc.remove(stack_node_callFunc.size() - 1);

        // TODO проверяем количество параметров у вызываемой функции
        if (node_callFunc.nodeBase instanceof _NextNode_Func) {
            _NextNode_Func nodeBase = (_NextNode_Func) node_callFunc.nodeBase;
            if (nodeBase.countParam != lastParameter_counting)
                throw new Ex_Signature(nodeBase.countParam,
                        lastParameter_counting);
        } else
            throw new Exception("jsd9625asjcv20q2ks[pdnv6s");
        System.out.print("");
    }

    public void match() {
        System.out.print("");
    }

    public void matchLeft() {
        System.out.print("");
    }

    public void checkDubl(String lexemToStr) {
        System.out.print("");
    }

    public Stack<NextNode> stack_findLast = new Stack<>();
    public Stack<NextNode> stack_findLastFunc = new Stack<>();

    public void add_stack_findLast(NextNode node) {
        if (node.nodeBase instanceof _NextNode_Func)
            stack_findLastFunc.add(node);
        stack_findLast.add(node);
    }

    public NextNode find(String lexemToStr) throws Exception {

        NextNode node;
        if (flag_Decl)
            node = this.current.parent;
        else
            node = this.current;

        do {

            NextNode left = node.left;
            if (left == null) {
                NextNode nodeOld = node;
                do {
                    nodeOld = node;
                    node = node.parent;
                    if (node == null) {
                        //find_last = node;
                        //add_stack_findLast(find_last);
                        throw new Ex_NotFound(lexemToStr);
                    }
                    if (node != nodeOld && node.nodeBase instanceof _NextNode_Next)
                        break;
                } while (true);

                continue;
            }
            if ((left.nodeBase instanceof _NextNode_DeclareVariable)) {
                _NextNode_DeclareVariable tmp = (_NextNode_DeclareVariable) left.nodeBase;
                if (tmp.lexem.equals(lexemToStr)) {
                    find_last = left;
                    add_stack_findLast(find_last);
                    return left;
                }
            } else if ((left.nodeBase instanceof _NextNode_Func)) {
                _NextNode_Func tmp = (_NextNode_Func) left.nodeBase;
                if (tmp.lexem.equals(lexemToStr)) {
                    find_last = left;
                    add_stack_findLast(find_last);
                    return left;
                }
            }

            {
                NextNode nodeOld = node;
                do {
                    nodeOld = node;
                    node = node.parent;
                    if (node == null) {
                        find_last = node;
                        add_stack_findLast(find_last);
                        throw new Ex_NotFound(lexemToStr);
                    }
                    if (node != nodeOld && node.nodeBase instanceof _NextNode_Next)
                        break;
                } while (true);
            }

            if (node == null)
                throw new Ex_NotFound(lexemToStr);

        } while (true);
    }

    // объявление функции, считаем количество параметров
    public void plusParam() throws Exception {
        if (this.k.nodeBase instanceof _NextNode_Func)
            ((_NextNode_Func) this.k.nodeBase).countParam++;
        else
            throw new Exception("7812389-a");
    }

    public void newBlack() throws IOException {
        /*// next вправо
        NextNode nextRight = new NextNode();
        nextRight.nodeBase = new _NextNode_Next();
        // next влево
        NextNode nextLeft = new NextNode();
        nextLeft.nodeBase = new _NextNode_Next();

        this.current.setRight(nextRight);
        nextRight.setLeft(nextLeft);

        this.current = nextLeft;
//        this.draw(current);
        System.out.print("");*/
    }

    List<NextNode> stack_startLevel = new ArrayList<>();

    public void startLevel() throws Exception {
        NextNode rightNextNode = new NextNode();
        rightNextNode.nodeBase = new _NextNode_Next(LLK.savePointCurrent);
        current.setRight(rightNextNode);
        current = rightNextNode;
        this.stack_startLevel.add(rightNextNode);

        NextNode leftNextNode = new NextNode();
        leftNextNode.nodeBase = new _NextNode_StartLevel(LLK.savePointCurrent);
        current.setLeft(leftNextNode);
        current = leftNextNode;
    }

    public void endLevel() {
        current = stack_startLevel.get(stack_startLevel.size() - 1);
        stack_startLevel.remove(stack_startLevel.size() - 1);
    }


    public void endDecl() {
        this.flag_Decl = false;
    }

    List<NextNode> listIf = new ArrayList<>();

    public void triad_gener_if() throws IOException {
        NextNode nextNode = new NextNode();
        nextNode.nodeBase = new _NextNode_Next(LLK.savePointCurrent);

        NextNode nodeIf = new NextNode();
        nodeIf.nodeBase = new _NextNode_If(LLK.savePointCurrent);

        nextNode.setLeft(nodeIf);
        listIf.add(nodeIf);

        NextNode nodeElse = new NextNode();
        nodeElse.nodeBase = new _NextNode_Else(LLK.savePointCurrent);

        nodeIf.setRight(nodeElse);


        this.current.setRight(nextNode);
        this.current = nextNode;

//        NextNode nodeIF = listIf.get(listIf.size() - 1);
        nodeIf.setLeft(this.getFromStack(-1));


        NextNode elseLeftNext = new NextNode();
        elseLeftNext.nodeBase = new _NextNode_Next(LLK.savePointCurrent);

        nodeElse.setLeft(elseLeftNext);
        this.current = elseLeftNext;

//        this.draw(this.current);
        System.out.print("");
    }


    public void triad_gener_goto() throws IOException {
        NextNode nodeIF = listIf.get(listIf.size() - 1);
        NextNode nodeElse = nodeIF.right;

        NextNode rightElseNext = new NextNode();
        rightElseNext.nodeBase = new _NextNode_Next(LLK.savePointCurrent);

        nodeElse.setRight(rightElseNext);

        this.current = rightElseNext;
//        this.draw(this.current);
        System.out.print("");
    }

    public void triad_form_if() {
//        NextNode nodeIF = listIf.get(listIf.size() - 1);
//        NextNode nodeElse = nodeIF.right;
//
//        NextNode nextNode = new NextNode();
//        nextNode.nodeBase = new _NextNode_Next();
//
//        nodeElse.setLeft(nextNode);
    }

    public void gener_equal() {
        NextNode greatVertex = new NextNode();
        greatVertex.nodeBase = new _NextNode_Equal(LLK.savePointCurrent);
        greatVertex.setLeft(this.getFromStack(-2));
        greatVertex.setRight(this.getFromStack(-1));
        this.stack.add(greatVertex);
    }

    public void gener_not_equal() {
        NextNode greatVertex = new NextNode();
        greatVertex.nodeBase = new _NextNode_Not_Equal(LLK.savePointCurrent);
        greatVertex.setLeft(this.getFromStack(-2));
        greatVertex.setRight(this.getFromStack(-1));
        this.stack.add(greatVertex);
    }

    public void gener_great() {
        NextNode greatVertex = new NextNode();
        greatVertex.nodeBase = new _NextNode_Great(LLK.savePointCurrent);
        greatVertex.setLeft(this.getFromStack(-2));
        greatVertex.setRight(this.getFromStack(-1));
        this.stack.add(greatVertex);
    }

    public void gener_great_equal() {
        NextNode greatVertex = new NextNode();
        greatVertex.nodeBase = new _NextNode_Great_Equal(LLK.savePointCurrent);
        greatVertex.setLeft(this.getFromStack(-2));
        greatVertex.setRight(this.getFromStack(-1));
        this.stack.add(greatVertex);
    }

    public void gener_less() {
        NextNode greatVertex = new NextNode();
        greatVertex.nodeBase = new _NextNode_Less(LLK.savePointCurrent);
        greatVertex.setLeft(this.getFromStack(-2));
        greatVertex.setRight(this.getFromStack(-1));
        this.stack.add(greatVertex);
    }

    public void gener_less_equal() {
        NextNode greatVertex = new NextNode();
        greatVertex.nodeBase = new _NextNode_Less_Equal(LLK.savePointCurrent);
        greatVertex.setLeft(this.getFromStack(-2));
        greatVertex.setRight(this.getFromStack(-1));
        this.stack.add(greatVertex);
    }

    public void triad_gener_if_NOP() throws Exception {
        NextNode nodeIF = listIf.get(listIf.size() - 1);
        listIf.remove(listIf.size() - 1);
        this.current = nodeIF.parent;
//        this.draw(this.current);
    }

    public void triad_return() throws Exception {
        NextNode nextNode = new NextNode();
        nextNode.nodeBase = new _NextNode_Next(LLK.savePointCurrent);

        NextNode returnNode = new NextNode();
        returnNode.nodeBase = new _NextNode_Return(LLK.savePointCurrent);

        // вершина функция
        NextNode left_ = k;
        // операнд который возвращаем
        NextNode right_fromStack = this.getFromStack(-1);
        right_fromStack = castToLeft(left_, right_fromStack);
        returnNode.setRight(right_fromStack);

        nextNode.setLeft(returnNode);

        this.current.setRight(nextNode);
        this.current = nextNode;
//        this.draw(this.current);
        System.out.print("");
    }

    public void endFunc() throws Exception {
        NextNode nextNode = new NextNode();
        nextNode.nodeBase = new _NextNode_Next(LLK.savePointCurrent);
        this.current.setRight(nextNode);

        NextNode endNode = new NextNode();
        endNode.nodeBase = new _NextNode_FuncEnd(k, LLK.savePointCurrent);
        nextNode.setLeft(endNode);

        this.current = k.parent;

        final NextNode startBody_ofFunc = findStartBody_OfFunc(k);
        final boolean is_AllBranchReturnValue = isAllBranchReturnValue(startBody_ofFunc);

        if (!is_AllBranchReturnValue)
            throw new Ex_MissingReturn(k);

    }

    List<NextNode> stackPushParam = new ArrayList<>();

    public void triad_push_param() throws Exception {
        NextNode nodePushParam = new NextNode();
        _NextNode_Push_Param nodeBase = new _NextNode_Push_Param(LLK.savePointCurrent);
        nodePushParam.nodeBase = nodeBase;

        NextNode right_FromStack = this.getFromStack(-1);
        // TODO get param of function by index
        // -1 т.к. прибавляем раньше вызова  метода triad_push_param
        final Integer indexOfParam = parameter_counting.get(parameter_counting.size() - 1) - 1;

        final NextNode paramNode = getParamOfFunc(stack_findLastFunc.peek(), indexOfParam);
        if (!(paramNode.nodeBase instanceof _NextNode_DeclareVariable))
            throw new Ex_Signature(228, 228);
        // Кастим передаваемый параметр к типу который принимает функция
        right_FromStack = castToLeft(paramNode, right_FromStack);

        nodePushParam.setRight(right_FromStack);

//        if (right_FromStack.nodeBase instanceof _NextNode_Int) {
////            nodeBase.whatIsPush = right_FromStack;
//            nodePushParam.left = right_FromStack;
//        } else if (right_FromStack.nodeBase instanceof _NextNode_Double) {
////            nodeBase.whatIsPush = right_FromStack;
//            nodePushParam.left = right_FromStack;
//        } else if (right_FromStack.nodeBase instanceof _NextNode_ID) {
////            nodeBase.whatIsPush = right_FromStack;
//            nodePushParam.left = right_FromStack;
//        } else if (right_FromStack.nodeBase instanceof _NextNode_Call) {
////            nodeBase.whatIsPush = right_FromStack;
//            nodePushParam.left = right_FromStack;
//        }else {
//            throw new Exception("TreeNext triad_push_param");
//        }
        this.stackPushParam.add(nodePushParam);

        System.out.print("");
    }

    private NextNode getParamOfFunc(NextNode func, int indexParam) throws Exception {
        NextNode currentParamParent = func.left.right;
        for (int i = 0; i < indexParam; i++) {
            currentParamParent = currentParamParent.right;
        }
        return currentParamParent.left;
    }

    public void triad_remember_call(String lexemToStr) {
        this.stackPushParam.add(find_last);
        System.out.print("");
    }

    public void triad_call() throws Exception {
        NextNode funcNode = null;
        List<NextNode> tmp_list = new ArrayList<>();
        int index;
        // вытаскиваем из стека параметры который кладем в стек при трансляции, и вызываемую фуцнкцию
        for (index = this.stackPushParam.size() - 1; index >= 0; index--) {
            // если дошли до вызываемой функции
            if (this.stackPushParam.get(index).nodeBase instanceof _NextNode_Func) {
                funcNode = this.stackPushParam.get(index);
                break;
            } else
                tmp_list.add(this.stackPushParam.get(index));
        }
        // удаляем элементы из стека
        for (int i = this.stackPushParam.size() - 1; i >= index; i--)
            this.stackPushParam.remove(i);
        // ВЫЗОВ ФУНКЦИИ
        NextNode funcCallNode = new NextNode();
        _NextNode_Call nodebase = new _NextNode_Call(LLK.savePointCurrent);
        funcCallNode.nodeBase = nodebase;
        // какую функцию вызываем
        nodebase.func = funcNode;

        /// ПУШИМ ПАРАМЕТРЫ В СТЕК ДЛЯ ВЫЗОВА ФУНКЦИИ
        NextNode nextNode = new NextNode();
        nextNode.nodeBase = new _NextNode_Next(LLK.savePointCurrent);
        funcCallNode.setLeft(nextNode);

        NextNode last = nextNode;
        for (int i = tmp_list.size() - 1; i >= 0; i--) {
            NextNode tmp = new NextNode();
            tmp.nodeBase = new _NextNode_Next(LLK.savePointCurrent);
//
//            NextNode nodePushParam = new NextNode();
//            _NextNode_Push_Param push_param = new _NextNode_Push_Param();
//            nodePushParam.nodeBase = push_param;
            NextNode nodePushParam = tmp_list.get(i);
//            push_param.whatIsPush = this.stackPushParam.get(i);

            tmp.setLeft(nodePushParam);

            last.setRight(tmp);
            last = tmp;
        }

        this.stack.add(funcCallNode);

        final NextNode pop = stack_findLast.pop();
        System.out.print("");

    }

    // просто вызываем функцию, никуда ее не присваивая, надо ее достать из стека
    public void next_call() {
        NextNode fromStack = this.getFromStack(-1);

        NextNode nextNode = new NextNode();
        nextNode.nodeBase = new _NextNode_Next(LLK.savePointCurrent);

        current.setRight(nextNode);
        current = nextNode;

        nextNode.setLeft(fromStack);
    }

    private void clearCreateTriads(NextNode nextNode) {
        nextNode.isCreateTriad = false;
        if (nextNode.left != null)
            clearCreateTriads(nextNode.left);
        if (nextNode.right != null)
            clearCreateTriads(nextNode.right);
    }

    public String createTriads() throws Exception {


        clearCreateTriads(this.root);

        listTriads.clear();
        listTriads = root.createTriads_str(listTriads);


        final String collect = listTriads.stream()
                .map(nextNode_triad -> {
                    String operation = nextNode_triad.operation;
                    String second = nextNode_triad.second;
                    String first = nextNode_triad.first;
                    Integer size = nextNode_triad.index;

                    if (operation == null || operation.isEmpty())
                        operation = "";
                    else
                        operation = "    " + operation;
                    if (second == null || second.isEmpty())
                        second = "";
                    else
                        second = "    " + second;
                    if (first == null || first.isEmpty())
                        first = "";
                    else
                        first = "    " + first;
                    String str = (size) + ")" + operation + first + second;
                    return str;
                })
                .collect(Collectors.joining("\n"));

        return collect;
    }

    int isAllBranchReturnValue_count = 0;
    boolean isDraw = false;

    private boolean isAllBranchReturnValue(NextNode nextNode) throws Exception {

        isAllBranchReturnValue_count++;
        System.out.println(isAllBranchReturnValue_count);
        if (isAllBranchReturnValue_count == 16)
            System.out.println();
        if (isAllBranchReturnValue_count == 54) {
            System.out.println();
            //isDraw = true;
        }
        boolean flag = false;
        if (isDraw) draw(k, nextNode, "test");
        if (nextNode.nodeBase instanceof _NextNode_If) {
            final NextNode elseNode = nextNode.right;
            final NextNode true_side = elseNode.left;
            final NextNode false_side = elseNode.right;
            boolean true_side_flag = isAllBranchReturnValue(true_side);
            if (isDraw) draw(k, nextNode, "test");
            boolean false_side_flag;
            // Если часть else есть, то проверяем ее
            if (false_side.isHasChild()) {
                false_side_flag = isAllBranchReturnValue(false_side);
                if (isDraw) draw(k, nextNode, "test");
            } else {
                // иначе говориим что часть возвращает значение(  хотя ее как таковой и нет)
                false_side_flag = false;
            }
            return true_side_flag & false_side_flag;
        } else if (nextNode.nodeBase instanceof _NextNode_StartLevel) {
//            boolean left_side_flag = false;
            boolean right_side_flag = false;
//            if (nextNode.left != null)
//                left_side_flag = isAllBranchReturnValue(nextNode.left);
            if (nextNode.right != null)
                right_side_flag = isAllBranchReturnValue(nextNode.right);
            if (isDraw) draw(k, nextNode, "test");
            return right_side_flag;
        } else if (nextNode.nodeBase instanceof _NextNode_Return) {
            return true;
        } else {
            boolean left_side_flag = false;
            boolean right_side_flag = false;
            if (nextNode.left != null)
                left_side_flag = isAllBranchReturnValue(nextNode.left);
            if (isDraw) draw(k, nextNode, "test");

            if (left_side_flag == true && nextNode.right != null) {
//                NextNode kek = getNodeWithType(nextNode,new _NextNode_Return);
                throw new Ex_Uncreachable_code(k, nextNode);
            }

            if (nextNode.right != null)
                right_side_flag = isAllBranchReturnValue(nextNode.right);
            if (isDraw) draw(k, nextNode, "test");
            boolean result = true;
            if (nextNode.left == null && nextNode.right == null)
                result = false;
            if (nextNode.left == null && nextNode.right != null)
                result = right_side_flag;
            if (nextNode.left != null && nextNode.right == null)
                result = left_side_flag;
            if (nextNode.left != null && nextNode.right != null)
                result = left_side_flag || right_side_flag;

            return result;
        }
    }

//    private NextNode getNodeWithType(NextNode nextNode, _NextNodeBase nextNodeBase) {
////        if(nextNode instanceof nextNodeBase.getClass(). )
//        if( nextNode.right != null)
//            return getNodeWithType(nextNode.right, nextNodeBase);
//        if( nextNode.left != null)
//            return getNodeWithType(nextNode.left, nextNodeBase);
//    }

    private NextNode findStartParams_OfFunc(NextNode func) {
        return func.left;
    }

    private NextNode findStartBody_OfFunc(NextNode func) {
        NextNode tmp = func.left;
        do {
            tmp = tmp.right;
        } while (!(tmp.left.nodeBase instanceof _NextNode_StartLevel));
        return tmp.left;
    }

    public void optimization() throws Exception {

        boolean isDraw = true;

        {
            CalculateBeforeCompile calculateBeforeCompile = new CalculateBeforeCompile(this);
            calculateBeforeCompile.start();
        }
        if (isDraw) draw(root, current, "1_calculateBeforeCompile");
        {
            Agrigate agrigate = new Agrigate(this);
            agrigate.optimization_agrigate(this.root);
        }
        if (isDraw) draw(root, current, "2_agrigate");

        {
            CalculateBeforeCompile calculateBeforeCompile = new CalculateBeforeCompile(this);
            calculateBeforeCompile.start();
        }
        if (isDraw) draw(root, current, "3_calculateBeforeCompile");

        {
            Agrigate agrigate = new Agrigate(this);
            agrigate.optimization_agrigate(this.root);
        }
        draw(root, current, "4_agrigate");

        {
            Shift shift = new Shift(this);
            shift.start();
        }
        draw(root, current, "5_shift");


    }

    private void optimization_all_сommutativity(NextNode startNode) {

    }

    /**
     * Устанавливаем тип переменных, будь то глобальная, локальная, параметр функции
     */
    public void setGlobalLocalParamTypeToDeclareVariable() {
        this.setGlobalTypeToDeclareVariable_recursion(this.root);
        this.setLocalTypeToDeclareVariable_recursion(this.root, false);
        this.setParamTypeToDeclareVariable_recursion(this.root, false);
    }

    private void setParamTypeToDeclareVariable_recursion(NextNode nextNode, boolean isInFunc) {
        final NextNode left = nextNode.left;
        final NextNode right = nextNode.right;

        if (isInFunc) {
            if (left != null && left.nodeBase instanceof _NextNode_DeclareVariable) {
                ((_NextNode_DeclareVariable) left.nodeBase).isLocal = false;
                ((_NextNode_DeclareVariable) left.nodeBase).isGlobal = false;
                ((_NextNode_DeclareVariable) left.nodeBase).isParam = true;
            }
        }
        if (left != null) {
            if (left.nodeBase instanceof _NextNode_Func) {
                isInFunc = true;
                setParamTypeToDeclareVariable_recursion(left, isInFunc);
            } else if (left.nodeBase instanceof _NextNode_StartLevel) {
                return;
            } else if (left.nodeBase instanceof _NextNode_FuncEnd) {
                return;
            } else
                setParamTypeToDeclareVariable_recursion(left, isInFunc);
        }

        if (right != null)
            setParamTypeToDeclareVariable_recursion(right, isInFunc);
    }

    private void setLocalTypeToDeclareVariable_recursion(NextNode nextNode, Boolean isInFunc) {
        final NextNode left = nextNode.left;
        final NextNode right = nextNode.right;

        if (isInFunc) {
            if (left != null && left.nodeBase instanceof _NextNode_DeclareVariable) {
                ((_NextNode_DeclareVariable) left.nodeBase).isLocal = true;
                ((_NextNode_DeclareVariable) left.nodeBase).isGlobal = false;
                ((_NextNode_DeclareVariable) left.nodeBase).isParam = false;
            }
        }

        if (left != null) {
            if (left.nodeBase instanceof _NextNode_StartLevel) {
                isInFunc = true;
                setLocalTypeToDeclareVariable_recursion(left, isInFunc);
                isInFunc = false;
            } else
                setLocalTypeToDeclareVariable_recursion(left, isInFunc);
        }

        if (right != null)
            setLocalTypeToDeclareVariable_recursion(right, isInFunc);
    }

    private void setGlobalTypeToDeclareVariable_recursion(NextNode nextNode) {
        final NextNode left = nextNode.left;
        final NextNode right = nextNode.right;
        if (left != null && left.nodeBase instanceof _NextNode_DeclareVariable) {
            ((_NextNode_DeclareVariable) left.nodeBase).isGlobal = true;
            ((_NextNode_DeclareVariable) left.nodeBase).isLocal = false;
            ((_NextNode_DeclareVariable) left.nodeBase).isParam = false;
        }
        if (left != null && left.nodeBase instanceof _NextNode_Func) {
            if (((_NextNode_Func) left.nodeBase).lexem.equals("main"))
                return;
        }
        if (right != null) {
            this.setGlobalTypeToDeclareVariable_recursion(right);
        }

    }

}
