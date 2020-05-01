package main.Lab4.Optimizations;

import main.Lab2.LexTypeTERMINAL;
import main.Lab3.LLK;
import main.Lab4.TreeNext.*;
import main.Lab4.TreeNext.Const._NextNode_Double;
import main.Lab4.TreeNext.Const._NextNode_Int;
import main.Lab4.TreeNext.MathOperation.*;

import java.io.IOException;
import java.util.List;

public class Agrigate {

    TreeNext treeNext ;

    NextNode startNodeOptimization = null;


    public Agrigate(TreeNext treeNext) {
        this.treeNext = treeNext;
    }

    private NextNode optimization_agrigate_createStar(List<NextNode> a1) {
        NextNode startNextNode = new NextNode();
        NextNode last = startNextNode;
        final _NextNode_Star nextNode_star = new _NextNode_Star(LLK.savePointCurrent);
        nextNode_star.lexTypeTERMINAL = LexTypeTERMINAL._DOUBLE;
        last.nodeBase = nextNode_star;

        if (a1.size() <= 0)
            return null;
        last.left = a1.get(0);

        for (int i = 1; i < a1.size() - 1; i++) {
            NextNode tmpStartNextNode = new NextNode();
            final _NextNode_Star tmp_nextNode_star = new _NextNode_Star(LLK.savePointCurrent);
            // TODO тут не должен быть дабл
            tmp_nextNode_star.lexTypeTERMINAL = LexTypeTERMINAL._DOUBLE;
            tmpStartNextNode.nodeBase = tmp_nextNode_star;
            tmpStartNextNode.left = a1.get(i);
            last.right = tmpStartNextNode;
            last = tmpStartNextNode;
        }
        last.right = a1.get(a1.size() - 1);
        return startNextNode;
    }

    // Правильно расставляем типы вершин. Добавляем нужные ведения типов.
    private void optimization_agrigate_setTypes(NextNode node) throws IOException {
        final NextNode left = node.left;
        final NextNode right = node.right;

        if (left != null)
            optimization_agrigate_setTypes(left);

        if (right != null)
            optimization_agrigate_setTypes(right);

        if (node.isMathOperation()) {
            try {
                treeNext.draw(treeNext.root, node, "kek");
                Interface_MathOperation nodeBase_interface_mathOperation = (Interface_MathOperation) node.nodeBase;
                Interface_LexType left_lexTypeNodeBase = (Interface_LexType) left.nodeBase;
                Interface_LexType right_lexTypeNodeBase = (Interface_LexType) right.nodeBase;



                LexTypeTERMINAL left_Type = LexTypeTERMINAL._INT;
                LexTypeTERMINAL right_Type = LexTypeTERMINAL._INT;
                if (left_lexTypeNodeBase.getType() == LexTypeTERMINAL._ID) {
                    // сначала кастим к ID, потом поле еще кастим к декларейшен
                    left_Type = ((_NextNode_DeclareVariable) (((_NextNode_ID) left.nodeBase).nextNode.nodeBase)).lexTypeTERMINAL;
                } else if (left_lexTypeNodeBase.getType() == LexTypeTERMINAL._DOUBLE) {
                    left_Type = LexTypeTERMINAL._DOUBLE;
                }

                if (right_lexTypeNodeBase.getType() == LexTypeTERMINAL._ID) {
                    // сначала кастим к ID, потом поле еще кастим к декларейшен
                    right_Type = ((_NextNode_DeclareVariable) (((_NextNode_ID) right.nodeBase).nextNode.nodeBase)).lexTypeTERMINAL;
                } else if (right_lexTypeNodeBase.getType() == LexTypeTERMINAL._DOUBLE) {
                    right_Type = LexTypeTERMINAL._DOUBLE;
                }

                // Расставляем типы
                // НО, есть исключение. Если деление, то в любом случае результат будет дабл
                if (node.nodeBase instanceof _NextNode_Div) {
                    nodeBase_interface_mathOperation.setType(LexTypeTERMINAL._DOUBLE);
                } else {
                    // Если правый или левый операнд дабл, то результат тоже дабл
                    if (left_Type == LexTypeTERMINAL._DOUBLE || right_Type == LexTypeTERMINAL._DOUBLE)
                        nodeBase_interface_mathOperation.setType(LexTypeTERMINAL._DOUBLE);
                    else     // иначе инт
                        nodeBase_interface_mathOperation.setType(LexTypeTERMINAL._INT);
                }

                // Добавляем приведение типов если нужно
                if(nodeBase_interface_mathOperation.getType() != left_Type){
                    NextNode castNode = new NextNode();
                    _NextNode_Cast cast = new _NextNode_Cast(nodeBase_interface_mathOperation.getType(), LLK.savePointCurrent);
                    castNode.nodeBase = cast;
                    castNode.setRight(left);
                    node.setLeft(castNode);
                }
                // Добавляем приведение типов если нужно
                if(nodeBase_interface_mathOperation.getType() != right_Type){
                    NextNode castNode = new NextNode();
                    _NextNode_Cast cast = new _NextNode_Cast(nodeBase_interface_mathOperation.getType(), LLK.savePointCurrent);
                    castNode.nodeBase = cast;
                    castNode.setRight(right);
                    node.setRight(castNode);
                }

            } catch (ClassCastException e) {
                throw e;
            }

        } else {

        }
    }

