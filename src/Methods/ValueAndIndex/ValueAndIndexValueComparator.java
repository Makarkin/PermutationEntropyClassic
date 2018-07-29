package Methods.ValueAndIndex;

import java.util.Comparator;

public class ValueAndIndexValueComparator implements Comparator<ValueAndIndex> {
    @Override
    public int compare(ValueAndIndex o1, ValueAndIndex o2) {
        if (o1.getValue() > o2.getValue()) {
            return 1;
        } else if (o1.getValue() < o2.getValue()) {
            return -1;
        } else {
            return 0;
        }
    }
}

