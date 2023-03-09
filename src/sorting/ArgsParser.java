package sorting;

public class ArgsParser {
    private final String[] args;
    private SortingType sortingType;
    private DataType dataType;
    private String inputPath;
    private String outputPath;

    public ArgsParser(String[] args) {
        this.args = args;
        this.sortingType = SortingType.NATURAL;
        this.dataType = DataType.WORD;
        processArgs();
    }

    public SortingTool<?> getSortingTool() {
        return switch (getDataType()) {
            case WORD -> new SortingWords();
            case LINE -> new SortingLines();
            case LONG -> new SortingNumbers();
        };
    }

    private void processArgs() {
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-sortingType" -> {
                        if (args.length > i + 1) {
                            switch (args[i + 1]) {
                                case "byCount" -> sortingType = SortingType.BY_COUNT;
                                case "natural" -> sortingType = SortingType.NATURAL;
                                default -> System.out.println("No sorting type defined!");
                            }
                        } else {
                            System.out.println("No sorting type defined!");
                        }
                        i++;
                    }
                    case "-dataType" -> {
                        if (args.length > i + 1) {
                            switch (args[i + 1]) {
                                case "word" -> dataType = DataType.WORD;
                                case "line" -> dataType = DataType.LINE;
                                case "long" -> dataType = DataType.LONG;
                                default -> System.out.println("No data type defined!");
                            }
                        } else {
                            System.out.println("No data type defined!");
                        }
                        i++;
                    }
                    case "-inputFile" -> {
                        if (args.length > i + 1) {
                            inputPath = ".\\" + args[i + 1];
                        } else {
                            System.out.println("No input path defined!");
                        }
                        i++;
                    }
                    case "-outputFile" -> {
                        if (args.length > i + 1) {
                            outputPath = ".\\" + args[i + 1];
                        } else {
                            System.out.println("No output path defined!");
                        }
                        i++;
                    }
                    default -> System.out.printf("\"%s\" is not a valid parameter. It will be skipped.%n", args[i]);
                }
            }
        }
    }

    public SortingType getSortingType() {
        return sortingType;
    }

    public String getInputPath() {
        return inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    private DataType getDataType() {
        return dataType;
    }
}
