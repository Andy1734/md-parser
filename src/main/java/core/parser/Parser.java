package core.parser;

import core.TextType;
import core.lexer.token.Token;
import core.lexer.token.TokenType;
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
            TokenType tokenType = token.tokenType();
            Section section = state.getSection();
            switch(section) {
                case NEWLINE -> {
                    if(tokenType == TokenType.HEADING_TOKEN) {
                        properties.put("heading_type", token.text());
                        return run(state.with(Section.HEADING), properties);
                    }
                    if(tokenType == TokenType.TEXT_TOKEN) {
                        state.appendNode(new TextNode(TextType.P, token.text()));
                        return run(state.with(Section.NEWLINE));
                    }
                }
                case HEADING -> {
                    if(tokenType == TokenType.TEXT_TOKEN) {
                        //state.appendNode(new TextNode());
                        return run(state.with(Section.NEWLINE));
                    }
                }
            }
        }
        return state;
    }

}
