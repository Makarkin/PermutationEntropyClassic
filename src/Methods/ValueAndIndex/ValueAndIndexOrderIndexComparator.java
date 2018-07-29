package Methods.ValueAndIndex;

import java.util.Comparator;

public class ValueAndIndexOrderIndexComparator implements Comparator<ValueAndIndex> {
    @Override
    public int compare(ValueAndIndex o1, ValueAndIndex o2) {
        if (o1.getOrderIndex() > o2.getOrderIndex()) {
            return 1;
        } else if (o1.getOrderIndex() < o2.getOrderIndex()) {
            return -1;
        } else {
            return 0;
        }
    }
}
