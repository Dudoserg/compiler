package main.createLLK;

import lombok.Getter;
import lombok.NoArgsConstructor;
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
