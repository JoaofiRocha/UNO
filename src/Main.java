import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deck player = new Deck();

        Card table = new Card();
        if (table.getType() != 0)
            table = new Card();
        boolean game = true;
        while(game) {
            System.out.println("Current card: " + table.getCard());

            player.showDeck();
            boolean buy;
            while (true) {
                do {
                    buy = false;
                    System.out.println("Buy or play?");
                    String playerInput = in.next().toLowerCase();
                    if (playerInput.equals("buy")) {
                        player.buyCard();
                        buy = true;
                        System.out.println("Current card: " + table.getCard());
                        player.showDeck();
                    }
                }
                    while(buy);


                System.out.println("Select position of the card you want to play: ");
                int play = in.nextInt();
                if (table.getColor() == player.getCardColor(play) || table.getNumber() == player.getCardNumber(play)) {
                    table = player.card[play];
                    player.playCard(play);
                    break;
                } else
                    System.out.println("Invalid Card!!!");
            }
            if (player.getNumCards() == 1)
                System.out.println("UNO!!!");

            else if (player.getNumCards() == 0)
                game = false;
        }
    }
}