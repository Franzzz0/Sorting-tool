package sorting;

import java.io.*;
import java.util.*;

public abstract class SortingTool<T> {
    private final Map<T, Integer> map;
    private final List<T> list;
    private final List<Unit<?>> unitlist;
    private final String unitName;

    public SortingTool(String unitName) {
        this.map = new HashMap<>();
        this.list = new ArrayList<>();
        this.unitlist = new ArrayList<>();
        this.unitName = unitName;
    }

    abstract void processInput(String inputPath);

    abstract void sort(SortingType sortingType);

    void processUnit(T unit) {
        list.add(unit);
        map.put(unit, map.getOrDefault(unit, 0) + 1);
    }

    public void printSorted(SortingType sortingType, String outputPath, String format) {
        sort(sortingType);
        String output = getOutputString(sortingType, format);

        if (outputPath == null) {
            System.out.print(output);
        } else {
            File file = new File(outputPath);
            try (PrintWriter writer = new PrintWriter(file)){
                writer.print(output);
            } catch (FileNotFoundException e) {
                System.out.println("Writing error (File not found).");
            }
        }
    }

    private String getOutputString(SortingType sortingType, String format) {
        StringBuilder output = new StringBuilder(String.format("Total %ss: %d.%n", getUnitName(), list.size()));
        if (sortingType.equals(SortingType.NATURAL)) {
            output.append("Sorted data:");
            for (T unit : list) {
                output.append(format).append(unit);
            }
            output.append("\n");
        } else if (sortingType.equals(SortingType.BY_COUNT)) {
            for (Unit<?> u : unitlist) {
                output.append(u.value).append(": ").append(u.count).append(" time(s), ")
                        .append(getPercentage(u.count)).append("%\n");
            }
        }
        return output.toString();
    }

    public void printSorted(SortingType sortingType, String outputPath) {
        printSorted(sortingType, outputPath,  " ");
    }

    public List<T> getList() {
        return list;
    }

    public Map<T, Integer> getMap() {
        return this.map;
    }

    public List<Unit<?>> getUnitList() {
        return this.unitlist;
    }

    private int getPercentage(int count) {
        return 100 * count / list.size();
    }

    private String getUnitName() {
        return unitName;
    }
}
