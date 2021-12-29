package CardOperation;

import java.util.*;

public class Card {

    private String[] color;
    private String[] num;
    private String[] kingArray;
    private ArrayList<String> cardList;
    private ArrayList<String> firRobotCard;
    private ArrayList<String> secRobotCard;
    private ArrayList<String> userCard;
    private ArrayList<String> handCard;


    public String[] getNum() {
        return num;
    }

    public Card() {//构造方法中对总牌库进行按顺序初始化

        color = new String[]{"♣", "♥", "♦", "♠"};
        num = new String[]{"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A","2"};
        kingArray = new String[]{"小王", "大王"};
        cardList = new ArrayList<>();

        //添加进集合
        for (String colorStr : color) {
            for (String numStr : num)
                cardList.add(colorStr + numStr);
        }
        cardList.add(kingArray[0]);
        cardList.add(kingArray[1]);

    }

    public ArrayList<String> getCardList() {
        return this.cardList;
    }


    public void dealCard() { //Card类中的dealCard洗牌发牌方法
        List<String> randomCardList = getCardList();
        Collections.shuffle(randomCardList);

        firRobotCard = new ArrayList<>();
        secRobotCard = new ArrayList<>();
        userCard = new ArrayList<>();
        handCard = new ArrayList<>();
        for (int i = 0; i < randomCardList.size(); i++) {
            if (i >= 51)
                handCard.add(randomCardList.get(i));
            else if (i % 3 == 0)
                firRobotCard.add(randomCardList.get(i));
            else if (i % 3 == 1)
                secRobotCard.add(randomCardList.get(i));
            else if (i % 3 == 2)
                userCard.add(randomCardList.get(i));
        }

    }


    public ArrayList<String>[] getCardHeap() { //get牌堆，存入一个数组中
        ArrayList<String>[] cardHeap = new ArrayList[4];
        cardHeap[0] = this.handCard;
        cardHeap[1] = this.firRobotCard;
        cardHeap[2] = this.secRobotCard;
        cardHeap[3] = this.userCard;
        return cardHeap;
    }

    public void showHand(ArrayList<String>[] a) {//展现洗牌后各个玩家的牌
        for (ArrayList<String> strings : a) {
            System.out.println("cards:" + strings);
        }
    }
}