    // удаляем приведения типов
    private void optimization_agrigate_removeCast(NextNode nextNode) throws IOException {
        //this.draw(root, nextNode, "kek");
        if (nextNode.left != null) {
            //this.draw(root, nextNode, "kek");
            final NextNode left = nextNode.left;
            if (left.nodeBase instanceof _NextNode_Cast) {
                nextNode.setLeft(left.right);
                //this.draw(root, nextNode, "kek");
                System.out.print("");
            }
            optimization_agrigate_removeCast(nextNode.left);
        }
        if (nextNode.right != null) {
            //this.draw(root, nextNode, "kek");
            final NextNode right = nextNode.right;
            if (right.nodeBase instanceof _NextNode_Cast) {
                nextNode.setRight(right.right);
                //this.draw(root, nextNode, "kek");
                System.out.print("");
            }
            optimization_agrigate_removeCast(nextNode.right);
        }
        //this.draw(root, nextNode, "kek");
//        if (nextNode.nodeBase instanceof _NextNode_Cast) {
//            NextNode cast = nextNode;
//            final NextNode child = cast.right;
//            // если приведение типов это правый потомок родителя
//            if (cast.parent.right == cast) {
//                cast.parent.setRight(child);
//            }
//            // если приведение типов это левый потомок родителя
//            if (cast.parent.left == cast) {
//                cast.parent.setLeft(child);
//                cast.parent = null;
//                cast.left = null;
//                cast.right = null;
//            }
//            this.draw(root, current, "kek");
//        }
    }

    public NextNode optimization_agrigate(NextNode startNode) throws Exception {
        //this.draw(startNode, startNode, "test");
        if (startNode.isStar() || startNode.isDiv()) {
            startNodeOptimization = startNode;
            boolean isCanOptimize = optimization_agrigate_do_it(startNode);

            final List<NextNode> a1 = startNode.get_A1();
            NextNode firstStar = optimization_agrigate_createStar(a1);

            final List<NextNode> a2 = startNode.get_A2();
            NextNode secondStar = optimization_agrigate_createStar(a2);

            if (a1.size() < 2 || a2.size() < 2)
                return null;

            NextNode divNextNode = new NextNode();
            final _NextNode_Div nextNode_div = new _NextNode_Div(LLK.savePointCurrent);
            nextNode_div.lexTypeTERMINAL = LexTypeTERMINAL._DOUBLE;
            divNextNode.nodeBase = nextNode_div;

            divNextNode.left = firstStar;
            divNextNode.right = secondStar;

            if (startNode == startNode.parent.right) {
                startNode.parent.setRight(divNextNode);
                treeNext.draw(treeNext.root, divNextNode, "kek");
            }
            if (startNode == startNode.parent.left) {
                startNode.parent.setLeft(divNextNode);
                treeNext.draw(treeNext.root, divNextNode, "kek");
            }

            // теперь у нас уже новая структура выражения, нужно правильно расставить типы и привидения типов
            optimization_agrigate_removeCast(divNextNode);
            optimization_agrigate_setTypes(divNextNode);

//            return divNextNode;
        } else {
            if (startNode.left != null) {
                optimization_agrigate(startNode.left);
            }
            if (startNode.right != null) {
                optimization_agrigate(startNode.right);
            }
        }
        return null;
    }

    int ggg = 0;

    boolean flag_draw_optimization_agrigate_do_it = false;

