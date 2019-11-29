public class Player
{
    private Card card;
    private Card card2;
    private int money;
    public int bet;

    public Player(Card card, Card card2, int money)
    {
        this.card = card;
        this.card2 = card2;
        this.money = money;
    }

    public Card getCard()
    {
        return card;
    }

    public Card getCard2() {
        return card2;
    }

    public void raise(int amount)
    {
        money -= amount;
        bet += amount;
    }
}
