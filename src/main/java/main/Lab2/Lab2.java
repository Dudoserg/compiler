package main.Lab2;

import main.Scaner;
import main.algoritm_1and2.maga.*;
import javafx.util.Pair;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static main.Lab2.LexType._SSS_;
import static main.algoritm_1and2.maga.ElemType.NOT_TERMINAL;
import static main.algoritm_1and2.maga.ElemType.TERMINAL;

public class Lab2 {


    public static void main(String[] args) throws Exception {
        Lab2 lab2 = new Lab2();
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

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

    Stack<Elem> magazin = new Stack<>();

    Map<Pair<LexType, LexType>, List<Sign>> table;

    public Lab2() throws Exception {
        table = readFromExcel("book_2.xls");
        this.rows = this.readFromFile(System.getProperty("user.dir") + "/grammar_change.txt");
        this.initRules();

        this.createLongRigthPart();

        this.start();
        System.out.println();
    }


    private void start() throws Exception {
        ScanerV2 scanerV2 = new ScanerV2(System.getProperty("user.dir") + "\\test.txt");
        magazin.push(new Elem(LexType._END, "#", NOT_TERMINAL));

        while (true) {
            List<Character> lexem = new ArrayList<>();
            LexType next = scanerV2.next(lexem);
            magazin.push(new Elem(next, lexem, TERMINAL));
            boolean rolled = false;
            do {
                printMagazine(magazin);
                rolled = roll(magazin);
            } while (rolled);
            System.out.print("");
        }
    }

    private boolean roll(Stack<Elem> magazin) throws Exception {

        // Отношения между парами в магазине
        List<List<Sign>> rel = new ArrayList<>();
        for (int i = 0; i < magazin.size() - 1; ++i) {
            Elem left = magazin.get(i);
            Elem right = magazin.get(i + 1);

            // X _SSS_ значит смотрим сквозь _SSS_
            if( right.lexType == _SSS_){
                right = magazin.get(i + 2);
            }
            if( left.lexType == _SSS_){
                left = magazin.get(i - 1);
            }
            List<Sign> strings = this.table.get(new Pair<>(left.lexType, right.lexType));
            rel.add(strings);
        }
        if (rel.size() == 0)
            return false;
        // Если в последнем отношении есть знак больше
        if (rel.get(rel.size() - 1).contains(Sign.GREAT)) {
            // Надо свернуть. Сворачиваем до знака <
            // TODO Сворачиваем до знака <=
            // Ищем индекс начала свертки
            List<Elem> partRoll = new ArrayList<>();
            int index = findStartRoll(rel, partRoll);

            // Напечатаем ее
            System.out.println( "Ща сворачиваем\t\t" + partRoll.stream()
                    .map(elem -> elem.getStrByType()).collect(Collectors.joining("  ")));

            RightPart rightPartEqual = null;

            // Ищем эту штуку в наших возможных правых частях (data)
            for (int i = data.size() - 1; i >= 0; --i) {
                List<RightPart> rightParts = data.get(i);
                // Если есть правила такой длины, и длина нам подходит
                if (rightParts.size() > 0 && rightParts.get(0).elemList.size() == partRoll.size()) {

                    // перебираем правые части, и ищем, совпала ли хоть одна из них с тем что имеем в магазине
                    for (RightPart rightPart : rightParts) {
                        // Проверим, чтобы она совпала с нашей вырезанной из магазина последовательностью
                        boolean isCurrentRuleEqual = true;
                        for (int k = 0; k < rightPart.elemList.size(); k++) {
                            // Если LexType НЕ совпадает , Значит эта правая часть не подходит
                            Elem currentElem = partRoll.get(k);
                            Elem rightPartElem = rightPart.elemList.get(k);
                            if (!currentElem.lexType.equals(rightPartElem.lexType)) {
                                isCurrentRuleEqual = false;        // Если хоть одно несовпадение в одном из правил
                                break;
                            }
                        }
                        if (isCurrentRuleEqual) {    // Если правая часть полностью совпала
                            rightPartEqual = rightPart;
                            break;
                        }
                    }
                }
                if (rightPartEqual != null)
                    break;
            }
            // Если нашли совпадение, то все отлично, просто в магазине меняем эту вырезанную часть на <# S #>
            if (rightPartEqual != null) {
                System.out.print("");
                partRoll.forEach(elem -> this.magazin.remove(elem));
                this.magazin.add(index, new Elem(_SSS_, "S", NOT_TERMINAL));
                return true;
            } else {
                throw new Exception("Ошибка!!1");
            }
        }
        return false;
    }


    private int findStartRoll(List<List<Sign>> rel, List<Elem> partRoll ){
        int index = -1;
        for (int i = rel.size() - 1; i >= 0; --i) {
            // если встретили конкретно <, НЕ <=
            if (rel.get(i).contains(Sign.LESS) && rel.get(i).size() == 1) {
                index = i;
                break;
            }
        }
        index++;

        // Теперь получаем часть магазина, для свертки
        //List<Elem> partRoll = new ArrayList<>();
        for (int i = index; i < rel.size(); ++i) {
            partRoll.add(magazin.get(i));
        }
        return index;
    }

    private void printMagazine(Stack<Elem> magazin) {
        System.out.println("================================================================");
        List<String> strList = new ArrayList<>();
        List<LexType> typeList = new ArrayList<>();
        for (Elem Elem : magazin) {
            System.out.println(Elem.str + "    " + Elem.lexType.getString());
            strList.add(Elem.str);
            typeList.add(Elem.lexType);
        }
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

        String strRes = "";
        String typeRes = "";
        for (int i = 0; i < strList.size() - 1; i++) {
            String str = strList.get(i);
            LexType type = typeList.get(i);

            if (typeList.get(i) == _SSS_) {
                strRes += ANSI_GREEN + str + ANSI_RESET;
                typeRes += ANSI_GREEN + type.getString() + ANSI_RESET;
            } else {
                strRes += str;
                typeRes += type.getString();
            }

            if (str.length() >= type.getString().length()) {
                for (int q = 0; q < Math.abs(type.getString().length() - str.length()); q++)
                    typeRes += " ";
            } else {
                for (int q = 0; q < Math.abs(type.getString().length() - str.length()); q++)
                    strRes += " ";
            }
            strRes += "    ";
            typeRes += "    ";


            /// Тут уже может оказаться S, с которым нет отношения, он прозрачный, смотрим дальше
            if (typeList.get(i + 1) == _SSS_) {
                List<Sign> strings = table.get(new Pair<>(typeList.get(i), typeList.get(i + 2)));
                strRes += ANSI_RED + strings.stream().map(Sign::getStr).collect(Collectors.joining()) + ANSI_RESET;
                typeRes += ANSI_RED + strings.stream().map(Sign::getStr).collect(Collectors.joining()) + ANSI_RESET;
            } else if (typeList.get(i) == _SSS_) {
                List<Sign> strings = table.get(new Pair<>(typeList.get(i - 1), typeList.get(i + 1)));
                strRes += ANSI_RED + strings.stream().map(Sign::getStr).collect(Collectors.joining()) + ANSI_RESET;
                typeRes += ANSI_RED + strings.stream().map(Sign::getStr).collect(Collectors.joining()) + ANSI_RESET;
            } else {
                List<Sign> strings = table.get(new Pair<>(typeList.get(i), typeList.get(i + 1)));
                strRes += strings.stream().map(Sign::getStr).collect(Collectors.joining());
                typeRes += strings.stream().map(Sign::getStr).collect(Collectors.joining());
            }

            strRes += "    ";
            typeRes += "    ";
        }

        strRes += strList.get(strList.size() - 1);
        typeRes += typeList.get(typeList.size() - 1);

        System.out.println(strRes);
        System.out.println(typeRes);
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
                    //System.out.println("name : " + name);
                    if (rowNum == 0)
                        headers.add(name);
                    currentRow.add(name);
                    if (name.equals("константа_8сс"));
                        //System.out.println();
                } else {
                    //System.out.println("cellNum = " + cellNum);
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
            mapAfter.put(new Pair<>(convertMap.get(key.getKey()), convertMap.get(key.getValue())), value);
        }
        System.out.print("");
        return mapAfter;
    }


}
