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
}
