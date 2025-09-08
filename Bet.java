public class Bet {
    public enum Type { PASS_LINE, DONT_PASS }

    private final Type type;
    private final int amount;

    public Bet(Type type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public Type getType() { return type; }
    public int getAmount() { return amount; }
}
