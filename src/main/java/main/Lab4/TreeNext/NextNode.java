package main.Lab4.TreeNext;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import main.Lab3.Node;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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

    public void setLeft(NextNode vertex) {
        this.left = vertex;
        vertex.parent = this;
    }

    public void setRight(NextNode right) {
        this.right = right;
        right.parent = this;
    }

    public void print(FileWriter writer) throws Exception {
        String str = "";
        if (nodeBase instanceof _NextNode_Next) {
            writer.write("v" + this.id + "[style=filled, fillcolor=grey]" + "\n");
            writer.write("v" + this.id + "[label=\"" + "next" + "\"]" + "\n");
        }
        else if(nodeBase instanceof _NextNode_DeclareVariable){
            _NextNode_DeclareVariable nodeBase = (_NextNode_DeclareVariable) this.nodeBase;
            writer.write("v" + this.id + "" + "\n");
            writer.write("v" + this.id + "[label=\"" + nodeBase.node.lexem + "\"]" + "\n");
        }
        else if(nodeBase instanceof _NextNode_Assign){
            _NextNode_Assign nodeBase = (_NextNode_Assign) this.nodeBase;
            writer.write("v" + this.id + "" + "\n");
            writer.write("v" + this.id + "[label=\"" + "="+ "\"]" + "\n");
        }
        else if(nodeBase instanceof _NextNode_Div){
            writer.write("v" + this.id + "" + "\n");
            writer.write("v" + this.id + "[label=\"" + "/" + "\"]" + "\n");
        }
        else if(nodeBase instanceof _NextNode_Star){
            writer.write("v" + this.id + "" + "\n");
            writer.write("v" + this.id + "[label=\"" + "*" + "\"]" + "\n");
        }
        else if(nodeBase instanceof _NextNode_Plus){
            writer.write("v" + this.id + "" + "\n");
            writer.write("v" + this.id + "[label=\"" + "+" + "\"]" + "\n");
        }
        else if(nodeBase instanceof _NextNode_Int){
            _NextNode_Int nodeBase = (_NextNode_Int) this.nodeBase;
            writer.write("v" + this.id + "" + "\n");
            writer.write("v" + this.id + "[label=\"" + nodeBase.lexem + "\"]" + "\n");
        }
        else if(nodeBase instanceof _NextNode_Double){
            _NextNode_Double nodeBase = (_NextNode_Double) this.nodeBase;
            writer.write("v" + this.id + "" + "\n");
            writer.write("v" + this.id + "[label=\"" + nodeBase.lexem + "\"]" + "\n");
        } else if(nodeBase instanceof _NextNode_ID){
            _NextNode_ID nodeBase = (_NextNode_ID) this.nodeBase;
            writer.write("v" + this.id + "" + "\n");
            writer.write("v" + this.id + "[label=\"" + nodeBase.lexem + "\"]" + "\n");
        }
        else {
            throw  new Exception("ASD1ASDASD " + nodeBase.getClass().getName());
        }
        String isLeftRightNull = left == null && right == null ? "[style=invis]" : "";
        // прозрачный центр
        writer.write("v" + this.id + "center" + "[style=invis, width=0, label=\"\"];" + "\n");
        if(left == null && right == null){
            // если и правая и левая вершины нули, то и центр тоже не рисуем
            writer.write("v" + this.id + " -- " + "v" + this.id + "center" +
                    "[style=invis]" + "" + "\n");
        }else {
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
            this.left.print(writer);

        if (right != null)
            this.right.print(writer);
    }
}
