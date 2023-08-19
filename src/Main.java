import java.util.Scanner;
public class Main {
    static Card table;
    static int turn;
    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);

        //generate playerDeck
        Deck player = new Deck();
        Deck bot = new Deck();
        turn = 1;

        //generate card to begin the game
        table = new Card();
        if (table.getType() != 0)
            table = new Card();

        //loop of the main game
        boolean game = true;
        while(game) {
            while (turn == 1) {
                //show table card and number of opponent cards
                Clear();
                System.out.println("Current card: " + table.getCard());
                Thread.sleep(1000);
                System.out.println("\u001B[0mOpponent has " + bot.getNumCards() + " cards!");

                player.showDeck();

                //play or buy
                System.out.println("Buy or select position of the card you want to play: ");
                String playerInput = in.next().toLowerCase();

                //buys new card
                if (playerInput.equals("buy")) {
                    player.buyCard(1, 1);
                }

                //checks if card is valid
                else {
                    int play = Integer.parseInt(playerInput);
                    if (play < player.getNumCards() && (player.getCardType(play) > 0 || table.getColor() == player.getCardColor(play) || table.getNumber() == player.getCardNumber(play))) {
                        table = player.card[play];

                        //checks special cards
                        if (player.getCardNumber(play) == 10 || player.getCardNumber(play) == 11) {
                            turn = 1;
                        } else if (player.getCardNumber(play) == 12) {
                            bot.buyCard(2, 0);
                            turn = 0;
                        } else {
                            turn = 0;
                        }

                        //checks ultra special cards
                        if (table.getType() == 2 || table.getType() == 1) {
                            while (true) {
                                System.out.println("Chose color: ");
                                String colorPick = in.next().toLowerCase();
                                if (colorPick.equals("red") || colorPick.equals("blue") || colorPick.equals("green") || colorPick.equals("yellow")) {
                                    MulticolorOrPlus4(table, colorPick);
                                    if(table.getType() == 1)
                                        bot.buyCard(4, 0);
                                    break;
                                }
                            }
                        }

                        player.playCard(play);
                        break;

                    } else
                        System.out.println("Invalid Card!!!");
                }

            }

            //bot plays
            BotPlay(bot, player);

            //UNO and end game
            if (player.getNumCards() == 1){
                System.out.println("UNO!!!");
            Thread.sleep(1000);
            }
            else if (bot.getNumCards() == 1){
                System.out.println("BOT UNO!!!");
            Thread.sleep(1000);
            }

            else if (player.getNumCards() == 0) {
                game = false;
                System.out.println("YOU ARE THE BEST!!!");
            }
            else if (bot.getNumCards() == 0) {
                game = false;
                System.out.println("LOSER!!!");
            }
        }
    }

    public static void MulticolorOrPlus4(Card card,String color){
        card.changeColor(color);
    }


    public static void BotPlay(Deck bot, Deck player) throws InterruptedException {
        boolean played = false;
        int red = 0;
        int blue = 0;
        int green = 0;
        int yellow = 0;

        //checks if it is the bot turn
        if(turn == 1)
            return;

        while (!played) {

            //if it has a normal card it plays, else it counts how many of each color he has
            for (int i = 0; i < bot.getNumCards(); i++) {
                if (bot.getCardNumber(i) == table.getNumber() || bot.getCardColor(i) == table.getColor()) {
                    table = bot.card[i];
                    System.out.printf("%n%n\u001B[0mOpponentBot played %s \u001B[0m%n%n", table.getCard());
                    Thread.sleep(1000);
                    bot.playCard(i);
                    played = true;
                    break;
                } else if (bot.getCardColor(i) == 0)
                    red++;
                else if (bot.getCardColor(i) == 1)
                    blue++;
                else if (bot.getCardColor(i) == 2)
                    green++;
                else if (bot.getCardColor(i) == 3)
                    yellow++;
            }
            //Ultra special cards, picks a color
            for (int i = 0; i < bot.getNumCards(); i++) {
                if (bot.getCardType(i) > 0) {
                    table = bot.card[i];
                    System.out.printf("%n%n\u001B[0mOpponentBot played %s \u001B[0m%n%n", table.getCard());
                    Thread.sleep(1000);
                    if (red > blue && blue > green && green > yellow)
                        MulticolorOrPlus4(table, "red");
                    else if (red < blue && blue > green && green > yellow)
                        MulticolorOrPlus4(table, "blue");
                    else if (green > blue && green > red && green > yellow)
                        MulticolorOrPlus4(table, "green");
                    else if (yellow > blue && yellow > green && yellow > red)
                        MulticolorOrPlus4(table, "yellow");
                    else if (red == blue) {
                        int color = (int)(Math.random() * 2);
                        if (color == 0)
                            MulticolorOrPlus4(table, "red");
                        else if (color == 1)
                            MulticolorOrPlus4(table, "blue");
                    } else if (green == yellow) {
                        int color = (int)(Math.random() * 2);
                        if (color == 0)
                            MulticolorOrPlus4(table, "green");
                        else if (color == 1)
                            MulticolorOrPlus4(table, "yellow");
                    } else {
                        int color = (int)(Math.random() * 2);
                        if (color == 0)
                            MulticolorOrPlus4(table, "red");
                        else if (color == 1)
                            MulticolorOrPlus4(table, "green");
                        else
                            MulticolorOrPlus4(table, "blue");
                    }
                    if (bot.getCardType(i) == 1) {
                        player.buyCard(4,1);
                    }
                    bot.playCard(i);
                    played = true;
                    break;
                }
            }
            if (!played)
                bot.buyCard(1,turn);
        }
        //special cards
        if(table.getNumber() == 12) {
            player.buyCard(2,1);
            turn = 1;
        }
        else if(table.getNumber() == 10 || table.getNumber() == 11 && table.getType() == 0)
            turn = 0;
        else
        turn = 1;
    }

    public static void Clear(){
        System.out.printf("%n%n%n%n%n%n%n%n%n%n%n%n%n");
    }
}