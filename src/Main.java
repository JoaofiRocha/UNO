import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //generate playerDeck
        Deck player = new Deck();
        Deck bot = new Deck();

        //generate card to begin the game
        Card table = new Card();
        if (table.getType() != 0)
            table = new Card();

        //loop of the main game
        boolean game = true;
        while(game) {
            System.out.println("Current card: " + table.getCard());

            player.showDeck();

            //play or buy
            while (true) {
                System.out.println("Buy or select position of the card you want to play: ");
                String playerInput = in.next().toLowerCase();
                if (playerInput.equals("buy")) {
                    player.buyCard();
                    System.out.println("Current card: " + table.getCard());
                    player.showDeck();
                }

                //checks if card is valid
                else {
                    int play = Integer.parseInt(playerInput);
                    if (play < player.getNumCards() && (player.getCardType(play) > 0 ||table.getColor() == player.getCardColor(play) || table.getNumber() == player.getCardNumber(play))) {
                        table = player.card[play];

                        if(table.getType() == 2){
                            System.out.println("Chose color: ");
                            MulticolorOrPlus4(table, in.next().toLowerCase());
                        }
                        else if(table.getType() == 1){
                            System.out.println("Chose color: ");
                            MulticolorOrPlus4(table, in.next().toLowerCase());
                            bot.buyCard();
                            bot.buyCard();
                            bot.buyCard();
                            bot.buyCard();
                        }
                        else if(table.getNumber() == 12){
                            bot.buyCard();
                            bot.buyCard();
                        }
                        player.playCard(play);
                        break;
                    } else
                        System.out.println("Invalid Card!!!");
                }
            }
            if (player.getNumCards() == 1)
                System.out.println("UNO!!!");

            else if (player.getNumCards() == 0) {
                game = false;
                System.out.println("YOU WON!!!");
            }
        }
    }

    public static void MulticolorOrPlus4(Card card,String color){
        card.changeColor(color);
    }

    public static Card BotPlay(Card table, Deck bot){


        return table;
    }
}