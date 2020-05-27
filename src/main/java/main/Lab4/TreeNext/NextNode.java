package main.Lab4.TreeNext;

import lombok.AllArgsConstructor;
import main.Lab2.LexTypeTERMINAL;
import main.Lab4.TreeNext.Const.Interface_Const;
import main.Lab4.TreeNext.Const._NextNode_Double;
import main.Lab4.TreeNext.Const._NextNode_Int;
import main.Lab4.TreeNext.MathOperation.*;
import main.Lab4.TreeNext.Relations.*;
import main.Lab4.Triad;
import main.Lab4.TriadsByType.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;


@AllArgsConstructor
public class NextNode {
    public static int counter;

    public boolean isDrawed = false;
    public boolean isCreateTriad = false;
    public int id;
    public NextNode parent;
    public NextNode left;
    public NextNode right;

    public _NextNodeBase nodeBase;

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

    private void set_Xlabel(FileWriter writer, String vertexName, String Xlabel, Integer id) throws IOException {
        writer.write("v" + this.id + "[xlabel=\"" + "(" + id + ") " + Xlabel + "\"]" + "\n");
    }

    private void set_Color(FileWriter writer, String vertexName, String color, NextNode current)
            throws IOException {
        if (this == current)
            writer.write(vertexName + this.id + "[style=filled, fillcolor=yellow]" + "\n");
        else {
            if (color.charAt(0) == '#')
                color = "\"" + color + "\"";
            writer.write(vertexName + this.id + "[style=filled, fillcolor=" + color + "]" + "\n");
        }
    }

