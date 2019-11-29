public class Main
{
    public static void main(String[] args)
    {
        int numOfPlayers = 8;
        int money = 1000;
        Table table = new Table(numOfPlayers, money);
        Player[] players = table.players;
        int a;
        int sBlind = money/(5*numOfPlayers);
        int bBlind = 2*sBlind;
        int bBlindAt = (int)(Math.random()*numOfPlayers);
        int bet = bBlind;
        Player player;

        int whoseTurn = bBlindAt;

        while(true)
        {
            System.out.println("Player at " + whoseTurn + " is big blind.\nBig blind = " + bBlind);
            System.out.println("Player at " + (whoseTurn+1) + " is small blind.\nSmall blind = " + sBlind);
            table.pot = bBlind;
            players[whoseTurn++].raise(bBlind);
            table.pot += sBlind;
            players[whoseTurn++].raise(sBlind);
            while (bet != players[whoseTurn].bet)
            {
                a = table.checkHands(players[whoseTurn]);
                bet += table.shouldRaise(players[whoseTurn++],a);
                if(whoseTurn == players.length)
                    whoseTurn = 0;
            }
            break;
        }

//        System.out.println("PRE FLOP");
//        for (int i = 0; i < table.players.length; i++)
//        {
//            player = table.players[i];
//            a = table.checkHands(player);
//            table.shouldRaise(player,a);
//        }
//        table.checkHands();
//
//        System.out.println("\n\nAFTER FLOP");
//        table.flop();
//        table.checkHands();
//
//
//        System.out.println("\n\nAFTER TURN");
//        table.turn();
//        table.checkHands();
//
//        System.out.println("\n\nAFTER RIVER");
//        table.river();
//        a = table.checkHands();
//        table.shouldRaise(a);

        System.out.println();
    }
}
