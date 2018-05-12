package Methods;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class AuxilaryMethods {

    public static void readFirst(BufferedReader reader, int n) throws IOException {
        for (int i = 0; i < n; i++) {
            reader.readLine();
        }
    }

    public static void indexDecrement(ArrayList<ValueAndIndex> valueAndIndices) {
        for (int m = 0; m < valueAndIndices.size(); m++) {
            valueAndIndices.get(m).index--;
        }
    }

    public static void addPermutation(ArrayList<String> patternCount, String permutation) {
        if (patternCount.size() == 0) {
            patternCount.add(permutation);
        } else {
            if (!patternCount.contains(permutation)) {
                patternCount.add(permutation);
            }
        }
    }
}
