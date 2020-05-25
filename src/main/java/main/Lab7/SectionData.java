package main.Lab7;

import main.Lab4.TreeNext.NextNode;

public class SectionData {
    public String variable_str;
    public String type_str;
    public String value_str;
    public NextNode node;

    public SectionData(NextNode node, String variable_str, String type_str, String value_str) {
        this.variable_str = variable_str;
        this.type_str = type_str;
        if(value_str != null && !value_str.isEmpty())
            this.value_str = value_str;
        else
            this.value_str = "0";
        this.node = node;
    }
}
