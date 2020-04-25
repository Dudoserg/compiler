package main.Lab4.TreeNext;

import main.Lab2.LexTypeTERMINAL;
import main.Lab3.Semantic;
import main.Lab3.exceptions.Ex_Dublicate;
import main.Lab3.exceptions.Ex_NotFound;
import main.Lab4.TreeNext.Relations.*;
import main.Lab4.Triads;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TreeNext {
    Semantic semantic;
    Triads triads;
    boolean devMode;
    private NextNode savedVariable;
    private NextNode find_last;
    private NextNode node_callFunc;

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


    public void startFunc(String lexemToStr) {
        // новоя вершина - next
        NextNode nextVertex = new NextNode();
        nextVertex.nodeBase = new _NextNode_Next();

        // вершина - функция
        NextNode funcVertex = new NextNode();
        funcVertex.nodeBase = new _NextNode_Func(lexemToStr, dataType);
        k = funcVertex;
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
        id.nodeBase =
                new _NextNode_ID(dataType, lexem, declareVariable);
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
        node_callFunc = this.find_last;
    }

    public void start_parameter_counting() {
    }

    public void end_parameter_counting() {
    }

    public void plus_parameter_counting() {
    }

    public void match() {
    }

    public void matchLeft() {
    }

    public void checkDubl(String lexemToStr) {
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

    public void plusParam() {
    }

    public void newBlack() throws IOException {
        // next вправо
        NextNode nextRight = new NextNode();
        nextRight.nodeBase = new _NextNode_Next();
        // next влево
        NextNode nextLeft = new NextNode();
        nextLeft.nodeBase = new _NextNode_Next();

        this.current.setRight(nextRight);
        nextRight.setLeft(nextLeft);

        this.current = nextLeft;
//        this.draw(current);
        System.out.print("");
    }

    public void endLevel() {
    }

    public void startLevel() {
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

    public void endFunc() {
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
        for(index = this.stackPushParam.size() - 1 ; index>= 0; index--){
            // если дошли до вызываемой функции
            if(this.stackPushParam.get(index).nodeBase instanceof _NextNode_Func){
                funcNode = this.stackPushParam.get(index);
                break;
            }else
                tmp_list.add(this.stackPushParam.get(index));
        }
        // удаляем элементы из стека
        for(int i = this.stackPushParam.size() - 1; i>= index; i-- )
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
}
