package main.algoritm_1and2.maga;

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

	public String getStrByType(){
		String result = "";
		if( this.elementType.equals(ElemType.NOT_TERMINAL)){
			result += "<#" + this.str + "#>";
		}else if( this.elementType.equals(ElemType.TERMINAL)){
			result += this.str;
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
		return  (this.elementType == elem.elementType  || (this.elementType  != null && this.elementType.equals(elem.elementType)))
				&& (this.str == elem.str || (this.str != null && this.str.equals(elem.str)
		));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elementType == null) ? 0 : elementType.hashCode());
		result = prime * result + ((str == null) ? 0 : str.hashCode());
		return result;
	}



}