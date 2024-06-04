package core.parser;

import core.lexer.node.HeadingToken;
import core.lexer.node.TextToken;
import core.lexer.node.Token;
import core.lexer.node.utils.HeadingTokenType;
import core.parser.node.Node;
import core.parser.node.TextNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Parser {

    public static List<Node> run(List<Token> tokens) {
        return run(State.of(tokens)).getNodes();
    }

    private static State run(State state) {
        return run(state, new HashMap<>());
    }

    private static State run(State state, Map<String, String> properties) {
        Token token = state.getToken();
        if(token != null) {
            Section section = state.getSection();
            switch(section) {
                case NEWLINE -> {
                    if(token instanceof HeadingToken headingToken) {
                        properties.put("heading_type", headingToken.getType().toString());
                        return run(state.with(Section.HEADING), properties);
                    }
                }
                case HEADING -> {
                    if(token instanceof TextToken textToken) {
                        state.appendNode(new TextNode(null, textToken.getContent()));
                        return state;
                    }
                }
            }
        }
        return state;
    }

}
