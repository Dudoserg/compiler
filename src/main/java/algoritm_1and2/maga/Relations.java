package algoritm_1and2.maga;

public class Relations {
	public Elem leftElem;
	public Elem rightElem;
	public Sign sign;

	public Relations(Elem leftElem, Elem rightElem, Sign sign) {
		this.leftElem = leftElem;
		this.rightElem = rightElem;
		this.sign = sign;
	}

	public String print(){
		return leftElem.str + " = " + rightElem.str;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Relations relation = (Relations) o;
		return  (this.leftElem == relation.leftElem || (this.leftElem  != null && this.leftElem.equals(relation.leftElem)))  &&
				(this.rightElem == relation.rightElem || (this.rightElem  != null && this.rightElem.equals(relation.rightElem))) &&
				(this.sign == relation.sign || (this.sign != null && this.sign.equals(relation.sign)
		));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.leftElem == null) ? 0 : this.leftElem.hashCode());
		result = prime * result + ((this.rightElem == null) ? 0 : this.rightElem.hashCode());
		result = prime * result + ((this.sign == null) ? 0 : this.sign.hashCode());
		return result;
	}
}
