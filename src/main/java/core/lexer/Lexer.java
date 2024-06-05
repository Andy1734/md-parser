package core.lexer;

import core.lexer.node.HeadingToken;
import core.lexer.node.TextToken;
import core.lexer.node.Token;

import java.util.List;

public class Lexer {

    private Lexer() {
    }

    public static List<Token> run(String text) {
        return run(State.of(text)).getTokens();
    }

    private static State run(State state) {
        final Character character = state.getChar();
        if(character != null) {
            final Section section = state.getSection();
            if(!List.of('\n', '\r').contains(character)) {
                state.appendChar(character);
            }
            switch(section) {
                case NEWLINE -> {
                    if(character == '#') {
                        return run(state.with(Section.HEADING_MARKER));
                    }
                    if(Character.isDigit(character) || Character.isLetter(character)) {
                        return run(state.with(Section.TEXT));
                    }
                    if(character == '\0') {
                        return state;
                    }
                }
                case HEADING_MARKER -> {
                    if(character == '#') {
                        return run(state.with(Section.HEADING_MARKER));
                    }
                    if(Character.isWhitespace(character)) {
                        state.appendToken(new HeadingToken());
                        return run(state.with(Section.TEXT));
                    }
                }
                case TEXT -> {
                    if(character == '\n' || character == '\r') {
                        state.appendToken(new TextToken());
                        return run(state.with(Section.NEWLINE));
                    }
                    if(Character.isWhitespace(character) || Character.isLetter(character) || Character.isDigit(character)) {
                        return run(state.with(Section.TEXT));
                    }
                    if(character == '\0') {
                        state.appendToken(new TextToken());
                        return state;
                    }
                }
            }
            throw new LexingException(state);
        }
        return state;
    }

}
