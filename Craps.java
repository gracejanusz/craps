import java.util.Random;

public class Craps {
    private final Random rng = new Random();

    private int rollDice() {
        int d1 = rng.nextInt(6) + 1;
        int d2 = rng.nextInt(6) + 1;
        int total = d1 + d2;
        System.out.println("Rolled: " + d1 + " + " + d2 + " = " + total);
        return total;
    }

    public int playRound(Bet bet) {
        
        int amt = bet.getAmount();

        int comeOut = rollDice();

        switch (bet.getType()) {
            case PASS_LINE: {

                // immediate win 7 or 11
                if (comeOut == 7 || comeOut == 11) {
                    System.out.println("Pass Line wins on the come-out!");
                    return amt; // win amt (even money)
                }
                // craps: 2, 3, 12
                if (comeOut == 2 || comeOut == 3 || comeOut == 12) {
                    System.out.println("Craps on the come-out. Pass Line loses.");
                    return -amt;
                }
                // point established
                int point = comeOut;
                System.out.println("Point is " + point + ". Rolling until " + point + " (win) or 7 (lose)...");
                while (true) {
                    int r = rollDice();
                    if (r == point) {
                        System.out.println("Hit the point! Pass Line wins.");
                        return amt;
                    } else if (r == 7) {
                        System.out.println("Seven-out. Pass Line loses.");
                        return -amt;
                    }
                }
            }

            case DONT_PASS: {
                
                // immediate win 2 or 3
                if (comeOut == 2 || comeOut == 3) {
                    System.out.println("Don't Pass wins on the come-out!");
                    return amt;
                }
                if (comeOut == 12) {
                    System.out.println("Bar the 12: push (no win/loss) on the come-out.");
                    return 0;
                }
                // immediate loss 7 or 11
                if (comeOut == 7 || comeOut == 11) {
                    System.out.println("Don't Pass loses on the come-out.");
                    return -amt;
                }
                // point established
                int point = comeOut;
                System.out.println("Point is " + point + ". Rolling until 7 (win) or " + point + " (lose)...");
                while (true) {
                    int r = rollDice();
                    if (r == 7) {
                        System.out.println("Seven-out! Don't Pass wins.");
                        return amt;
                    } else if (r == point) {
                        System.out.println("Point hit. Don't Pass loses.");
                        return -amt;
                    }
                }
            }
        }
        return 0;
    }
}
