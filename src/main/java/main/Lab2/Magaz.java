package main.Lab2;

import javafx.util.Pair;
import main.algoritm_1and2.maga.Elem;
import main.algoritm_1and2.maga.RightPart;
import main.algoritm_1and2.maga.Sign;

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
    int countCollision = 0;
    int countFindRoll = 0;
    Boolean flag_PARENTHESIS_OPEN = false;


    Boolean devMode;

    public Magaz(Boolean devMode, Map<Pair<LexType, LexType>, List<Sign>> table, List<List<RightPart>> data) {
        this.devMode = devMode;
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
        if (!devMode)
            return;
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
            if (i == 10)
                System.out.print("");
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

    public void printMagazineByRel() {
        if (!devMode)
            return;
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
                for (int i = 0; i < partRoll.size(); i++) {
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
            Elem elem = magazin.get(i);
            if (rel.get(i).contains(Sign.LESS) && rel.get(i).size() == 1) {
                index = i;
                if (elem.lexType == _SSS_)
                    index--;
                break;
            }
        }
        index++;

        // Теперь получаем часть магазина, для свертки
        //List<Elem> partRoll = new ArrayList<>();
        for (int i = index; i < rel.size(); ++i) {
            partRoll.add(magazin.get(i));
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11
        // и без коллизий бывают косяки
        // вот тут, он сначала сворачивает INT ID, как буд-то это список параметров функции, А ЭТО список переменных, и тут сначала надо свернуть _ID
        // int     <=    privet2    =    (                    =    S        =    )                     <    {              <    int     <=    c      >    ,
        //  _INT    <=    _ID        =    _PARENTHESIS_OPEN    =    _SSS_    =    _PARENTHESIS_CLOSE    <    _BRACE_OPEN    <    _INT    <=    _ID    >    _COMMA
        // сейчас мы НЕ в списке параметров, значит сворачиваем только _ID

        System.out.println("countFindRoll = " + countFindRoll);

        if (!(findFirst_PARENTHESIS() == _PARENTHESIS_OPEN)) {
            //  <    _INT    _IDугу
            if (partRoll.size() == 2 && partRoll.get(0).lexType == _INT && partRoll.get(1).lexType == _ID) {
                partRoll.remove(0);
                index++;
            }
            //  <    _INT    <    _SSS_    <    _COMMA    <=    _ID    >    _SEMICOLON
            // тут надо свернуть сначала _ID, поэтому удаляем _SSS_ и _COMMA
            if (partRoll.size() == 3 && partRoll.get(0).lexType == _SSS_ && partRoll.get(1).lexType == _COMMA && partRoll.get(2).lexType == _ID) {
                partRoll.remove(0); // _SSS_
                partRoll.remove(0); // _COMMA
                index++;
                index++;
            }
        }
        countFindRoll++;
        return index;
    }

    // Ищем первую скобку от знака > До дна магазина. Это небходимо для разрешения проблемы int x
    // ( понять это список переменны или список параметров функции)
    public LexType findFirst_PARENTHESIS() {
        for (int i = magazin.size() - 2; i >= 0; i--) {
            if (magazin.get(i).lexType == _PARENTHESIS_CLOSE)
                return _PARENTHESIS_CLOSE;
            if (magazin.get(i).lexType == _PARENTHESIS_OPEN)
                return _PARENTHESIS_OPEN;
        }
        return null;
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
            int index_Left = i;
            int index_Right = i + 1;
            Elem left = partRoll.get(index_Left);
            Elem right = partRoll.get(index_Right);

            // X _SSS_ значит смотрим сквозь _SSS_
            if (right.lexType == _SSS_) {
//                right = partRoll.get(i + 2);
                System.out.print("");
                throw new Exception("А тут я еще не думал! 1");
            }
            if (left.lexType == _SSS_) {
//                left = partRoll.get(i - 1);
                System.out.print("");
                partRoll.remove(i);
                countCut++;
                left = partRoll.get(index_Left);
                right = partRoll.get(index_Right);
                //throw new Exception("А тут я еще не думал! 1");
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


    private int rel_ADD(Sign sign, Boolean isSSS, int i) {
        rel.add(Arrays.asList(sign));       // Если встретили аксиому, то отношение в массив положим дважды
        if (isSSS) {
            rel.add(Arrays.asList(sign));
            i++;
        }
        return i;
    }

    private boolean checkCollision(int index_RIGHT, List<Elem> list) {
        // ПРоверяем действительно ли в магазине сейчас лежит такое сверху
        int countSSS = 0;
        boolean isEqual = true;
        for (int i = 0; i < list.size(); i++) {
            Elem listElem = list.get(list.size() - 1 - i);
            Elem magazElem = null;
            do {
                magazElem = magazin.get(index_RIGHT - i - countSSS);

                // Если в листе сейчас тоже _SSS_ то мы просто выйдем, и сравним их
                if (magazElem.lexType == _SSS_ && listElem.lexType == _SSS_)
                    break;
                // Если в листе не _SSS_ То и в магазине надо найти следующий не _SSS_
                if (magazElem.lexType == _SSS_)
                    countSSS++;
            } while (magazElem.lexType == _SSS_);
            if (listElem.lexType != magazElem.lexType) {
                isEqual = false;
                break;
            }
        }
        return isEqual;
    }

    private boolean checkCollision_STRONG(int index_RIGHT, List<Elem> list) {
        // ПРоверяем действительно ли в магазине сейчас лежит такое сверху
        for (int i = 0; i < list.size(); i++) {
            Elem listElem = list.get(list.size() - 1 - i);
            Elem magazElem = magazin.get(index_RIGHT - i);
            if (listElem.lexType != magazElem.lexType) {
                // ИСКЛЮЧИТЕЛЬНАЯ СИТУАЦИЯ
                // может быть _INT _DOUBLE
                if ((listElem.lexType == _INT || listElem.lexType == _DOUBLE) &&
                        (magazElem.lexType == _INT || magazElem.lexType == _DOUBLE)
                ) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    public boolean checkEnd() {
        if (magazin.size() == 3) {
            if (magazin.get(0).lexType == _END &&
                    magazin.get(1).lexType == _SSS_ &&
                    magazin.get(2).lexType == _END
            ) {
                System.out.println("Ну все норм, заканчиваю");
                return true;
            }
        }
        return false;
    }


    public List<List<Sign>> createRelBetweenMagazin() throws Exception {
        rel.clear();
        for (int i = 0; i < magazin.size() - 1; ++i) {
            Elem left = magazin.get(i);
            Elem right = magazin.get(i + 1);
            int index_LEFT = i;
            int index_RIGHT = i + 1;
            // Если встретили аксиому, то отношение в массив положим дважды _ASSIGN    >=    _SSS_    >=    _SEMICOLON
            boolean isSSS = false;

            // X _SSS_ значит смотрим сквозь _SSS_
            if (right.lexType == _SSS_) {
                right = magazin.get(i + 2);
                index_RIGHT = i + 2;
                isSSS = true;
            }
            if (left.lexType == _SSS_) {
                left = magazin.get(i - 1);
                index_LEFT = i - 1;
                isSSS = true;
                //throw new Exception("так далеко я еще не думал!!2");
            }
            List<Sign> strings = this.table.get(new Pair<>(left.lexType, right.lexType));
            if ((strings.contains(Sign.GREAT) && strings.contains(Sign.EQUALS)) ||
                    (strings.contains(Sign.LESS) && strings.contains(Sign.GREAT))) {
                // КОЛЛИЗИЯ МАТЬ ЕГО ЗА НОГУ
                countCollision++;
                System.out.println("коллизия # " + countCollision);
                if (countCollision == 15)
                    System.out.print("");
                // _ASSIGN <= _SEMICOLON
                if (left.lexType == _ASSIGN && right.lexType == _SEMICOLON) {
                    i = ASSIGN____SEMICOLON(i, index_RIGHT, isSSS);
                }

                if (left.lexType == _PARENTHESIS_CLOSE && right.lexType == _BRACE_OPEN) {
                    i = PARENTHESIS_CLOSE____BRACE_OPEN(i, index_RIGHT, isSSS);
                }

                //  _PARENTHESIS_CLOSE    <>    _ID
                if (left.lexType == _PARENTHESIS_CLOSE && right.lexType == _ID) {
                    i = PARENTHESIS_CLOSE____ID(i, index_RIGHT, isSSS);
                }

                // _PARENTHESIS_CLOSE    <>=    _SEMICOLON
                if (left.lexType == _PARENTHESIS_CLOSE && right.lexType == _SEMICOLON) {
                    i = PARENTHESIS_CLOSE____SEMICOLON(i, index_RIGHT, isSSS);
                }

                // _PARENTHESIS_CLOSE  >=  _ELSE
                if (left.lexType == _PARENTHESIS_CLOSE && right.lexType == _ELSE) {
                    i = PARENTHESIS_CLOSE____ELSE(i, index_RIGHT, isSSS);
                }

                // _ELSE  ><  _ID
                if (left.lexType == _ELSE && right.lexType == _ID) {
                    i = ELSE___ID(i, index_RIGHT, isSSS);
                }
                // _ELSE  ><  _SEMICOLON
                if (left.lexType == _ELSE && right.lexType == _SEMICOLON) {
                    i = ELSE___SEMICOLON(i, index_RIGHT, isSSS);
                }
                // _ELSE    <>    _BRACE_OPEN
                if (left.lexType == _ELSE && right.lexType == _BRACE_OPEN) {
                    i = ELSE_____BRACE_OPEN(i, index_RIGHT, isSSS);
                }
                // _ELSE    ><    _IF
                if (left.lexType == _ELSE && right.lexType == _IF) {
                    i = ELSE____IF(i, index_RIGHT, isSSS);
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

    //  _PARENTHESIS_CLOSE    <>    _ID
    private int PARENTHESIS_CLOSE____ID(int i, int index_RIGHT, boolean isSSS) {
        boolean isEqual;
        //   _IF    =    _PARENTHESIS_OPEN    =    _SSS_    =    _PARENTHESIS_CLOSE    <>    _ID
        isEqual = checkCollision_STRONG(index_RIGHT, Arrays.asList(
                new Elem(_IF), new Elem(_PARENTHESIS_OPEN), new Elem(_SSS_), new Elem(_PARENTHESIS_CLOSE), new Elem(_ID)
        ));
        if (isEqual) {
            i = rel_ADD(Sign.LESS, isSSS, i);
            return i;
        }
        //  _IF    =    _PARENTHESIS_OPEN    =    _SSS_    =    _PARENTHESIS_CLOSE    <>    _SSS_    <>    _ID
        isEqual = checkCollision_STRONG(index_RIGHT, Arrays.asList(
                new Elem(_IF), new Elem(_PARENTHESIS_OPEN), new Elem(_SSS_), new Elem(_PARENTHESIS_CLOSE), new Elem(_SSS_), new Elem(_ID)
        ));
        if (isEqual) {
            i = rel_ADD(Sign.GREAT, isSSS, i);
            return i;
        }
        return i;
    }

    private int ASSIGN____SEMICOLON(int i, int index_RIGHT, boolean isSSS) {
        boolean isEqual;
        /*        if ((i - 2) >= 0 && magazin.get(i - 1).lexType == _ID && magazin.get(i - 2).lexType == _DOUBLE) {
            // тогда отношение >
            i = rel_ADD(Sign.GREAT, isSSS, i);
            return i;
        }*/
        //  int     <=    c      =    =          >=    S        >=    ;
        //  _INT    <=    _ID    =    _ASSIGN    >=    _SSS_    >=    _SEMICOLON
        // или _DOUBLE
        isEqual = checkCollision_STRONG(index_RIGHT, Arrays.asList(
                new Elem(_ID), new Elem(_ASSIGN), new Elem(_SSS_), new Elem(_SEMICOLON)
        ));
        if (isEqual) {
            i = rel_ADD(Sign.GREAT, isSSS, i);
            return i;
        }

        return i;
    }

    // _ELSE    ><    _IF
    private int ELSE____IF(int i, int index_RIGHT, boolean isSSS) {
        boolean isEqual;

        //  else     ><    S        ><    if
        //  _ELSE    ><    _SSS_    ><    _IF
        isEqual = checkCollision_STRONG(index_RIGHT, Arrays.asList(
                new Elem(_ELSE), new Elem(_SSS_), new Elem(_IF)
        ));
        if (isEqual) {
            i = rel_ADD(Sign.GREAT, isSSS, i);
            return i;
        }
        return i;
    }

    private int PARENTHESIS_CLOSE____BRACE_OPEN(int i, int index_RIGHT, boolean isSSS) {
        boolean isEqual;

        /*                if ((i - 3) >= 0 && magazin.get(i - 1).lexType == _PARENTHESIS_OPEN && magazin.get(i - 2).lexType == _ID &&
                        magazin.get(i - 3).lexType == _INT) {
                    // тогда отношение <
                    i = rel_ADD(Sign.LESS, isSSS, i);
                    return i;
                }*/
        // _END    <    _INT    <=    _ID        =    _PARENTHESIS_OPEN    =    _PARENTHESIS_CLOSE    ><    _BRACE_OPEN
        isEqual = checkCollision_STRONG(index_RIGHT, Arrays.asList(
                new Elem(_INT), new Elem(_ID), new Elem(_PARENTHESIS_OPEN),
                new Elem(_PARENTHESIS_CLOSE), new Elem(_BRACE_OPEN)
        ));
        if (isEqual) {
            i = rel_ADD(Sign.LESS, isSSS, i);
            return i;
        }

        /*if ((i - 4) >= 0 && magazin.get(i - 1).lexType == _SSS_ && magazin.get(i - 2).lexType == _PARENTHESIS_OPEN &&
                magazin.get(i - 3).lexType == _ID && magazin.get(i - 4).lexType == _INT) {
            // тогда отношение >
            i = rel_ADD(Sign.LESS, isSSS, i);
            return i;
        }*/

        isEqual = checkCollision_STRONG(index_RIGHT, Arrays.asList(
                new Elem(_INT), new Elem(_ID), new Elem(_PARENTHESIS_OPEN), new Elem(_SSS_),
                new Elem(_PARENTHESIS_CLOSE), new Elem(_BRACE_OPEN)
        ));
        if (isEqual) {
            i = rel_ADD(Sign.LESS, isSSS, i);
            return i;
        }

        //  if     =    (                    =    S        =    )                     <>    {
        //  _IF    =    _PARENTHESIS_OPEN    =    _SSS_    =    _PARENTHESIS_CLOSE    <>    _BRACE_OPEN
        isEqual = checkCollision_STRONG(index_RIGHT, Arrays.asList(
                new Elem(_IF), new Elem(_PARENTHESIS_OPEN), new Elem(_SSS_),
                new Elem(_PARENTHESIS_CLOSE), new Elem(_BRACE_OPEN)
        ));
        if (isEqual) {
            i = rel_ADD(Sign.LESS, isSSS, i);
            return i;
        }
        return i;
    }

    // _PARENTHESIS_CLOSE    <>=    _SEMICOLON
    private int PARENTHESIS_CLOSE____SEMICOLON(int i, int index_RIGHT, boolean isSSS) {
        boolean isEqual;
        //   {              <    S        <    heh    =    (                    =    )                     >    ;
        //   _BRACE_OPEN    <    _SSS_    <    _ID    =    _PARENTHESIS_OPEN    =    _PARENTHESIS_CLOSE    >    _SEMICOLON
        isEqual = checkCollision_STRONG(index_RIGHT, Arrays.asList(
                new Elem(_BRACE_OPEN), new Elem(_SSS_), new Elem(_ID),
                new Elem(_PARENTHESIS_OPEN), new Elem(_PARENTHESIS_CLOSE), new Elem(_SEMICOLON)
        ));
        if (isEqual) {
            i = rel_ADD(Sign.EQUALS, isSSS, i);
            return i;
        }

        /*        if ((i - 2) >= 0 && magazin.get(i - 1).lexType == _PARENTHESIS_OPEN && magazin.get(i - 2).lexType == _ID) {
            i = rel_ADD(Sign.GREAT, isSSS, i);
            return i;
        }*/

        //  _ID    =    _PARENTHESIS_OPEN    =    _PARENTHESIS_CLOSE    <>=    _SEMICOLON
        isEqual = checkCollision_STRONG(index_RIGHT, Arrays.asList(
                new Elem(_ID), new Elem(_PARENTHESIS_OPEN), new Elem(_PARENTHESIS_CLOSE), new Elem(_SEMICOLON)
        ));
        if (isEqual) {
            i = rel_ADD(Sign.GREAT, isSSS, i);
            return i;
        }

        //  _ID    =    _PARENTHESIS_OPEN    = _SSS_ =   _PARENTHESIS_CLOSE    <>=    _SEMICOLON
        if ((i - 3) >= 0 && magazin.get(i - 1).lexType == _SSS_ && magazin.get(i - 2).lexType == _PARENTHESIS_OPEN
                && magazin.get(i - 2).lexType == _ID) {
            i = rel_ADD(Sign.GREAT, isSSS, i);
            return i;
        }

        isEqual = checkCollision(index_RIGHT, Arrays.asList(
                new Elem(_IF), new Elem(_PARENTHESIS_OPEN), new Elem(_PARENTHESIS_CLOSE), new Elem(_SEMICOLON)
        ));
        if (isEqual) {
            i = rel_ADD(Sign.LESS, isSSS, i);
            return i;
        }


        //    S        <    heh    =    (                    =    S        =    )                     <>=    ;
        //    _SSS_    <    _ID    =    _PARENTHESIS_OPEN    =    _SSS_    =    _PARENTHESIS_CLOSE    <>=    _SEMICOLON
        isEqual = checkCollision_STRONG(index_RIGHT, Arrays.asList(
                new Elem(_SSS_), new Elem(_ID), new Elem(_PARENTHESIS_OPEN), new Elem(_SSS_),
                new Elem(_PARENTHESIS_CLOSE), new Elem(_SEMICOLON)
        ));
        if (isEqual) {
            i = rel_ADD(Sign.EQUALS, isSSS, i);
            return i;
        }
        // эта и ситуация сверху оч похожи, но разные)00
        // <#оператор#> ->  <#вызов_функции#> ; это ситуация снизу, там надо сначала выззов свернуть
        // <#операторы_и_описания#> ->  <#операторы_и_описания#> идентификатор ( <#параметры#> ) ; это ситуация сверху,
        // тут закрывающая скобка равна точке запятой, нужно сворачивать все вместе

        //   heh    =    (                    =    S        =    )                     <>=    ;
        //   _ID    =    _PARENTHESIS_OPEN    =    _SSS_    =    _PARENTHESIS_CLOSE    <>=    _SEMICOLON
        isEqual = checkCollision_STRONG(index_RIGHT, Arrays.asList(
                new Elem(_ID), new Elem(_PARENTHESIS_OPEN), new Elem(_SSS_),
                new Elem(_PARENTHESIS_CLOSE), new Elem(_SEMICOLON)
        ));
        if (isEqual) {
            i = rel_ADD(Sign.GREAT, isSSS, i);
            return i;
        }


        return i;
    }

    // _ELSE    <>    _BRACE_OPEN
    private int ELSE_____BRACE_OPEN(int i, int index_RIGHT, boolean isSSS) {
        boolean isEqual;
        //   if     =    (                    =    S        =    )                     >=    S        >=    else     <>    {
        //  _IF    =    _PARENTHESIS_OPEN    =    _SSS_    =    _PARENTHESIS_CLOSE    >=    _SSS_    >=    _ELSE    <>    _BRACE_OPEN
        isEqual = checkCollision_STRONG(index_RIGHT, Arrays.asList(
                new Elem(_IF), new Elem(_PARENTHESIS_OPEN), new Elem(_SSS_),
                new Elem(_PARENTHESIS_CLOSE), new Elem(_SSS_), new Elem(_ELSE), new Elem(_BRACE_OPEN)
        ));
        if (isEqual) {
            i = rel_ADD(Sign.LESS, isSSS, i);
            return i;
        }
        return i;
    }

    // _PARENTHESIS_CLOSE  >=  _ELSE
    private int PARENTHESIS_CLOSE____ELSE(int i, int index_RIGHT, boolean isSSS) {
        boolean isEqual;
        //   if     =    (                    =    S        =    )                     >=    S        >=    else
        //  _IF    =    _PARENTHESIS_OPEN    =    _SSS_    =    _PARENTHESIS_CLOSE    >=    _SSS_    >=    _ELSE
        isEqual = checkCollision_STRONG(index_RIGHT, Arrays.asList(
                new Elem(_IF), new Elem(_PARENTHESIS_OPEN), new Elem(_SSS_),
                new Elem(_PARENTHESIS_CLOSE), new Elem(_SSS_), new Elem(_ELSE)
        ));
        if (isEqual) {
            i = rel_ADD(Sign.EQUALS, isSSS, i);
            return i;
        }
        return i;
    }

    // _ELSE  ><  _ID
    private int ELSE___ID(int i, int index_RIGHT, boolean isSSS) {
        boolean isEqual;
        //  if     =    (                    =    S        =    )                     >=    S        >=    else     ><    kek
        //  _IF    =    _PARENTHESIS_OPEN    =    _SSS_    =    _PARENTHESIS_CLOSE    >=    _SSS_    >=    _ELSE    ><    _ID
        isEqual = checkCollision_STRONG(index_RIGHT, Arrays.asList(
                new Elem(_IF), new Elem(_PARENTHESIS_OPEN), new Elem(_SSS_),
                new Elem(_PARENTHESIS_CLOSE), new Elem(_SSS_), new Elem(_ELSE), new Elem(_ID)
        ));
        if (isEqual) {
            i = rel_ADD(Sign.LESS, isSSS, i);
            return i;
        }
        //  if     =    (                    =    S        =    )                     >=    S        >=    else     ><    S        ><    heh
        //  _IF    =    _PARENTHESIS_OPEN    =    _SSS_    =    _PARENTHESIS_CLOSE    >=    _SSS_    >=    _ELSE    ><    _SSS_    ><    _ID
        isEqual = checkCollision_STRONG(index_RIGHT, Arrays.asList(
                new Elem(_IF), new Elem(_PARENTHESIS_OPEN), new Elem(_SSS_),
                new Elem(_PARENTHESIS_CLOSE), new Elem(_SSS_), new Elem(_ELSE),
                new Elem(_SSS_), new Elem(_ID)
        ));
        if (isEqual) {
            i = rel_ADD(Sign.GREAT, isSSS, i);
            return i;
        }
        return i;
    }

    private int ELSE___SEMICOLON(int i, int index_RIGHT, boolean isSSS) {
        boolean isEqual;
        //  if     =    (                    =    S        =    )                     >=    S        >=    else     <>    S        <>    ;
        //  _IF    =    _PARENTHESIS_OPEN    =    _SSS_    =    _PARENTHESIS_CLOSE    >=    _SSS_    >=    _ELSE    <>    _SSS_    <>    _SEMICOLON
        isEqual = checkCollision_STRONG(index_RIGHT, Arrays.asList(
                new Elem(_IF), new Elem(_PARENTHESIS_OPEN), new Elem(_SSS_),
                new Elem(_PARENTHESIS_CLOSE), new Elem(_SSS_), new Elem(_ELSE)
                , new Elem(_SSS_), new Elem(_SEMICOLON)
        ));
        if (isEqual) {
            i = rel_ADD(Sign.GREAT, isSSS, i);
            return i;
        }
        return i;
    }

}