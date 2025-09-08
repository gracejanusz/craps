import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Player player = new Player(100); 
        Craps game = new Craps();

        System.out.println("=== Console Craps ===");
        System.out.println("You start with $" + player.getBalance());

        while (true) {
            System.out.println("\nBalance: $" + player.getBalance());
            if (player.getBalance() <= 0) {
                System.out.println("You're out of money. Game over.");
                break;
            }

            System.out.print("Choose bet type ([P]ass Line / [D]on't Pass / [Q]uit): ");
            String choice = in.nextLine().trim().toUpperCase();
            if (choice.equals("Q")) {
                System.out.println("Thanks for playing! Your final balance was: $" + player.getBalance());
                break;
            }

            Bet.Type type;

            if (choice.equals("P")) {
                type = Bet.Type.PASS_LINE;
            } else if (choice.equals("D")) {
                type = Bet.Type.DONT_PASS;
            } else {
                System.out.println("Invalid choice.");
                continue;
            }

            System.out.print("Bet amount: ");
            String amtStr = in.nextLine().trim();
            int amt;
            try {
                amt = Integer.parseInt(amtStr);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
                continue;
            }

            if (!player.canBet(amt)) {
                System.out.println("Invalid amount. Must be > 0 and ≤ your balance.");
                continue;
            }

            Bet bet = new Bet(type, amt);
            int result = game.playRound(bet);

            if (result > 0) {
                System.out.println("You won $" + result + "!");
                player.win(result);
            } else if (result < 0) {
                System.out.println("You lost $" + (-result) + ".");
                player.lose(-result);
            } else {
                System.out.println("Push. No change.");
            }
        }

        in.close();
    }
}
