package main.Lab2;

import main.Scaner;
import main.algoritm_1and2.maga.Elem;
import main.algoritm_1and2.maga.ElemType;
import main.algoritm_1and2.maga.RightPart;
import main.algoritm_1and2.maga.Rule;
import javafx.util.Pair;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lab2 {


    public static void main(String[] args) throws IOException {
        Lab2 lab2 = new Lab2();
    }

    List<String> rows;
    List<Rule> rules;

    List<List<RightPart>> data = new ArrayList<>();

    Stack<ReadSymbol> magazin = new Stack<>();

    Map<Pair<ReadSymbol, ReadSymbol>, List<String>> table ;

    public Lab2() throws IOException {
        table = readFromExcel("book_2.xls");
        this.rows = this.readFromFile(System.getProperty("user.dir") + "/grammar_change.txt");
        this.initRules();

        this.createLongRigthPart();

        this.start();
        System.out.println();
    }


    private void start() throws IOException {
        Scaner scaner = new Scaner(System.getProperty("user.dir") + "\\test.txt");
        magazin.push(new ReadSymbol(Scaner._END, "#"));

        while (true) {
            List<Character> lexem = new ArrayList<>();
            int next = scaner.next(lexem);
            magazin.push(new ReadSymbol(next, lexem));
            printMagazine(magazin);
            System.out.print("");
        }
    }

    private void printMagazine(Stack<ReadSymbol> magazin){
        System.out.println("========================================================");
        for (ReadSymbol readSymbol : magazin) {
            System.out.println(readSymbol.lexString + "    " + readSymbol.typ);
        }
    }

    // Объединяем все правые части в S, сортируем по размеру
    private void createLongRigthPart() {
        Rule S;

        S = new Rule();
        S.left = new Elem("S", ElemType.NOT_TERMINAL);
        for (Rule rule : this.rules) {
            S.parts.addAll(rule.parts);
        }
        IntStream.range(0, 20).forEach(value -> data.add(new ArrayList<>()));
        for (RightPart part : S.parts) {
            int size = part.elemList.size();
            data.get(size).add(part);
        }

        for (int i = data.size() - 1; i >= 0; --i) {
            if (data.get(i).size() == 0)
                data.remove(i);
            else
                break;
        }
    }

    // Читаем грамматику
    public List<String> readFromFile(String pathToFIle) {
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
                System.out.println(line);
                row.add(line);
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return row;
    }

    private void initRules() {
        rules = new ArrayList<>();
        //      <#S#> -> a <#A#> <#S#> <#B#> | c
        for (String row : rows) {
            String[] arr = row.split("->");
            if (arr.length != 2)
                continue;
            Rule rule = new Rule();
            final List<Object> typeleft = ElemType.getType(arr[0]);
            rule.left = new Elem((String) typeleft.get(0), (ElemType) typeleft.get(1));

            String[] rightParts = arr[1].split("\\|");
            //    a <#A#> <#S#> <#B#>       ,       c
            for (String rightPart : rightParts) {
                String[] elems = rightPart.split(" ");
                RightPart rightPart_object = new RightPart();
                // a    ,   <#A#>   ,   <#S#>   ,   <#B#>
                for (String elem : elems) {
                    elem = elem.trim();
                    if (elem.length() == 0)
                        continue;
                    final List<Object> type = ElemType.getType(elem);
                    Elem elem_object = new Elem((String) type.get(0), (ElemType) type.get(1));
                    rightPart_object.elemList.add(elem_object);
                }
                rule.parts.add(rightPart_object);
            }
            rules.add(rule);
        }
        System.out.print("");
    }

    // Читаем таблицу с экселя
    public static Map<Pair<ReadSymbol, ReadSymbol>, List<String>> readFromExcel(String file) throws IOException {
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet myExcelSheet = myExcelBook.getSheet("Birthdays");

        boolean isReadRows = true;
        int rowNum = 0;

        List<String> headers = new ArrayList<>();

        Map<Pair<String, String>, List<String>> map = new HashMap<>();

        while (isReadRows) {
            HSSFRow row = myExcelSheet.getRow(rowNum);  // Читаем строку
            if (row == null)
                break;
            boolean isReadCells = true;
            int cellNum = 0;

            List<String> currentRow = new ArrayList<>();
            while (isReadCells) {
                if (row.getCell(cellNum) != null && row.getCell(cellNum).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    String name = row.getCell(cellNum).getStringCellValue();
                    System.out.println("name : " + name);
                    if (rowNum == 0)
                        headers.add(name);
                    currentRow.add(name);
                    if (name.equals("константа_8сс"))
                        System.out.println();
                } else {
                    System.out.println("cellNum = " + cellNum);
                    break;
                }
                cellNum++;
            }

            // Если не первая строка, то начинаем обрабатывать
            if (rowNum != 0) {
                String terminalFirst = currentRow.get(0);
                for (int i = 1; i < currentRow.size(); i++) {
                    String terminalSecond = headers.get(i);
                    String cell = currentRow.get(i);
                    if (cell.isEmpty())
                        continue;
                    cell = cell.trim();
                    List<String> cellItems = Arrays.asList(cell.split(""));
                    cellItems = cellItems.stream().filter(s -> !s.equals("\n")).collect(Collectors.toList());
                    map.put(new Pair<>(terminalFirst, terminalSecond), cellItems);
                }
            }
            rowNum++;
        }

        myExcelBook.close();


        // Переконвертируем наш хешмап в другой формат
        Map<Pair<ReadSymbol, ReadSymbol>, List<String>> mapAfter = new HashMap<>();

        for (Pair<String, String> key : map.keySet()) {
            List<String> value = map.get(key);
            ReadSymbol letf = new ReadSymbol(convertMap.get(key.getKey()), key.getKey());
            ReadSymbol right = new ReadSymbol(convertMap.get(key.getValue()), key.getValue());
            mapAfter.put(new Pair<>(letf, right), value);
        }
        System.out.print("");
        return mapAfter;
    }


}
