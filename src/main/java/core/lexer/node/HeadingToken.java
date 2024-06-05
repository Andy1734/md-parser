package core.lexer.node;

import core.TextType;

public class HeadingToken implements Token {

    private TextType type;

    @Override
    public String toString() {
        return "HEADING type=[" + type.toString() + "]";
    }

    @Override
    public void setContent(String content) {
        int length = content.length();
        if(length > 0) {
            type = TextType.values()[length - 1];
        }
    }

    public TextType getType() {
        return type;
    }
}
