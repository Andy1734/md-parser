package core.lexer;

import core.lexer.node.Node;

import java.util.LinkedList;
import java.util.List;

public class State {

    private String text;
    private Section section;
    private int index;
    private StringBuilder content;
    private List<Node> nodes;

    public static State of(String text) {
        return new State(text);
    }

    private State(String text) {
        this.text = text;
        this.section = Section.NEWLINE;
        this.index = 0;
        this.content = new StringBuilder();
        this.nodes = new LinkedList<>();
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

    public void appendNode(Node node) {
        node.setContent(content.toString());
        content = new StringBuilder();
        nodes.add(node);
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

    public List<Node> getNodes() {
        return nodes;
    }

    public Character getChar() {
        if(index < text.length()) {
            return text.charAt(index);
        }
        return null;
    }
}
