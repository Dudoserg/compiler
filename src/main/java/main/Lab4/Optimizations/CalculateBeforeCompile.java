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
        DFS(treeNext.root);
    }

    private void DFS(NextNode node) throws Exception {
        // TODO тут я просто привожу к даблу, хотя чет не оч)0
        if (node.left != null)
            DFS(node.left);
        if (node.right != null)
            DFS(node.right);

        if (node.isMathOperation() && (node.left.isConstant()) && node.right.isConstant()) {
            Interface_MathOperation nodeMathOperation = (Interface_MathOperation) node.nodeBase;
            final NextNode left = node.left;
            final NextNode right = node.right;

            NextNode newNode = new NextNode();
            Interface_Const newNodeBase = null;

            final LexTypeTERMINAL nodeType = nodeMathOperation.getType();

            // создаем NodeBase определенного типа
            if (nodeType == LexTypeTERMINAL._DOUBLE){
                newNodeBase = new _NextNode_Double(nodeType, null, LLK.savePointCurrent);
                newNode.nodeBase = (_NextNodeBase) newNodeBase;
            }
            else if (nodeType == LexTypeTERMINAL._INT){
                newNodeBase = new _NextNode_Int(nodeType, null, LLK.savePointCurrent);
                newNode.nodeBase = (_NextNodeBase) newNodeBase;
            }
            else
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
            if (node.parent.left == node) {
                node.parent.setLeft(newNode);
                //treeNext.draw(treeNext.root, newNode, "kek");
            } else if (node.parent.right == node) {
                node.parent.setRight(newNode);
                //treeNext.draw(treeNext.root, newNode, "kek");
            }


        }

    }
}
