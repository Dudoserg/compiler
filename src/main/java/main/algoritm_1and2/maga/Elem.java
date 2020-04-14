package main.algoritm_1and2.maga;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;
import main.Lab3.LexTypeNot;
import main.SavePoint;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class Elem {
    public ElemType elementType;    // терминал или нет
    public String str;            //
    public LexTypeNot lexTypeNot;        // Лексема со сканера
    public LexTypeTERMINAL lexTypeTERMINAL;        // Лексема со сканера

    public SavePoint savePoint;    /// это инфа о лексеме из сканера

    //lab3
    public Elem(ElemType elementType, String str, LexTypeNot lexTypeNot) {
        this.elementType = elementType;
        this.str = str;
        this.lexTypeNot = lexTypeNot;
    }

    //lab3
    public Elem(ElemType elementType, String str, LexTypeTERMINAL lexTypeTERMINAL) {
        this.elementType = elementType;
        this.str = str;
        this.lexTypeTERMINAL = lexTypeTERMINAL;
    }


    public Elem(String str, ElemType elementType) {
        this.elementType = elementType;
        this.str = str;
    }

    // Конструктор для поиска коллизий checkCollision
    public Elem(LexTypeNot lexTypeNot) {
        this.lexTypeNot = lexTypeNot;
        this.elementType = null;
        this.str = null;
    }

    public Elem(LexTypeNot lexTypeNot, String str, ElemType elementType) {
        this.lexTypeNot = lexTypeNot;
        this.elementType = elementType;
        this.str = str;
    }

    public Elem(LexTypeNot lexTypeNot, List<Character> str, ElemType elementType) {
        this.lexTypeNot = lexTypeNot;
        this.elementType = elementType;
        this.str = str.stream().map(character -> character.toString()).collect(Collectors.joining());
    }

    public Elem(LexTypeNot lexTypeNot, List<Character> str, ElemType elementType, SavePoint savePoint) {
        this.lexTypeNot = lexTypeNot;
        this.elementType = elementType;
        this.str = str.stream().map(character -> character.toString()).collect(Collectors.joining());
        this.savePoint = savePoint;
    }

    public Elem copy() {
        Elem tmp = new Elem(this.str, this.elementType);
        return tmp;
    }

    public String print() {
        String result = "";
        result += this.str;
        return result;
    }

    public String getStrByType_SHARP() {
        String result = "";
        if (this.elementType.equals(ElemType.NOT_TERMINAL)) {
            result += "<#" + this.str + "#>";
        } else if (this.elementType.equals(ElemType.TERMINAL)) {
            result += this.str;
        }  else if (this.elementType.equals(ElemType.PROGRAMM)) {
            result += "<@" + this.str + "@>";
        }
        return result;
    }

    public String getStrByType_LIGHT() {
        String result = "";
        if (this.elementType.equals(ElemType.NOT_TERMINAL)) {
            result += "<" + this.str + ">";
        } else if (this.elementType.equals(ElemType.TERMINAL)) {
            result += this.str;
        } else if (this.elementType.equals(ElemType.PROGRAMM)) {
            result += "<@" + this.str + "@>";
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Elem elem = (Elem) o;
//        return (this.elementType == elem.elementType || (this.elementType != null && this.elementType.equals(elem.elementType)))
//                && (this.str == elem.str || (this.str != null && this.str.equals(elem.str)
//        ));
        boolean result = (this.elementType == elem.elementType || (this.elementType != null && this.elementType.equals(elem.elementType))) &&
                (this.lexTypeTERMINAL == elem.lexTypeTERMINAL || (this.lexTypeTERMINAL != null && this.lexTypeTERMINAL.equals(elem.lexTypeTERMINAL))) &&
                (this.lexTypeNot == elem.lexTypeNot || (this.lexTypeNot != null && this.lexTypeNot.equals(elem.lexTypeNot)));
        return result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((elementType == null) ? 0 : elementType.hashCode());
        result = prime * result + ((lexTypeTERMINAL == null) ? 0 : lexTypeTERMINAL.hashCode());
        result = prime * result + ((lexTypeNot == null) ? 0 : lexTypeNot.hashCode());
        //result = prime * result + ((str == null) ? 0 : str.hashCode());
        return result;
    }


}
