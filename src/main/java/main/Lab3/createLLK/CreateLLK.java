package main.Lab3.createLLK;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;
import main.Lab2.LexTypeTERMINAL;
import main.Lab3.LexTypeNot;
import main.algoritm_1and2.maga.*;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static main.algoritm_1and2.maga.ElemType.NOT_TERMINAL;
import static main.algoritm_1and2.maga.ElemType.TERMINAL;


public class CreateLLK {

    private static <T> List<T> reversedView(final List<T> list) {
        return new AbstractList<T>() {
            @Override
            public T get(int index) {
                return list.get(list.size() - 1 - index);
            }

            @Override
            public int size() {
                return list.size();
            }
        };
    }


    // Считанные с файла строки
    private final List<String> rows;
    // строки в виде объектов
    List<Rule> rules;

    Map<Elem, Set<Elem>> first = new LinkedHashMap<Elem, Set<Elem>>();
    Map<Pair<Elem, Elem>, List<RightPart>> table;
    Map<String, LexTypeTERMINAL> map_TERMINAL = new HashMap<>();
    private boolean devMode;

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        CreateLLK createLLK = new CreateLLK(true);
        System.out.println("CreateLLK time = " + (System.currentTimeMillis() - startTime) / 1000.0 + " s.");
    }

    public CreateLLK(boolean devMode) throws Exception {
        this.devMode = devMode;
        this.rows = this.readFromFile(System.getProperty("user.dir") + "/файлики/LLK_грамматика.txt");

        this.initRules();
        setLexemTypeToTable();

        // L()  - first Elem
        createFirstList();

        Set<Elem> тип = first.get(new Elem("одно_описание", NOT_TERMINAL));


        table = createTable();

        AddEpsilons();

        saveTable(table);


        Table tableObj = new Table();
        for (Pair<Elem, Elem> elemElemPair : table.keySet()) {
            Elem left = elemElemPair.getKey();
            Elem right = elemElemPair.getValue();
            List<RightPart> rightParts = table.get(elemElemPair);

            TableElem tableElem = new TableElem(left, right, rightParts);

            tableObj.tableElemList.add(tableElem);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));

        String json = mapper.writeValueAsString(tableObj);


        try (PrintWriter out = new PrintWriter("файлики/LLK_table.json")) {
            out.println(json);
        }

        System.out.println();
    }


    private void addOneMoreEpsilon(Elem left, Elem right) {
        RightPart epsilon = new RightPart();
        epsilon.elemList.add(new Elem(TERMINAL, "e", LexTypeTERMINAL._epsilon));

        Pair<Elem, Elem> pair = new Pair<>(left, right);
        List<RightPart> rightParts = this.table.get(pair);
        if (rightParts == null) {
            rightParts = new ArrayList<>();
            this.table.put(pair, rightParts);
        }
        rightParts.add(epsilon);

    }

    private void setLexemTypeToTable() {
        for (Rule rule : this.rules) {
            setLexemType_NOT(rule.left);
            for (RightPart part : rule.parts) {
                for (Elem elem : part.elemList) {
                    if (elem.elementType == TERMINAL) {
                        setLexemType_TERMINAL(elem);
                    } else if (elem.elementType == NOT_TERMINAL) {
                        setLexemType_NOT(elem);
                    }
                }
            }
        }
       /* for (Pair<Elem, Elem> elemElemPair : table.keySet()) {
            Elem left = elemElemPair.getKey();
            Elem right = elemElemPair.getValue();
            setLexemType_NOT(left);
            setLexemType_TERMINAL(right);

            List<RightPart> rightParts = table.get(elemElemPair);
            for (RightPart rightPart : rightParts) {
                for (Elem elem : rightPart.elemList) {
                    if (elem.elementType == TERMINAL) {
                        setLexemType_TERMINAL(elem);
                    } else if (elem.elementType == NOT_TERMINAL) {
                        setLexemType_NOT(elem);
                    }
                }
            }
        }*/
        System.out.println();
    }

    private void setLexemType_TERMINAL(Elem elem) {
        {
            map_TERMINAL.put("идентификатор", LexTypeTERMINAL._ID);
            map_TERMINAL.put("*", LexTypeTERMINAL._STAR);
            map_TERMINAL.put("/", LexTypeTERMINAL._SLASH);
            map_TERMINAL.put("%", LexTypeTERMINAL._PERCENT);
            map_TERMINAL.put("+", LexTypeTERMINAL._PLUS);
            map_TERMINAL.put("-", LexTypeTERMINAL._MINUS);
            map_TERMINAL.put("<<", LexTypeTERMINAL._SHIFT_LEFT);
            map_TERMINAL.put(">>", LexTypeTERMINAL._SHIFT_RIGHT);
            map_TERMINAL.put(">", LexTypeTERMINAL._GREAT);
            map_TERMINAL.put("<", LexTypeTERMINAL._LESS);
            map_TERMINAL.put(">=", LexTypeTERMINAL._GREAT_EQUALLY);
            map_TERMINAL.put("<=", LexTypeTERMINAL._LESS_EQUALLY);
            map_TERMINAL.put("==", LexTypeTERMINAL._EQUALLY);
            map_TERMINAL.put("!=", LexTypeTERMINAL._NOT_EQUALLY);
            map_TERMINAL.put("=", LexTypeTERMINAL._ASSIGN);
            map_TERMINAL.put("(", LexTypeTERMINAL._PARENTHESIS_OPEN);
            map_TERMINAL.put(")", LexTypeTERMINAL._PARENTHESIS_CLOSE);
            map_TERMINAL.put("{", LexTypeTERMINAL._BRACE_OPEN);
            map_TERMINAL.put("}", LexTypeTERMINAL._BRACE_CLOSE);
            map_TERMINAL.put(".", LexTypeTERMINAL._POINT);
            map_TERMINAL.put(",", LexTypeTERMINAL._COMMA);
            map_TERMINAL.put(";", LexTypeTERMINAL._SEMICOLON);
            map_TERMINAL.put("int", LexTypeTERMINAL._INT);
            map_TERMINAL.put("double", LexTypeTERMINAL._DOUBLE);
            map_TERMINAL.put("const", LexTypeTERMINAL._CONST);
            map_TERMINAL.put("if", LexTypeTERMINAL._IF);
            map_TERMINAL.put("else", LexTypeTERMINAL._ELSE);
            map_TERMINAL.put("main", LexTypeTERMINAL._MAIN);
            //map_TERMINAL.put("_INT", LexTypeTERMINAL._ERROR);
            map_TERMINAL.put("#", LexTypeTERMINAL._END);
            map_TERMINAL.put("константа_8сс", LexTypeTERMINAL._TYPE_INT_8);
            map_TERMINAL.put("константа_10сс", LexTypeTERMINAL._TYPE_INT_10);
            map_TERMINAL.put("константа_16сс", LexTypeTERMINAL._TYPE_INT_16);
            map_TERMINAL.put("конст.символьн.", LexTypeTERMINAL._TYPE_CHAR);
            map_TERMINAL.put("E", LexTypeTERMINAL._epsilon);
            map_TERMINAL.put("return", LexTypeTERMINAL._RETURN);
        }
        elem.lexTypeTERMINAL = map_TERMINAL.get(elem.str);

    }

    private void setLexemType_NOT(Elem elem) {
        elem.lexTypeNot = LexTypeNot.valueOf("_" + elem.str);
    }

    private void saveTable(Map<Pair<Elem, Elem>, List<RightPart>> table) throws Exception {
        int colision_count = 0;
        System.out.print("");
        Set<Elem> set_TERMINAL = new HashSet<>();
        for (Rule rule : this.rules) {
            for (RightPart part : rule.parts) {
                for (Elem elem : part.elemList) {
                    if (elem.elementType == TERMINAL)
                        set_TERMINAL.add(elem);
                }
            }
        }

        List<Elem> list_NOT_TERMINAL = new ArrayList<>();
        for (Rule rule : this.rules) {
            list_NOT_TERMINAL.add(rule.left);
        }
        List<Elem> list_TERMINAL = new ArrayList<>(set_TERMINAL);


        //////////////////// отсортируем
//        set_list = set_list.stream()
//                .sorted((first, second) -> {
//                    if (first.str.equals(CreateGrammar.firstGrammarName))
//                        return -1;
//                    else if (second.str.equals(CreateGrammar.firstGrammarName))
//                        return 1;
//                    else if (first.elementType.equals(ElemType.NOT_TERMINAL) && second.elementType.equals(ElemType.TERMINAL))
//                        return -1;
//                    else if (first.elementType.equals(ElemType.TERMINAL) && second.elementType.equals(ElemType.NOT_TERMINAL))
//                        return 1;
//                    else if (first.elementType.equals(second.elementType))
//                        return first.str.compareTo(second.str);
//                    return 0;
//                }).collect(Collectors.toList());

        // Начинаем создавать документ
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("list_1");
        Font redFont = book.createFont();
        redFont.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());

        Font blackFont = book.createFont();
        blackFont.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());

        // Создаем строки
        for (int i = 0; i < list_NOT_TERMINAL.size() + 1; i++) {
            // Нумерация начинается с нуля
            Row row = sheet.createRow(i);
        }
        Row row_1 = sheet.getRow(0);
        Cell cel_1 = row_1.createCell(0);
        cel_1.setCellValue("⬛");
        CellStyle style_1 = book.createCellStyle();
        style_1.setFont(blackFont);
        style_1.setAlignment(HorizontalAlignment.CENTER);
        style_1.setVerticalAlignment(VerticalAlignment.CENTER);
        cel_1.setCellStyle(style_1);
        //sheet.autoSizeColumn(0);


        // шапка горизонтальная
        for (int i = 0; i < list_TERMINAL.size(); i++) {
            Row row = sheet.getRow(0);
            Cell cell = row.createCell(i + 1);
            cell.setCellValue(list_TERMINAL.get(i).str);

            CellStyle cellStyle = book.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cell.setCellStyle(cellStyle);
        }


        // Вертикальный столбец
        for (int i = 0; i < list_NOT_TERMINAL.size(); i++) {
            Row row = sheet.getRow(i + 1);
            Cell cell = row.createCell(0);
            cell.setCellValue(list_NOT_TERMINAL.get(i).str);

            CellStyle cellStyle = book.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cell.setCellStyle(cellStyle);

        }
        // Задаем ширину
        for (int i = 0; i < list_TERMINAL.size() + 1; ++i) {
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 4 * 256);
        }
        for (int i = 0; i < list_NOT_TERMINAL.size() + 1; ++i) {
            Row row = sheet.getRow(i);
            row.setHeight((short) -1);
        }


        for (Pair<Elem, Elem> elemElemPair : table.keySet()) {
            Elem left = elemElemPair.getKey();
            Elem right = elemElemPair.getValue();
            List<RightPart> rightParts = table.get(elemElemPair);
            String str = rightParts.stream().map(rightPart -> {
                return rightPart.elemList.stream().map(elem -> {
                    return elem.getStrByType_LIGHT();
                }).collect(Collectors.joining(" "));
            }).collect(Collectors.joining("\n"));

            if (left.elementType != NOT_TERMINAL || right.elementType != TERMINAL)
                throw new Exception("Чета не то с типом");

            int row_index = list_NOT_TERMINAL.indexOf(left) + 1;
            int col_index = list_TERMINAL.indexOf(right) + 1;

            Row row = sheet.getRow(row_index);
            Cell cell = row.createCell(col_index);
            if (rightParts.size() > 1) {
                CellStyle style = book.createCellStyle();
                style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                style.setAlignment(HorizontalAlignment.CENTER);
                style.setVerticalAlignment(VerticalAlignment.CENTER);

                style.setWrapText(true);

                style.setFont(blackFont);

                paintInRed(redFont, str, cell);

                cell.setCellStyle(style);
            } else {
                CellStyle style = book.createCellStyle();

                style.setFont(blackFont);

                style.setAlignment(HorizontalAlignment.CENTER);
                style.setVerticalAlignment(VerticalAlignment.CENTER);

                paintInRed(redFont, str, cell);

                cell.setCellStyle(style);
            }


        }
        // Задаем ширину
        for (int i = 0; i < list_TERMINAL.size() + 1; ++i) {
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 4 * 256);
        }


        book.write(new FileOutputStream("файлики/LLK" + ".xls"));
        book.close();
        System.out.println("colision_count  =  " + colision_count);
    }

    private void paintInRed(Font redFont, String str, Cell cell) {
        // Ищем индексы где встречается @
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '@')
                indexList.add(i);
        }
        HSSFRichTextString richString = new HSSFRichTextString(str);
        for (int i = 0; i < indexList.size(); i += 2) {
            int indexFirst = indexList.get(i) - 1;
            int indexSecond = indexList.get(i + 1) + 2;
            richString.applyFont(indexFirst, indexSecond, redFont);
        }
        cell.setCellValue(richString);
    }

    private void createFirstList() {
        // инициализируем начальный список
        for (Rule rule : rules) {
            if (rule.left.str.equals("константа"))
                System.out.println();
            LinkedHashSet<Elem> elemsSet = new LinkedHashSet<>();
            // Добавляем первый элемент ( не важно терминал или нет)
            for (RightPart part : rule.parts) {
                if (part.elemList.size() > 0) {
                    Elem tmp = part.elemList.get(0);
                    if (tmp.elementType == TERMINAL)
                        setLexemType_TERMINAL(tmp);
                    if (tmp.elementType == NOT_TERMINAL)
                        setLexemType_NOT(tmp);
                    elemsSet.add(tmp);

                }
            }
            // Добавляем первый нетерминал (если он есть)
            for (RightPart part : rule.parts) {
                for (Elem elem : part.elemList) {
                    // Находим первый терминал, добавляем его и выходим из правила
                    if (elem.elementType == TERMINAL || elem.elementType == NOT_TERMINAL) {
                        if (elem.elementType == TERMINAL)
                            setLexemType_TERMINAL(elem);
                        if (elem.elementType == NOT_TERMINAL)
                            setLexemType_NOT(elem);
                        elemsSet.add(elem);
                        break;
                    }
                }
            }
            first.put(rule.left, elemsSet);
        }
        createLR(first);


        for (Elem elem : first.keySet()) {
            String str = "L( " + elem.getStrByType_SHARP() + " )";
            str += "  =>  ";
            Set<Elem> elems = first.get(elem);
            for (Elem elem1 : elems) {
                str += elem1.getStrByType_SHARP() + ", ";
            }
            if (devMode)
                System.out.println(str);
        }
    }

    private Map<Pair<Elem, Elem>, List<RightPart>> createTable() {
        Map<Pair<Elem, Elem>, List<RightPart>> table = new HashMap<>();

        for (Rule rule : this.rules) {
            Elem left = rule.left;
            for (RightPart part : rule.parts) {
                // нашли first первого
                Set<Elem> elems = firstTerminal(part);  // список на что начинается правая часть
                if (elems.size() == 0)
                    System.out.println();
                for (Elem elem : elems) {
                    Pair<Elem, Elem> pair = new Pair<>(left, elem);
                    List<RightPart> rules = table.get(pair);
                    if (rules == null) {
                        rules = new ArrayList<>();
                        table.put(pair, rules);
                    }
                    rules.add(part);
                    System.out.println();

                }
                System.out.println();

            }
        }
        return table;
    }

    private Set<Elem> firstTerminal(RightPart partT) {
        List<Elem> collect = partT.elemList.stream()
                .filter(elem -> elem.elementType == TERMINAL || elem.elementType == NOT_TERMINAL)
                .collect(Collectors.toList());

        Elem elem = collect.get(0);
        if (elem.elementType == TERMINAL) {
            Set<Elem> set = new LinkedHashSet<>();
            set.add(elem);
            return set;
        }
        Set<Elem> elems = first.get(elem);
        return elems;
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
            for (int i = 0; i < 10; ++i)
                line = line.replace("  ", " ");

            while (line != null) {
                if (devMode)
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

    private void createLR(Map<Elem, Set<Elem>> map) {
        while (true) {
            boolean isChanged = false;
            for (Elem key : map.keySet()) {
                if (key.str.equals("A2"))
                    System.out.println();
                Set<Elem> elemsSet = map.get(key); // текущий список
                // Копируем множество, чтобы сравнить, добавится ли элементы после итерации
                Set<Elem> copy_elemsSet = new LinkedHashSet<>(elemsSet);
                // Перебираем кажыдй элемент из множества, и смотрим его левую часть
                for (Elem elem : copy_elemsSet) {
                    Set<Elem> tmp = map.get(elem);
                    if (tmp != null) {
                        elemsSet.addAll(tmp);
                    }

                }
                isChanged = isChanged | (!copy_elemsSet.equals(elemsSet));
            }
            if (!isChanged)
                break;
        }
        // Удалим НЕтерминалы из множества
        for (Elem elem : map.keySet()) {
            Set<Elem> collect = map.get(elem).stream()
                    .filter(elem1 -> elem1.elementType == TERMINAL)

                    .collect(Collectors.toSet());
            map.put(elem, collect);
        }
    }


    private void AddEpsilons() {
        {
            Elem left = new Elem(NOT_TERMINAL, "_W4", LexTypeNot._W4);
            Elem right = new Elem(TERMINAL, "_PARENTHESIS_CLOSE", LexTypeTERMINAL._PARENTHESIS_CLOSE);
            this.addOneMoreEpsilon(left, right);
        }
        {
            Elem left = new Elem(NOT_TERMINAL, "_операторы_и_описания", LexTypeNot._операторы_и_описания);
            Elem right = new Elem(TERMINAL, "_BRACE_CLOSE", LexTypeTERMINAL._BRACE_CLOSE);
            this.addOneMoreEpsilon(left, right);
        }
        //<программа>  #
        {
            Elem left = new Elem(NOT_TERMINAL, "_программа", LexTypeNot._программа);
            Elem right = new Elem(TERMINAL, "_END", LexTypeTERMINAL._END);
            this.addOneMoreEpsilon(left, right);
        }
        // <R5>  ;
        {
            Elem left = new Elem(NOT_TERMINAL, "_R5", LexTypeNot._R5);
            Elem right = new Elem(TERMINAL, "_SEMICOLON", LexTypeTERMINAL._SEMICOLON);
            this.addOneMoreEpsilon(left, right);
        }
        // <R4>  ;
        {
            Elem left = new Elem(NOT_TERMINAL, "_R4", LexTypeNot._R4);
            Elem right = new Elem(TERMINAL, "_SEMICOLON", LexTypeTERMINAL._SEMICOLON);
            this.addOneMoreEpsilon(left, right);
        }
        // <R3>  ;
        {
            Elem left = new Elem(NOT_TERMINAL, "_R3", LexTypeNot._R3);
            Elem right = new Elem(TERMINAL, "_SEMICOLON", LexTypeTERMINAL._SEMICOLON);
            this.addOneMoreEpsilon(left, right);
        }
        // <R2>  ;
        {
            Elem left = new Elem(NOT_TERMINAL, "_R2", LexTypeNot._R2);
            Elem right = new Elem(TERMINAL, "_SEMICOLON", LexTypeTERMINAL._SEMICOLON);
            this.addOneMoreEpsilon(left, right);
        }
        // <R1>  ;
        {
            Elem left = new Elem(NOT_TERMINAL, "_R1", LexTypeNot._R1);
            Elem right = new Elem(TERMINAL, "_SEMICOLON", LexTypeTERMINAL._SEMICOLON);
            this.addOneMoreEpsilon(left, right);
        }
        // <W2>  ;
        {
            Elem left = new Elem(NOT_TERMINAL, "_W2", LexTypeNot._W2);
            Elem right = new Elem(TERMINAL, "_SEMICOLON", LexTypeTERMINAL._SEMICOLON);
            this.addOneMoreEpsilon(left, right);
        }
        // <R5>  ==
        {
            Elem left = new Elem(NOT_TERMINAL, "_R5", LexTypeNot._R5);
            Elem right = new Elem(TERMINAL, "_EQUALLY", LexTypeTERMINAL._EQUALLY);
            this.addOneMoreEpsilon(left, right);
        }
        // <R4>  ==
        {
            Elem left = new Elem(NOT_TERMINAL, "_R4", LexTypeNot._R4);
            Elem right = new Elem(TERMINAL, "_EQUALLY", LexTypeTERMINAL._EQUALLY);
            this.addOneMoreEpsilon(left, right);
        }
        // <R3>  ==
        {
            Elem left = new Elem(NOT_TERMINAL, "_R3", LexTypeNot._R3);
            Elem right = new Elem(TERMINAL, "_EQUALLY", LexTypeTERMINAL._EQUALLY);
            this.addOneMoreEpsilon(left, right);
        }
        // <R2>  ==
        {
            Elem left = new Elem(NOT_TERMINAL, "_R2", LexTypeNot._R2);
            Elem right = new Elem(TERMINAL, "_EQUALLY", LexTypeTERMINAL._EQUALLY);
            this.addOneMoreEpsilon(left, right);
        }
        // <R5>  )
        {
            Elem left = new Elem(NOT_TERMINAL, "_R5", LexTypeNot._R5);
            Elem right = new Elem(TERMINAL, "_PARENTHESIS_CLOSE", LexTypeTERMINAL._PARENTHESIS_CLOSE);
            this.addOneMoreEpsilon(left, right);
        }
        // <R4>  )
        {
            Elem left = new Elem(NOT_TERMINAL, "_R4", LexTypeNot._R4);
            Elem right = new Elem(TERMINAL, "_PARENTHESIS_CLOSE", LexTypeTERMINAL._PARENTHESIS_CLOSE);
            this.addOneMoreEpsilon(left, right);
        }
        // <R3>  )
        {
            Elem left = new Elem(NOT_TERMINAL, "_R3", LexTypeNot._R3);
            Elem right = new Elem(TERMINAL, "_PARENTHESIS_CLOSE", LexTypeTERMINAL._PARENTHESIS_CLOSE);
            this.addOneMoreEpsilon(left, right);
        }
        // <R2>  )
        {
            Elem left = new Elem(NOT_TERMINAL, "_R2", LexTypeNot._R2);
            Elem right = new Elem(TERMINAL, "_PARENTHESIS_CLOSE", LexTypeTERMINAL._PARENTHESIS_CLOSE);
            this.addOneMoreEpsilon(left, right);
        }
        // <R1>  )
        {
            Elem left = new Elem(NOT_TERMINAL, "_R1", LexTypeNot._R1);
            Elem right = new Elem(TERMINAL, "_PARENTHESIS_CLOSE", LexTypeTERMINAL._PARENTHESIS_CLOSE);
            this.addOneMoreEpsilon(left, right);
        }
        // <R5>  +
        {
            Elem left = new Elem(NOT_TERMINAL, "_R5", LexTypeNot._R5);
            Elem right = new Elem(TERMINAL, "_PLUS", LexTypeTERMINAL._PLUS);
            this.addOneMoreEpsilon(left, right);
        }
        // <R5>  -
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._R5), Elem.createEpsilon(LexTypeTERMINAL._MINUS));

        // <W9>  )
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._W9), Elem.createEpsilon(LexTypeTERMINAL._PARENTHESIS_CLOSE));

        // <W3>  ,
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._W3), Elem.createEpsilon(LexTypeTERMINAL._COMMA));

        // <W3>  ;
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._W3), Elem.createEpsilon(LexTypeTERMINAL._SEMICOLON));

        // <R5>  ,
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._R5), Elem.createEpsilon(LexTypeTERMINAL._COMMA));

        // <R4>  ,
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._R4), Elem.createEpsilon(LexTypeTERMINAL._COMMA));

        // <R3>  ,
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._R3), Elem.createEpsilon(LexTypeTERMINAL._COMMA));

        // <R2>  ,
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._R2), Elem.createEpsilon(LexTypeTERMINAL._COMMA));

        // <R1>  ,
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._R1), Elem.createEpsilon(LexTypeTERMINAL._COMMA));


        // Случай, когда функция без параметров
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._список_параметров), Elem.createEpsilon(LexTypeTERMINAL._PARENTHESIS_CLOSE));

        // Случай, для знака больше if( a > b)
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._R5), Elem.createEpsilon(LexTypeTERMINAL._GREAT));
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._R4), Elem.createEpsilon(LexTypeTERMINAL._GREAT));
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._R3), Elem.createEpsilon(LexTypeTERMINAL._GREAT));

        // Случай, для знака меньше if( a < b)
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._R5), Elem.createEpsilon(LexTypeTERMINAL._LESS));
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._R4), Elem.createEpsilon(LexTypeTERMINAL._LESS));
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._R3), Elem.createEpsilon(LexTypeTERMINAL._LESS));

        // Случай, для знака больше или равно if( a >= b)
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._R5), Elem.createEpsilon(LexTypeTERMINAL._GREAT_EQUALLY));
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._R4), Elem.createEpsilon(LexTypeTERMINAL._GREAT_EQUALLY));
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._R3), Elem.createEpsilon(LexTypeTERMINAL._GREAT_EQUALLY));

        // Случай, для знака меньше или равно if( a <= b)
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._R5), Elem.createEpsilon(LexTypeTERMINAL._LESS_EQUALLY));
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._R4), Elem.createEpsilon(LexTypeTERMINAL._LESS_EQUALLY));
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._R3), Elem.createEpsilon(LexTypeTERMINAL._LESS_EQUALLY));

        // Случай, для знака неравно if( a != b)
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._R5), Elem.createEpsilon(LexTypeTERMINAL._NOT_EQUALLY));
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._R4), Elem.createEpsilon(LexTypeTERMINAL._NOT_EQUALLY));
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._R3), Elem.createEpsilon(LexTypeTERMINAL._NOT_EQUALLY));
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._R2), Elem.createEpsilon(LexTypeTERMINAL._NOT_EQUALLY));


        //  <W7>  double
        // if( 0 - 2 + 5 > 3)
        //     int a = 2;
        // double cc = 228;
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._W7), Elem.createEpsilon(LexTypeTERMINAL._DOUBLE));

        // <W7>  int
        //i f( 0 - 2 + 5 > 3)
        //     int a = 2;
        // int cc = 228;
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._W7), Elem.createEpsilon(LexTypeTERMINAL._INT));


        // <операторы_и_описания>  return
//        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._операторы_и_описания), Elem.createEpsilon(LexTypeTERMINAL._RETURN));

        // <возврат>  }
//        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._возврат), Elem.createEpsilon(LexTypeTERMINAL._BRACE_CLOSE));


        //  <W7>  }
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._W7), Elem.createEpsilon(LexTypeTERMINAL._BRACE_CLOSE));
        //  <W7>  _RETURN
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._W7), Elem.createEpsilon(LexTypeTERMINAL._RETURN));


        //  <W7>  id
        this.addOneMoreEpsilon(Elem.createEpsilon(LexTypeNot._W7), Elem.createEpsilon(LexTypeTERMINAL._ID));
    }
}
