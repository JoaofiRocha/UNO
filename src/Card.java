public class Card {
    private int type;
    private String typeStr = "";
    private int color;
    private String colorStr = "";
    private int number;
    private String numberStr = "";

    public Card() {
        int typeSet = (int) (Math.random() * 32);

        //type 0 = color
        //type 1 = +4
        //type 2 = multicolor
        type = 0;
        if (typeSet == 31) {
            type = 1;
            typeStr = "\u001B[36m+4";
        } else if (typeSet == 30) {
            type = 2;
            typeStr = "\u001B[31mM\u001B[34mu\u001B[32ml\u001B[33mt\u001B[31mi\u001B[34mc\u001B[32mo\u001B[33ml\u001B[31mo\u001B[34mr";
        }


        //color 0 = red
        //color 1 = blue
        //color 2 = green
        //color 3 = yellow
        if (type == 0) {
            color = (int) (Math.random() * 4);
            switch (color) {
                case (0) -> colorStr = "\u001B[31m Red ";
                case (1) -> colorStr = "\u001B[34m Blue ";
                case (2) -> colorStr = "\u001B[32m Green ";
                case (3) -> colorStr = "\u001B[33m Yellow ";
            }


        //number 0 - 9 = number
        //number 10 = block
        //number 11 = rotate
        //number 12 = +2

            number = (int) (Math.random() * 13);
            switch (number) {

                case (10) -> numberStr = "block";
                case (11) -> numberStr = "rotate";
                case (12) -> numberStr = "+2";
                default -> numberStr = Integer.toString(number);
            }
        }
    }

    public String getCard(){
        return typeStr + colorStr + numberStr;
    }

    public int getType(){
        return type;
    }

    public int getColor(){
        return color;
    }

    public int getNumber(){
        return number;
    }

    public void changeColor(String colors) {

        switch (colors) {
            case ("red") -> {
                colorStr = "\u001B[31m Red ";
                this.color = 0;
            }
            case ("blue") -> {
                colorStr = "\u001B[34m Blue ";
                this.color = 1;
            }
            case ("green") -> {
                colorStr = "\u001B[32m Green ";
                this.color = 2;
            }
            case ("yellow") -> {
                colorStr = "\u001B[33m Yellow ";
                this.color = 3;
            }
        }
    }
}
