package core;

import core.lexer.Lexer;
import core.lexer.node.Token;
import core.parser.Parser;
import core.parser.node.Node;

import java.util.List;

public class Runner {

    public static List<Node> use(String text) {
        return Parser.run(Lexer.run(text + '\0'));
    }

}
