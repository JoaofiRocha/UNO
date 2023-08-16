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
        System.out.println("Select position of the card you want to play: ");
        int play = in.nextInt();

    }
}