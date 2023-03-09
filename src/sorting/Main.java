package sorting;

public class Main {
    public static void main(final String[] args) {
        ArgsParser parser = new ArgsParser(args);

        var sortingTool = parser.getSortingTool();

        sortingTool.processInput(parser.getInputPath());
        sortingTool.printSorted(parser.getSortingType(), parser.getOutputPath());
    }
}
