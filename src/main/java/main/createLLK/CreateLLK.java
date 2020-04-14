package main.createLLK;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;
import main.algoritm_1and2.maga.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        CreateLLK createLLK = new CreateLLK();
        System.out.println("CreateLLK time = " + (System.currentTimeMillis() - startTime) / 1000.0 + " s.");
    }

    public CreateLLK() throws Exception {
        this.rows = this.readFromFile(System.getProperty("user.dir") + "/файлики/LLK_грамматика.txt");

        this.initRules();

        // L()  - first Elem
        createFirstList();

        Set<Elem> тип = first.get(new Elem("одно_описание", NOT_TERMINAL));

        table = createTable();

        try {
            saveTable(table);
        }catch (FileNotFoundException e){

        }

        Table tableObj = new Table();
        for (Pair<Elem, Elem> elemElemPair : table.keySet()) {
            Elem left = elemElemPair.getKey();
            Elem right = elemElemPair.getValue();
            List<RightPart> rightParts = table.get(elemElemPair);

            TableElem tableElem = new TableElem(left, right, rightParts);

            tableObj.tableElemList.add(tableElem);
        }


        ObjectMapper mapper  = new ObjectMapper();
        mapper.setVisibility(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));

        String json =mapper.writeValueAsString(tableObj);


        try (PrintWriter out = new PrintWriter("файлики/LLK_table.json")) {
            out.println(json);
        }

        System.out.println();
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

        // Создаем строки
        for (int i = 0; i < list_NOT_TERMINAL.size() + 1; i++) {
            // Нумерация начинается с нуля
            Row row = sheet.createRow(i);
        }
        sheet.getRow(0).createCell(0).setCellValue("⬛");
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


//        Row row = sheet.getRow(5);
//        Cell cell = row.createCell(5);
//        CellStyle backgroundStyle = book.createCellStyle();
//        backgroundStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
//        backgroundStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        cell.setCellStyle(backgroundStyle);
//        cell.setCellValue("hello");

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
            cell.setCellValue(str);
            if (rightParts.size() > 1) {
                CellStyle backgroundStyle = book.createCellStyle();
                backgroundStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
                backgroundStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                backgroundStyle.setWrapText(true);
                cell.setCellStyle(backgroundStyle);
            }
        }
        // Задаем ширину
        for (int i = 0; i < list_TERMINAL.size() + 1; ++i) {
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 4 * 256);
        }

       /* for (MyPair<Elem, Elem> elemElemMyPair : relations_Hashmap.keySet()) {
            List<Relations> relations = relations_Hashmap.get(elemElemMyPair);
            Elem left = elemElemMyPair.getKey();
            Elem right = elemElemMyPair.getValue();
            int left_index = -1;
            int right_index = -1;
            for(int i = 0 ; i < set_list.size(); ++i){
                if(left.str.equals(set_list.get(i).str) && left.elementType.equals(set_list.get(i).elementType))
                    left_index = i;
                if(right.str.equals(set_list.get(i).str) && right.elementType.equals(set_list.get(i).elementType))
                    right_index = i;
            }
            final String cellValue = relations
                    .stream()
                    .distinct()
                    .map(r -> r.sign.getStr()).collect(Collectors.joining("\n"));

            Row row = sheet.getRow(left_index + 1);

            Cell cell = row.createCell(right_index + 1);

            cell.setCellValue(cellValue);

            if (relations.size() >= 2)
                colision_count++;
        }*/

        // Теперь пройдемся по контенту
//        for (int i = 0; i < set_list.size(); ++i) {
//            for (int j = 0; j < set_list.size(); ++j) {
//                Elem elem_first = set_list.get(i);
//                Elem elem_second = set_list.get(j);
//
//                Row row = sheet.getRow(i + 1);
//
//                Cell cell = row.createCell(j + 1);
//                List<Relations> relations = relations_Hashmap.get(new MyPair<Elem, Elem>(elem_first, elem_second));
//                if( relations == null)
//                    break;
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////УДАЛЕНИЕ ДУБЛИКАТОВ знаков//////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                if (relations.size() > 1) {
//                    System.out.print("");
//                    HashSet<String> seen = new HashSet<>();
//                    relations.removeIf(e -> !seen.add(e.sign.getStr()));
//                }
//
//
//                final String cellValue = relations
//                        .stream()
//                        .distinct()
//                        .map(r -> r.sign.getStr()).collect(Collectors.joining("\n"));
//
//                cell.setCellValue(cellValue);
////
//                if (relations.size() >= 2) {
//                    colision_count++;
//
//                }
////				CellStyle cellStyle = book.createCellStyle();
////				cellStyle.setWrapText(true);
////				cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
////				cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
////				cell.setCellStyle(cellStyle);
//            }
//        }


        // Записываем всё в файл


        book.write(new FileOutputStream("файлики/LLK" + ".xls"));
        book.close();
        System.out.println("colision_count  =  " + colision_count);
    }

    private void createFirstList() {
        // инициализируем начальный список
        for (Rule rule : rules) {
            LinkedHashSet<Elem> elemsSet = new LinkedHashSet<>();
            // Добавляем первый элемент ( не важно терминал или нет)
            for (RightPart part : rule.parts) {
                if (part.elemList.size() > 0)
                    elemsSet.add(part.elemList.get(0));
            }
            // Добавляем первый нетерминал (если он есть)
            for (RightPart part : rule.parts) {
                for (Elem elem : part.elemList) {
                    // Находим первый терминал, добавляем его и выходим из правила
                    if (elem.elementType == TERMINAL || elem.elementType == NOT_TERMINAL) {
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

    private Set<Elem> firstTerminal(RightPart part) {
        Elem elem = part.elemList.get(0);
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
                    if (tmp != null)
                        elemsSet.addAll(tmp);
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
}
