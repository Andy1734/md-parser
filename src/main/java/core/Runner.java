package core;

import core.utils.FileReader;
import core.lexer.Lexer;
import core.parser.Parser;
import core.parser.node.Node;

import java.io.IOException;
import java.util.List;

public class Runner {

    public static List<Node> use(String text) {
        return Parser.run(Lexer.run(text + '\0'));
    }

    public static List<Node> useFile(String path) throws IOException {
        return use(FileReader.read(path));
    }

}
