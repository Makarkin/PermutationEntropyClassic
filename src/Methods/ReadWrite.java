package Methods;

import java.io.*;
import java.util.*;

public class ReadWrite {

    private LinkedHashMap<Double, Integer> resmap = new LinkedHashMap<>();

    private HashMap<String, Integer> patternCount = new HashMap<>();

    public LinkedHashMap<Double, Integer> getResmap() {
        return resmap;
    }

    public HashMap<String, Integer> getPatternCount() {
        return patternCount;
    }

    public void writePermutationEntropy(String filePathPermutationEntropy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathPermutationEntropy, false))) {
            int sumOfPattern = 0;
            int probability = 0;
            double permutationEntropy = 0;
            for (int i : patternCount.values()) {
                sumOfPattern += i;
            }

            for (int i : patternCount.values()) {
                permutationEntropy += (i / sumOfPattern) * Math.log((i / sumOfPattern));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void writePatternCount(String filePathPatternCount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathPatternCount, false))) {
            for (Map.Entry e : resmap.entrySet()) {
                writer.write(e.getKey() + " " + e.getValue() + "\r\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public LinkedHashMap<Double, Integer> read(String filePath, int dimension) {

        ArrayList<ValueAndIndex> valueAndIndices = new ArrayList<>();
        ArrayList<ValueAndIndex> valueAndIndicesClone;
        ValueAndIndexComparator comparator = new ValueAndIndexComparator();

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
