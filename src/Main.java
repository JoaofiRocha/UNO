import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deck player = new Deck();

        Card table = new Card();
        if (table.getType() != 0)
            table = new Card();
        System.out.println("Current card: " + table.getCard());

        player.showDeck();
        while (true) {
            System.out.println("Select position of the card you want to play: ");
            int play = in.nextInt();
            if (table.getColor() == player.getCardColor(play) || table.getNumber() == player.getCardNumber(play)) {
                table = player.card[play];
                player.playCard(play);
                break;
            } else
                System.out.println("Invalid Card!!!");
        }

        System.out.println("Current card: " + table.getCard());
        player.showDeck();
    }
}