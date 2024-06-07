package core.lexer;

import core.lexer.token.*;

import java.util.List;

public class Lexer {

    private Lexer() {
    }

    public static List<Token> run(String text) {
        return run(State.of(text + '\0')).getTokens();
    }

    private static State run(State state) {
        final Character character = state.getChar();
        if(character != null) {
            final boolean alphanumeric = Character.isDigit(character) || Character.isLetter(character);
            final boolean end = character == '\0';
            final boolean newline = character == '\n' || character == '\r';
            final Section section = state.getSection();
            switch(section) {
                case UNDETERMINED -> {
                    if(character == '#') {
                        return run(state.start(Section.HEADING_MARKER));
                    }
                    if(alphanumeric) {
                        return run(state.start(Section.TEXT));
                    }
                    if(newline) {
                        return run(state.with(Section.UNDETERMINED));
                    }
                    if(character == '[') {
                        return run(TagLexer.run(state.start(Section.TAG_START)).with(Section.UNDETERMINED));
                    }
                    if(end) {
                        return state;
                    }
                    return run(state.reset());
                }
                case HEADING_MARKER -> {
                    if(character == '#') {
                        state.appendChar(character);
                        return run(state.with(Section.HEADING_MARKER));
                    }
                    if(alphanumeric) {
                        return run(state.start(Section.TEXT));
                    }
                    if(Character.isWhitespace(character)) {
                        state.appendToken(TokenType.HEADING_TOKEN);
                        return run(state.with(Section.TEXT));
                    }
                }
                case TEXT -> {
                    if(newline) {
                        state.appendToken(TokenType.TEXT_TOKEN);
                        return run(state.with(Section.UNDETERMINED));
                    }
                    if(character == '\0') {
                        state.appendToken(TokenType.TEXT_TOKEN);
                        return state;
                    }
                    state.appendChar(character);
                    return run(state.with(Section.TEXT));
                }
            }
            throw new LexingException(state);
        }
        return state;
    }

}
