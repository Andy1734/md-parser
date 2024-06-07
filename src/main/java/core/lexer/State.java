package core.lexer;

import core.lexer.token.Token;
import core.lexer.token.TokenType;

import java.util.LinkedList;
import java.util.List;

public class State {

    private final String text;
    private Section section;
    private int index;
    private StringBuilder content;
    private final List<Token> tokens;

    public static State of(String text) {
        return new State(text);
    }

    private State(String text) {
        this.text = text;
        this.section = Section.UNDETERMINED;
        this.index = 0;
        this.content = new StringBuilder();
        this.tokens = new LinkedList<>();
    }

    public State with(Section section) {
        this.index += 1;
        this.section = section;
        return this;
    }

    public State start(Section section) {
        this.section = section;
        return this;
    }

    public State reset() {
        this.section = Section.UNDETERMINED;
        return this;
    }

    public void appendChar(char character) {
        if(character != '\0') {
            content.append(character);
        }
    }

    public void appendToken(TokenType tokenType) {
        tokens.add(new Token(tokenType, content.toString()));
        content = new StringBuilder();
    }

    public Section getSection() {
        return section;
    }

    public int getIndex() {
        return index;
    }

    public String getContent() {
        return content.toString();
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public Character getChar() {
        if(index < text.length()) {
            return text.charAt(index);
        }
        return null;
    }
}
