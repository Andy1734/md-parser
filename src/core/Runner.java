package core;

import core.lexer.Lexer;
import core.lexer.node.Node;

import java.util.List;

public class Runner {

    public static List<Node> use(String text) {
        return Lexer.run(text + '\0');
    }

}
