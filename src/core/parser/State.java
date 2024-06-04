package core.parser;

import core.lexer.node.Token;
import core.parser.node.Node;

import java.util.LinkedList;
import java.util.List;

public class State {

    private final List<Token> tokens;
    private final List<Node> nodes;
    private int index;
    private Section section;

    public static State of(List<Token> tokens) {
        return new State(tokens);
    }

    private State(List<Token> tokens) {
        this.tokens = tokens;
        this.nodes = new LinkedList<>();
    }

    public void appendNode(Node node) {
        nodes.add(node);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Token getToken() {
        if(index < tokens.size()) {
            return tokens.get(index);
        }
        return null;
    }

    public State with(Section section) {
        index += 1;
        this.section = section;
        return this;
    }
}
