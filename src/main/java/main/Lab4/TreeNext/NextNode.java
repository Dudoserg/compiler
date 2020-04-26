package main.Lab4.TreeNext;

import lombok.AllArgsConstructor;
import main.Lab4.TreeNext.Relations.*;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
        } else if (nodeBase instanceof _NextNode_DeclareVariable) {
            _NextNode_DeclareVariable nodeBase = (_NextNode_DeclareVariable) this.nodeBase;
            if (this == current)
                writer.write("v" + this.id + "[style=filled, fillcolor=yellow]" + "\n");
            else
                writer.write("v" + this.id + "[style=filled, fillcolor=\"#ebcccc\"]" + "\n");
            writer.write("v" + this.id + "[label=\"" + nodeBase.lexem + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_Assign) {
            _NextNode_Assign nodeBase = (_NextNode_Assign) this.nodeBase;
            if (this == current)
                writer.write("v" + this.id + "[style=filled, fillcolor=yellow]" + "\n");
            else
                writer.write("v" + this.id + "" + "\n");
            writer.write("v" + this.id + "[label=\"" + "=" + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_Div) {
            if (this == current)
                writer.write("v" + this.id + "[style=filled, fillcolor=yellow]" + "\n");
            else
                writer.write("v" + this.id + "[style=filled, fillcolor=\"#ccebe8\"]" + "\n");
            writer.write("v" + this.id + "[label=\"" + "/" + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_Star) {
            if (this == current)
                writer.write("v" + this.id + "[style=filled, fillcolor=yellow]" + "\n");
            else
                writer.write("v" + this.id + "[style=filled, fillcolor=\"#ccebe8\"]" + "\n");
            writer.write("v" + this.id + "[label=\"" + "*" + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_Plus) {
            if (this == current)
                writer.write("v" + this.id + "[style=filled, fillcolor=yellow]" + "\n");
            else
                writer.write("v" + this.id + "[style=filled, fillcolor=\"#ccebe8\"]" + "\n");
            writer.write("v" + this.id + "[label=\"" + "+" + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_Int) {

            _NextNode_Int nodeBase = (_NextNode_Int) this.nodeBase;
            if (this == current)
                writer.write("v" + this.id + "[style=filled, fillcolor=yellow]" + "\n");
            else
                writer.write("v" + this.id + "[style=filled, fillcolor=\"#e1e8bc\"]" + "\n");
            writer.write("v" + this.id + "[label=\"" + nodeBase.lexem + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_Double) {
            _NextNode_Double nodeBase = (_NextNode_Double) this.nodeBase;
            if (this == current)
                writer.write("v" + this.id + "[style=filled, fillcolor=yellow]" + "\n");
            else
                writer.write("v" + this.id + "[style=filled, fillcolor=\"#e1e8bc\"]" + "\n");
            writer.write("v" + this.id + "[label=\"" + nodeBase.lexem + "\"]" + "\n");
        } else if (nodeBase instanceof _NextNode_ID) {
            _NextNode_ID nodeBase = (_NextNode_ID) this.nodeBase;
            if (this == current)
                writer.write("v" + this.id + "[style=filled, fillcolor=yellow]" + "\n");
            else
                writer.write("v" + this.id + "[style=filled, fillcolor=\"#9991e3\"]" + "\n");
            writer.write("v" + this.id + "[label=\"" + nodeBase.lexem + "\"]" + "\n");
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
            writer.write("v" + this.id + "[style=filled, fillcolor=\"#000000\"]" + "\n");
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
            writer.write("v" + this.id + "[style=filled, fillcolor=\"#de7d0d\"]" + "\n");
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
            writer.write("v" + this.id + " -- " + "v" + left.id + "\n");
            rankSame.add("v" + left.id);
        } else {
            writer.write("v" + this.id + "notVisibleL" + "[style=invis]" + "\n");
            writer.write("v" + this.id + " -- " + "v" + this.id + "notVisibleL" + isLeftRightNull + "\n");
            rankSame.add("v" + this.id + "notVisibleL");
        }
        rankSame.add("v" + this.id + "center");

        if (right != null) {
            writer.write("v" + this.id + " -- " + "v" + right.id + "\n");
            rankSame.add("v" + right.id);
        } else {
            writer.write("v" + this.id + "notVisibleR" + "[style=invis]" + "\n");
            writer.write("v" + this.id + " -- " + "v" + this.id + "notVisibleR" + isLeftRightNull + "\n");
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
}
