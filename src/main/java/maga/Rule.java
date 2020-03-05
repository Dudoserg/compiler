package maga;

import java.util.ArrayList;
import java.util.List;

public class Rule {
	Elem left;
	List<RightPart> parts;

	public Rule() {
		this.parts = new ArrayList<>();
	}
}
