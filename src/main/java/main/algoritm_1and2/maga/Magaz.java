package main.algoritm_1and2.maga;

import javafx.util.Pair;
import main.Lab2.LexType;

import java.util.*;
import java.util.stream.Collectors;

import static main.Lab2.LexType.*;
import static main.algoritm_1and2.maga.ElemType.NOT_TERMINAL;

public class Magaz {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public Stack<Elem> magazin = new Stack<>();
    Map<Pair<LexType, LexType>, List<Sign>> table;
    List<List<RightPart>> data;

    List<List<Sign>> rel = new ArrayList<>();       // отношения между элементами

    public Magaz(Map<Pair<LexType, LexType>, List<Sign>> table, List<List<RightPart>> data) {
        this.table = table;
        this.data = data;
    }

    public Elem get(int index) {
        return magazin.get(index);
    }

    public void push(Elem elem) {
        magazin.push(elem);
    }

    public Elem pop() {
        return magazin.pop();
    }

    public Elem peek() {
        return magazin.peek();
    }

    public void printMagazineByTable() {
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

    public void printMagazineByRel(){
//        System.out.println("================================================================");
        List<String> strList = new ArrayList<>();
        List<LexType> typeList = new ArrayList<>();
        for (Elem Elem : magazin) {
            //System.out.println(Elem.str + "    " + Elem.lexType.getString());
            strList.add(Elem.str);
            typeList.add(Elem.lexType);
        }
        //System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

        String strLex = "";
        String strType = "";

        for (int i = 0; i < strList.size() - 1; i++) {
            String str = strList.get(i);
            LexType type = typeList.get(i);

            if (typeList.get(i) == _SSS_) {
                strLex += ANSI_GREEN + str + ANSI_RESET;
                strType += ANSI_GREEN + type.getString() + ANSI_RESET;
            } else {
                strLex += str;
                strType += type.getString();
            }

            if (str.length() >= type.getString().length()) {
                for (int q = 0; q < Math.abs(type.getString().length() - str.length()); q++)
                    strType += " ";
            } else {
                for (int q = 0; q < Math.abs(type.getString().length() - str.length()); q++)
                    strLex += " ";
            }
            strLex += "    ";
            strType += "    ";

//            /// Тут уже может оказаться S, с которым нет отношения, он прозрачный, смотрим дальше
//            if (typeList.get(i + 1) == _SSS_) {
//                List<Sign> strings = table.get(new Pair<>(typeList.get(i), typeList.get(i + 2)));
//                strRes += ANSI_RED + strings.stream().map(Sign::getStr).collect(Collectors.joining()) + ANSI_RESET;
//                typeRes += ANSI_RED + strings.stream().map(Sign::getStr).collect(Collectors.joining()) + ANSI_RESET;
//            } else if (typeList.get(i) == _SSS_) {
//                List<Sign> strings = table.get(new Pair<>(typeList.get(i - 1), typeList.get(i + 1)));
//                strRes += ANSI_RED + strings.stream().map(Sign::getStr).collect(Collectors.joining()) + ANSI_RESET;
//                typeRes += ANSI_RED + strings.stream().map(Sign::getStr).collect(Collectors.joining()) + ANSI_RESET;
//            } else {
//                List<Sign> strings = table.get(new Pair<>(typeList.get(i), typeList.get(i + 1)));
//                strRes += strings.stream().map(Sign::getStr).collect(Collectors.joining());
//                typeRes += strings.stream().map(Sign::getStr).collect(Collectors.joining());
//            }

            List<Sign> signs = rel.get(i);
            String strSign = signs.stream().map(sign -> sign.getStr()).collect(Collectors.joining());
            strLex += strSign;
            strType += strSign;

            strLex += "    ";
            strType += "    ";
        }

        strLex += strList.get(strList.size() - 1);
        strType += typeList.get(typeList.size() - 1);

        System.out.println(strLex);
        System.out.println(strType);
    }

    public boolean roll() throws Exception {

        // Отношения между парами в магазине
        List<List<Sign>> rel = createRelBetweenMagazin();


        if (rel.size() == 0)
            return false;

        // Если в последнем отношении есть знак больше
        if (rel.get(rel.size() - 1).contains(Sign.GREAT)) {
            // Надо свернуть. Сворачиваем до знака <
            // TODO Сворачиваем до знака <=
            // Ищем индекс начала свертки
            List<Elem> partRoll = new ArrayList<>();
            int index = findPartRoll(rel, partRoll);

            // Напечатаем ее
            System.out.println("Ща сворачиваем\t\t" + partRoll.stream()
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
                    // если ниче не нашли, нужно попробовать свернуть поменьше
                    if (rightPartEqual == null) {
                        index += cutPartRoll(partRoll);
                        System.out.println("Ща сворачиваем\t\t" + partRoll.stream()
                                .map(elem -> elem.getStrByType()).collect(Collectors.joining("  ")));
                        // i = data.size() - 1;    // заново начинаем обход всех правых частей
                    }

                }
                if (rightPartEqual != null)
                    break;

            }
            // Если нашли совпадение, то все отлично, просто в магазине меняем эту вырезанную часть на <# S #>
            if (rightPartEqual != null) {
                System.out.print("");
               // partRoll.forEach(elem -> this.magazin.remove(elem));
                for(int i = 0; i < partRoll.size(); i++){
                    this.magazin.remove(index);
                }
                this.magazin.add(index, new Elem(_SSS_, "S", NOT_TERMINAL));
                return true;
            } else {
                throw new Exception("Ошибка!!1");
            }
        }
        return false;
    }

