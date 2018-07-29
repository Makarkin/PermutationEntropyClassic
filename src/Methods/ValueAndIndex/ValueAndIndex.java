package Methods.ValueAndIndex;

import java.util.ArrayList;

public class ValueAndIndex implements Cloneable {
    private double value;
    private int patternIndex;
    private int orderIndex;

    public ValueAndIndex(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setPatternIndex(int patternIndex) {
        this.patternIndex = patternIndex;

    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public int getPatternIndex() {
        return patternIndex;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public ValueAndIndex clone() throws CloneNotSupportedException {
        return (ValueAndIndex) super.clone();
    }

    public static ArrayList<ValueAndIndex> createValueAndIndices(ArrayList<Double> values) {
        ArrayList<ValueAndIndex> result = new ArrayList<ValueAndIndex>();
        int size = values.size();
        for (int i = 0; i < size; i++) {
            ValueAndIndex valueAndIndex = new ValueAndIndex(values.get(i));
            valueAndIndex.setOrderIndex(i);
            result.add(valueAndIndex);
        }

        result.sort(new ValueAndIndexValueComparator());
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setPatternIndex(i);
        }

        result.sort(new ValueAndIndexOrderIndexComparator());
        return result;
    }
}
