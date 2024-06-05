package core;

import core.html.HtmlBuilder;
import core.utils.FileWriter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(Runner.useFile("input.md"));
        FileWriter.write(HtmlBuilder.run(Runner.useFile("input.md")));
    }
}