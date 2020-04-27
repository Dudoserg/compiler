package main.Lab4.TreeNext;

import lombok.AllArgsConstructor;
import main.Lab2.LexTypeTERMINAL;
import main.Lab4.TreeNext.Relations.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;


@AllArgsConstructor
public class NextNode {
    public static int counter;

    public int id;
    public NextNode parent;
    public NextNode left;
    public NextNode right;

    _NextNodeBase nodeBase;

    public NextNode() {
        this.id = counter++;
    }

    public boolean isHasChild() {
        if (left == null && right == null)
            return false;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NextNode nextNode = (NextNode) o;
        return id == nextNode.id &&
                Objects.equals(parent, nextNode.parent) &&
                Objects.equals(left, nextNode.left) &&
                Objects.equals(right, nextNode.right) &&
                nodeBase.equals(nextNode.nodeBase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parent, left, right, nodeBase);
    }

    public void setLeft(NextNode vertex) {
        this.left = vertex;
        vertex.parent = this;
    }

    public void setRight(NextNode right) {
        this.right = right;
        right.parent = this;
    }

    private void set_Label(FileWriter writer, String vertexName, String label) throws IOException {
        writer.write(vertexName + this.id + "[label=\"" + label + "\"]" + "\n");
    }

    private void set_Color(FileWriter writer, String vertexName, String color, NextNode current)
            throws IOException {
        if (this == current)
            writer.write(vertexName + this.id + "[style=filled, fillcolor=yellow]" + "\n");
        else
            writer.write(vertexName + this.id + "[style=filled, fillcolor=" + color + "]" + "\n");
    }

