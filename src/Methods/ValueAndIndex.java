package Methods;

import java.util.Comparator;

public class ValueAndIndex implements Cloneable {
    public double value;
    public int index;

    public ValueAndIndex clone() throws CloneNotSupportedException {
        return (ValueAndIndex) super.clone();
    }

    public ValueAndIndex(double value, int index) {
        this.value = value;
        this.index = index;
    }
}
