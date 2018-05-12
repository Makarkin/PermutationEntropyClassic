package Methods;

import java.io.*;
import java.util.*;

public class ReadWrite {

    public static void write(String filePath, HashMap<Double, Integer> resmap) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Map.Entry e: resmap.entrySet()) {
                writer.write(e.getKey() + " " + e.getValue() + "\r\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static LinkedHashMap<Double, Integer> read(String filePath, int dimension) {
        ArrayList<String> patternCount = new ArrayList<>();
        ArrayList<ValueAndIndex> valueAndIndices = new ArrayList<>();
        ArrayList<ValueAndIndex> valueAndIndicesClone = new ArrayList<>();
        ValueAndIndexComparator comparator = new ValueAndIndexComparator();
        LinkedHashMap<Double, Integer> resmap = new LinkedHashMap<>();
        int i = 0;
        double t;
        int n = 21;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            AuxilaryMethods.readFirst(reader, n);
            String line = reader.readLine();
            String[] timeAndValue = new String[2];
            do {
                timeAndValue = line.split(",");
                t = Double.valueOf(timeAndValue[0]);
                valueAndIndices.add(new ValueAndIndex(Double.valueOf(timeAndValue[1]), i));
                if (i == dimension - 1) {
                    valueAndIndicesClone = (ArrayList<ValueAndIndex>) valueAndIndices.clone();
                    AuxilaryMethods.addPermutation(patternCount, Permutation.getPermutation(valueAndIndicesClone, comparator));
                    valueAndIndices.remove(0);
                    AuxilaryMethods.indexDecrement(valueAndIndices);
                    i--;
                }

                i++;
                resmap.put(t, patternCount.size());
            }
            while ((line = reader.readLine()) != null);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return resmap;
    }
}
