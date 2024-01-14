package com.example.pokedex.utilities;

import com.example.pokedex.models.Pokemon;

public class ConsoleOutputUtility {
    private OutputFormat outputFormat;
    private MultipleFormatGenerator formatsGenerator;

    public ConsoleOutputUtility(OutputFormat outputFormat, MultipleFormatGenerator formatsGenerator) {
        this.outputFormat = outputFormat;
        this.formatsGenerator = formatsGenerator;
    }

    public String generateOutput(Pokemon pokemon) {
        if (this.outputFormat == OutputFormat.TEXT) {
            return formatsGenerator.generateHumanReadableText(pokemon);
        } else if (this.outputFormat == OutputFormat.HTML) {
            return formatsGenerator.generateHTML(pokemon);
        } else if (this.outputFormat == OutputFormat.CSV) {
            return formatsGenerator.generateCSV(pokemon);
        } else {
            return "Invalid output format";
        }
    }
}
