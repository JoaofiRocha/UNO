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
        System.out.println("\u001B[0mYour cards:");
        System.out.print(" | ");
        for(int i = 0; i < numCards; i++){
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

    public void buyCard(){
        card[numCards] = new Card();
        numCards++;
    }
}
