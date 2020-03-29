package main.algoritm_1and2.maga;

import java.util.ArrayList;
import java.util.List;

public class Rule {
	public Elem left;
	public List<RightPart> parts;

	public Rule() {
		this.parts = new ArrayList<>();
	}
}
