package com.example.pokedex;


import com.example.pokedex.controllers.PokemonController;
import com.example.pokedex.models.Pokemon;
import com.example.pokedex.services.ApiService;
import com.example.pokedex.services.LocalDatabaseService;
import com.example.pokedex.services.PokemonService;
import com.example.pokedex.utilities.ConsoleOutputUtility;
import com.example.pokedex.utilities.OutputFormat;
import com.example.pokedex.views.PokemonView;



import org.apache.commons.cli.*;

public class Pokedex {

    private enum DataSource {WEB_API, LOCAL_DATABASE}

    private static DataSource dataSource = DataSource.WEB_API;
    private static String databasePath;
    private static OutputFormat outputFormat = OutputFormat.TEXT;
    private static int pokemonId;

    public static void main(String[] args) throws ParseException {

        /* Parse the command line arguments */
        try {
            parseCommandLineArguments(args);
        } catch (PokemonCommandLineParsingException e) {
            System.err.println(e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("./Pokedex <PokemonId> [-d|--database <databaseFile>] [-f|--format <format>]", e.getOptions());
            System.exit(0);
        }

        // Usa el servicio correspondiente según la fuente de datos seleccionada
        PokemonService dataService;
        if (dataSource == DataSource.WEB_API) {
            dataService = new ApiService();
        } else {
            dataService = new LocalDatabaseService();
        }
        PokemonController pokemonController = new PokemonController(dataService);

        // Use the PokemonController to get Pokemon data
        Pokemon pokemon = pokemonController.getPokemonById(pokemonId);

        // Display Pokemon data using ConsoleOutputUtility
        if (pokemon != null) {
            PokemonView pokemonView = new PokemonView();  // Instancia de PokemonView
            ConsoleOutputUtility consoleOutputUtility = new ConsoleOutputUtility(outputFormat, pokemonView);
            String output = consoleOutputUtility.generateOutput(pokemon);
            System.out.println(output);
        } else {
            System.out.println("No se pudo obtener información del Pokémon.");
        }
    }

    public static void parseCommandLineArguments(String[] args) throws PokemonCommandLineParsingException, ParseException {
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption("d", "database", true, "Path to a SQLite database containing pokemons");
        options.addOption("f", "format", true, "Specify the output format, between 'text', 'html' and 'csv'. By default 'text'.");
        options.addOption("s", "source", true, "Specify the data source, either 'web' or 'local'. By default 'web'.");

        // parse the command line arguments
        CommandLine line = parser.parse(options, args);
        if (line.hasOption("d")) {
            dataSource = DataSource.LOCAL_DATABASE;
            databasePath = line.getOptionValue("d");
        }

        if (line.hasOption("f")) {
            String formatArgValue = line.getOptionValue("f");
            if (formatArgValue.equals("html")) {
                outputFormat = OutputFormat.HTML;
            } else if (formatArgValue.equals("csv")) {
                outputFormat = OutputFormat.CSV;
            } else if (formatArgValue.equals("text")) {
                outputFormat = OutputFormat.TEXT;
            } else {
                throw new PokemonCommandLineParsingException("Invalid value for the option -f/--format", options);
            }
        }

        if (line.hasOption("s")) {
            String sourceArgValue = line.getOptionValue("s");
            if (sourceArgValue.equals("local")) {
                dataSource = DataSource.LOCAL_DATABASE;
            } else if (sourceArgValue.equals("web")) {
                dataSource = DataSource.WEB_API;
            } else {
                throw new PokemonCommandLineParsingException("Invalid value for the option -s/--source", options);
            }
        }

        // Get pokemon ID from remaining arguments
        String[] remainingArgs = line.getArgs();
        if (remainingArgs.length < 1) {
            throw new PokemonCommandLineParsingException("You must provide a pokemon ID", options);
        }
        try {
            pokemonId = Integer.parseInt(remainingArgs[0]);
        } catch (NumberFormatException e) {
            throw new PokemonCommandLineParsingException("'" + remainingArgs[0] + "' is not a valid pokemon ID", options);
        }
    }


    static class PokemonCommandLineParsingException extends Exception {

        private Options options;

        public PokemonCommandLineParsingException(String msg, Options options) {
            super(msg);
            this.options = options;
        }

        public Options getOptions() {
            return options;
        }

    };
}
