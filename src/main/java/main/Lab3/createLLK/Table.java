package main.Lab3.createLLK;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Table {
    List<TableElem> tableElemList;

    public Table() {
        this.tableElemList = new ArrayList<>();
    }
}
