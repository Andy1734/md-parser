package core.lexer.node;

import core.lexer.node.utils.HeadingTokenType;

public class HeadingToken implements Token {

    private HeadingTokenType type;

    @Override
    public String toString() {
        return "HEADING type=[" + type.toString() + "]";
    }

    @Override
    public void setContent(String content) {
        int length = content.length();
        if(length > 0) {
            type = HeadingTokenType.values()[length - 1];
        }
    }

    public HeadingTokenType getType() {
        return type;
    }
}
