package Methods;

import Methods.ValueAndIndex.ValueAndIndex;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AuxilaryMethods {

    public static void readFirst(BufferedReader reader, int n) throws IOException {
        for (int i = 0; i < n; i++) {
            reader.readLine();
        }
    }

    public static void addPattern(HashMap<String, Integer> patternCount, String permutation) {
        int temp;
        if (patternCount.size() == 0 || !patternCount.containsKey(permutation)) {
            patternCount.put(permutation, 1);
        } else {
            temp = patternCount.get(permutation);
            temp++;
            patternCount.put(permutation, temp);
        }
    }
}
