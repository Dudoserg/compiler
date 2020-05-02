package main.Lab4.Optimizations;

import main.Lab2.LexTypeTERMINAL;
import main.Lab3.LLK;
import main.Lab4.TreeNext.Const.Interface_Const;
import main.Lab4.TreeNext.Const._NextNode_Int;
import main.Lab4.TreeNext.MathOperation.Interface_LexType;
import main.Lab4.TreeNext.MathOperation._NextNode_Minus;
import main.Lab4.TreeNext.MathOperation._NextNode_Plus;
import main.Lab4.TreeNext.NextNode;
import main.Lab4.TreeNext.TreeNext;
import main.Lab4.TreeNext._NextNode_Shift;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Shift {


    private final TreeNext treeNext;

    public Shift(TreeNext treeNext) {
        this.treeNext = treeNext;
    }

    /**
     * Получаем на какие числа нужно сдвинуть
     *
     * @param x какое число нужно получить сдвигами
     * @return степени двойки
     */
    private List<Integer> getListShifts(int x) {
        int original = x;
        List<Integer> degrees = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        List<Integer> resultPow = new ArrayList<>();
        degrees.add(1);
        for (int i = 0; i < 25; i++) {
            degrees.add(degrees.get(degrees.size() - 1) * 2);
        }
        System.out.println();
        int index_near = findNear(degrees, x);

        result.add(degrees.get(index_near));
        resultPow.add(index_near);

        int sum = result.get(result.size() - 1);
        do {
            x = Math.abs(x - degrees.get(index_near));
            if (x <= 1)
                break;
            index_near = findNear(degrees, x);
            // если ближе с вычетанием
            if (Math.abs((sum + degrees.get(index_near)) - original) >= Math.abs((sum - degrees.get(index_near)) - original)) {
                result.add((-1) * degrees.get(index_near));
                resultPow.add((-1) * index_near);
                sum += result.get(result.size() - 1);
            } else {
                result.add(degrees.get(index_near));
                resultPow.add(index_near);
                sum += result.get(result.size() - 1);
            }
        } while (true);

        if (original - sum != 0) {
            result.add(original - sum);
            resultPow.add(original - sum);

        }

        return result;
    }

    private List<Integer> getListShiftsPow(int x) {
        int original = x;
        List<Integer> degrees = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        List<Integer> resultPow = new ArrayList<>();
        degrees.add(1);
        for (int i = 0; i < 25; i++) {
            degrees.add(degrees.get(degrees.size() - 1) * 2);
        }
        System.out.println();
        int index_near = findNear(degrees, x);

        result.add(degrees.get(index_near));
        resultPow.add(index_near);

        int sum = result.get(result.size() - 1);
        do {
            x = Math.abs(x - degrees.get(index_near));
            if (x <= 1)
                break;
            index_near = findNear(degrees, x);
            // если ближе с вычетанием
            if (Math.abs((sum + degrees.get(index_near)) - original) >= Math.abs((sum - degrees.get(index_near)) - original)) {
                result.add((-1) * degrees.get(index_near));
                resultPow.add((-1) * index_near);
                sum += result.get(result.size() - 1);
            } else {
                result.add(degrees.get(index_near));
                resultPow.add(index_near);
                sum += result.get(result.size() - 1);
            }
        } while (true);

        if (original - sum != 0) {
            result.add(original - sum);
            resultPow.add(original - sum);

        }
        resultPow.clear();
        result.forEach(integer -> {
            resultPow.add(this.log2(Math.abs(integer)) * sign(integer));
        });
        return resultPow;
    }

    private int findNear(List<Integer> degrees, int x) {
        int i;
        for (i = 0; i < degrees.size() - 1; i++) {
            int left = degrees.get(i);
            int right = degrees.get(i + 1);
            if (left <= x && x < right)
                break;
        }
        if (Math.abs(x - degrees.get(i)) < Math.abs(x - degrees.get(i + 1)))
            return i;
        else
            return i + 1;
    }

    public void start() throws Exception {
        this.DFS(this.treeNext.root);

    }

    /**
     * Заменяем целые числа сдигами
     *
     * @param node
     */
    private void DFS(NextNode node) throws Exception {
        final NextNode left = node.left;
        final NextNode right = node.right;
        if (left != null)
            DFS(left);
        if (right != null)
            DFS(right);
        if (node.isStar() && left != null && right != null &&
                (
                        (left.isInt() && (right.isMathOperation() || right.isId())) ||
                                (right.isInt() && (left.isMathOperation() || left.isId()))
                )
        ) {
            NextNode constantChild = null;
            NextNode otherChild = null;
            if (left.isInt()) {
                // константа слева
                constantChild = left;
                // остальное справа
                otherChild = right;
            } else if (right.isInt()) {
                // константа справа
                constantChild = right;
                // остальное слева
                otherChild = left;
            } else
                throw new Exception("a92vle[js");
            Interface_LexType interface_lexType = null;

            interface_lexType = (Interface_LexType) otherChild.nodeBase;
            // Идем дальше если только второй потомок типа инт
            if (interface_lexType.getType() != LexTypeTERMINAL._INT)
                return;

            final String lexem_str = ((_NextNode_Int) constantChild.nodeBase).lexem;
            Integer lexem_int = Integer.valueOf(lexem_str);

            final List<Integer> listShifts = getListShifts(lexem_int);
            System.out.println("===" + lexem_str + "===");
            listShifts.forEach(integer -> System.out.println(integer));
            System.out.println("===");

            NextNode newNode = createNewVertex(otherChild, listShifts);
        }
    }

    private NextNode createNewVertex(NextNode otherChild, List<Integer> listShifts) throws IOException {
        List<NextNode> nodeList = new ArrayList<>();
        listShifts = listShifts.stream().sorted((o1, o2) -> -Integer.compare(Math.abs(o1), Math.abs(o2)) ).collect(Collectors.toList());
        listShifts.forEach(integer -> {
            int sign = sign(integer);
            NextNode nextNode = new NextNode();

            if (Math.abs(integer) == 1) {
                nextNode.nodeBase = new _NextNode_Int(LexTypeTERMINAL._INT, "1", LLK.savePointCurrent);
            } else {
                int value = log2(Math.abs(integer));
                nextNode.nodeBase = new _NextNode_Shift(value, true, LLK.savePointCurrent);
                nextNode.right = otherChild;
            }

            nodeList.add(nextNode);
        });
        NextNode left = nodeList.get(0);
        NextNode right;

        for (int i = 1; i < nodeList.size(); i++) {
            int sign = sign(listShifts.get(i));
            right = nodeList.get(i);
            NextNode nextNode = new NextNode();
            if (sign > 0) {
                final _NextNode_Plus nodeBase = new _NextNode_Plus(LLK.savePointCurrent);
                nodeBase.lexTypeTERMINAL = LexTypeTERMINAL._INT;
                nextNode.nodeBase = nodeBase;
            } else {
                final _NextNode_Minus nodeBase = new _NextNode_Minus(LLK.savePointCurrent);
                nodeBase.lexTypeTERMINAL = LexTypeTERMINAL._INT;
                nextNode.nodeBase = nodeBase;
            }
            nextNode.setLeft(left);
            nextNode.setRight(right);
            left = nextNode;
        }
        treeNext.draw(left, left, "part");
        return null;
    }

    private int log2(int N) {
        int result = (int) (Math.log(N) / Math.log(2));
        return result;
    }

    private int sign(int x) {
        return x >= 0 ? 1 : -1;
    }
}
