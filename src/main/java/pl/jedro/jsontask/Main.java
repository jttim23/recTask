package pl.jedro.jsontask;

import pl.jedro.jsontask.services.AbstractService;
import pl.jedro.jsontask.services.CsvService;
import pl.jedro.jsontask.services.JsonService;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Terminal terminal = new Terminal();
        terminal.run();
    }

}

