package core.lexer;

import core.lexer.node.Token;

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
        this.section = Section.NEWLINE;
        this.index = 0;
        this.content = new StringBuilder();
        this.tokens = new LinkedList<>();
    }

    public State with(Section section) {
        this.index += 1;
        this.section = section;
        return this;
    }

    public void appendChar(char character) {
        if(character != '\0') {
            content.append(character);
        }
    }

    public void appendToken(Token token) {
        token.setContent(content.toString());
        content = new StringBuilder();
        tokens.add(token);
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
