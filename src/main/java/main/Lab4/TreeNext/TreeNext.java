package main.Lab4.TreeNext;

import main.Lab2.LexTypeTERMINAL;
import main.Lab3.Semantic;
import main.Lab3.exceptions.Ex_Dublicate;
import main.Lab3.exceptions.Ex_Dublicate_Func;
import main.Lab3.exceptions.Ex_NotFound;
import main.Lab3.exceptions.Ex_Signature;
import main.Lab4.TreeNext.Relations.*;
import main.Lab4.Triads;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreeNext {
    Semantic semantic;
    Triads triads;
    boolean devMode;
    private NextNode savedVariable;
    private NextNode find_last;
    private List<NextNode> stack_node_callFunc = new ArrayList<>(0);

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
    NextNode k;

    List<NextNode> stack = new ArrayList<>();


    public void startFunc(String lexemToStr) throws Exception {
        // новоя вершина - next
        NextNode nextVertex = new NextNode();
        nextVertex.nodeBase = new _NextNode_Next();


        // вершина - функция
        NextNode funcVertex = new NextNode();
        _NextNode_Func funcNodeBase = new _NextNode_Func(lexemToStr, dataType);
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
        firstNextVertex.nodeBase = new _NextNode_Next();

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

    public void generAssign() throws IOException {
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

    public void generMinus() throws Exception {
        NextNode minusVertex = new NextNode();
        final _NextNode_Minus nodeBase = new _NextNode_Minus();
        minusVertex.nodeBase = nodeBase;

        final NextNode left_fromStack = getFromStack(-2);
        minusVertex.setLeft(left_fromStack);
        LexTypeTERMINAL left_lexTypeTERMINAL = getLexTypeTerminal_inMathOper(left_fromStack);

        final NextNode right_fromStack = getFromStack(-1);
        minusVertex.setRight(right_fromStack);
        LexTypeTERMINAL right_lexTypeTERMINAL = getLexTypeTerminal_inMathOper(right_fromStack);

        nodeBase.lexTypeTERMINAL = calculateResultLexTypeTerminal(left_lexTypeTERMINAL, right_lexTypeTERMINAL);

        this.stack.add(minusVertex);
    }


    public void generDiv() throws Exception {
        NextNode devVertex = new NextNode();
        final _NextNode_Div nodeBase = new _NextNode_Div();
        devVertex.nodeBase = nodeBase;

        final NextNode left_fromStack = getFromStack(-2);
        devVertex.setLeft(left_fromStack);
        LexTypeTERMINAL left_lexTypeTERMINAL = getLexTypeTerminal_inMathOper(left_fromStack);

        final NextNode right_fromStack = getFromStack(-1);
        devVertex.setRight(right_fromStack);
        LexTypeTERMINAL right_lexTypeTERMINAL = getLexTypeTerminal_inMathOper(right_fromStack);

        nodeBase.lexTypeTERMINAL = calculateResultLexTypeTerminal(left_lexTypeTERMINAL, right_lexTypeTERMINAL);

        this.stack.add(devVertex);
    }

    public void generStar() throws Exception {
        NextNode starVertex = new NextNode();
        final _NextNode_Star nodeBase = new _NextNode_Star();
        starVertex.nodeBase = nodeBase;

        final NextNode left_fromStack = getFromStack(-2);
        starVertex.setLeft(left_fromStack);
        LexTypeTERMINAL left_lexTypeTERMINAL = getLexTypeTerminal_inMathOper(left_fromStack);

        final NextNode right_fromStack = getFromStack(-1);
        starVertex.setRight(right_fromStack);
        LexTypeTERMINAL right_lexTypeTERMINAL = getLexTypeTerminal_inMathOper(right_fromStack);

        nodeBase.lexTypeTERMINAL = calculateResultLexTypeTerminal(left_lexTypeTERMINAL, right_lexTypeTERMINAL);

        this.stack.add(starVertex);
    }

    public void generPlus() throws Exception {
        NextNode plusVertex = new NextNode();
        final _NextNode_Plus nodeBase = new _NextNode_Plus();
        plusVertex.nodeBase = nodeBase;

        final NextNode left_fromStack = getFromStack(-2);
        plusVertex.setLeft(left_fromStack);
        LexTypeTERMINAL left_lexTypeTERMINAL = getLexTypeTerminal_inMathOper(left_fromStack);

        final NextNode right_fromStack = getFromStack(-1);
        plusVertex.setRight(right_fromStack);
        LexTypeTERMINAL right_lexTypeTERMINAL = getLexTypeTerminal_inMathOper(right_fromStack);

        nodeBase.lexTypeTERMINAL = calculateResultLexTypeTerminal(left_lexTypeTERMINAL, right_lexTypeTERMINAL);

        this.stack.add(plusVertex);
    }

    // вычисляем результат операции над двумя операндами определенных типов
    private LexTypeTERMINAL calculateResultLexTypeTerminal(LexTypeTERMINAL left_lexTypeTERMINAL, LexTypeTERMINAL right_lexTypeTERMINAL)
            throws Exception {
        LexTypeTERMINAL result_lexTypeTERMINAL = null;// если один из оперант дабл, то результат дабл
        if (left_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE && right_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE) {
            // результат дабл
            result_lexTypeTERMINAL = LexTypeTERMINAL._DOUBLE;
        }
        if ((left_lexTypeTERMINAL == LexTypeTERMINAL._INT || left_lexTypeTERMINAL == LexTypeTERMINAL._TYPE_INT_10 ) &&
                right_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE) {
            // результат дабл
            result_lexTypeTERMINAL = LexTypeTERMINAL._DOUBLE;
        } else if (left_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE &&
                (right_lexTypeTERMINAL == LexTypeTERMINAL._INT || right_lexTypeTERMINAL == LexTypeTERMINAL._TYPE_INT_10 )) {
            // результат дабл
            result_lexTypeTERMINAL = LexTypeTERMINAL._DOUBLE;
        } else if ((left_lexTypeTERMINAL == LexTypeTERMINAL._INT || left_lexTypeTERMINAL == LexTypeTERMINAL._TYPE_INT_10 ) &&
                (right_lexTypeTERMINAL == LexTypeTERMINAL._INT || right_lexTypeTERMINAL == LexTypeTERMINAL._TYPE_INT_10 )) {
            // результат дабл
            result_lexTypeTERMINAL = LexTypeTERMINAL._INT;
        } else {
            throw new Exception("12820463820742546hj");
        }
        return result_lexTypeTERMINAL;
    }

    // получаем тип операнды из вершины
    private LexTypeTERMINAL getLexTypeTerminal_inMathOper(NextNode fromStack) throws Exception {
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
        }else if (fromStack.nodeBase instanceof _NextNode_Func) {
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
        } else {
            throw new Exception("SAhjf78013031z';sw");
        }
        return lexTypeTERMINAL;
    }



    public void triad_push(LexTypeTERMINAL next, String lexem) throws Exception {
        if (next == LexTypeTERMINAL._INT || next == LexTypeTERMINAL._TYPE_INT_8 ||
                next == LexTypeTERMINAL._TYPE_INT_10 || next == LexTypeTERMINAL._TYPE_INT_16) {
            NextNode intVertex = new NextNode();
            intVertex.nodeBase = new _NextNode_Int(next, lexem);
            this.stack.add(intVertex);
        }

        if (next == LexTypeTERMINAL._DOUBLE) {
            NextNode intVertex = new NextNode();
            intVertex.nodeBase = new _NextNode_Double(next, lexem);
            this.stack.add(intVertex);
        }

        if (next == LexTypeTERMINAL._TYPE_CHAR) {
            throw new Exception("class TreeNext, method push, TYPE_CHAR");
        }

        // переменная, ищем ее тип
        if (next == LexTypeTERMINAL._ID) {
            NextNode intVertex = new NextNode();
            intVertex.nodeBase = new _NextNode_ID(next, lexem, this.find_last);
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

    public void draw(NextNode current) throws Exception {
        // если рисуем чаще 2 секунд, то не рисуем)
//        if( System.currentTimeMillis() - lastDrawTime < 1000)
//            return;
        // составляем файл
        try (FileWriter writer = new FileWriter("result_tree_next.gv", false)) {
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
            Runtime.getRuntime().exec("dot result_tree_next.gv -Tpng -o result_tree_next.jpg");

        } else {
            Runtime.getRuntime().exec("cmd /c dot result_tree_next.gv -Tpng -o result_tree_next.jpg");
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
        _NextNode_DeclareVariable nodeBase = new _NextNode_DeclareVariable();
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
        nodeNext.nodeBase = new _NextNode_Next();
        nodeNext.setLeft(declareVariable);

        current.setRight(nodeNext);
        current = nodeNext;

        System.out.print("");

        // кладем переменную в стек;
        NextNode id = new NextNode();
        id.nodeBase = new _NextNode_ID(dataType, lexem, declareVariable);

        if (dataType == LexTypeTERMINAL._INT ){
        } else  if (dataType == LexTypeTERMINAL._DOUBLE ){
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

    public void plus_parameter_counting() {
        parameter_counting.set(
                parameter_counting.size() - 1,
                parameter_counting.get(parameter_counting.size() - 1) + 1
        );
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
                        find_last = node;
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
                    return left;
                }
            } else if ((left.nodeBase instanceof _NextNode_Func)) {
                _NextNode_Func tmp = (_NextNode_Func) left.nodeBase;
                if (tmp.lexem.equals(lexemToStr)) {
                    find_last = left;
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
        rightNextNode.nodeBase = new _NextNode_Next();
        current.setRight(rightNextNode);
        current = rightNextNode;
        this.stack_startLevel.add(rightNextNode);

        NextNode leftNextNode = new NextNode();
        leftNextNode.nodeBase = new _NextNode_StartLevel();
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
        nextNode.nodeBase = new _NextNode_Next();

        NextNode nodeIf = new NextNode();
        nodeIf.nodeBase = new _NextNode_If();

        nextNode.setLeft(nodeIf);
        listIf.add(nodeIf);

        NextNode nodeElse = new NextNode();
        nodeElse.nodeBase = new _NextNode_Else();

        nodeIf.setRight(nodeElse);


        this.current.setRight(nextNode);
        this.current = nextNode;

//        NextNode nodeIF = listIf.get(listIf.size() - 1);
        nodeIf.left = this.getFromStack(-1);


        NextNode elseLeftNext = new NextNode();
        elseLeftNext.nodeBase = new _NextNode_Next();

        nodeElse.setLeft(elseLeftNext);
        this.current = elseLeftNext;

//        this.draw(this.current);
        System.out.print("");
    }


    public void triad_gener_goto() throws IOException {
        NextNode nodeIF = listIf.get(listIf.size() - 1);
        NextNode nodeElse = nodeIF.right;

        NextNode rightElseNext = new NextNode();
        rightElseNext.nodeBase = new _NextNode_Next();

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
        greatVertex.nodeBase = new _NextNode_Equal();
        greatVertex.setLeft(this.getFromStack(-2));
        greatVertex.setRight(this.getFromStack(-1));
        this.stack.add(greatVertex);
    }

    public void gener_not_equal() {
        NextNode greatVertex = new NextNode();
        greatVertex.nodeBase = new _NextNode_Not_Equal();
        greatVertex.setLeft(this.getFromStack(-2));
        greatVertex.setRight(this.getFromStack(-1));
        this.stack.add(greatVertex);
    }

    public void gener_great() {
        NextNode greatVertex = new NextNode();
        greatVertex.nodeBase = new _NextNode_Great();
        greatVertex.setLeft(this.getFromStack(-2));
        greatVertex.setRight(this.getFromStack(-1));
        this.stack.add(greatVertex);
    }

    public void gener_great_equal() {
        NextNode greatVertex = new NextNode();
        greatVertex.nodeBase = new _NextNode_Great_Equal();
        greatVertex.setLeft(this.getFromStack(-2));
        greatVertex.setRight(this.getFromStack(-1));
        this.stack.add(greatVertex);
    }

    public void gener_less() {
        NextNode greatVertex = new NextNode();
        greatVertex.nodeBase = new _NextNode_Less();
        greatVertex.setLeft(this.getFromStack(-2));
        greatVertex.setRight(this.getFromStack(-1));
        this.stack.add(greatVertex);
    }

    public void gener_less_equal() {
        NextNode greatVertex = new NextNode();
        greatVertex.nodeBase = new _NextNode_Less_Equal();
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
        nextNode.nodeBase = new _NextNode_Next();

        NextNode returnNode = new NextNode();
        returnNode.nodeBase = new _NextNode_Return();

        returnNode.setRight(this.getFromStack(-1));

        nextNode.setLeft(returnNode);

        this.current.setRight(nextNode);
        this.current = nextNode;
//        this.draw(this.current);
        System.out.print("");
    }

    public void endFunc() throws Exception {
        NextNode nextNode = new NextNode();
        nextNode.nodeBase = new _NextNode_Next();
        this.current.setRight(nextNode);

        NextNode endNode = new NextNode();
        endNode.nodeBase = new _NextNode_FuncEnd(k);
        nextNode.setLeft(endNode);

        this.current = k.parent;
    }

    List<NextNode> stackPushParam = new ArrayList<>();

    public void triad_push_param() throws Exception {
        NextNode nodePushParam = new NextNode();
        _NextNode_Push_Param nodeBase = new _NextNode_Push_Param();
        nodePushParam.nodeBase = nodeBase;

        NextNode fromStack = this.getFromStack(-1);
        nodePushParam.left = fromStack;

//        if (fromStack.nodeBase instanceof _NextNode_Int) {
////            nodeBase.whatIsPush = fromStack;
//            nodePushParam.left = fromStack;
//        } else if (fromStack.nodeBase instanceof _NextNode_Double) {
////            nodeBase.whatIsPush = fromStack;
//            nodePushParam.left = fromStack;
//        } else if (fromStack.nodeBase instanceof _NextNode_ID) {
////            nodeBase.whatIsPush = fromStack;
//            nodePushParam.left = fromStack;
//        } else if (fromStack.nodeBase instanceof _NextNode_Call) {
////            nodeBase.whatIsPush = fromStack;
//            nodePushParam.left = fromStack;
//        }else {
//            throw new Exception("TreeNext triad_push_param");
//        }
        this.stackPushParam.add(nodePushParam);


        System.out.print("");
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
        _NextNode_Call nodebase = new _NextNode_Call();
        funcCallNode.nodeBase = nodebase;
        // какую функцию вызываем
        nodebase.func = funcNode;

        /// ПУШИМ ПАРАМЕТРЫ В СТЕК ДЛЯ ВЫЗОВА ФУНКЦИИ
        NextNode nextNode = new NextNode();
        nextNode.nodeBase = new _NextNode_Next();
        funcCallNode.setLeft(nextNode);

        NextNode last = nextNode;
        for (int i = tmp_list.size() - 1; i >= 0; i--) {
            NextNode tmp = new NextNode();
            tmp.nodeBase = new _NextNode_Next();
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
        System.out.print("");

    }

    // просто вызываем функцию, никуда ее не присваивая, надо ее достать из стека
    public void next_call() {
        NextNode fromStack = this.getFromStack(-1);

        NextNode nextNode = new NextNode();
        nextNode.nodeBase = new _NextNode_Next();

        current.setRight(nextNode);
        current = nextNode;

        nextNode.setLeft(fromStack);
    }


    public String createTriads() throws Exception {
        List<String> listTriads = new ArrayList<>();

        listTriads = root.createTriads(listTriads);

        final String collect = listTriads.stream().collect(Collectors.joining("\n"));

        return collect;
    }
}
