import core.lexer.Lexer;
import core.lexer.token.Token;
import core.lexer.token.TokenType;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

public class LexerTest {

    @Test
    public void testLexer() {
        {
            String input = "### Testüberschrift";
            List<Token> token = Lexer.run(input);
            MatcherAssert.assertThat(token.stream().map(Token::tokenType).toList(), Matchers.contains(TokenType.HEADING_TOKEN, TokenType.TEXT_TOKEN));
            MatcherAssert.assertThat(token.get(0).text(), Matchers.is("###"));
            MatcherAssert.assertThat(token.get(1).text(), Matchers.is("Testüberschrift"));
        }
        {
            String input = "###Testüberschrift";
            List<Token> token = Lexer.run(input);
            MatcherAssert.assertThat(token.stream().map(Token::tokenType).toList(), Matchers.contains(TokenType.TEXT_TOKEN));
            MatcherAssert.assertThat(token.get(0).text(), Matchers.is("###Testüberschrift"));
        }
        {
            String input = "Test###Testüberschrift";
            List<Token> token = Lexer.run(input);
            MatcherAssert.assertThat(token.stream().map(Token::tokenType).toList(), Matchers.contains(TokenType.TEXT_TOKEN));
            MatcherAssert.assertThat(token.get(0).text(), Matchers.is("Test###Testüberschrift"));
        }
        {
            String input = "[TAB]";
            List<Token> token = Lexer.run(input);
            MatcherAssert.assertThat(token.stream().map(Token::tokenType).toList(), Matchers.contains(TokenType.OPEN_SQUARE_BRACKET, TokenType.TAG_IDENTIFIER, TokenType.CLOSE_SQUARE_BRACKET));
        }
        {
            String input = "[TAB]\n[TABCELL]";
            List<Token> token = Lexer.run(input);
            MatcherAssert.assertThat(token.stream().map(Token::tokenType).toList(), Matchers.contains(
                TokenType.OPEN_SQUARE_BRACKET,
                TokenType.TAG_IDENTIFIER,
                TokenType.CLOSE_SQUARE_BRACKET,
                TokenType.OPEN_SQUARE_BRACKET,
                TokenType.TAG_IDENTIFIER,
                TokenType.CLOSE_SQUARE_BRACKET
            ));
            MatcherAssert.assertThat(token.get(1).text(), Matchers.is("TAB"));
            MatcherAssert.assertThat(token.get(4).text(), Matchers.is("TABCELL"));
        }
    }

}
