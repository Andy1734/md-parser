package core.parser;

import core.TextType;
import core.lexer.node.HeadingToken;
import core.lexer.node.TextToken;
import core.lexer.node.Token;
import core.parser.node.Node;
import core.parser.node.TextNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

    public static List<Node> run(List<Token> tokens) {
        return run(State.of(tokens)).getNodes();
    }

    private static State run(State state) {
        return run(state, new HashMap<>());
    }

    private static State run(State state, Map<String, Object> properties) {
        Token token = state.getToken();
        if(token != null) {
            Section section = state.getSection();
            switch(section) {
                case NEWLINE -> {
                    if(token instanceof HeadingToken headingToken) {
                        properties.put("heading_type", headingToken.getType());
                        return run(state.with(Section.HEADING), properties);
                    }
                    if(token instanceof TextToken textToken) {
                        state.appendNode(new TextNode(TextType.P, textToken.getContent()));
                        return run(state.with(Section.NEWLINE));
                    }
                }
                case HEADING -> {
                    if(token instanceof TextToken textToken && properties.get("heading_type") instanceof TextType textType) {
                        state.appendNode(new TextNode(textType, textToken.getContent()));
                        return run(state.with(Section.NEWLINE));
                    }
                }
            }
        }
        return state;
    }

}
