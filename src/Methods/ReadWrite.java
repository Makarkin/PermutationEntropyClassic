package Methods;

import Methods.ValueAndIndex.ValueAndIndex;

import java.io.*;
import java.util.*;

public class ReadWrite {

    private double t;

    private LinkedHashMap<Double, Integer> timeVsNumberOfPattern = new LinkedHashMap<>();

    private HashMap<String, Integer> patternVsPatternCount = new HashMap<>();

    private ArrayList<Double> weightCoefficients = new ArrayList<>();

    private ArrayList<String> patternSequence = new ArrayList<>();

    private void readAndAddValue(String line, ArrayList<Double> values) throws IOException {
        String[] timeAndValue = line.split(",");
        t = Double.valueOf(timeAndValue[0]);
        double value = Double.valueOf(timeAndValue[1]);
        values.add(value);
    }

    private void calculateCoefficient(ArrayList<Double> values, int dimension) {
        int counter = 1;
        double averageVectorValue = 0.0;
        double weightCoefficientValue = 0.0;
        for (int i = 0; i <= values.size() - dimension - 1; i++) {

            while (counter <= dimension) {
                averageVectorValue += (1 / (double) dimension) * values.get(i + counter);
                counter++;
            }

            counter = 0;
            while (counter <= dimension) {
                weightCoefficientValue += (1 / (double) dimension) * Math.pow((values.get(i) - averageVectorValue), 2);
                counter++;
            }

            counter = 0;
            weightCoefficients.add(weightCoefficientValue);
        }
    }

    private void addPatternInCollections(ArrayList<Double> values, String pattern, int dimension) {
        AuxilaryMethods.addPattern(patternVsPatternCount, pattern);
        patternSequence.add(pattern);
        calculateCoefficient(values, dimension);
    }

    private void writeResult(String filePathForResult1, String filePathForResult2) {
        try (BufferedWriter writer1 = new BufferedWriter(new FileWriter(filePathForResult1, false));
             BufferedWriter writer2 = new BufferedWriter(new FileWriter(filePathForResult2, false))) {

            double sumOfPattern = 0;
            double permutationEntropy = 0;
            double probability;
            for (int patternCount : patternVsPatternCount.values()) {
                sumOfPattern += patternCount;
            }

            for (int i = 0; i < patternSequence.size(); i++) {
                probability = patternVsPatternCount.get(patternSequence.get(i)) / sumOfPattern;
                permutationEntropy -= (probability) * Math.log((probability));
                writer1.write(i + " " + permutationEntropy + "\r\n");
                writer2.write(i + " " + permutationEntropy * weightCoefficients.get(i) + "\r\n");
                permutationEntropy = 0;
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void calculateAll(int dimension, String filePathPermutationEntropy, String filePathForResult1,
                              String filePathForResult2) throws IOException {
        ArrayList<Double> values = new ArrayList<>();
        String line = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathPermutationEntropy))) {

            AuxilaryMethods.readFirst(reader, 21);

            for (int i = 0; i <= dimension; i++) {
                line = reader.readLine();
                readAndAddValue(line, values);
            }

            ArrayList<ValueAndIndex> source = ValueAndIndex.createValueAndIndices(values);
            String pattern = "";
            for (int i = 0; i < source.size(); i++) {
                pattern += source.get(i).getPatternIndex();
            }

            addPatternInCollections(values, pattern, dimension);

            boolean b = true;
            while (b) {
                values.remove(0);
                line = reader.readLine();
                if (line == null) break;
                readAndAddValue(line, values);
                source = ValueAndIndex.createValueAndIndices(values);
                pattern = "";
                for (int i = 0; i < source.size(); i++) {
                    pattern += source.get(i).getPatternIndex();
                }

                addPatternInCollections(values, pattern, dimension);
            }

            writeResult(filePathForResult1, filePathForResult2);
        }
    }
}
