package core.lexer;

public class LexingException extends RuntimeException {

    public LexingException(State state) {
        super("Unknown character " + state.getChar() + " at index " + state.getIndex() + " in section " + state.getSection().toString());
    }
}
