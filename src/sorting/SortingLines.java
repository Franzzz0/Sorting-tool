package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class SortingLines extends SortingTool<String> {

    public SortingLines() {
        super("line");
    }

    @Override
    public void processInput(String inputPath) {
        Scanner scanner;
        try {
            if (inputPath == null) {
                scanner = new Scanner(System.in);
            } else {
                File file = new File(inputPath);
                scanner = new Scanner(file);
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                processUnit(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    void sort(SortingType sortingType) {
        Collections.sort(getList());
        if (sortingType.equals(SortingType.BY_COUNT)) {
            sortByCount();
        }
    }

    private void sortByCount() {
        getUnitList().clear();
        getList().stream().distinct().forEachOrdered(x -> getUnitList().add(new Unit<>(x, getMap().get(x))));
        getUnitList().sort(Comparator.comparingInt(o -> o.count));
    }

    @Override
    public void printSorted(SortingType sortingType, String outputPath) {
        super.printSorted(sortingType, outputPath, "\n");
    }
}
