package core.lexer;

import core.lexer.node.HeadingNode;
import core.lexer.node.Node;
import core.lexer.node.TextNode;

import java.util.List;

public class Lexer {

    private Lexer() {
    }

    public static List<Node> run(String text) {
        return run(State.of(text)).getNodes();
    }

    private static State run(State state) {
        final Character character = state.getChar();
        if(character != null) {
            final Section section = state.getSection();
            state.appendChar(character);
            switch(section) {
                case NEWLINE -> {
                    if(character == '#') {
                        return run(state.with(Section.HEADING_MARKER));
                    }
                    if(Character.isDigit(character) || Character.isLetter(character)) {
                        return run(state.with(Section.TEXT));
                    }
                }
                case HEADING_MARKER -> {
                    if(character == '#') {
                        return run(state.with(Section.HEADING_MARKER));
                    }
                    if(Character.isWhitespace(character)) {
                        state.appendNode(new HeadingNode());
                        return run(state.with(Section.TEXT));
                    }
                }
                case TEXT -> {
                    if(Character.isWhitespace(character) || Character.isLetter(character) || Character.isDigit(character)) {
                        return run(state.with(Section.TEXT));
                    }
                    if(character == '\0') {
                        state.appendNode(new TextNode());
                        return state;
                    }
                }
            }
            throw new LexingException(state);
        }
        return state;
    }

}