    public void print(FileWriter writer, NextNode current) throws Exception {
        String str = "";
        if (isDrawed)
            return;
        isDrawed = true;

        if (nodeBase instanceof _NextNode_Next) {
            set_Color(writer, "v", "grey", current);
            set_Label(writer, "v", "next");
            set_Xlabel(writer, "v", "", this.id);
        } else if (nodeBase instanceof _NextNode_Func) {
            _NextNode_Func nodeBase = (_NextNode_Func) this.nodeBase;
            set_Color(writer, "v", "red", current);
            set_Label(writer, "v", nodeBase.lexem + "\n" + "loc_v = " + nodeBase.asm_countLocalVariable);
            set_Xlabel(writer, "v", nodeBase.lexTypeTERMINAL.getMin(), this.id);
        } else if (nodeBase instanceof _NextNode_FuncEnd) {
            _NextNode_FuncEnd nodeBaseFuncEnd = (_NextNode_FuncEnd) this.nodeBase;
            final _NextNode_Func nodeBaseFunc = (_NextNode_Func) nodeBaseFuncEnd.func.nodeBase;
            set_Color(writer, "v", "red", current);
            set_Label(writer, "v", "END " + nodeBaseFunc.lexem);
            set_Xlabel(writer, "v", "", this.id);
        } else if (nodeBase instanceof _NextNode_DeclareVariable) {
            _NextNode_DeclareVariable nodeBase = (_NextNode_DeclareVariable) this.nodeBase;
            set_Color(writer, "v", "#ebcccc", current);
            set_Label(writer, "v",
                    "decl. " + nodeBase.lexem + "\n" +
                            "shift " + nodeBase.asm_addr + "\n" +
                            nodeBase.asm_name + "\n" +
                            "# " + nodeBase.asm_index
            );
            String globalLocalParamType = "";
            if (nodeBase.isGlobal)
                globalLocalParamType = "Global";
            if (nodeBase.isLocal)
                globalLocalParamType = "Local";
            if (nodeBase.isParam)
                globalLocalParamType = "Param";
            set_Xlabel(writer, "v", nodeBase.lexTypeTERMINAL.getMin() + " " + globalLocalParamType, this.id);
        } else if (nodeBase instanceof _NextNode_Assign) {
            _NextNode_Assign nodeBase = (_NextNode_Assign) this.nodeBase;
            set_Color(writer, "v", "white", current);
            set_Label(writer, "v", "=");
            set_Xlabel(writer, "v", "", this.id);
        } else if (nodeBase instanceof _NextNode_Cast) {
            _NextNode_Cast nodeBase = (_NextNode_Cast) this.nodeBase;
            set_Color(writer, "v", "#88db8b", current);
            set_Label(writer, "v", nodeBase.castTo_lexTypeTERMINAL.toString());
            set_Xlabel(writer, "v", "CAST", this.id);
        } else if (nodeBase instanceof _NextNode_Div) {
            _NextNode_Div nodeBase = (_NextNode_Div) this.nodeBase;
            set_Color(writer, "v", "#ccebe8", current);
            set_Label(writer, "v", "/");
            set_Xlabel(writer, "v", nodeBase.lexTypeTERMINAL.getMin(), this.id);
        } else if (nodeBase instanceof _NextNode_Star) {
            _NextNode_Star nodeBase = (_NextNode_Star) this.nodeBase;
            set_Color(writer, "v", "#ccebe8", current);
            set_Label(writer, "v", "*");
            set_Xlabel(writer, "v", nodeBase.lexTypeTERMINAL.getMin(), this.id);
        } else if (nodeBase instanceof _NextNode_Plus) {
            _NextNode_Plus nodeBase = (_NextNode_Plus) this.nodeBase;
            set_Color(writer, "v", "#ccebe8", current);
            set_Label(writer, "v", "+");
            set_Xlabel(writer, "v", nodeBase.lexTypeTERMINAL.getMin(), this.id);
        } else if (nodeBase instanceof _NextNode_Minus) {
            _NextNode_Minus nodeBase = (_NextNode_Minus) this.nodeBase;
            set_Color(writer, "v", "#ccebe8", current);
            set_Label(writer, "v", "-");
            set_Xlabel(writer, "v", nodeBase.lexTypeTERMINAL.getMin(), this.id);
        } else if (nodeBase instanceof _NextNode_Int) {
            _NextNode_Int nodeBase = (_NextNode_Int) this.nodeBase;
            set_Color(writer, "v", "#e1e8bc", current);
            set_Label(writer, "v", nodeBase.lexem);
            set_Xlabel(writer, "v", nodeBase.lexTypeTERMINAL.getMin(), this.id);
        } else if (nodeBase instanceof _NextNode_Double) {
            _NextNode_Double nodeBase = (_NextNode_Double) this.nodeBase;
            set_Color(writer, "v", "#e1e8bc", current);
            set_Label(writer, "v", nodeBase.lexem);
            set_Xlabel(writer, "v", nodeBase.lexTypeTERMINAL.getMin(), this.id);
        } else if (nodeBase instanceof _NextNode_ID) {
            final NextNode tmp = ((_NextNode_ID) nodeBase).nextNode;
            final _NextNode_DeclareVariable tmpBase = (_NextNode_DeclareVariable) tmp.nodeBase;
            _NextNode_ID nodeBase = (_NextNode_ID) this.nodeBase;
            set_Color(writer, "v", "#9991e3", current);
            set_Label(writer, "v", nodeBase.lexem + "\n" + tmpBase.asm_name);
            set_Xlabel(writer, "v", tmpBase.lexTypeTERMINAL.getMin(), this.id);
        } else if (nodeBase instanceof _NextNode_If) {
            set_Color(writer, "v", "#00c200", current);
            set_Label(writer, "v", "IF");
            set_Xlabel(writer, "v", "", this.id);
        } else if (nodeBase instanceof _NextNode_Else) {
            set_Color(writer, "v", "#00c200", current);
            set_Label(writer, "v", "ELSE");
            set_Xlabel(writer, "v", "", this.id);
        } else if (nodeBase instanceof _NextNode_Great) {
            set_Color(writer, "v", "white", current);
            set_Label(writer, "v", ">");
            set_Xlabel(writer, "v", "", this.id);
        } else if (nodeBase instanceof _NextNode_Great_Equal) {
            set_Color(writer, "v", "white", current);
            set_Label(writer, "v", ">=");
            set_Xlabel(writer, "v", "", this.id);
        } else if (nodeBase instanceof _NextNode_Less) {
            set_Color(writer, "v", "white", current);
            set_Label(writer, "v", "<");
            set_Xlabel(writer, "v", "", this.id);
        } else if (nodeBase instanceof _NextNode_Less_Equal) {
            set_Color(writer, "v", "white", current);
            set_Label(writer, "v", "<=");
            set_Xlabel(writer, "v", "", this.id);
        } else if (nodeBase instanceof _NextNode_Equal) {
            set_Color(writer, "v", "white", current);
            set_Label(writer, "v", "==");
            set_Xlabel(writer, "v", "", this.id);
        } else if (nodeBase instanceof _NextNode_Not_Equal) {
            set_Color(writer, "v", "white", current);
            set_Label(writer, "v", "!=");
            set_Xlabel(writer, "v", "", this.id);
        } else if (nodeBase instanceof _NextNode_Return) {
            set_Color(writer, "v", "#00d4d4", current);
            set_Label(writer, "v", "RETURN");
            set_Xlabel(writer, "v", "", this.id);
        } else if (nodeBase instanceof _NextNode_StartLevel) {
            set_Color(writer, "v", "#000000", current);
            set_Xlabel(writer, "v", "level", this.id);
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
            set_Color(writer, "v", "#fff15c", current);
            set_Label(writer, "v", "push");
            set_Xlabel(writer, "v", "", this.id);
        } else if (nodeBase instanceof _NextNode_Call) {
            _NextNode_Call nodeBase = (_NextNode_Call) this.nodeBase;
            String tmp_lexem = ((_NextNode_Func) (nodeBase.func.nodeBase)).lexem;

            set_Color(writer, "v", "#de7d0d", current);
            set_Label(writer, "v", tmp_lexem);
            set_Xlabel(writer, "v", "CALL", this.id);
        } else if (nodeBase instanceof _NextNode_Shift) {
            _NextNode_Shift nodeBase = (_NextNode_Shift) this.nodeBase;
            final int degree = nodeBase.degree;
            String tmp = nodeBase.isLeft == true ? "<<" : ">>";
            set_Color(writer, "v", "#FFFFFF", current);
            set_Label(writer, "v", tmp + degree);
            set_Xlabel(writer, "v", "", this.id);
//            set_Xlabel(writer, "v", "CALL");
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
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Создаем триады
     */
    public List<NextNode_Triad> createTriads_str(List<NextNode_Triad> listTriads) throws Exception {
        if (isCreateTriad)
            return new ArrayList<>();
        isCreateTriad = true;
        String str = "";
        if (nodeBase instanceof _NextNode_Next) {
            _NextNode_Next nodeBase = (_NextNode_Next) this.nodeBase;

            if (left != null) this.left.createTriads_str(listTriads);
            if (right != null) this.right.createTriads_str(listTriads);

        } else if (nodeBase instanceof _NextNode_Func) {
            _NextNode_Func nodeBase = (_NextNode_Func) this.nodeBase;

            final NextNode_Triad proc = addTriad("proc", nodeBase.lexem, null, listTriads);
            proc.triad.triad_base = new Triad_Proc(nodeBase.lexem, this);

            final NextNode_Triad prolog = addTriad("prolog", null, null, listTriads);
            prolog.triad.triad_base = new Triad_Prolog();

            if (left != null)
                this.left.createTriads_str(listTriads);

        } else if (nodeBase instanceof _NextNode_FuncEnd) {
            _NextNode_FuncEnd nodeBase = (_NextNode_FuncEnd) this.nodeBase;

            final NextNode_Triad epilog = addTriad("epilog", null, null, listTriads);
            epilog.triad.triad_base = new Triad_Epilog();

            final NextNode_Triad ret = addTriad("ret", null, null, listTriads);
            ret.triad.triad_base = new Triad_Ret();

            final NextNode_Triad endp = addTriad("endp", null, null, listTriads);
            endp.triad.triad_base = new Triad_Endp();

        } else if (nodeBase instanceof _NextNode_DeclareVariable) {
            _NextNode_DeclareVariable nodeBase = (_NextNode_DeclareVariable) this.nodeBase;

        } else if (nodeBase instanceof _NextNode_Assign) {
            _NextNode_Assign nodeBase = (_NextNode_Assign) this.nodeBase;

            if (left != null) this.left.createTriads_str(listTriads);
            if (right != null) this.right.createTriads_str(listTriads);

            final _NextNode_ID leftNodeBase = (_NextNode_ID) left.nodeBase;

            String first = leftNodeBase.lexem;
            String second = "??";
            if (right.nodeBase.triad_number >= 0)
                second = "(" + right.nodeBase.triad_number.toString() + ")";
            else
                second = right.nodeBase.triad_lexem;

            final NextNode_Triad assignTriad = addTriad("=", first, second, listTriads);

            Triad_Math_Operation triad_math_operation = new Triad_Math_Operation();
            assignTriad.triad.triad_base = triad_math_operation;
            {
                triad_math_operation.left_node = left;
                triad_math_operation.left_isNode = true;
                triad_math_operation.left_lexTypeTERMINAL = ((Interface_LexType) left.nodeBase).getType();
                triad_math_operation.left_lexemStr = ((Interface_Const) left.nodeBase).getLexem_();

                triad_math_operation.right_node = right;
                if (right.isConstant()) {
                    // константа
                    triad_math_operation.right_lexTypeTERMINAL = ((Interface_LexType) right.nodeBase).getType();
                    triad_math_operation.right_lexemStr = ((Interface_Const) right.nodeBase).getLexem_();
                } else if (right.nodeBase.isTriad()) {
                    triad_math_operation.right_triad = listTriads.get(right.nodeBase.triad_number).triad;
                    triad_math_operation.right_triad_index = right.nodeBase.triad_number;
                } else if (right.nodeBase instanceof _NextNode_ID) {
                    triad_math_operation.right_lexTypeTERMINAL = ((Interface_LexType) right.nodeBase).getType();
                    triad_math_operation.right_lexemStr = ((Interface_Const) right.nodeBase).getLexem_();
                    triad_math_operation.right_isNode = true;
                }
            }
            System.out.print("");
        } else if (nodeBase instanceof _NextNode_Cast) {
            _NextNode_Cast nodeBase = (_NextNode_Cast) this.nodeBase;
            if (left != null) this.left.createTriads_str(listTriads);
            if (right != null) this.right.createTriads_str(listTriads);
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
            System.out.print("");
        } else if (nodeBase instanceof _NextNode_Minus) {
            _NextNode_Minus nodeBase = (_NextNode_Minus) this.nodeBase;
            createMathOperation_Triads(this, listTriads);
        } else if (nodeBase instanceof _NextNode_Div) {
            _NextNode_Div nodeBase = (_NextNode_Div) this.nodeBase;
            createMathOperation_Triads(this, listTriads);
        } else if (nodeBase instanceof _NextNode_Star) {
            _NextNode_Star nodeBase = (_NextNode_Star) this.nodeBase;
            createMathOperation_Triads(this, listTriads);
            System.out.print("");
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
                if (left.nodeBase instanceof _NextNode_ID || left.nodeBase instanceof _NextNode_Int ||
                        left.nodeBase instanceof _NextNode_Double) {
                    final NextNode_Triad nextNode_triad = addTriad("+", "0", ((Interface_Const) left.nodeBase).getLexem_(), listTriads);
                    nextNode_triad.triad = new Triad(nextNode_triad.operation, nextNode_triad.first, nextNode_triad.second);

                    Triad_Math_Operation triad_math_operation = new Triad_Math_Operation();
                    nextNode_triad.triad.triad_base = triad_math_operation;

                    triad_math_operation.left_lexTypeTERMINAL = LexTypeTERMINAL._INT;
                    triad_math_operation.left_lexemStr = "0";
                    // TODO желательно еще вершину создать для однообразия

                }

                this.left.createTriads_str(listTriads);
            }

            String first = "(" + (listTriads.size() + 1) + ")";
            String second = "??";
            final NextNode_Triad triad_IF = addTriad("if", first, second, listTriads);

            triad_IF.triad = new Triad(triad_IF.operation, triad_IF.first, triad_IF.second);    // создаем объект триаду

            final Triad_IF triad_if = new Triad_IF();   // помещаем туда триаду иф
            triad_IF.triad.triad_base = triad_if;

            triad_if.triad_true_index = listTriads.size() + 1;

            NextNode.stack_IF.add(triad_IF);

            if (right != null) this.right.createTriads_str(listTriads);


        } else if (nodeBase instanceof _NextNode_Else) {
            _NextNode_Else nodeBase = (_NextNode_Else) this.nodeBase;

            // иф истина
            if (left != null) this.left.createTriads_str(listTriads);
            // GOTO
            final NextNode_Triad triad_GOTO = addTriad("goto", null, null, listTriads);
            triad_GOTO.triad = new Triad(triad_GOTO.operation, triad_GOTO.first, triad_GOTO.second);
            triad_GOTO.triad.triad_base = new Triad_GOTO(stack_IF.peek().triad);
            NextNode.stack_GOTO.add(triad_GOTO);
            // иф ложь
            if (right != null) this.right.createTriads_str(listTriads);
            // NOP
            final NextNode_Triad nop = addTriad("NOP", null, null, listTriads);
            nop.triad = new Triad(nop.operation, nop.first, nop.second);
            nop.triad.triad_base = new Triad_NOP(stack_GOTO.peek().triad);


            final NextNode_Triad popGOTO = stack_GOTO.pop();
            popGOTO.second = "(" + String.valueOf(listTriads.size() - 1) + ")";
            ((Triad_GOTO) popGOTO.triad.triad_base).jumpTo = listTriads.get(listTriads.size() - 1).triad;
            ((Triad_GOTO) popGOTO.triad.triad_base).jumpTo_index = listTriads.size() - 1;
//            ((Triad_GOTO) popGOTO.triad.triad_base).nodeIf  = ;// Запоминаем в GOTO указатель на IF

            final NextNode_Triad popIf = stack_IF.pop();
            popIf.second = "(" + String.valueOf(popGOTO.index + 1) + ")";
            popIf.triad.second = "(" + String.valueOf(popGOTO.index + 1) + ")";
            ((Triad_IF) popIf.triad.triad_base).triad_true = listTriads.get(((Triad_IF) popIf.triad.triad_base).triad_true_index).triad;
            ((Triad_IF) popIf.triad.triad_base).triad_false = listTriads.get(popGOTO.index + 1).triad;
            ((Triad_IF) popIf.triad.triad_base).triad_false_index = popGOTO.index + 1;

            // Если goto и NOP Рядом, то значит GOTO не нужен
            if (nop.index.equals(popGOTO.index + 1)) {
                // удаляем goto из массива
                listTriads.remove(popGOTO);
            }

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

            if (right != null) this.right.createTriads_str(listTriads);

            String first = "??";
            if (right.nodeBase.triad_number >= 0)
                first = "(" + right.nodeBase.triad_number.toString() + ")";
            else
                first = right.nodeBase.triad_lexem;
            final NextNode_Triad push_for_return = addTriad("push_for_return", first, null, listTriads);

            Triad_Push_For_Return triad_push_for_return = new Triad_Push_For_Return();
            push_for_return.triad.triad_base = triad_push_for_return;
            {

                if (right.isConstant()) {
                    // константа
                    triad_push_for_return.lexTypeTERMINAL = ((Interface_LexType) right.nodeBase).getType();
                    triad_push_for_return.lexemStr = ((Interface_Const) right.nodeBase).getLexem_();
                } else if (right.nodeBase.isTriad()) {
                    triad_push_for_return.triad = listTriads.get(right.nodeBase.triad_number).triad;
                    triad_push_for_return.triad_index = right.nodeBase.triad_number;
                } else if (right.nodeBase instanceof _NextNode_ID) {
                    triad_push_for_return.lexTypeTERMINAL = ((Interface_LexType) right.nodeBase).getType();
                    triad_push_for_return.lexemStr = ((Interface_Const) right.nodeBase).getLexem_();
                    triad_push_for_return.node = right;
                    triad_push_for_return.isNode = true;
                }
            }

        } else if (nodeBase instanceof _NextNode_StartLevel) {
            _NextNode_StartLevel nodeBase = (_NextNode_StartLevel) this.nodeBase;
            if (left != null) this.left.createTriads_str(listTriads);
            if (right != null) this.right.createTriads_str(listTriads);

        } else if (nodeBase instanceof _NextNode_Push_Param) {
            _NextNode_Push_Param nodeBase = (_NextNode_Push_Param) this.nodeBase;

            if (right != null) this.right.createTriads_str(listTriads);

            String first = "??";
            if (right.nodeBase.triad_number >= 0)
                first = "(" + right.nodeBase.triad_number.toString() + ")";
            else
                first = right.nodeBase.triad_lexem;

            final NextNode_Triad push = addTriad("push", first, null, listTriads);

            Triad_PUSH triad_push = new Triad_PUSH();
            push.triad.triad_base = triad_push;
            {
                if (right.isConstant()) {
                    // константа
                    triad_push.lexTypeTERMINAL = ((Interface_LexType) right.nodeBase).getType();
                    triad_push.lexemStr = ((Interface_Const) right.nodeBase).getLexem_();
                } else if (right.nodeBase.isTriad()) {
                    triad_push.triad = listTriads.get(right.nodeBase.triad_number).triad;
                    triad_push.triad_index = right.nodeBase.triad_number;
                } else if (right.nodeBase instanceof _NextNode_ID) {
                    triad_push.lexTypeTERMINAL = ((Interface_LexType) right.nodeBase).getType();
                    triad_push.lexemStr = ((Interface_Const) right.nodeBase).getLexem_();
                    triad_push.isNode = true;
                }
            }
        } else if (nodeBase instanceof _NextNode_Call) {
            _NextNode_Call callNodeBase = (_NextNode_Call) this.nodeBase;
            _NextNode_Func funcNodeBase = (_NextNode_Func) callNodeBase.func.nodeBase;
            if (left != null) this.left.createTriads_str(listTriads);
            final NextNode_Triad call = addTriad("call", funcNodeBase.lexem, null, listTriads);
            callNodeBase.triad_number = listTriads.size() - 1;

            Triad_CALL triad_call =
                    new Triad_CALL(callNodeBase.func, funcNodeBase.lexem, funcNodeBase.lexTypeTERMINAL);
            call.triad.triad_base = triad_call;


        } else if (nodeBase instanceof _NextNode_Shift) {
            if (right != null) this.right.createTriads_str(listTriads);

            _NextNode_Shift shiftNodeBase = (_NextNode_Shift) this.nodeBase;

            String first = "??";
            if (right.nodeBase.triad_number >= 0)
                first = "(" + right.nodeBase.triad_number.toString() + ")";
            else
                first = right.nodeBase.triad_lexem;

            addTriad("<<", first, shiftNodeBase.degree.toString(), listTriads);

            shiftNodeBase.triad_number = listTriads.size() - 1;
        } else {
            throw new Exception("us8188-xjjk " + nodeBase.getClass().getName());
        }


        return listTriads;
    }

    private void createMathOperation_Triads(NextNode nextNode, List<NextNode_Triad> list) throws Exception {

        if (nextNode.left != null) nextNode.left.createTriads_str(list);
        if (nextNode.right != null) nextNode.right.createTriads_str(list);

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

        NextNode_Triad currentTriad;
        if (nextNode.nodeBase instanceof _NextNode_Plus) {
            currentTriad = addTriad("+", first, second, list);
        } else if (nextNode.nodeBase instanceof _NextNode_Minus) {
            currentTriad = addTriad("-", first, second, list);
        } else if (nextNode.nodeBase instanceof _NextNode_Div) {
            currentTriad = addTriad("/", first, second, list);
        } else if (nextNode.nodeBase instanceof _NextNode_Star) {
            currentTriad = addTriad("*", first, second, list);
        } else if (nextNode.nodeBase instanceof _NextNode_Great) {
            currentTriad = addTriad(">", first, second, list);
        } else if (nextNode.nodeBase instanceof _NextNode_Great_Equal) {
            currentTriad = addTriad(">=", first, second, list);
        } else if (nextNode.nodeBase instanceof _NextNode_Less) {
            currentTriad = addTriad("<", first, second, list);
        } else if (nextNode.nodeBase instanceof _NextNode_Less_Equal) {
            currentTriad = addTriad("<=", first, second, list);
        } else if (nextNode.nodeBase instanceof _NextNode_Equal) {
            currentTriad = addTriad("==", first, second, list);
        } else if (nextNode.nodeBase instanceof _NextNode_Not_Equal) {
            currentTriad = addTriad("!=", first, second, list);
        } else
            throw new Exception("asdf7320jsj");


        Triad_Math_Operation triad_math_operation = new Triad_Math_Operation();
        currentTriad.triad.triad_base = triad_math_operation;
        {
            triad_math_operation.left_node = left;
            if (left.isConstant()) {
                // константа
                triad_math_operation.left_lexTypeTERMINAL = ((Interface_LexType) left.nodeBase).getType();
                triad_math_operation.left_lexemStr = ((Interface_Const) left.nodeBase).getLexem_();
            } else if (left.nodeBase.isTriad()) {
                triad_math_operation.left_triad = list.get(left.nodeBase.triad_number).triad;
                triad_math_operation.left_triad_index = left.nodeBase.triad_number;
            } else if (left.nodeBase instanceof _NextNode_ID) {
                triad_math_operation.left_lexTypeTERMINAL = ((Interface_LexType) left.nodeBase).getType();
                triad_math_operation.left_lexemStr = ((Interface_Const) left.nodeBase).getLexem_();
                triad_math_operation.left_isNode = true;
            }
        }

        {
            triad_math_operation.right_node = right;
            if (right.isConstant()) {
                // константа
                triad_math_operation.right_lexTypeTERMINAL = ((Interface_LexType) right.nodeBase).getType();
                triad_math_operation.right_lexemStr = ((Interface_Const) right.nodeBase).getLexem_();
            } else if (right.nodeBase.isTriad()) {
                triad_math_operation.right_triad = list.get(right.nodeBase.triad_number).triad;
                triad_math_operation.right_triad_index = right.nodeBase.triad_number;
            } else if (right.nodeBase instanceof _NextNode_ID) {
                triad_math_operation.right_lexTypeTERMINAL = ((Interface_LexType) right.nodeBase).getType();
                triad_math_operation.right_lexemStr = ((Interface_Const) right.nodeBase).getLexem_();
                triad_math_operation.right_isNode = true;

            }
        }

        nodeBase.triad_number = list.size() - 1;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void isConst_or_isVar(NextNode nextNode) {
//        if(nextNode.nodeBase instanceof _Nex)
    }

    private NextNode_Triad addTriad(String operation, String first, String second, List<NextNode_Triad> list) {
        final NextNode_Triad nextNode_triad = new NextNode_Triad(operation, first, second, list.size());
        list.add(nextNode_triad);
        return nextNode_triad;
    }


    public List<NextNode> get_A1() {
        if (this.nodeBase instanceof _NextNode_Star) {
            return ((_NextNode_Star) this.nodeBase).A_1;
        } else if (this.nodeBase instanceof _NextNode_Div) {
            return ((_NextNode_Div) this.nodeBase).A_1;
        } else if (this.nodeBase instanceof _NextNode_Plus) {
            return ((_NextNode_Plus) this.nodeBase).A_1;
        } else if (this.nodeBase instanceof _NextNode_Minus) {
            return ((_NextNode_Minus) this.nodeBase).A_1;
        } else return null;
    }

    public List<NextNode> get_A2() {
        if (this.nodeBase instanceof _NextNode_Star) {
            return ((_NextNode_Star) this.nodeBase).A_2;
        } else if (this.nodeBase instanceof _NextNode_Div) {
            return ((_NextNode_Div) this.nodeBase).A_2;
        } else if (this.nodeBase instanceof _NextNode_Plus) {
            return ((_NextNode_Plus) this.nodeBase).A_2;
        } else if (this.nodeBase instanceof _NextNode_Minus) {
            return ((_NextNode_Minus) this.nodeBase).A_2;
        } else return null;
    }

    public boolean isStar() {
        return this.nodeBase instanceof _NextNode_Star;
    }

    public boolean isDiv() {
        return this.nodeBase instanceof _NextNode_Div;
    }

    public boolean isOperand() {
        if (nodeBase instanceof _NextNode_ID ||
                nodeBase instanceof _NextNode_Int ||
                nodeBase instanceof _NextNode_Double ||
                nodeBase instanceof _NextNode_Call ||
                nodeBase instanceof _NextNode_Cast
        )
            return true;
        return false;
    }

    public boolean isMathOperation() {
        if (nodeBase instanceof _NextNode_Plus ||
                nodeBase instanceof _NextNode_Minus ||
                nodeBase instanceof _NextNode_Star ||
                nodeBase instanceof _NextNode_Div)
            return true;
        return false;
    }

    public boolean isConstant() {
        return (this.nodeBase instanceof _NextNode_Int) || (this.nodeBase instanceof _NextNode_Double);
    }

    public boolean isAssign() {
        return (this.nodeBase instanceof _NextNode_Assign);
    }

    // объявленная переменная со значением.
    public boolean isId_withValue() {
        if (this.nodeBase instanceof _NextNode_ID) {
            final _NextNode_ID nodeBase = (_NextNode_ID) this.nodeBase;
            final NextNode nextNode = nodeBase.nextNode;
            if (!(nextNode.nodeBase instanceof _NextNode_DeclareVariable))
                return false;
            _NextNode_DeclareVariable nextNode_declareVariable =
                    (_NextNode_DeclareVariable) nextNode.nodeBase;

            final String currentValue = nextNode_declareVariable.currentValue;
            if (currentValue != null)
                return true;
        }
        return false;
    }
    // объявленная переменная в математическом выражении

    public boolean isId() {
        return (this.nodeBase instanceof _NextNode_ID);
    }

    public boolean isDeclareVariable() {
        return (this.nodeBase instanceof _NextNode_DeclareVariable);
    }

    public boolean isCall() {
        return (this.nodeBase instanceof _NextNode_Call);
    }

    public boolean isCast() {
        return this.nodeBase instanceof _NextNode_Cast;
    }

    public boolean isInt() {
        return (this.nodeBase instanceof _NextNode_Int);
    }

    public boolean isDouble() {
        return (this.nodeBase instanceof _NextNode_Double);
    }
}
