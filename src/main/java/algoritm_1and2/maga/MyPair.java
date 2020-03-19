package algoritm_1and2.maga;

import javafx.util.Pair;

public class MyPair<T,X> extends Pair<T,X> {

	/**
	 * Creates a new pair
	 *
	 * @param key   The key for this pair
	 * @param value The value to use for this pair
	 */
	public MyPair(T key, X value) {
		super(key, value);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public boolean equals(MyPair<T,X> o) {
		return super.getKey().equals(o.getKey()) && super.getValue().equals(o.getValue());
	}
}
