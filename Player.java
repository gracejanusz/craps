public class Player {
    private int balance;

    public Player(int startingBalance) {
        this.balance = startingBalance;
    }

    public int getBalance() {
        return balance;
    }

    public boolean canBet(int amount) {
        return amount > 0 && amount <= balance;
    }

    public void win(int amount) {
        balance += amount;
    }

    public void lose(int amount) {
        balance -= amount;
    }
}
