package main.algoritm_1and2.maga;

import javafx.util.Pair;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static main.algoritm_1and2.maga.ElemType.NOT_TERMINAL;
import static main.algoritm_1and2.maga.ElemType.TERMINAL;

public class CreateGrammar_LR {

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

    public int ALGORITM = 2;
    // Считанные с файла строки
    List<String> rows;
    // строки в виде объектов
    List<Rule> rules;

    List<Relations> relations = new ArrayList<>();

    //	HashMap<String, String> relations_Hashmap = new HashMap<String, String>();
    Map<MyPair<Elem, Elem>, List<Relations>> relations_Hashmap = new HashMap<MyPair<Elem, Elem>, List<Relations>>();

    // Все пары
    List<Pair<Elem, Elem>> pairs;

    public static String firstGrammarName = "программа";

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        CreateGrammar_LR createGrammar = new CreateGrammar_LR();
        long finish = System.currentTimeMillis();
        System.out.println("\n\n\ntime = " + (finish - start) / 1000.0 + " s.");
    }

    public CreateGrammar_LR() throws Exception {


        this.rows = this.readFromFile(System.getProperty("user.dir") + "/grammar_change.txt");
        CreateGrammar_LR.firstGrammarName = "S";

        this.initRules();

        // L()
        Map<Elem, Set<Elem>> mapL = new LinkedHashMap<>();
        {
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
                        if (elem.elementType == TERMINAL) {
                            elemsSet.add(elem);
                            break;
                        }
                    }
                }
                mapL.put(rule.left, elemsSet);
            }
            createLR(mapL);


            for (Elem elem : mapL.keySet()) {
                String str = "L( " + elem.getStrByType_SHARP() + " )";
                str += "  =>  ";
                Set<Elem> elems = mapL.get(elem);
                for (Elem elem1 : elems) {
                    str += elem1.getStrByType_SHARP() + ", ";
                }
                System.out.println(str);
            }

        }

        // R()
        Map<Elem, Set<Elem>> mapR = new LinkedHashMap<>();
        {
            // инициализируем начальный список
            for (Rule rule : rules) {
                LinkedHashSet<Elem> elemsSet = new LinkedHashSet<>();
                // Добавляем первый элемент ( не важно терминал или нет)
                for (RightPart part : rule.parts) {
                    if (part.elemList.size() > 0)
                        elemsSet.add(reversedView(part.elemList).get(0));
                }
                // Добавляем первый нетерминал (если он есть)
                for (RightPart part : rule.parts) {
                    for (Elem elem : reversedView(part.elemList)) {
                        // Находим первый терминал, добавляем его и выходим из правила
                        if (elem.elementType == TERMINAL) {
                            elemsSet.add(elem);
                            break;
                        }
                    }
                }
                mapR.put(rule.left, elemsSet);
            }
            createLR(mapR);

            for (Elem elem : mapR.keySet()) {
                String str = "R( " + elem.getStrByType_SHARP() + " )";
                str += "  =>  ";
                Set<Elem> elems = mapR.get(elem);
                for (Elem elem1 : elems) {
                    str += elem1.getStrByType_SHARP() + ", ";
                }
                System.out.println(str);
            }

        }

        

        // create Pair
        Set<Pair<Elem, Elem>> pairs = createPairs();
        System.out.print("\n\n");
        for (Pair<Elem, Elem> pair : pairs) {
            System.out.println(pair.getKey().getStrByType_SHARP() + "    " + pair.getValue().getStrByType_SHARP());
        }
        // create Relations > <
        relations_Hashmap = createRelations(pairs, mapL, mapR);
        for (MyPair<Elem, Elem> elemElemMyPair : relations_Hashmap.keySet()) {
            List<Relations> relations = relations_Hashmap.get(elemElemMyPair);
            if (relations.size() > 1) {
                relations = new ArrayList<Relations>(new HashSet<Relations>(relations));
                relations = relations.stream()
                        .distinct()
                        .collect(Collectors.toList());

                if (relations.get(0).leftElem.str.equals("конст.символьн."))
                    System.out.print("");

                relations_Hashmap.put(elemElemMyPair, relations);
                System.out.print("");
            }
        }
        // create Relations =
        for (Rule rule : rules) {
            for (RightPart part : rule.parts) {
                List<Elem> copy = new ArrayList<>(part.elemList);
                copy = copy.stream()
                        .filter(elem -> elem.elementType == TERMINAL)
                        .collect(Collectors.toList());
                for(int i = 0 ; i < copy.size() - 1 ; ++i){
                    Elem first = copy.get(i);
                    Elem second = copy.get(i + 1);
                    List<Relations> relations = this.relations_Hashmap.get(new MyPair<>(first, second));
                    if (relations == null) {
                        relations = new ArrayList<>();
                        relations_Hashmap.put(new MyPair<>(first, second), relations);
                    }
                    relations.add(new Relations(first, second, Sign.EQUALS));
                }
            }
        }
        this.printTable();


    }

    public Map<MyPair<Elem, Elem>, List<Relations>> createRelations(
            Set<Pair<Elem, Elem>> pairs, Map<Elem, Set<Elem>> mapL, Map<Elem, Set<Elem>> mapR) throws Exception {
        Map<MyPair<Elem, Elem>, List<Relations>> hashMap = new HashMap<>();

        for (Pair<Elem, Elem> pair : pairs) {
            Elem left = pair.getKey();
            Elem right = pair.getValue();

            // R(E) > +
            if (left.elementType == NOT_TERMINAL && right.elementType == TERMINAL) {
                Sign sign = Sign.GREAT;
                Set<Elem> setR = mapR.get(left);
                for (Elem elem : setR) {
                    MyPair<Elem, Elem> pairForFound = new MyPair<>(elem, right);
                    List<Relations> relations = hashMap.get(pairForFound);
                    if (relations == null) {
                        relations = new ArrayList<>();
                        hashMap.put(pairForFound, relations);
                    }
                    relations.add(new Relations(elem, right, sign));
                }
            } else if (left.elementType == TERMINAL && right.elementType == NOT_TERMINAL) {
                Sign sign = Sign.LESS;
                Set<Elem> setL = mapL.get(right);
                for (Elem elem : setL) {
                    MyPair<Elem, Elem> pairForFound = new MyPair<>(left, elem);
                    List<Relations> relations = hashMap.get(pairForFound);
                    if (relations == null) {
                        relations = new ArrayList<>();
                        hashMap.put(pairForFound, relations);
                    }
                    relations.add(new Relations(left, elem, sign));
                }
            } else
                throw new Exception("two equal types!");


        }

        return hashMap;
    }

    public Set<Pair<Elem, Elem>> createPairs() {
        Set<Pair<Elem, Elem>> pairs = new LinkedHashSet<>();

        // две начальные пары, С аксиомой
        Elem sharp = new Elem("#", TERMINAL);
        pairs.add(new Pair<>(rules.get(0).left, sharp));
        pairs.add(new Pair<>(sharp, rules.get(0).left));

        for (Rule rule : rules) {
            for (RightPart part : rule.parts) {
                Set collect = IntStream.range(1, part.elemList.size())
                        .mapToObj(i -> new Pair(part.elemList.get(i - 1), part.elemList.get(i)))
                        .collect(Collectors.toSet());
                pairs.addAll(collect);
            }
        }
        pairs = pairs.stream()
                .filter(pair -> pair.getKey().elementType != pair.getValue().elementType)
                .collect(Collectors.toSet());

        System.out.print("");
        return pairs;
    }


    public void createLR(Map<Elem, Set<Elem>> map) {
        while (true) {
            boolean isChanged = false;
            for (Elem key : map.keySet()) {
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


//    private void createHashMap() throws IOException {
////        Map<MyPair<Elem,Elem>, List<Relations>> relations_Hashmap = new HashMap<>();
////        Set<Elem> set = new HashSet<>();
////        set.add(new Elem("#", TERMINAL));
//
////        for (int i = 0; i < this.rules.size(); i++) {
////            final Rule rule = this.rules.get(i);
////            set.add(rule.left);
////
////            for (RightPart part : rule.parts) {
////                for (Elem elem : part.elemList) {
////                    set.add(elem);
////                }
////            }
////        }
////        for (Elem elem : set) {
////            for (Elem elem1 : set) {
////                this.relations_Hashmap.put(new MyPair<>(elem, elem1), new ArrayList<>());
////            }
////        }
////
////        for (int i = 0; i < this.relations.size(); ++i) {
////            final Relations relations = this.relations.get(i);
////
////            MyPair<Elem, Elem> elemElemMyPair = new MyPair<>(relations.leftElem, relations.rightElem);
////            // Ищем ячейку
////            List<Relations> cell = this.relations_Hashmap.get(elemElemMyPair);
////            cell.add(relations);
////        }
//
////        EXCEL.printTable(set, this.relations_Hashmap, algoritm);
//    }


    public void printTable() throws IOException {
        int colision_count = 0;
        System.out.print("");
        Set<Elem> set = new HashSet<>();
        for (MyPair<Elem, Elem> elemElemMyPair : relations_Hashmap.keySet()) {
//            List<Relations> relations = relations_Hashmap.get(elemElemMyPair);
            set.add(elemElemMyPair.getKey());
            set.add(elemElemMyPair.getValue());
        }

        List<Elem> set_list = new ArrayList<>();
        for (Elem elem : set) {
            if (!elem.elementType.equals(ElemType.NOT_TERMINAL))
                set_list.add(elem);
        }

        //////////////////// отсортируем
        set_list = set_list.stream()
                .sorted((first, second) -> {
                    if (first.str.equals(CreateGrammar.firstGrammarName))
                        return -1;
                    else if (second.str.equals(CreateGrammar.firstGrammarName))
                        return 1;
                    else if (first.elementType.equals(ElemType.NOT_TERMINAL) && second.elementType.equals(ElemType.TERMINAL))
                        return -1;
                    else if (first.elementType.equals(ElemType.TERMINAL) && second.elementType.equals(ElemType.NOT_TERMINAL))
                        return 1;
                    else if (first.elementType.equals(second.elementType))
                        return first.str.compareTo(second.str);
                    return 0;
                }).collect(Collectors.toList());

        // Начинаем создавать документ
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("Birthdays");

        // Создаем строки
        for (int i = 0; i < set_list.size() + 1; i++) {
            // Нумерация начинается с нуля
            Row row = sheet.createRow(i);
        }
        sheet.getRow(0).createCell(0).setCellValue("⬛");
        //sheet.autoSizeColumn(0);


        // шапка горизонтальная
        for (int i = 0; i < set_list.size(); i++) {
            Row row = sheet.getRow(0);
            Cell cell = row.createCell(i + 1);
            cell.setCellValue(
                    set_list.get(i).elementType.equals(ElemType.NOT_TERMINAL) ?
                            "<" + set_list.get(i).str + ">"
                            : set_list.get(i).str
            );

            CellStyle cellStyle = book.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cell.setCellStyle(cellStyle);
        }


        // Вертикальный столбец
        for (int i = 0; i < set_list.size(); i++) {
            Row row = sheet.getRow(i + 1);
            Cell cell = row.createCell(0);
            cell.setCellValue(
                    set_list.get(i).elementType.equals(ElemType.NOT_TERMINAL) ?
                            "<" + set_list.get(i).str + ">"
                            : set_list.get(i).str
            );

            CellStyle cellStyle = book.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cell.setCellStyle(cellStyle);

        }
        // Задаем ширину
        for (int i = 0; i < set.size() + 1; ++i) {
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 4 * 256);
        }

        for (MyPair<Elem, Elem> elemElemMyPair : relations_Hashmap.keySet()) {
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
        }
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


        book.write(new FileOutputStream("book_LR" + ".xls"));
        book.close();
        System.out.println("colision_count  =  " + colision_count);
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


}