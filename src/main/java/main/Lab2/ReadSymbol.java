package main.Lab2;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.algoritm_1and2.maga.ElemType;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadSymbol {
    /**
     * Тип, если терминал то LLK._XXX \ иначе Scaner._
     */
    public LexType typ;

    /**
     * Лексема в виде строки, для вывода в дебаге
     */
    public String lexString;

    public ElemType elemType;

    public ReadSymbol(LexType typ, List<Character> lexString, ElemType elemType) {
        this.typ = typ;
        this.lexString = lexString.stream().map(Object::toString).collect(Collectors.joining());
        this.elemType = elemType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadSymbol that = (ReadSymbol) o;
        return typ == that.typ &&
                Objects.equals(lexString, that.lexString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typ, lexString);
    }
}
