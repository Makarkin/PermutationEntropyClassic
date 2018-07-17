package Methods;

import java.io.*;
import java.util.*;

public class ReadWrite {

    private LinkedHashMap<Double, Integer> resmap = new LinkedHashMap<>();

    private HashMap<String, Integer> patternCount = new HashMap<>();

    private LinkedHashMap<Double, Integer> getResmap() {
        return resmap;
    }

    private HashMap<String, Integer> getPatternCount() {
        return patternCount;
    }

    private ArrayList<Double> valuesForWeight = new ArrayList<>();

    private ArrayList<Double> weightCoefficients = new ArrayList<>();

    public void calculateWeight(ArrayList<Double> values, int dimension) {
        ArrayList<Double> averageVectors = new ArrayList<>();
        Double averageVectorValue = 0.0;
        Double weightCoefficientValue = 0.0;
        int counter = 0;
        for (int i = 0; i <= values.size() - dimension - 1; i++) {
            for (int j = 1 ; j <= dimension; j++) {
                averageVectorValue += (1/dimension) * values.get(i + j + 1);
            }

            averageVectors.add(averageVectorValue);
            averageVectorValue = 0.0;
        }

        for (int i = 0; i <= values.size() - dimension + 1; i++) {
            for (int j = 1 ; j <= dimension; j++) {
                weightCoefficientValue += (1/dimension) * Math.pow((values.get(i) -  averageVectors.get(counter)), 2);
                counter++;
            }

            weightCoefficients.add(weightCoefficientValue);
            weightCoefficientValue = 0.0;
        }
    }

    public void writePermutationEntropy(String filePathPermutationEntropy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathPermutationEntropy, false))) {
            double sumOfPattern = 0;
            double permutationEntropy = 0;
            for (int i : patternCount.values()) {
                sumOfPattern += i;
            }

            for (int i : patternCount.values()) {
                permutationEntropy -= (i / sumOfPattern) * Math.log((i / sumOfPattern));
            }

            System.out.println(permutationEntropy);
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
        double value;
        int n = 21;
        String[] timeAndValue;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            AuxilaryMethods.readFirst(reader, n);
            String line = reader.readLine();
            do {
                timeAndValue = line.split(",");
                t = Double.valueOf(timeAndValue[0]);
                value = Double.valueOf(timeAndValue[1]);
                //TODO mean value like in articles
                valuesForWeight.add(value);
                valueAndIndices.add(new ValueAndIndex(value, i));
                if (i == dimension - 1) {
                    valueAndIndicesClone = (ArrayList<ValueAndIndex>) valueAndIndices.clone();
                    AuxilaryMethods.addPermutation(patternCount, Permutation.getPermutation(valueAndIndicesClone, comparator));
                    valueAndIndices.remove(0);
                    AuxilaryMethods.indexDecrement(valueAndIndices);
                    writePermutationEntropy("r");
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
