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
//		return super.hashCode();
		return this.getKey().hashCode() + this.getValue().hashCode();
	}

	@Override
	public boolean equals(Object o) {
//		return super.getKey().equals(o.getKey()) && super.getValue().equals(o.getValue());
		if( this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		MyPair<T,X> relation = (MyPair<T,X>) o;
		return  (this.getKey() == relation.getKey() || (this.getKey()  != null && this.getKey().equals(relation.getKey())))  &&
				(this.getValue() == relation.getValue() || (this.getValue()  != null && this.getValue().equals(relation.getValue())));
	}
}
