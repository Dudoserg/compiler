package main.Lab2;

import com.sun.xml.internal.fastinfoset.algorithm.BooleanEncodingAlgorithm;
import javafx.util.Pair;
import main.SavePoint;
import main.algoritm_1and2.maga.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static main.Lab2.LexType.*;
import static main.algoritm_1and2.maga.ElemType.NOT_TERMINAL;
import static main.algoritm_1and2.maga.ElemType.TERMINAL;

public class Lab2 {


    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        Lab2 lab2 = new Lab2(true);
        lab2.start(System.getProperty("user.dir") + "/test.txt");

        System.out.println("\n\n\ntime = " + (System.currentTimeMillis() - start) / 1000.0 + " s.");
    }


    Map<String, LexType> convertMap = new HashMap<>();

    {
        convertMap.put("!=", LexType._NOT_EQUALLY);
        convertMap.put("#", LexType._END);
        convertMap.put("%", LexType._PERCENT);
        convertMap.put("(", LexType._PARENTHESIS_OPEN);
        convertMap.put(")", LexType._PARENTHESIS_CLOSE);
        convertMap.put("*", LexType._STAR);
        convertMap.put("+", LexType._PLUS);
        convertMap.put(",", LexType._COMMA);
        convertMap.put("-", LexType._MINUS);
        convertMap.put("/", LexType._SLASH);
        convertMap.put(";", LexType._SEMICOLON);
        convertMap.put("<", LexType._LESS);
        convertMap.put("<<", LexType._SHIFT_LEFT);
        convertMap.put("<=", LexType._LESS_EQUALLY);
        convertMap.put("=", LexType._ASSIGN);
        convertMap.put("==", LexType._EQUALLY);
        convertMap.put(">", LexType._GREAT);
        convertMap.put(">=", LexType._GREAT_EQUALLY);
        convertMap.put(">>", LexType._SHIFT_RIGHT);
        convertMap.put("const", LexType._CONST);
        convertMap.put("double", LexType._DOUBLE);
        convertMap.put("else", LexType._ELSE);
        convertMap.put("if", LexType._IF);
        convertMap.put("int", LexType._INT);
        convertMap.put("{", LexType._BRACE_OPEN);
        convertMap.put("}", LexType._BRACE_CLOSE);
        convertMap.put("идентификатор", LexType._ID);
        convertMap.put("конст.символьн.", LexType._TYPE_CHAR);
        convertMap.put("константа_10сс", LexType._TYPE_INT_10);
        convertMap.put("константа_16сс", LexType._TYPE_INT_16);
        convertMap.put("константа_8сс", LexType._TYPE_INT_8);
    }


    List<String> rows;
    List<Rule> rules;

    List<List<RightPart>> data = new ArrayList<>();

    //Stack<Elem> magazin = new Stack<>();
    Magaz magaz;

    Map<Pair<LexType, LexType>, List<Sign>> table;




    Boolean flagEnd = false;
    Boolean devMode;



    public Lab2(Boolean devMode) throws Exception {
        this.devMode = devMode;
        table = readFromExcel("book_LR.xls");
        System.out.println("===========table===============");
        for (Pair<LexType, LexType> lexTypeLexTypePair : table.keySet()) {
            LexType left = lexTypeLexTypePair.getKey();
            LexType right = lexTypeLexTypePair.getValue();
            List<Sign> signs = table.get(lexTypeLexTypePair);
            if (devMode)
                System.out.println(left + "    " + right + "      " +
                        signs.stream().map(sign -> sign.getStr()).collect(Collectors.joining()));
        }
        System.out.println("===========table-end===============");
        this.rows = this.readFromFile(System.getProperty("user.dir") + "/grammar_change.txt");
        this.initRules();

        this.createLongRigthPart();

//        System.out.println();
    }


    public boolean start(String filePath) throws Exception {
        ScanerV2 scanerV2 = new ScanerV2(filePath);
        magaz = new Magaz(devMode, table, data);
        magaz.push(new Elem(LexType._END, "#", NOT_TERMINAL));

        int countRead = 0;
        while (true) {
            List<Character> lexem = new ArrayList<>();
            LexType next = scanerV2.next(lexem);
            SavePoint savePoint = scanerV2.getSavePoint();
            magaz.push(new Elem(next, lexem, TERMINAL,savePoint));
            boolean rolled = false;
            System.out.println("countRead = " + (countRead));
            if (countRead == 20)
                System.out.print("");
            countRead++;
            do {
                magaz.checkError();
                magaz.printMagazineByTable();
                magaz.createRelBetweenMagazin();
                magaz.printMagazineByRel();

                rolled = magaz.roll();
                if (rolled) {
                    if (magaz.checkEnd()) {
                        flagEnd = true;
                        break;
                    }
                    magaz.checkError();
                    magaz.printMagazineByTable();
                    magaz.createRelBetweenMagazin();
                    magaz.printMagazineByRel();

                }
            } while (rolled);
            if (flagEnd) break;
            System.out.print("");
        }
        if (flagEnd) {
            System.out.println("НУ ТУТ Я НЕ ВИЖУ ОШИБОК (НЕ ФАКТ КОНЕЧНО ЧТО ИХ НЕТ)");
            return true;
        }
        return false;

    }


    // Объединяем все правые части в S, сортируем по размеру
    private void createLongRigthPart() {
        Rule S;

        S = new Rule();
        S.left = new Elem("S", NOT_TERMINAL);
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
        for (List<RightPart> datum : data) {
            for (RightPart rightPart : datum) {
                for (Elem elem : rightPart.elemList) {
                    elem.lexType = convertMap.get(elem.str);
                }
            }
        }
        /// ПРОПИСЫВАЕМ _SSS_ нетерминалам
        for (List<RightPart> datum : data) {
            for (RightPart rightPart : datum) {
                for (Elem elem : rightPart.elemList) {
                    if (elem.elementType == NOT_TERMINAL)
                        elem.lexType = _SSS_;
                }
            }
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
        //System.out.print("");
    }

    // Читаем таблицу с экселя
    public Map<Pair<LexType, LexType>, List<Sign>> readFromExcel(String file) throws IOException {
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet myExcelSheet = myExcelBook.getSheet("Birthdays");

        boolean isReadRows = true;
        int rowNum = 0;

        List<String> headers = new ArrayList<>();

        Map<Pair<String, String>, List<String>> map = new HashMap<>();


        // все строки
        List<Row> rowlList = new ArrayList<Row>();
        myExcelSheet.rowIterator().forEachRemaining(rowlList::add);

        /// ячейки первой строки
        List<Cell> firstRowCellList = new ArrayList<>();
        rowlList.get(0).cellIterator().forEachRemaining(firstRowCellList::add);

        // строим массив элементов ШАПКИ
        // firstRowCellList.remove(0);
        firstRowCellList.forEach(cell -> headers.add(cell.getStringCellValue()));

        // проходим все строки начиная со второй
        for (int r = 1; r < rowlList.size(); r++) {
            Row currentRow = rowlList.get(r);

            // список ячеек очередной строки
            List<Cell> currentRowCellList = new ArrayList<>();
            for (int i = 0; i < rowlList.size(); i++) {
                Cell cell = currentRow.getCell(i);
                currentRowCellList.add(cell);
            }

            String firstTerminal = currentRowCellList.get(0).getStringCellValue();
            for (int c = 1; c < currentRowCellList.size(); c++) {
                String secondTerminal = headers.get(c);
                if (currentRowCellList.get(c) == null)
                    continue;
                String cell = currentRowCellList.get(c).getStringCellValue();
                if (cell.isEmpty())
                    continue;
                cell = cell.trim();
                List<String> cellItems = Arrays.asList(cell.split(""));
                cellItems = cellItems.stream().filter(s -> !s.equals("\n")).collect(Collectors.toList());
                if (devMode)
                    System.out.println(firstTerminal + "    " + secondTerminal + "    " + cellItems);
                map.put(new Pair<>(firstTerminal, secondTerminal), cellItems);
            }
        }


        myExcelBook.close();


        // Переконвертируем наш хешмап в другой формат
//        Map<Pair<Elem, Elem>, List<String>> mapAfter = new HashMap<>();
//
//        for (Pair<String, String> key : map.keySet()) {
//            List<String> value = map.get(key);
//            Elem letf = new Elem(convertMap.get(key.getKey()), key.getKey());
//            Elem right = new Elem(convertMap.get(key.getValue()), key.getValue());
//            mapAfter.put(new Pair<>(letf, right), value);
//        }
//        System.out.print("");
        Map<Pair<LexType, LexType>, List<Sign>> mapAfter = new HashMap<>();

        for (Pair<String, String> key : map.keySet()) {
            List<Sign> value = map.get(key).stream().map(s -> {
                if (s.equals("="))
                    return Sign.EQUALS;
                else if (s.equals(">"))
                    return Sign.GREAT;
                else
                    return Sign.LESS;
            }).collect(Collectors.toList());
            if (key.getKey().equals("=="))
                System.out.print("");
            mapAfter.put(new Pair<>(convertMap.get(key.getKey()), convertMap.get(key.getValue())), value);
        }
        System.out.print("");
        return mapAfter;
    }

}
