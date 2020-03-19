package algoritm_2.maga;

public class Relations {
	public Elem leftElem;
	public Elem rightElem;
	public Sign sign;

	public Relations(Elem leftElem, Elem rightElem, Sign sign) {
		this.leftElem = leftElem;
		this.rightElem = rightElem;
		this.sign = sign;
	}

	public String print() {
		return leftElem.str + " = " + rightElem.str;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.leftElem == null) ? 0 : this.leftElem .hashCode());
		result = prime * result + ((this.rightElem == null) ? 0 : this.rightElem .hashCode());
		result = prime * result + ((this.sign == null) ? 0 : sign.getStr().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean isEqual = true;
		if (obj == this)
			return true;
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		Relations tmp = (Relations) obj;
		isEqual =
				this.sign.getStr().equals(tmp.sign.getStr()) &&
						this.rightElem.equals(tmp.rightElem) &&
						this.leftElem.equals(tmp.leftElem);

		return isEqual;


	}
}
