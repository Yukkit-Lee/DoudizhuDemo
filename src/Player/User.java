package Player;

import CardOperation.Card;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;


public class User extends Robot {
    ArrayList<String> cardHeap;

    public User(Card card) {
        super(card);
    }


//    @Override
//    public void operation(int i) {
//        i = CardOperation.WholeGame.gameCount;
//
//        int input = new Scanner(System.in).nextInt();
//        while (CardOperation.WholeGame.cardCount > 17) {
//            if (i == 0&&CardOperation.WholeGame.flag==false)
//                switch (input) {
//                    case 1: { //抢地主
//                        i++;
//                        CardOperation.WholeGame.cardCount--;
//                        System.out.println("抢地主成功!");
//                        CardOperation.WholeGame.flag=true;
//                        //获取Jlabel
//                        break;//close
//                    }
//                    case 2:
//                        break;//close
//                }
//            else {
//                int secInput = new Scanner(System.in).nextInt();
//                switch (secInput) {
//                    case 1://出牌
//                    {
//                        CardOperation.WholeGame.cardCount--;
//                        System.out.println("出牌成功");
//                        break;
//                    }
//                    case 2: {//不要
//                        break;
//                    }
//
//                }
//            }
//        }
//    }
}
