package core.lexer.node;

public class TextNode implements Node{

    private String content;

    @Override
    public String toString() {
        return "TEXT content=[" + content + "]";
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }
}
