package maga;

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
