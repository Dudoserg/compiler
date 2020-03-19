package algoritm_2.maga;

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
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Elem elem = (Elem) o;
		return this.elementType.equals(elem.elementType) &&
				this.str.equals(elem.str);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.elementType == null) ? 0 : this.elementType.hashCode());
		result = prime * result + ((this.str == null) ? 0 : this.str .hashCode());
		return result;
	}



}
