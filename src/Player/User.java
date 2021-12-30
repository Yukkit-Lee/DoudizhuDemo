package Player;

import CardOperation.Card;
import CardOperation.WholeGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class User extends Player {
    ArrayList<String> cardHeap;
    int cardCount;

    public User(Card card) {
        super(card);
    }

    public void showCard() {
        System.out.println(getClass().getName() + ": " + super.cardHeap);
    }
    public boolean operation(ArrayList<String> cardHeap,  WholeGame game) {
        String cardPlayerSend = new String();
        System.out.println("input or pass(input:pass):");
        String inputCard = new Scanner(System.in).next();
        if (!inputCard.contains("pass")) {
            cardPlayerSend = inputCard;
            String[] splitCard = inputCard.split(",");

            int i;
            for (i = 0; i < splitCard.length; i++) {
                cardHeap.remove(splitCard[i]);
            }
            System.out.println(getClass().getName() + "已经出牌：" + cardPlayerSend.replace(",",""));
            game.cardCount -= i;
            game.cardInGame.add(cardPlayerSend.replace(",",""));//删去输入的英文逗号并添加进cardInGame
            System.out.println("玩家手牌:" + cardHeap);
            return true;
        } else {
            System.out.println(this.getClass().getName() + "玩家过");
            return false;
        }
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
