package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class SortingNumbers extends SortingTool<Long> {

    public SortingNumbers() {
        super("number");
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
            while (scanner.hasNext()) {
                String next = scanner.next();
                long number;
                try {
                    number = Long.parseLong(next);
                } catch (NumberFormatException e) {
                    System.out.printf("\"%s\" is not a long. It will be skipped.%n", next);
                    continue;
                }
                processUnit(number);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    @Override
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
}
