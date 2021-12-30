import CardOperation.Card;
import CardOperation.WholeGame;
import Player.FirstRobot;
import Player.Robot;
import Player.SecRobot;
import Player.User;

import java.util.ArrayList;


//git config --global http.postBuffer 524288000
public class Test {
    public static void main(String[] args) throws InterruptedException {

        Card card = new Card();
        card.dealCard();
//        card.showHand(card.getCardHeap()); 内部调用查看所有牌


//        user.operation(CardOperation.WholeGame.gameCount);

//        card.showHand(card.getCardHeap());
//        UI userUI=new UI();

        Robot oo = new FirstRobot(card);
        oo.sortCardHeap();
        oo.showCard();
        Robot aa = new SecRobot(card);
        aa.sortCardHeap();
        aa.showCard();
        User user = new User(card);
        user.sortCardHeap();
        user.showCard();
        System.out.println();
        ArrayList<String> ooo = new ArrayList<>();
        oo.sortCardHeap();
        aa.sortCardHeap();
        WholeGame game = new WholeGame();
        int i = 0;
        System.out.println("===========");
        int count = 14;
        ArrayList<Integer> list = new ArrayList<>();

        while (count > 3) {
            System.out.println();
            if (game.cardCount > 0) {
                if (game.isClearCardInGame(list))
                    game.cardInGame.clear();
                if (!user.operation(user.getCardHeap(), game))
                    list.add(0);
                else
                    list.add(1);
                Thread.sleep(2000);
                count--;
                System.out.println();
            }

            if (game.cardCount > 0) {
                if (game.isClearCardInGame(list))
                    game.cardInGame.clear();
                if (!oo.operation(oo.getCardHeap(), game))
                    list.add(0);
                else
                    list.add(1);
                Thread.sleep(2000);
                count--;
                System.out.println();
            }

            if (game.cardCount > 0) {
                if (game.isClearCardInGame(list))
                    game.cardInGame.clear();
                if (!aa.operation(aa.getCardHeap(),game))
                    list.add(0);
                else
                    list.add(1);
                Thread.sleep(2000);
                count--;
                System.out.println();
            }
        }
//            ArrayList<String> oop=new ArrayList<>();
//            oop.add("i3");
//            oop.add("3");
//        for (int i1 = 0; i1 < oop.size(); i1++) {
//            if(oop.get(i).contains("3")) {
//                System.out.println(oop.get(i));
//break;
//            }
//        }
    }
}
