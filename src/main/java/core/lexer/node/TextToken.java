package core.lexer.node;

public class TextToken implements Token {

    private String content;

    @Override
    public String toString() {
        return "TEXT content=[" + content + "]";
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
