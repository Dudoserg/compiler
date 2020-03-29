package main.algoritm_1and2.maga;

public enum Sign {
	GREAT(">"),
	LESS("<"),
	EQUALS("=");

	private String str;

	Sign(String str) {
		this.str = str;
	}


	public String getStr() {
		return str;
	}
}
