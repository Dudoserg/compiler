package algoritm_1and2.maga;

import java.util.Objects;

public class Elem {
	public ElemType elementType;
	public String str;

	public Elem(String str, ElemType elementType) {
		this.elementType = elementType;
		this.str = str;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Elem elem = (Elem) o;
		return elementType == elem.elementType &&
				Objects.equals(str, elem.str);
	}

	@Override
	public int hashCode() {
		return Objects.hash(elementType, str);
	}



}
