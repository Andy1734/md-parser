package core.lexer.node;

public class HeadingNode implements Node {

    private HeadingNodeType type;

    @Override
    public String toString() {
        return "HEADING type=[" + type.toString() + "]";
    }

    @Override
    public void setContent(String content) {
        int length = content.length();
        if(length > 0) {
            type = HeadingNodeType.values()[length - 1];
        }
    }
}
