package core.parser.node;

import core.TextType;

public class TextNode implements Node {

    private final TextType type;
    private final String text;

    public TextNode(TextType type, String text) {
        this.type = type;
        this.text = text;
    }

    public TextType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "TextNode type=[" + type.toString() + "] content=[" + text + "]";
    }
}
