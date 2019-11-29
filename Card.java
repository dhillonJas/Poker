public class Card
{
    private String house;
    private int value;

    public Card(String newHouse, int newValue)
    {
        house = newHouse;
        value = newValue;
    }

    public String toString()
    {
        String val;
        if(value == 11)
            val = "Jack";
        else if(value == 12)
            val = "Queen";
        else if(value == 13)
            val = "King";
        else if(value == 1)
            val = "Ace";
        else
            val = Integer.toString(value);
        return val + " of " + house;
    }

    public int getValue()
    {
        return value;
    }

    public String getHouse()
    {
        return house;
    }

    public boolean equals(Card card)
    {
        return card != null && this.value == card.value;
    }
}
