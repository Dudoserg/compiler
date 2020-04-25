package main.Lab4.TreeNext;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class TreeNext {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class TreeNextNode{
        public TreeNextNode parent;
        public TreeNextNode left;
        public TreeNextNode right;
        public String lexemStr;

    }
    TreeNextNode root;
    TreeNextNode current;
}
