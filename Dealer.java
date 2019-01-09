import java.util.*;
import javafx.stage.Stage;
import java.text.DecimalFormat;

public class Dealer
{
    private Card[] deck;
    private int[] totals = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    
    public static void main(String[] args)
    {
        Dealer d = new Dealer();
        d.run(args);
    }
    
    public void run(String[] args)
    {
        DecimalFormat f = new DecimalFormat("##.000000");
        Scanner keyboard = new Scanner(System.in);
        String action = "";
        
        String[] suits = {"C", "D", "H", "S"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        
        deck = new Card[52];
        
        int count = 0;
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 13; j++)
            {
                deck[count] = new Card(suits[i], values[j]);
                count++;
            }
        }
        
        if(args[0].equals("stats"))
        {
            int total = Integer.parseInt(args[1]);
            for(int i = 0; i < total; i++)
            {
                shuffle();
                deal(false);
            }
        
            System.out.println(totals[0] + " High Cards");
            System.out.println(totals[1] + " Pairs");
            System.out.println(totals[2] + " Two Pairs");
            System.out.println(totals[3] + " Trips");
            System.out.println(totals[4] + " Straights");
            System.out.println(totals[5] + " Flushes");
            System.out.println(totals[6] + " Full Houses");
            System.out.println(totals[7] + " Quads");
            System.out.println(totals[8] + " Straight Flushes");
            System.out.println(totals[9] + " Royal Flushes\n");
        
            double sum = 0;
            double[] per = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for(int i = 0; i < 10; i++)
            {
                sum += totals[i];
            }   
            for(int i = 0; i < 10; i++)
            {
                per[i] = totals[i] / (sum/100);
            }
            System.out.println("High Cards: " + f.format(per[0]) + "%");
            System.out.println("Pairs: " + f.format(per[1]) + "%");
            System.out.println("Two Pairs: " + f.format(per[2]) + "%");
            System.out.println("Trips: " + f.format(per[3]) + "%");
            System.out.println("Straights: " + f.format(per[4]) + "%");
            System.out.println("Flushes: " + f.format(per[5]) + "%");
            System.out.println("Full Houses: " + f.format(per[6]) + "%");
            System.out.println("Quads: " + f.format(per[7]) + "%");
            System.out.println("Straight Flushes: " + f.format(per[8]) + "%");
            System.out.println("Royal Flushes: " + f.format(per[9]) + "%");
            
        }
        else if(args[0].equals("hands"))
        {
            while(!action.equals("x"))
            {
                shuffle();
                deal(true);
                action = keyboard.next();
                System.out.println("\n");
            }
        }
        
