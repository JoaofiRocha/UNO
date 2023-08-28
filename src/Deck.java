public class Deck {
    Card[] card = new Card[35];
    private int numCards = 0;
    public Deck(){
        for(int i = 0; i < 5; i++){
            card[i] = new Card();
            numCards++;
        }
    }

    public int getCardType(int i){
        return card[i].getType();
    }

    public int getCardNumber(int i){
        return card[i].getNumber();
    }

    public int getCardColor(int i){
        return card[i].getColor();
    }

    public void showDeck(){
        Order();
        System.out.println("\u001B[0mYour cards:");
        System.out.print(" | ");
        for(int i = 0; i < numCards; i++){
            System.out.printf("\u001B[37m%d ", i);
            System.out.print(card[i].getCard());
            System.out.print("\u001B[0m | ");
        }
        System.out.println("");
    }

    public void playCard(int a){
        boolean found = false;
        numCards--;
        for(int i = 0; i < numCards; i++){
            if (a == i)
                found = true;
            if (found){
                card[i] = card[i+1];
                card[i+1] = null;
            }

        }
    }

    public int getNumCards(){
        return numCards;
    }

    public void buyCard(int i, int turn)throws InterruptedException{
        int j = 0;
        while(j != i) {
            card[numCards] = new Card();
            if(turn == 1) {
                System.out.printf("%n Card bought: %s \u001B[0m%n", card[numCards].getCard() );
                Thread.sleep(1200);
            }
            numCards++;
            j++;

        }
    }

    private void Order(){
        int nRed = 0;
        int nBlue = 0;
        int nGreen = 0;
        int nYellow = 0;
        int nSpecial = 0;

        // Count the number of each color and special cards
        for (int i = 0; i < numCards; i++) {
            if (getCardColor(i) == 0) {
                nRed++;
            } else if (getCardColor(i) == 1) {
                nBlue++;
            } else if (getCardColor(i) == 2) {
                nGreen++;
            } else if (getCardColor(i) == 3) {
                nYellow++;
            } else if (getCardType(i) > 0) {
                nSpecial++;
            }
        }

        Card[] red = new Card[nRed];
        Card[] blue = new Card[nBlue];
        Card[] green = new Card[nGreen];
        Card[] yellow = new Card[nYellow];
        Card[] special = new Card[nSpecial];

        // Separate cards into respective color arrays
        for (int i = 0; i < numCards; i++) {
            if (getCardColor(i) == 0) {
                red[--nRed] = card[i];
            }
            else if (getCardColor(i) == 1) {
                blue[--nBlue] = card[i];
            }
            else if (getCardColor(i) == 2) {
                green[--nGreen] = card[i];
            }
            else if (getCardColor(i) == 3) {
                yellow[--nYellow] = card[i];
            }
            else if (getCardType(i) > 0) {
                special[--nSpecial] = card[i];
            }
        }

        // Reorder the card array based on color and type
        int index = 0;
        for (int i = 0; i < red.length; i++) {
            card[index++] = red[i];
        }
        for (int i = 0; i < blue.length; i++) {
            card[index++] = blue[i];
        }
        for (int i = 0; i < green.length; i++) {
            card[index++] = green[i];
        }
        for (int i = 0; i < yellow.length; i++) {
            card[index++] = yellow[i];
        }
        for (int i = 0; i < special.length; i++) {
            card[index++] = special[i];
        }
    }
}