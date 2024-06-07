package core.lexer.token;

public record Token(TokenType tokenType, String text) {

    public Token(TokenType tokenType, String text) {
        this.tokenType = tokenType;
        this.text = text.trim();
    }
}