        //BarChartSample b = new BarChartSample();
        //b.run(totals);
    }
    
    public void deal(boolean hand)
    {
        Card[] board = new Card[5];
        Hand[] hands = new Hand[8];
        
        int count = 0;
        for(int i = 0; i < 5; i++)
        {
            board[i] = deck[i];
            count++;
        }
        
        for(int i = 0; i < 8; i++)
        {
            Card[] c = new Card[2];
            c[0] = deck[count];
            count++;
            c[1] = deck[count];
            count++;
            
            hands[i] = new Hand(c);
        }   
        
        if(hand)
        {
            String[] ranks = {"High Card", "Pair", "Two Pair", "Trips", "Straight", "Flush", "Full House", "Quads", "Straight Flush", "Royal Flush"};
            System.out.print("Board: ");
            for(int i = 0; i < 5; i++)
            {
                System.out.print(board[i].getCard() + " ");
            }
            System.out.println();
            for(int i = 0; i < 8; i++)
            {
                int rank = rankHand(board, hands[i].getHand());
                System.out.print("Hand " + (i+1) + ": ");
                System.out.print(hands[i].printHand() + " - " + ranks[rank] + "\n");
            }
        }
        else
        {
            for(int i = 0; i < 8; i++)
            {
                int x = rankHand(board, hands[i].getHand());
                totals[x]++;
            }
        }
    }
    
    public int rankHand(Card[] b, Card[] h)
    {
        String[] faces = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suit = {"Club", "Diamond", "Heart", "Spade"};
        String[] checkSuit = {"C", "D", "H", "S"};
        Card[] board = {b[0], b[1], b[2], b[3], b[4], h[0], h[1]};
        String rank = "High Card";
        
        int totalAdd = 0;
        
        int[] values = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] suits = {0, 0, 0, 0};
        
        for(int i = 0; i < 7; i++)
        {
            switch(board[i].getValue())
            {
                case "2": values[0] ++;
                          break;
                case "3": values[1] ++;
                          break;
                case "4": values[2] ++;
                          break;
                case "5": values[3] ++;
                          break;
                case "6": values[4] ++;
                          break;
                case "7": values[5] ++;
                          break;
                case "8": values[6] ++;
                          break;
                case "9": values[7] ++;
                          break;
                case "10": values[8] ++;
                          break;
                case "J": values[9] ++;
                          break;
                case "Q": values[10] ++;
                          break;
                case "K": values[11] ++;
                          break;
                case "A": values[12] ++;
                          break;          
            }
            
            switch(board[i].getSuit())
            {
                case "C": suits[0] ++;
                          break;
                case "D": suits[1] ++;
                          break;
                case "H": suits[2] ++;
                          break;
                case "S": suits[3] ++;
                          break;          
            }
        }
        
        //Pair check
        for(int i = 0; i < 13; i++)
        {
            if(values[i] == 2)
            {
                rank = "Pair of " + faces[i] + "'s";
                totalAdd = 1;
            }
        }
        
        //2 Pair check
        int pairs = 0;
        for(int i = 0; i < 13; i++)
        {
            if(values[i] == 2)
            {
                pairs++;
            }
        }
        if(pairs >=2)
        {
            rank = "Two Pair";
            totalAdd = 2;
        }
        
        //Trips Check
        for(int i = 0; i < 13; i++)
        {
            if(values[i] == 3)
            {
                rank = "Trip " + faces[i] + "'s";
                totalAdd = 3;
            }
        }
        
        //Straight Check
        int straight = 0;
        boolean str = false;
        if(values[12] > 0)
        {
            straight++;
        }
        
        for(int i = 0; i < 13; i++)
        {
            if(values[i] > 0)
            {
                straight++;
            }
            else
            {
                straight = 0;
            }
            
            if(straight >= 5)
            {
                str = true;
                rank = faces[i] + " High Straight";
                totalAdd = 4;
            }
        }
        
        //Flush Check
        Card[] flushCards = null;
        boolean flush = false;
        for(int i = 0; i < 4; i++)
        {
            if(suits[i] >= 5)
            {
                rank = suit[i] + " Flush";
                flush = true;
                
                flushCards = new Card[suits[i]];
                totalAdd = 5;
                int c = 0;
                for(int j = 0; j < 7; j++)
                {
                    if(board[j].getSuit().equals(checkSuit[i]))
                    {
                        flushCards[c] = board[j];
                        c++;
                    }
                }
            }
        }
        
        //Full House check
        boolean three = false;
        boolean two = false;
        for(int i = 0; i < 13; i++)
        {
            if(values[i] == 3)
            {
                three = true;
            }
            if(values[i] == 2)
            {
                two = true;
            }
        }
        if(three && two)
        {
            rank = "Full House";
            totalAdd = 6;
        }
        
        //Quads check
        for(int i = 0; i < 13; i++)
        {
            if(values[i] == 4)
            {
                rank = "Quad " + faces[i] + "'s";
                totalAdd = 7;
            }
        }
        
        //Straight flush check
        if(str && flush)
        {
            int[] flushValues = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for(int i = 0; i < flushCards.length; i++)
            {
                switch(flushCards[i].getValue())
                {
                    case "2": flushValues[0] ++;
                              break;
                    case "3": flushValues[1] ++;
                              break;
                    case "4": flushValues[2] ++;
                              break;
                    case "5": flushValues[3] ++;
                              break;
                    case "6": flushValues[4] ++;
                              break;
                    case "7": flushValues[5] ++;
                              break;
                    case "8": flushValues[6] ++;
                              break;
                    case "9": flushValues[7] ++;
                              break;
                    case "10": flushValues[8] ++;
                              break;
                    case "J": flushValues[9] ++;
                              break;
                    case "Q": flushValues[10] ++;
                              break;
                    case "K": flushValues[11] ++;
                              break;
                    case "A": flushValues[12] ++;
                              break;          
                }
            }
            
            int straightFlush = 0;
                if(flushValues[12] > 0)
                {
                    straightFlush++;
                }
        
                for(int i = 0; i < 13; i++)
                {
                    if(flushValues[i] > 0)
                    {
                        straightFlush++;
                    }
                    else
                    {
                        straightFlush = 0;
                    }
            
                    if(straightFlush >= 5)
                    {
                        if(faces[i].equals("A"))
                        {
                            rank = "Royal Flush";
                            totalAdd = 9;
                        }
                        else
                        {
                            rank = faces[i] + " High Straight Flush";
                            totalAdd = 8;
                        }
                    }
                }
        }
        //return rank;
        return totalAdd;
    }
    
    public void printDeck()
    {
        for(int i = 0; i < 52; i++)
        {
            if(i != 51)
            {
                System.out.print(deck[i].getCard() + " ");
            }
            else
            {
                System.out.print(deck[i].getCard() + "\n");
            }
        }
    }
    
    public void shuffle()
    {
        Random r = new Random();
        int x = 0;
        Card c = null;
        
        for(int i = 0; i < 52; i++)
        {
            x = r.nextInt(51 - 0 + 1) + 0;
            c = deck[i];
            deck[i] = deck[x];
            deck[x] = c;
        }
    }
}
