import org.apache.commons.cli.*;

public class Configuration {

    public static final String OUTPUT = "o";
    public static final String INPUT = "i";
    public static final String MODE = "mode";
    public static final String SHAPE = "shape";
    public static final String ALTITUDE = "altitude";
    public static final String LAKES = "lakes";
    public static final String RIVERS = "rivers";
    public static final String AQUIFERS = "aquifers";
    public static final String SOIL = "soil";
    public static final String BIOMES = "biomes";
    public static final String SEED = "seed";
    public static final String CITY = "city";


    private CommandLine cli;
    public Configuration(String[] args) {
        try {
            this.cli = parser().parse(options(), args);
        } catch (ParseException pe) {
            throw new IllegalArgumentException(pe);
        }
    }

    private CommandLineParser parser() {
        return new DefaultParser();
    }

    public String input() { return this.cli.getOptionValue(INPUT);}
    public String output() {
        return this.cli.getOptionValue(OUTPUT, "output.svg");
    }
    public String mode() { return this.cli.getOptionValue(MODE); }
    public String shape() { return this.cli.getOptionValue(SHAPE); }
    public String altitude() { return this.cli.getOptionValue(ALTITUDE); }
    public String lakes() { return this.cli.getOptionValue(LAKES); }
    public String rivers() { return this.cli.getOptionValue(RIVERS); }
    public String aquifers() { return this.cli.getOptionValue(AQUIFERS); }
    public String soil() { return this.cli.getOptionValue(SOIL); }
    public String biomes() { return this.cli.getOptionValue(BIOMES); }
    public String seed() { return this.cli.getOptionValue(SEED); }
    public String city() { return this.cli.getOptionValue(CITY); }


    public Options options() {
        Options options = new Options();
        options.addOption(new Option(INPUT, true, "Input file (MESH)"));
        options.addOption(new Option(OUTPUT, true, "Output file (MESH)"));
        options.addOption(Option.builder().longOpt(MODE).hasArg().argName("mode").desc("Island").build());
        options.addOption(Option.builder().longOpt(SHAPE).hasArg().argName("shape").desc("Island shape").build());
        options.addOption(Option.builder().longOpt(ALTITUDE).hasArg().argName("altitude").desc("Island altitude").build());
        options.addOption(Option.builder().longOpt(LAKES).hasArg().argName("lakes").desc("Island with lakes").build());
        options.addOption(Option.builder().longOpt(RIVERS).hasArg().argName("rivers").desc("Island with rivers").build());
        options.addOption(Option.builder().longOpt(AQUIFERS).hasArg().argName("aquifers").desc("Island with aquifers").build());
        options.addOption(Option.builder().longOpt(SOIL).hasArg().argName("soil").desc("Island with soil").build());
        options.addOption(Option.builder().longOpt(BIOMES).hasArg().argName("biomes").desc("Island with biomes").build());
        options.addOption(Option.builder().longOpt(SEED).hasArg().argName("seed").desc("Island with seed").build());
        options.addOption(Option.builder().longOpt(CITY).hasArg().argName("city").desc("Island with cities").build());

        return options;
    }

}