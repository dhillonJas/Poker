public class Deck
{
    private Card[] cards = new Card[52];
    public boolean[] check = new boolean[52];

    public Deck()
    {
        String house = "Diamonds";
        int value = 1;
        for (int i = 0; i < cards.length; i++)
        {
            cards[i] = new Card(house,value++);
            if(i == 12)
            {
                house = "Hearts";
                value = 1;
            }
            else if(i == 25)
            {
                house = "Clubs";
                value = 1;
            }
            else if(i == 38)
            {
                house = "Spades";
                value = 1;
            }
        }
    }

    public void shuffle()
    {
        int random = 50 + (int)(Math.random()*50);
        int index;
        int pos;
        Card temporary;
        for (int i = 0; i < random; i++)
        {
            index = (int) (Math.random()*52);
            pos = (int) (Math.random()*52);
            temporary = cards[index];
            cards[index] = cards[pos];
            cards[pos] = temporary;
        }
    }

    public Card randomCard()
    {
        int index = (int)(Math.random()*52);
        while(check[index])
            index = (int)(Math.random()*52);
        Card card = cards[index];
        check[index] = true;
        return card;
    }

    public Card[] flop()
    {
        return new Card[]{randomCard(),randomCard(),randomCard(),null,null};
    }

    public Card highCard(Card card1, Card card2)
    {
        Card card = card1;
        if(card1.getValue() == 1)
            return card1;
        if(card2.getValue() == 1)
            return card2;
        if(card.getValue() < card2.getValue())
            card = card2;
        return card;
    }


    public String pairs(Card card1, Card card2, Card[] cards)
    {
        String hand;
        int pairs = 0, aPair = 0, bPair = 0;
        if(card1.equals(card2))
            pairs++;
        if(cards != null)
        {
            for (Card card : cards)
            {
                if (card1.equals(card))
                {
                    pairs++;
                    aPair++;
                }
                if (card2.equals(card))
                {
                    pairs++;
                    bPair++;
                }
            }
        }


        if(pairs == 0)
            hand = "High Card";
        else if(pairs == 1)
            hand = "Pair";
        else if(pairs == 2)
        {
            if(aPair == 2 || bPair == 2)
                hand = "Three of a kind";
            else
                hand = "Two pairs";
        }
        else if(pairs == 3)
        {
            if(aPair == 3 || bPair == 3)
                hand = "Four of a kind";
            else if(aPair == 1)
                hand = "Three of a kind";
            else
                hand = "Full house";
        }
        else
            hand = "Four of a kind";
        return hand;
    }

    public Card straight(Card card1, Card card2, Card[] cards)
    {
        Card rCard = null;
        int card1Value = card1.getValue();
        int card2Value = card2.getValue();
        int cardsValue;
        if (isIn(card1Value + 1, card1, card2, cards, null) && isIn(card1Value + 2, card1, card2, cards, null) &&
                isIn(card1Value + 3, card1, card2, cards, null) && isIn(card1Value + 4, card1, card2,   cards, null))
        {
            rCard = card1;
        }
        else if (isIn(card2Value + 1, card1, card2, cards, null) && isIn(card2Value + 2, card1, card2, cards, null) &&
                isIn(card2Value + 3, card1, card2, cards, null) && isIn(card2Value + 4, card1, card2,   cards, null))
        {
            rCard = card2;
        }
        else
        {
            for (Card card : cards)
            {
                if(card != null)
                {
                    cardsValue = card.getValue();
                    if (isIn(cardsValue + 1, card1, card2, cards, null) && isIn(cardsValue + 2, card1, card2, cards, null) &&
                            isIn(cardsValue + 3, card1, card2, cards, null) && isIn(cardsValue + 4, card1, card2, cards, null))
                    {
                        rCard = card;
                        break;
                    }
                }
            }

        }

        return rCard;
    }


    private boolean isIn(int check, Card card1, Card card2, Card[] cards, String house)
    {
        boolean result = false;
        if(house == null)
        {
            for (Card card : cards)
            {
                if (card != null)
                {
                    if (check == card.getValue())
                        result = true;
                    if (check == card1.getValue())
                        result = true;
                    if (check == card2.getValue())
                        result = true;
                }
            }
        }
        else
        {
            for (Card card : cards)
            {
                if(card != null)
                {
                    if(check == card1.getValue() && house.equals(card1.getHouse()))
                        result = true;
                    if(check == card2.getValue() && house.equals(card2.getHouse()))
                        result = true;
                    if(check == card.getValue() && house.equals(card.getHouse()))
                        result = true;
                }
            }
        }
        return result;
    }

    public String flush(Card card1, Card card2, Card[] cards)
    {
        String flush = "No flush";
        int house = 0, aHouse = 0, bHouse = 0;
        if(card1.getHouse().equals(card2.getHouse()))
        {
            house++;
            for (Card card : cards)
            {
                if (card != null && card1.getHouse().equals(card.getHouse()))
                    house++;
            }
            if(house >= 4)
                flush = "Flush";
        }
        else
        {
            for (Card card : cards)
            {
                if(card != null)
                {
                    if (card1.getHouse().equals(card.getHouse()))
                    {
                        house++;
                        aHouse++;
                    }
                    if (card1.getHouse().equals(card.getHouse()))
                    {
                        house++;
                        bHouse++;
                    }
                }
            }
            if(house >= 4 && (aHouse >= 4 || bHouse >= 4))
                flush = "Flush";
        }
        return flush;
    }

    public Card straightFlush(Card straight, Card card1, Card card2, Card[] cards)
    {
        Card card = null;
        String house;
        house = straight.getHouse();
        if(isIn(straight.getValue()+1,card1,card2,cards,house) && isIn(straight.getValue()+2,card1,card2,cards,house) &&
                isIn(straight.getValue()+3,card1,card2,cards,house) && isIn(straight.getValue()+4,card1,card2,cards,house))
            card = straight;
        return card;
    }

    public String royalFlush(Card straightFlush, Card card1, Card card2, Card[] cards)
    {
        String house = straightFlush.getHouse();
        if(isIn(1,card1,card2,cards,house))
            return "Royal Flush";
        else
            return "No royal flush";
    }

}
