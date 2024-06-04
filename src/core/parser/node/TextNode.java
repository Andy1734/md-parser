package core.parser.node;

import core.parser.node.utils.TextNodeType;

public class TextNode implements Node {

    private final TextNodeType type;
    private final String text;

    public TextNode(TextNodeType type, String text) {
        this.type = type;
        this.text = text;
    }

    public TextNodeType getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}
