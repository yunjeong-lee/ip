public class KnightException extends Exception {
    public KnightException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