    public void print(FileWriter writer, NextNode current) throws Exception {
        String str = "";
        if (nodeBase instanceof _NextNode_Next) {
            if (this == current)
                writer.write("v" + this.id + "[style=filled, fillcolor=yellow]" + "\n");
            else
                writer.write("v" + this.id + "[style=filled, fillcolor=grey]" + "\n");
            writer.write("v" + this.id + "[label=\"" + "next" + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_Func) {
            _NextNode_Func nodeBase = (_NextNode_Func) this.nodeBase;
            if (this == current)
                writer.write("v" + this.id + "[style=filled, fillcolor=yellow]" + "\n");
            else
                writer.write("v" + this.id + "[style=filled, fillcolor=red]" + "\n");
            writer.write("v" + this.id + "[label=\"" + nodeBase.lexem + "\"]" + "\n");
            writer.write("v" + this.id + "[xlabel=\"" + nodeBase.lexTypeTERMINAL.getMin() + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_FuncEnd) {
            _NextNode_FuncEnd nodeBaseFuncEnd = (_NextNode_FuncEnd) this.nodeBase;
            final _NextNode_Func nodeBaseFunc = (_NextNode_Func) nodeBaseFuncEnd.func.nodeBase;
            if (this == current)
                writer.write("v" + this.id + "[style=filled, fillcolor=yellow]" + "\n");
            else
                writer.write("v" + this.id + "[style=filled, fillcolor=red]" + "\n");
            writer.write("v" + this.id + "[label=\"" + "END " + nodeBaseFunc.lexem + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_DeclareVariable) {
            _NextNode_DeclareVariable nodeBase = (_NextNode_DeclareVariable) this.nodeBase;
            if (this == current)
                writer.write("v" + this.id + "[style=filled, fillcolor=yellow]" + "\n");
            else
                writer.write("v" + this.id + "[style=filled, fillcolor=\"#ebcccc\"]" + "\n");
            writer.write("v" + this.id + "[label=\"" + nodeBase.lexem + "\"]" + "\n");
            writer.write("v" + this.id + "[xlabel=\"" + nodeBase.lexTypeTERMINAL.getMin() + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_Assign) {
            _NextNode_Assign nodeBase = (_NextNode_Assign) this.nodeBase;
            if (this == current)
                writer.write("v" + this.id + "[style=filled, fillcolor=yellow]" + "\n");
            else
                writer.write("v" + this.id + "" + "\n");
            writer.write("v" + this.id + "[label=\"" + "=" + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_Cast) {
            _NextNode_Cast nodeBase = (_NextNode_Cast) this.nodeBase;
            if (this == current)
                writer.write("v" + this.id + "[style=filled, fillcolor=yellow]" + "\n");
            else
                writer.write("v" + this.id + "" + "\n");

            writer.write("v" + this.id + "[style=filled,  fillcolor=\"#88db8b\"]" + "\n");
            writer.write("v" + this.id + "[label=\"" + nodeBase.castTo_lexTypeTERMINAL.toString() + "\"]" + "\n");
            writer.write("v" + this.id + "[xlabel=\"" + "CAST" + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_Div) {
            _NextNode_Div nodeBase = (_NextNode_Div) this.nodeBase;
            if (this == current)
                writer.write("v" + this.id + "[style=filled, fillcolor=yellow]" + "\n");
            else
                writer.write("v" + this.id + "[style=filled, fillcolor=\"#ccebe8\"]" + "\n");
            writer.write("v" + this.id + "[label=\"" + "/" + "\"]" + "\n");
            writer.write("v" + this.id + "[xlabel=\"" + nodeBase.lexTypeTERMINAL.getMin() + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_Star) {
            _NextNode_Star nodeBase = (_NextNode_Star) this.nodeBase;

            if (this == current)
                writer.write("v" + this.id + "[style=filled, fillcolor=yellow]" + "\n");
            else
                writer.write("v" + this.id + "[style=filled, fillcolor=\"#ccebe8\"]" + "\n");
            writer.write("v" + this.id + "[label=\"" + "*" + "\"]" + "\n");
            writer.write("v" + this.id + "[xlabel=\"" + nodeBase.lexTypeTERMINAL.getMin() + "\"]" + "\n");

        } else if (nodeBase instanceof _NextNode_Plus) {
            _NextNode_Plus nodeBase = (_NextNode_Plus) this.nodeBase;
            if (this == current)
                writer.write("v" + this.id + "[style=filled, fillcolor=yellow]" + "\n");
            else
                writer.write("v" + this.id + "[style=filled, fillcolor=\"#ccebe8\"]" + "\n");
            writer.write("v" + this.id + "[label=\"" + "+" + "\"]" + "\n");
            writer.write("v" + this.id + "[xlabel=\"" + nodeBase.lexTypeTERMINAL.getMin() + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_Minus) {
            _NextNode_Minus nodeBase = (_NextNode_Minus) this.nodeBase;

            if (this == current)
                writer.write("v" + this.id + "[style=filled, fillcolor=yellow]" + "\n");
            else
                writer.write("v" + this.id + "[style=filled, fillcolor=\"#ccebe8\"]" + "\n");
            writer.write("v" + this.id + "[label=\"" + "-" + "\"]" + "\n");
            writer.write("v" + this.id + "[xlabel=\"" + nodeBase.lexTypeTERMINAL.getMin() + "\"]" + "\n");

        } else if (nodeBase instanceof _NextNode_Int) {
            _NextNode_Int nodeBase = (_NextNode_Int) this.nodeBase;
            if (this == current)
                writer.write("v" + this.id + "[style=filled, fillcolor=yellow]" + "\n");
            else
                writer.write("v" + this.id + "[style=filled, fillcolor=\"#e1e8bc\"]" + "\n");
            writer.write("v" + this.id + "[label=\"" + nodeBase.lexem + "\"]" + "\n");
            writer.write("v" + this.id + "[xlabel=\"" + nodeBase.lexTypeTERMINAL.getMin() + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_Double) {
            _NextNode_Double nodeBase = (_NextNode_Double) this.nodeBase;
            if (this == current)
                writer.write("v" + this.id + "[style=filled, fillcolor=yellow]" + "\n");
            else
                writer.write("v" + this.id + "[style=filled, fillcolor=\"#e1e8bc\"]" + "\n");
            writer.write("v" + this.id + "[label=\"" + nodeBase.lexem + "\"]" + "\n");
            writer.write("v" + this.id + "[xlabel=\"" + nodeBase.lexTypeTERMINAL.getMin() + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_ID) {
            _NextNode_ID nodeBase = (_NextNode_ID) this.nodeBase;
            if (this == current)
                writer.write("v" + this.id + "[style=filled, fillcolor=yellow]" + "\n");
            else
                writer.write("v" + this.id + "[style=filled, fillcolor=\"#9991e3\"]" + "\n");
            writer.write("v" + this.id + "[label=\"" + nodeBase.lexem + "\"]" + "\n");
            writer.write("v" + this.id + "[xlabel=\"" + nodeBase.lexTypeTERMINAL.getMin() + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_If) {
            writer.write("v" + this.id + "[style=filled, fillcolor=\"#00c200\"]" + "\n");
            writer.write("v" + this.id + "[label=\"" + "IF" + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_Else) {
            writer.write("v" + this.id + "[style=filled, fillcolor=\"#00c200\"]" + "\n");
            writer.write("v" + this.id + "[label=\"" + "ELSE" + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_Great) {
            writer.write("v" + this.id + "[label=\"" + ">" + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_Great_Equal) {
            writer.write("v" + this.id + "[label=\"" + ">=" + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_Less) {
            writer.write("v" + this.id + "[label=\"" + "<" + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_Less_Equal) {
            writer.write("v" + this.id + "[label=\"" + "<=" + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_Equal) {
            writer.write("v" + this.id + "[label=\"" + "==" + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_Not_Equal) {
            writer.write("v" + this.id + "[label=\"" + "!=" + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_Return) {
            writer.write("v" + this.id + "[style=filled, fillcolor=\"#00d4d4\"]" + "\n");
            writer.write("v" + this.id + "[label=\"" + "RETURN" + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_StartLevel) {
            if (this == current)
                writer.write("v" + this.id + "[style=filled, fillcolor=yellow]" + "\n");
            else
                writer.write("v" + this.id + "[style=filled, fillcolor=\"#000000\"]" + "\n");
            writer.write("v" + this.id + "[xlabel=\"" + "level" + "\"]" + "\n");
//            writer.write("v" + this.id + "[label=\"" + "RETURN" + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_Push_Param) {
            _NextNode_Push_Param nodeBase = (_NextNode_Push_Param) this.nodeBase;
            String tmp_str = "";
//            if (nodeBase.whatIsPush.nodeBase instanceof _NextNode_Int) {
//                tmp_str += ((_NextNode_Int) nodeBase.whatIsPush.nodeBase).lexem;
//                writer.write("v" + this.id + "[style=filled, fillcolor=\"#00d4d4\"]" + "\n");
//            } else if (nodeBase.whatIsPush.nodeBase instanceof _NextNode_Double) {
//                tmp_str += ((_NextNode_Double) nodeBase.whatIsPush.nodeBase).lexem;
//                writer.write("v" + this.id + "[style=filled, fillcolor=\"#00d4d4\"]" + "\n");
//            } else if (nodeBase.whatIsPush.nodeBase instanceof _NextNode_ID) {
//                tmp_str += ((_NextNode_ID) nodeBase.whatIsPush.nodeBase).lexem;
//                writer.write("v" + this.id + "[style=filled, fillcolor=\"#00d4d4\"]" + "\n");
//            } else if (nodeBase.whatIsPush.nodeBase instanceof _NextNode_Call) {
//                tmp_str +=
//                        ((_NextNode_Func) ((_NextNode_Call) nodeBase.whatIsPush.nodeBase).func.nodeBase)
//                                .lexem;
//                writer.write("v" + this.id + "[style=filled, fillcolor=\"#de7d0d\"]" + "\n");
//                writer.write("v" + this.id + "[xlabel=\"" + "CALL" + "\"]" + "\n");
//            } else
//                throw new Exception("asd078fa0s");
            writer.write("v" + this.id + "[style=filled, fillcolor=\"#fff15c\"]" + "\n");
            writer.write("v" + this.id + "[label=\"" + "push" + "\"]" + "\n");

        } else if (nodeBase instanceof _NextNode_Call) {
            _NextNode_Call nodeBase = (_NextNode_Call) this.nodeBase;
            String tmp_lexem = ((_NextNode_Func) (nodeBase.func.nodeBase)).lexem;

            writer.write("v" + this.id + "[style=filled, fillcolor=\"#de7d0d\"]" + "\n");
            writer.write("v" + this.id + "[label=\"" + tmp_lexem + "\"]" + "\n");
            writer.write("v" + this.id + "[xlabel=\"" + "CALL" + "\"]" + "\n");
        } else {
            throw new Exception("ASD1ASDASD " + nodeBase.getClass().getName());
        }


        String isLeftRightNull = left == null && right == null ? "[style=invis]" : "";
        // прозрачный центр
        writer.write("v" + this.id + "center" + "[style=invis, width=0, label=\"\"];" + "\n");
        if (left == null && right == null) {
            // если и правая и левая вершины нули, то и центр тоже не рисуем
            writer.write("v" + this.id + " -- " + "v" + this.id + "center" +
                    "[style=invis]" + "" + "\n");
        } else {
//            writer.write("v" + this.id + " -- " + "v" + this.id + "center" +
//                    "[ label=\"center\" ] " + "" + "\n");
            writer.write("v" + this.id + " -- " + "v" + this.id + "center" +
                    "[style=invis]" + "" + "\n");
        }


        List<String> rankSame = new ArrayList<>();

        if (this.left != null) {
            writer.write("v" + this.id + " -- " + "v" + left.id + "[color=\"red\"]" + "\n");
            rankSame.add("v" + left.id);
        } else {
            writer.write("v" + this.id + "notVisibleL" + "[style=invis]" + "\n");
            writer.write("v" + this.id + " -- " + "v" + this.id + "notVisibleL" + isLeftRightNull + "[style=invis]" + "\n");
            rankSame.add("v" + this.id + "notVisibleL");
        }
        rankSame.add("v" + this.id + "center");

        if (right != null) {
            writer.write("v" + this.id + " -- " + "v" + right.id + "[color=\"blue\"]" + "\n");
            rankSame.add("v" + right.id);
        } else {
            writer.write("v" + this.id + "notVisibleR" + "[style=invis]" + "\n");
            writer.write("v" + this.id + " -- " + "v" + this.id + "notVisibleR" + isLeftRightNull + "[style=invis]" + "\n");
            rankSame.add("v" + this.id + "notVisibleR");
        }

        writer.write("{\n\trank=same " +
                String.join(" -- ", rankSame) +
                "  [style=invis]\n} \n");

        writer.write(str);

        if (left != null)
            this.left.print(writer, current);

        if (right != null)
            this.right.print(writer, current);
    }

    private static Stack<NextNode_Triad> stack_IF = new Stack<>();
    private static Stack<NextNode_Triad> stack_GOTO = new Stack<>();

    public List<NextNode_Triad> createTriads(List<NextNode_Triad> listTriads) throws Exception {
        String str = "";
        if (nodeBase instanceof _NextNode_Next) {
            _NextNode_Next nodeBase = (_NextNode_Next) this.nodeBase;

            if (left != null) this.left.createTriads(listTriads);
            if (right != null) this.right.createTriads(listTriads);

        } else if (nodeBase instanceof _NextNode_Func) {
            _NextNode_Func nodeBase = (_NextNode_Func) this.nodeBase;

            addTriad("proc", nodeBase.lexem, null, listTriads);
            addTriad("prolog", null, null, listTriads);
            if (left != null)
                this.left.createTriads(listTriads);

        } else if (nodeBase instanceof _NextNode_FuncEnd) {
            _NextNode_FuncEnd nodeBase = (_NextNode_FuncEnd) this.nodeBase;

            addTriad("epilog", null, null, listTriads);
            addTriad("ret", null, null, listTriads);
            addTriad("endp", null, null, listTriads);

        } else if (nodeBase instanceof _NextNode_DeclareVariable) {
            _NextNode_DeclareVariable nodeBase = (_NextNode_DeclareVariable) this.nodeBase;

        } else if (nodeBase instanceof _NextNode_Assign) {
            _NextNode_Assign nodeBase = (_NextNode_Assign) this.nodeBase;

            if (left != null) this.left.createTriads(listTriads);
            if (right != null) this.right.createTriads(listTriads);

            final _NextNode_ID leftNodeBase = (_NextNode_ID) left.nodeBase;

            String first = leftNodeBase.lexem;
            String second = "??";
            if (right.nodeBase.triad_number >= 0)
                second = "(" + right.nodeBase.triad_number.toString() + ")";
            else
                second = right.nodeBase.triad_lexem;

            addTriad("=", first, second, listTriads);


        } else if (nodeBase instanceof _NextNode_Cast) {
            _NextNode_Cast nodeBase = (_NextNode_Cast) this.nodeBase;
            if (left != null) this.left.createTriads(listTriads);
            if (right != null) this.right.createTriads(listTriads);
            String cast_to = "";

            // у потомка получаем исходный тип из которого приводим
            String cast_from = "";
            final LexTypeTERMINAL right_lexType = TreeNext.getLexTypeTerminal_inMathOper(right);
            if (right_lexType == LexTypeTERMINAL._DOUBLE)
                cast_from = "double";
            else if (right_lexType == LexTypeTERMINAL._INT || right_lexType == LexTypeTERMINAL._TYPE_INT_8 ||
                    right_lexType == LexTypeTERMINAL._TYPE_INT_10 || right_lexType == LexTypeTERMINAL._TYPE_INT_16)
                cast_from = "int";
            else
                throw new Exception("Not found cast_from type!");

            // у текущйвй вершины получаем тип к чему приводим
            if (nodeBase.castTo_lexTypeTERMINAL == LexTypeTERMINAL._DOUBLE)
                cast_to = "double";
            else if (nodeBase.castTo_lexTypeTERMINAL == LexTypeTERMINAL._INT ||
                    nodeBase.castTo_lexTypeTERMINAL == LexTypeTERMINAL._TYPE_INT_8 ||
                    nodeBase.castTo_lexTypeTERMINAL == LexTypeTERMINAL._TYPE_INT_10 ||
                    nodeBase.castTo_lexTypeTERMINAL == LexTypeTERMINAL._TYPE_INT_16)
                cast_to = "int";
            else
                throw new Exception("Not found cast_to type!");

            // получаем номер триады потомка
            String first = "??";
            if (right.nodeBase.triad_number >= 0)
                first = "(" + right.nodeBase.triad_number.toString() + ")";
            else
                first = right.nodeBase.triad_lexem;

            addTriad(cast_from + " -> " + cast_to, first, null, listTriads);

            nodeBase.triad_number = listTriads.size() - 1;

        } else if (nodeBase instanceof _NextNode_Plus) {
            _NextNode_Plus nodeBase = (_NextNode_Plus) this.nodeBase;
            createMathOperation_Triads(this, listTriads);

        } else if (nodeBase instanceof _NextNode_Minus) {
            _NextNode_Minus nodeBase = (_NextNode_Minus) this.nodeBase;
            createMathOperation_Triads(this, listTriads);

        } else if (nodeBase instanceof _NextNode_Div) {
            _NextNode_Div nodeBase = (_NextNode_Div) this.nodeBase;
            createMathOperation_Triads(this, listTriads);

        } else if (nodeBase instanceof _NextNode_Star) {
            _NextNode_Star nodeBase = (_NextNode_Star) this.nodeBase;
            createMathOperation_Triads(this, listTriads);

        } else if (nodeBase instanceof _NextNode_Int) {
            _NextNode_Int nodeBase = (_NextNode_Int) this.nodeBase;
            nodeBase.triad_lexem = nodeBase.lexem;

        } else if (nodeBase instanceof _NextNode_Double) {
            _NextNode_Double nodeBase = (_NextNode_Double) this.nodeBase;
            nodeBase.triad_lexem = nodeBase.lexem;

        } else if (nodeBase instanceof _NextNode_ID) {
            _NextNode_ID nodeBase = (_NextNode_ID) this.nodeBase;
            nodeBase.triad_lexem = nodeBase.lexem;

        } else if (nodeBase instanceof _NextNode_If) {
            _NextNode_If nodeBase = (_NextNode_If) this.nodeBase;

            if (left != null) {
//                if (! left.isHasChild()) // Если у условия нет потомков, т.е. там только один объект
//                if (left.nodeBase instanceof _NextNode_Call) {
//                    final _NextNode_Call callNodeBase = (_NextNode_Call) left.nodeBase;
//                    final _NextNode_Func funcNodeBase = (_NextNode_Func) callNodeBase.func.nodeBase;
//                    addTriad("+", "0", funcNodeBase.lexem, listTriads);
//                }
                if (left.nodeBase instanceof _NextNode_ID) {
                    addTriad("+", "0", ((_NextNode_ID) left.nodeBase).lexem, listTriads);
                }
                if (left.nodeBase instanceof _NextNode_Int) {
                    addTriad("+", "0", ((_NextNode_Int) left.nodeBase).lexem, listTriads);
                }
                if (left.nodeBase instanceof _NextNode_Double) {
                    addTriad("+", "0", ((_NextNode_Double) left.nodeBase).lexem, listTriads);
                }
                this.left.createTriads(listTriads);
            }

            String first = "(" + (listTriads.size() + 1) + ")";
            String second = "??";
            final NextNode_Triad triad_IF = addTriad("if", first, second, listTriads);
            NextNode.stack_IF.add(triad_IF);

            if (right != null) this.right.createTriads(listTriads);


        } else if (nodeBase instanceof _NextNode_Else) {
            _NextNode_Else nodeBase = (_NextNode_Else) this.nodeBase;

            // иф истина
            if (left != null) this.left.createTriads(listTriads);
            // GOTO
            final NextNode_Triad triad_GOTO = addTriad("goto", null, null, listTriads);
            NextNode.stack_GOTO.add(triad_GOTO);
            // иф ложь
            if (right != null) this.right.createTriads(listTriads);
            // NOP
            addTriad("NOP", null, null, listTriads);
            final NextNode_Triad popIf = stack_IF.pop();
            final NextNode_Triad popGOTO = stack_GOTO.pop();
            popIf.second = "(" + String.valueOf(listTriads.size() - 1) + ")";
            popGOTO.second = "(" + String.valueOf(listTriads.size() - 1) + ")";

        } else if (nodeBase instanceof _NextNode_Great) {
            _NextNode_Great nodeBase = (_NextNode_Great) this.nodeBase;
            createMathOperation_Triads(this, listTriads);

        } else if (nodeBase instanceof _NextNode_Great_Equal) {
            _NextNode_Great_Equal nodeBase = (_NextNode_Great_Equal) this.nodeBase;
            createMathOperation_Triads(this, listTriads);

        } else if (nodeBase instanceof _NextNode_Less) {
            _NextNode_Less nodeBase = (_NextNode_Less) this.nodeBase;
            createMathOperation_Triads(this, listTriads);

        } else if (nodeBase instanceof _NextNode_Less_Equal) {
            _NextNode_Less_Equal nodeBase = (_NextNode_Less_Equal) this.nodeBase;
            createMathOperation_Triads(this, listTriads);

        } else if (nodeBase instanceof _NextNode_Equal) {
            _NextNode_Equal nodeBase = (_NextNode_Equal) this.nodeBase;
            createMathOperation_Triads(this, listTriads);

        } else if (nodeBase instanceof _NextNode_Not_Equal) {
            _NextNode_Not_Equal nodeBase = (_NextNode_Not_Equal) this.nodeBase;
            createMathOperation_Triads(this, listTriads);

        } else if (nodeBase instanceof _NextNode_Return) {
            _NextNode_Return nodeBase = (_NextNode_Return) this.nodeBase;

            if (right != null) this.right.createTriads(listTriads);

            String first = "??";
            if (right.nodeBase.triad_number >= 0)
                first = "(" + right.nodeBase.triad_number.toString() + ")";
            else
                first = right.nodeBase.triad_lexem;
            addTriad("push_for_return", first, null, listTriads);

        } else if (nodeBase instanceof _NextNode_StartLevel) {
            _NextNode_StartLevel nodeBase = (_NextNode_StartLevel) this.nodeBase;
            if (left != null) this.left.createTriads(listTriads);
            if (right != null) this.right.createTriads(listTriads);

        } else if (nodeBase instanceof _NextNode_Push_Param) {
            _NextNode_Push_Param nodeBase = (_NextNode_Push_Param) this.nodeBase;

            if (right != null) this.right.createTriads(listTriads);

            String first = "??";
            if (right.nodeBase.triad_number >= 0)
                first = "(" + right.nodeBase.triad_number.toString() + ")";
            else
                first = right.nodeBase.triad_lexem;

            addTriad("push", first, null, listTriads);

        } else if (nodeBase instanceof _NextNode_Call) {
            _NextNode_Call callNodeBase = (_NextNode_Call) this.nodeBase;
            _NextNode_Func funcNodeBase = (_NextNode_Func) callNodeBase.func.nodeBase;
            if (left != null) this.left.createTriads(listTriads);
            addTriad("call", funcNodeBase.lexem, null, listTriads);
            callNodeBase.triad_number = listTriads.size() - 1;
        } else {
            throw new Exception("us8188-xjjk " + nodeBase.getClass().getName());
        }


        return listTriads;
    }

    private void createMathOperation_Triads(NextNode nextNode, List<NextNode_Triad> list) throws Exception {

        if (nextNode.left != null) nextNode.left.createTriads(list);
        if (nextNode.right != null) nextNode.right.createTriads(list);

        String first = "";
        if (nextNode.left.nodeBase.triad_number >= 0)
            first = "(" + nextNode.left.nodeBase.triad_number.toString() + ")";
        else
            first = nextNode.left.nodeBase.triad_lexem;

        String second = "";
        if (nextNode.right.nodeBase.triad_number >= 0)
            second = "(" + nextNode.right.nodeBase.triad_number.toString() + ")";
        else
            second = nextNode.right.nodeBase.triad_lexem;


        if (nextNode.nodeBase instanceof _NextNode_Plus) {
            addTriad("+", first, second, list);
        } else if (nextNode.nodeBase instanceof _NextNode_Minus) {
            addTriad("-", first, second, list);
        } else if (nextNode.nodeBase instanceof _NextNode_Div) {
            addTriad("/", first, second, list);
        } else if (nextNode.nodeBase instanceof _NextNode_Star) {
            addTriad("*", first, second, list);
        } else if (nextNode.nodeBase instanceof _NextNode_Great) {
            addTriad(">", first, second, list);
        } else if (nextNode.nodeBase instanceof _NextNode_Great_Equal) {
            addTriad(">=", first, second, list);
        } else if (nextNode.nodeBase instanceof _NextNode_Less) {
            addTriad("<", first, second, list);
        } else if (nextNode.nodeBase instanceof _NextNode_Less_Equal) {
            addTriad("<=", first, second, list);
        } else if (nextNode.nodeBase instanceof _NextNode_Equal) {
            addTriad("==", first, second, list);
        } else if (nextNode.nodeBase instanceof _NextNode_Not_Equal) {
            addTriad("!=", first, second, list);
        } else
            throw new Exception("asdf7320jsj");

        nodeBase.triad_number = list.size() - 1;
    }

    private void isConst_or_isVar(NextNode nextNode) {
//        if(nextNode.nodeBase instanceof _Nex)
    }

    private NextNode_Triad addTriad(String operation, String first, String second, List<NextNode_Triad> list) {
        final NextNode_Triad nextNode_triad = new NextNode_Triad(operation, first, second, list.size());
        list.add(nextNode_triad);
        return nextNode_triad;
    }
}
