package main.Lab4.TreeNext;

import lombok.AllArgsConstructor;
import main.Lab4.TreeNext.Relations.*;
import main.Lab4.TriadsByType._Triad_Base;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class NextNode_Triad {
    public String operation;

    public String first;
    public String second;
    public Integer index;

    public NextNode_Triad(String operation, String first, String second, Integer index) {
        this.operation = operation;
        this.first = first;
        this.second = second;
        this.index = index;
    }

    ///
    _Triad_Base triad_base;

}
