package Methods;

import java.util.Comparator;

public class ValueAndIndexComparator implements Comparator<ValueAndIndex> {

    @Override
    public int compare(ValueAndIndex valueAndIndex1, ValueAndIndex valueAndIndex2) {
        if (valueAndIndex1.value > valueAndIndex2.value) {
            return 1;
        } else if (valueAndIndex1.value < valueAndIndex2.value) {
            return -1;
        } else {
            return 0;
        }
    }
}
