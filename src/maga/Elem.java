package maga;

public class Elem {
	ElemType elementType;
	String str;

	public Elem(String str, ElemType elementType ) {
		this.elementType = elementType;
		this.str = str;
	}

	public Elem copy(){
		Elem tmp = new Elem(this.str, this.elementType);
		return tmp;
	}

	public String print() {
		String result = "";
		result += this.str;
		return result;
	}
}