    private boolean optimization_agrigate_do_it(NextNode startNode) throws Exception {
        if (flag_draw_optimization_agrigate_do_it) treeNext.draw(startNodeOptimization, startNode, "test");
        System.out.println("ggg " + ++ggg);

        if (startNode.left != null)
            optimization_agrigate_do_it(startNode.left);
        if (flag_draw_optimization_agrigate_do_it) treeNext.draw(startNodeOptimization, startNode, "test");
        System.out.println("ggg " + ++ggg);

        if (startNode.right != null)
            optimization_agrigate_do_it(startNode.right);
        if (flag_draw_optimization_agrigate_do_it) treeNext.draw(startNodeOptimization, startNode, "test");
        System.out.println("ggg " + ++ggg);



        if (startNode.nodeBase instanceof _NextNode_Div) {
            final _NextNode_Div nodeBase = (_NextNode_Div) startNode.nodeBase;
            // левый потомок
            {
                if (isOperand(startNode.left)) {
                    nodeBase.A_1.add(startNode.left);
                } else if (startNode.left.isDiv() || startNode.left.isStar()) {
                    final List<NextNode> left_A_1 = startNode.left.get_A1();
                    final List<NextNode> left_A_2 = startNode.left.get_A2();
                    // у левого потомка берем А1 и кладем в А1 ( тут ничего местами не меняем )
                    nodeBase.A_1.addAll(left_A_1);
                    nodeBase.A_2.addAll(left_A_2);
                } else {
                    final List<NextNode> a_1 = nodeBase.getA_1();
                    a_1.add(startNode.left);
                }
            }
            // правый потомок
            {
                if (isOperand(startNode.right)) {
                    nodeBase.A_2.add(startNode.right);
                } else if (startNode.right.isDiv() || startNode.right.isStar()) {
                    final List<NextNode> right_A_1 = startNode.right.get_A1();
                    final List<NextNode> right_A_2 = startNode.right.get_A2();
                    // у левого потомка берем А1 и кладем в А2 ( МЕНЯЕМ МЕСТАМИ А1 А2 )
                    nodeBase.A_1.addAll(right_A_2);
                    nodeBase.A_2.addAll(right_A_1);
                } else {
                    // + -
                    final List<NextNode> a_2 = nodeBase.getA_2();
                    a_2.add(startNode.left);
                }
            }
        }
        // ******
        else if (startNode.nodeBase instanceof _NextNode_Star) {
            final _NextNode_Star nodeBase = (_NextNode_Star) startNode.nodeBase;
            // левый потомок
            {
                if (isOperand(startNode.left)) {
                    nodeBase.A_1.add(startNode.left);
                } else if (startNode.left.isDiv() || startNode.left.isStar()) {
                    final List<NextNode> left_A_1 = startNode.left.get_A1();
                    final List<NextNode> left_A_2 = startNode.left.get_A2();
                    // у левого потомка берем А1 и кладем в А1 ( тут ничего местами не меняем )
                    nodeBase.A_1.addAll(left_A_1);
                    nodeBase.A_2.addAll(left_A_2);
                } else {
                    // тут всякий треш типа вызова функции плюс минус
                    final List<NextNode> a_1 = nodeBase.getA_1();
                    a_1.add(startNode.left);
                }
            }
            // правый потомок
            {
                if (isOperand(startNode.right)) {
                    nodeBase.A_1.add(startNode.right);
                } else if (startNode.right.isDiv() || startNode.right.isStar()) {
                    final List<NextNode> right_A_1 = startNode.right.get_A1();
                    final List<NextNode> right_A_2 = startNode.right.get_A2();
                    // у левого потомка берем А1 и кладем в А1 ( тут ничего местами не меняем )
                    nodeBase.A_1.addAll(right_A_1);
                    nodeBase.A_2.addAll(right_A_2);
                } else {
                    final List<NextNode> a_1 = nodeBase.getA_1();
                    a_1.add(startNode.right);
                }
            }
        } else if (isOperand(startNode)) {
            System.out.println();
        }
        // все остальные операции, т.е. + - и другие)00
        else if (isOperand(startNode)) {
            startNode.get_A1();
            // тут надо пробовать перестроить дерево
        }


//        if(startNode.nodeBase instanceof _NextNode_Star){
//            final _NextNode_Star nodeBase = (_NextNode_Star) startNode.nodeBase;
//        }
//        if (startNode.left != null) {
//
//            if (startNode.left.nodeBase instanceof _NextNode_Star  ){
//
//            }else if (startNode.left.nodeBase instanceof _NextNode_Div ){
//
//            }else if(isOperand(startNode.left)){
//                // Если слева операнд
//                if( startNode.nodeBase instanceof _NextNode_Div){
//                    // Если сейчас операция деление
//                    final _NextNode_Div nodeBase = (_NextNode_Div) startNode.nodeBase;
//                    // делимое влево, делитель вправо
//                    nodeBase.A_1.add(startNode.left);
//                    nodeBase.A_2.add(startNode.right);
//                }
//                else if( startNode.nodeBase instanceof _NextNode_Star){
//                    // Если сейчас операция умножение
//                    final _NextNode_Star nodeBase = (_NextNode_Star) startNode.nodeBase;
//                    // оба операнда в А1
//                    nodeBase.A_1.add(startNode.left);
//                    nodeBase.A_1.add(startNode.left);
//                }
//            }
//        }
//        if (startNode.nodeBase instanceof _NextNode_Star) {
//            boolean left = optimization_agrigate_do_it(startNode.left);
//            boolean right = optimization_agrigate_do_it(startNode.right);
//            return left & right;
//        }
//        if (startNode.nodeBase instanceof _NextNode_Div) {
//            boolean left = optimization_agrigate_do_it(startNode.left);
//            boolean right = optimization_agrigate_do_it(startNode.right);
//            return left & right;
//        }
//        if (isOperand(startNode))
//            return true;

        return false;
    }

    private boolean isMathOperation(NextNode node) {
        if (node.nodeBase instanceof _NextNode_Plus ||
                node.nodeBase instanceof _NextNode_Minus ||
                node.nodeBase instanceof _NextNode_Star ||
                node.nodeBase instanceof _NextNode_Div)
            return true;
        return false;
    }

    private boolean isOperand(NextNode node) {
        if (node.nodeBase instanceof _NextNode_ID ||
                node.nodeBase instanceof _NextNode_Int ||
                node.nodeBase instanceof _NextNode_Double ||
                node.nodeBase instanceof _NextNode_Call ||
                node.nodeBase instanceof _NextNode_Cast
        )
            return true;
        return false;
    }
}
