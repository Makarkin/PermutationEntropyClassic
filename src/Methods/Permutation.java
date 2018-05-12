package Methods;

import java.util.*;

public class Permutation {

    public static String getPermutation(ArrayList<ValueAndIndex> valueAndIndices, ValueAndIndexComparator comparator) {
        valueAndIndices.sort(comparator);
        String s = new String();
        for (int i = 0; i < valueAndIndices.size(); i++) {
            s += valueAndIndices.get(i).index;
        }

        return s;
    }
}
