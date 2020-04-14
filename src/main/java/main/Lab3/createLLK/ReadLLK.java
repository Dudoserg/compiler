package main.Lab3.createLLK;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;
import lombok.Getter;
import lombok.Setter;
import main.algoritm_1and2.maga.Elem;
import main.algoritm_1and2.maga.RightPart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class ReadLLK {
    public static void main(String[] args) throws JsonProcessingException {
        ReadLLK readLLK = new ReadLLK();
    }

    private Table tableOjb;
    private Map<Pair<Elem, Elem>, List<RightPart>> table;


    public ReadLLK() throws JsonProcessingException {
        long startTime = System.currentTimeMillis();

        String json = readFromFile("файлики/LLK_table.json").stream().collect(Collectors.joining());

        ObjectMapper mapper = new ObjectMapper();

        tableOjb = mapper.readValue(json, Table.class);
        table = createTableMap(tableOjb);
        System.out.println("ReadLLK time = " + (System.currentTimeMillis() - startTime) / 1000.0 + " s.");

    }

    private  Map<Pair<Elem, Elem>, List<RightPart>> createTableMap(Table tableObj){
        Map<Pair<Elem, Elem>, List<RightPart>> table = new HashMap<>();
        for (TableElem tableElem : tableObj.tableElemList) {
            Pair<Elem,Elem> pair = new Pair<>(tableElem.left, tableElem.right);
            table.put(pair, tableElem.getRightPartList());
        }
        return table;
    }

    private List<String> readFromFile(String pathToFIle) {
        List<String> row = new ArrayList<>();
        try {
            File file = new File(pathToFIle);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                //System.out.println(line);
                row.add(line);
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return row;
    }
}
