class Table
{
    public Player[] players;
    private Deck deck = new Deck();
    private Card[] tableCards;
    public int pot;

    public Table(int numOfPlayers, int money)
    {
        deck.shuffle();
        players = new Player[numOfPlayers];



        Card card, card1;
        for (int i = 0; i < numOfPlayers; i++)
        {
            card = deck.randomCard();
            card1 = deck.randomCard();
            players[i] = new Player(card,card1, money);
        }
    }

    public void flop()
    {
        tableCards = deck.flop();
    }

    public void turn()
    {
        tableCards[3] = deck.randomCard();
    }

    public void river()
    {
        tableCards[4] = deck.randomCard();
    }

    public int checkHands(Player player)
    {
        int chance = 0;
        Card card1, card2;
        Card[] cards;
        String pairs;
        Card straight;
        String flush = "No flush";
        Card straightFlush;
        String royalFlush = "No royal flush";
        String isStraight = "No straight", isStraightFlush = "No straight flush";
        card1 = player.getCard();
        card2 = player.getCard2();
        cards = tableCards;
        pairs = deck.pairs(card1, card2, cards);
        if (cards != null)
        {
            straight = deck.straight(card1, card2, cards);
            flush = deck.flush(card1, card2, cards);
            if(flush.equals("Flush"))
                chance = 91;
            if (straight != null)
            {
                chance = 91;
                isStraight = "Straight";
                straightFlush = deck.straightFlush(straight, card1, card2, cards);
                if (straightFlush != null)
                {
                    chance = 97;
                    isStraightFlush = "Straight flush";
                    royalFlush = deck.royalFlush(straightFlush, card1, card2, cards);
                    if(royalFlush.equals("Royal Flush"))
                        chance = 100;
                }

            }
        }
            System.out.println("Player has\n" + pairs + "\n" + isStraight + "\n" + flush + "\n" + isStraightFlush + "\n" + royalFlush + "\n");
        
        
        int highCard = deck.highCard(card1,card2).getValue();
        if ("High Card".equals(pairs))
            if (highCard != 1)
                chance = highCard * 3;
            else
                chance = 30;
        else if ("Pair".equals(pairs))
            if (highCard != 1)
                chance = highCard * 6;
            else
                chance = 65;
        else if ("Two pairs".equals(pairs))
            chance = 70;
        else if ("Three of a kind".equals(pairs))
            chance = 73;
        else if ("Four of a kind".equals(pairs))
            chance = 79;
        else if ("Full house".equals(pairs))
            chance = 81;

        return chance;
    }


    public int shouldRaise(Player player,int chance)
    {
        int number = (int)(Math.random()*100);
        int amount = (int)(Math.random()*100);
        if(number <= chance)
        {
            player.raise(amount);
            pot += amount;
            return amount;
        }
        return 0;
    }

    public void shouldCall(Player player, int chance)
    {
        int number = (int)(Math.random()*100);
        if(number <= chance*2.3)
        {

        }
    }

}
