package main.Lab4.Optimizations;

import main.Lab2.LexTypeTERMINAL;
import main.Lab3.LLK;
import main.Lab4.TreeNext.Const.Interface_Const;
import main.Lab4.TreeNext.Const._NextNode_Double;
import main.Lab4.TreeNext.Const._NextNode_Int;
import main.Lab4.TreeNext.MathOperation.*;
import main.Lab4.TreeNext.*;

public class CalculateBeforeCompile {

    TreeNext treeNext;


    public CalculateBeforeCompile(TreeNext treeNext) {
        this.treeNext = treeNext;
    }

    public void start() throws Exception {
        DFS_mathOperation(treeNext.root);
    }

    private void DFS_mathOperation(NextNode node) throws Exception {
        // TODO тут я просто привожу к даблу, хотя чет не оч)0
        if (node.left != null)
            DFS_mathOperation(node.left);
        if (node.right != null)
            DFS_mathOperation(node.right);

        // если это математическая операции
        if (node.isMathOperation() &&
                (node.left.isConstant() || (node.left.isCast() && node.left.right.isConstant())) &&
                (node.right.isConstant() || (node.right.isCast() && node.right.right.isConstant()))) {
            Interface_MathOperation nodeMathOperation = (Interface_MathOperation) node.nodeBase;
            NextNode left = node.left;
            if (!left.isConstant()) {
                // значит тут приведение типов
                if (left.left == null && left.right != null)
                    left = left.right;
            }
            NextNode right = node.right;
            if (!right.isConstant()) {
                // значит тут приведение типов
                if (right.left == null && right.right != null)
                    right = right.right;
            }

            NextNode newNode = new NextNode();
            Interface_Const newNodeBase = null;

            final LexTypeTERMINAL nodeType = nodeMathOperation.getType();

            // создаем NodeBase определенного типа
            if (nodeType == LexTypeTERMINAL._DOUBLE) {
                newNodeBase = new _NextNode_Double(nodeType, null, LLK.savePointCurrent);
                newNode.nodeBase = (_NextNodeBase) newNodeBase;
            } else if (nodeType == LexTypeTERMINAL._INT) {
                newNodeBase = new _NextNode_Int(nodeType, null, LLK.savePointCurrent);
                newNode.nodeBase = (_NextNodeBase) newNodeBase;
            } else
                throw new Exception("asd12877f7bhhb2");

            String resultStr = null;

            if (left.nodeBase instanceof _NextNode_Int && right.nodeBase instanceof _NextNode_Int) {
                final String lexemLeft = ((_NextNode_Int) left.nodeBase).lexem;
                final String lexemRight = ((_NextNode_Int) right.nodeBase).lexem;
                resultStr = nodeMathOperation.calculate(Integer.valueOf(lexemLeft), Integer.valueOf(lexemRight));
                newNodeBase.setLexem_(resultStr);

            } else if (left.nodeBase instanceof _NextNode_Int && right.nodeBase instanceof _NextNode_Double) {
                final String lexemLeft = ((_NextNode_Int) left.nodeBase).lexem;
                final String lexemRight = ((_NextNode_Double) right.nodeBase).lexem;
                resultStr = nodeMathOperation.calculate(Integer.valueOf(lexemLeft), Double.valueOf(lexemRight));
                newNodeBase.setLexem_(resultStr);

            } else if (left.nodeBase instanceof _NextNode_Double && right.nodeBase instanceof _NextNode_Int) {
                final String lexemLeft = ((_NextNode_Double) left.nodeBase).lexem;
                final String lexemRight = ((_NextNode_Int) right.nodeBase).lexem;
                resultStr = nodeMathOperation.calculate(Double.valueOf(lexemLeft), Integer.valueOf(lexemRight));
                newNodeBase.setLexem_(resultStr);

            } else if (left.nodeBase instanceof _NextNode_Double && right.nodeBase instanceof _NextNode_Double) {
                final String lexemLeft = ((_NextNode_Double) left.nodeBase).lexem;
                final String lexemRight = ((_NextNode_Double) right.nodeBase).lexem;
                resultStr = nodeMathOperation.calculate(Double.valueOf(lexemLeft), Double.valueOf(lexemRight));
                newNodeBase.setLexem_(resultStr);

            } else throw new Exception("CalculateBeforeCompile Asd810");

            // TODO заменить
            if (node.parent == null)
                System.out.println();
            if (node.parent.left == node) {
                node.parent.setLeft(newNode);
                //treeNext.draw(treeNext.root, newNode, "kek");
            } else if (node.parent.right == node) {
                node.parent.setRight(newNode);
                //treeNext.draw(treeNext.root, newNode, "kek");
            }


        }



        if (
                node.nodeBase instanceof _NextNode_Assign &&
                        ((node.right.isCast() && node.right.right.isConstant()) ||
                                node.right.isConstant())
        ) {
            NextNode right = node.right;
            NextNode left = node.left;
            NextNode value = null;
            // Если приведение типа, и дальше константа
            if (right.isCast() && right.right.isConstant()) {
                value = right.right;
            }
            // если просто константа
            if (right.isConstant()) {
                value = right;
            }
            NextNode left_declNode = null;
            _NextNode_DeclareVariable left_declNode_nodeBase = null;
            LexTypeTERMINAL left_type = null;
            if (left.nodeBase instanceof _NextNode_ID) {
                left_declNode = ((_NextNode_ID) left.nodeBase).nextNode;
                left_declNode_nodeBase = (_NextNode_DeclareVariable) left_declNode.nodeBase;

                left_type = ((Interface_LexType) left_declNode.nodeBase).getType();
            } else
                throw new Exception("kfncn31");

            NextNode newVertex = null;
            if (left_type == LexTypeTERMINAL._INT) {
                newVertex = createIntVertex(value);
            } else if (left_type == LexTypeTERMINAL._DOUBLE) {
                newVertex = createDoubleVertex(value);
            }

            node.setRight(newVertex);

            Interface_Const myConst = (Interface_Const) newVertex.nodeBase;
            left_declNode_nodeBase.currentValue = myConst.getLexem_();
            System.out.print("");
        }

        // Если эту переменную мы вычислили на этапе компиляции,
        // теперь подставим ее вместо вызова переменной
        if (node.parent != null &&
                node.isId_withValue()) {
            if( node.parent.isAssign() && node.parent.left == node )
                return;

            NextNode decl = node;
            // вызов переменной
            _NextNode_ID id_nodeBase = (_NextNode_ID) node.nodeBase;
            // объявление этой переменной
            _NextNode_DeclareVariable decl_nodeBase = (_NextNode_DeclareVariable) id_nodeBase.nextNode.nodeBase;
            NextNode newVertex = new NextNode();

            if (decl_nodeBase.lexTypeTERMINAL == LexTypeTERMINAL._INT) {
                newVertex.nodeBase = new _NextNode_Int(
                        LexTypeTERMINAL._INT,
                        decl_nodeBase.currentValue,
                        LLK.savePointCurrent
                );
            } else if (decl_nodeBase.lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE) {
                newVertex.nodeBase = new _NextNode_Double(
                        LexTypeTERMINAL._DOUBLE,
                        decl_nodeBase.currentValue,
                        LLK.savePointCurrent
                );
            } else
                throw new Exception("iwec94nh2");

            // меняем текущую вершину на новую
            if (node.parent.left == node) {
                node.parent.setLeft(newVertex);
            } else if (node.parent.right == node) {
                node.parent.setRight(newVertex);
            }
        }
    }

    public NextNode createIntVertex(NextNode value) {
        assert value != null;
        Interface_Const myConst = ((Interface_Const) value.nodeBase);
        NextNode nextNode = new NextNode();
        String stringValue = myConst.getLexem_().trim();
        double doubleValue = (Double.valueOf(stringValue));
        Integer intValue = (int) doubleValue;
        nextNode.nodeBase = new _NextNode_Int(
                LexTypeTERMINAL._INT,
                intValue.toString(),
                LLK.savePointCurrent
        );
        return nextNode;
    }

    public NextNode createDoubleVertex(NextNode value) {
        assert value != null;
        Interface_Const myConst = ((Interface_Const) value.nodeBase);
        NextNode nextNode = new NextNode();
        nextNode.nodeBase = new _NextNode_Double(
                LexTypeTERMINAL._DOUBLE,
                Double.valueOf(myConst.getLexem_()).toString(),
                LLK.savePointCurrent
        );
        return nextNode;
    }
}
