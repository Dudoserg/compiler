package maga;

public class Relations {
	Elem leftElem;
	Elem rightElem;
	Sign sign;

	public Relations(Elem leftElem, Elem rightElem, Sign sign) {
		this.leftElem = leftElem;
		this.rightElem = rightElem;
		this.sign = sign;
	}

	public String print(){
		return leftElem.str + " = " + rightElem.str;
	}
}
