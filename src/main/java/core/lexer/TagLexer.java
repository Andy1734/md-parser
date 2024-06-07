package core.lexer;

import core.lexer.token.TokenType;

public class TagLexer {

    public static State run(State state) {
        final Character character = state.getChar();
        if(character != null) {
            final Section section = state.getSection();
            final boolean alphanumeric = Character.isDigit(character) || Character.isLetter(character);
            final boolean end = character == '\0';
            switch(section) {
                case TAG_START -> {
                    if(character == '[') {
                        state.appendToken(TokenType.OPEN_SQUARE_BRACKET);
                        return run(state.with(Section.TAG_IDENTIFIER));
                    }
                }
                case TAG_IDENTIFIER -> {
                    if(alphanumeric) {
                        state.appendChar(character);
                        return run(state.with(Section.TAG_IDENTIFIER));
                    }
                    if(character == ']') {
                        state.appendToken(TokenType.TAG_IDENTIFIER);
                        return run(state.start(Section.TAG_END));
                    }
                    if(character == ' ') {
                        return run(state.with(Section.TAG_WHITESPACE));
                    }
                }
                case TAG_WHITESPACE -> {
                    if(character == ' ') {
                        return run(state.with(Section.TAG_WHITESPACE));
                    }
                    if(alphanumeric) {
                        return run(state.start(Section.TAG_ATTRIBUTE));
                    }
                }
                case TAG_ATTRIBUTE -> {
                    if(alphanumeric) {
                        return run(state.with(Section.TAG_ATTRIBUTE));
                    }
                }
                case TAG_ATTRIBUTE_EQUALS -> {
                    if(character == '"') {

                    }
                }
                case TAG_END -> {
                    if(character == ']') {
                        state.appendToken(TokenType.CLOSE_SQUARE_BRACKET);
                        return state;
                    }
                }
            }
            throw new LexingException(state);
        }
        return state;
    }

}