    public int findPartRoll(List<List<Sign>> rel, List<Elem> partRoll) {
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


    /**
     * метод укорачивает часть для свертки, удалив элементы из начала, до отношения <=
     *
     * @param partRoll часть для сворки
     * @return Возвращает количество отрезанных элементов
     * @throws Exception
     */
    public int cutPartRoll(List<Elem> partRoll) throws Exception {
        // обрезаем до первого <=
        int countCut = 0;
        for (int i = 0; i < partRoll.size() - 1; i++) {
            Elem left = partRoll.get(i);
            Elem right = partRoll.get(i + 1);

            // X _SSS_ значит смотрим сквозь _SSS_
            if (right.lexType == _SSS_) {
//                right = partRoll.get(i + 2);
                throw new Exception("А тут я еще не думал! 1");
            }
            if (left.lexType == _SSS_) {
//                left = partRoll.get(i - 1);
                throw new Exception("А тут я еще не думал! 1");
            }
            List<Sign> strings = this.table.get(new Pair<>(left.lexType, right.lexType));

            partRoll.remove(i);
            i--;
//            //проверяем ситуацию  = _SSS_ = XXX
//            if (left.lexType == _SSS_)
//                partRoll.remove(i); i--;    // remove XXX
            // Когда дошли до <=
            countCut++;
            if (strings.contains(Sign.LESS) && strings.contains(Sign.EQUALS)) {
                break;
            }
        }
        return countCut;
    }


    public List<List<Sign>> createRelBetweenMagazin() throws Exception {
        rel.clear();
        for (int i = 0; i < magazin.size() - 1; ++i) {
            Elem left = magazin.get(i);
            Elem right = magazin.get(i + 1);
            // Если встретили аксиому, то отношение в массив положим дважды _ASSIGN    >=    _SSS_    >=    _SEMICOLON
            boolean isSSS = false;
            // X _SSS_ значит смотрим сквозь _SSS_
            if (right.lexType == _SSS_) {
                right = magazin.get(i + 2);
                isSSS = true;
            }
            if (left.lexType == _SSS_) {
                //left = magazin.get(i - 1);
                //isSSS = true;
                throw new Exception("так далеко я еще не думал!!2");
            }
            List<Sign> strings = this.table.get(new Pair<>(left.lexType, right.lexType));
            if ((strings.contains(Sign.GREAT) && strings.contains(Sign.EQUALS)) ||
                    (strings.contains(Sign.LESS) && strings.contains(Sign.GREAT))) {
                // КОЛЛИЗИЯ МАТЬ ЕГО ЗА НОГУ
                System.out.println("коллизия");
                // _ASSIGN <= _SEMICOLON
                if (left.lexType == _ASSIGN && right.lexType == _SEMICOLON) {
                    if ((i - 2) >= 0 && magazin.get(i - 1).lexType == _ID && magazin.get(i - 2).lexType == _DOUBLE) {
                        // тогда отношение >
                        rel.add(Arrays.asList(Sign.GREAT));       // Если встретили аксиому, то отношение в массив положим дважды
                        if (isSSS) {
                            rel.add(Arrays.asList(Sign.GREAT));
                            i++;
                        }
                    }
                }
                if (left.lexType == _PARENTHESIS_CLOSE && right.lexType == _BRACE_OPEN) {
                    // _END    <    _INT    <=    _ID        =    _PARENTHESIS_OPEN    =    _PARENTHESIS_CLOSE    ><    _BRACE_OPEN
                    if ((i - 3) >= 0 && magazin.get(i - 1).lexType == _PARENTHESIS_OPEN && magazin.get(i - 2).lexType == _ID &&
                            magazin.get(i - 3).lexType == _INT) {
                        // тогда отношение <
                        rel.add(Arrays.asList(Sign.LESS));       // Если встретили аксиому, то отношение в массив положим дважды
                        if (isSSS) {
                            rel.add(Arrays.asList(Sign.LESS));
                            i++;
                        }
                    }
                    if ((i - 4) >= 0 && magazin.get(i - 1).lexType == _SSS_  && magazin.get(i - 2).lexType == _PARENTHESIS_OPEN &&
                            magazin.get(i - 3).lexType == _ID && magazin.get(i - 4).lexType == _INT) {
                        // тогда отношение >
                        rel.add(Arrays.asList(Sign.LESS));       // Если встретили аксиому, то отношение в массив положим дважды
                        if (isSSS) {
                            rel.add(Arrays.asList(Sign.LESS));
                            i++;
                        }
                    }
                }

            } else {
                rel.add(strings);       // Если встретили аксиому, то отношение в массив положим дважды
                // _ASSIGN    >=    _SSS_    >=    _SEMICOLON
                if (isSSS) {
                    rel.add(strings);
                    i++;
                }
            }
        }
        //

        return rel;
    }



}
