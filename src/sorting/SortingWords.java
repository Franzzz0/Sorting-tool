package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class SortingWords extends SortingTool<String> {

    public SortingWords() {
        super("word");
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
                String word = scanner.next();
                processUnit(word);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
}
