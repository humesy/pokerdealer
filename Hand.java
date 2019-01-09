public class Hand
{
    private Card[] h;
    
    public Hand(Card[] c)
    {
        h = c;
    }

    public Card[] getHand()
    {
        return h;
    }
    
    public String printHand()
    {
        String s = h[0].getCard() + " " + h[1].getCard();
        return s;
    }
}
