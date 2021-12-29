import CardOperation.Card;
import Player.FirstRebot;
import Player.Robot;
import Player.SecRebot;
import Player.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
//git config --global http.postBuffer 524288000
public class Test {
    public static void main(String[] args) {

        Card card = new Card();
        card.dealCard();
//        card.showHand(card.getCardHeap()); 内部调用查看所有牌


//        user.operation(CardOperation.WholeGame.gameCount);

//        card.showHand(card.getCardHeap());
//        UI userUI=new UI();

        Robot oo = new FirstRebot(card);
        oo.showCard();
        Robot aa = new SecRebot(card);
        aa.showCard();
        User user = new User(card);
        user.showCard();
        user.sortCardHeap();
        user.showCard();
        System.out.println();
        ArrayList<String> ooo=new ArrayList<>();
        ooo.add("♣J");
        int i=0;
        while(i++<3) {
            user.operation(user.getCardHeap(), ooo);
//        Map<Character, Integer> characterIntegerMap = user.censusCardByHashMap();
//        for( Character key:characterIntegerMap.keySet()){
//            System.out.print(key+":"+characterIntegerMap.get(key));
//            System.out.print("  ");
//        }
            System.out.println();
            user.showCard();
            oo.sortCardHeap();
            oo.operation(oo.getCardHeap(), ooo);

        }

    }
}
