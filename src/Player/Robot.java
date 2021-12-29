package Player;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import CardOperation.Card;
import CardOperation.WholeGame;

public class Robot {
    ArrayList<String> cardHeap;
    static int dizhuNum = new Random().nextInt(3) + 1;//1 2 3
    static int i = 1;

    public Robot() {
    }

    public Robot(Card card) {
        cardHeap = card.getCardHeap()[i]; //first 1 2
        if ((i++) == dizhuNum)//如果静态变量i==地主数，则添加cardHeap[0]即底牌进此人cardHeap
            cardHeap.addAll(card.getCardHeap()[0]);
    }

    public ArrayList<String> getCardHeap() {
        return cardHeap;
    }

    public void showCard() {
        System.out.println(getClass().getName() + ": " + cardHeap);
    }

    public void sortCardHeap() { //User类 子类特有排序方法
        Collections.sort(cardHeap, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return  s.charAt(1)-t1.charAt(1); /**未完成*/
            }
        });
    }

    public int[] censusCard() { //统计当前手牌出现次数方法
        int[] cardAppearanceCount = new int[15];//定义一个数组 存放每张牌出现的次数
        int index = 0;
        while (index != cardHeap.size())// 3-9
        {
            if (cardHeap.get(index).length() > 2) //牌10存入下标为10的数组去
            {
                cardAppearanceCount[7]++;
                index++;
                continue;
            }
            if (cardHeap.get(index).charAt(1) == 'J') {
                cardAppearanceCount[8]++;
                index++;
                continue;
            }
            if (cardHeap.get(index).charAt(1) == 'Q') {
                cardAppearanceCount[9]++;
                index++;
                continue;
            }
            if (cardHeap.get(index).charAt(1) == 'K') {
                cardAppearanceCount[10]++;
                index++;
                continue;
            }
            if (cardHeap.get(index).charAt(1) == 'A') {
                cardAppearanceCount[11]++;
                index++;
                continue;
            }
            if (cardHeap.get(index).charAt(1) == '2') {
                cardAppearanceCount[12]++;
                index++;
                continue;
            }
            if (cardHeap.get(index).charAt(0) == '小') {
                cardAppearanceCount[13]++;
                index++;
                continue;
            }
            if (cardHeap.get(index).charAt(0) == '大') {
                cardAppearanceCount[14]++;
                index++;
                continue;
            }
            cardAppearanceCount[cardHeap.get(index).charAt(1) - '0' - 3]++;

            index++;
        }
        return cardAppearanceCount;
    }


    public Map<Character, Integer> censusCardByHashMap() { //统计当前手牌出现次数方法
        Map<Character, Integer> countMap = new HashMap<>();//定义一个数组 存放每张牌出现的次数
        for (int index = 0; index < cardHeap.size(); index++) {
            Character cardName = cardHeap.get(index).charAt(0) == '小' ? cardHeap.get(index).charAt(0) : cardHeap.get(index).charAt(1);
            countMap.put(cardName, countMap.getOrDefault(cardName, 0) + 1); //利用getOrDefault统计
        }
        return countMap;
    }

    public Character hasBoom(int[] nums) {
        WholeGame game = new WholeGame();
        for (int i1 = 0; i1 < nums.length; i1++) {
            if (nums[i1] == 4)
                return game.getCardByArrayIndex(i1);
        }
        return ' ';
    }

    /**
     * cardInGame存放此局所有牌    ->   2021-12-29 14:39:12 最新思路  ：牌库中所有牌进HashMap（根据每张牌权值put进去） 比较大小即比较权值
     */
    public void operation(ArrayList<String> cardHeap, ArrayList<String> cardInGame) {
        String lastCardInGame = cardInGame.get(cardInGame.size() - 1);//集合中最后一个元素 若上一家没出则不添加进集合，故总是拿有数据的一家相比
        int cardNum = lastCardInGame.length();//获取长度 若长度不相同 则 直接跳过  ♣3♣3
        System.out.println("lastCard="+lastCardInGame);
        WholeGame game = new WholeGame();
        boolean boom = false;
        String cardPlayerSend = new String();
        int cardPlayerSendCount=0; //统计玩家本轮出了多少张牌 与wholegame挂钩
        int[] countCardArray = censusCard();
        System.out.println(Arrays.toString(countCardArray));//定义countCardArray变量接受censusCard方法返回值
        Map<Character, Integer> map = censusCardByHashMap();

        //cardNum中case 2为单张牌情况
        switch (cardNum) {//User: [大王, ♣Q, ♠Q, ♥K, ♠K, ♣K, ♠J, ♣J, ♣A, ♦A, ♦9, ♦8, ♠8, ♥7, ♣3, ♥3, ♦3]
            case 2: {               //J:74 K:75 Q:81 9:57  0:48  A:65 [10]=10 [11]    [63]

                int minCardAscii = 51;
                // 10 J Q K
                boolean flag = false;

                for (String s : cardHeap) {

                    if (game.compareTo(s, lastCardInGame)) {
                        cardHeap.remove(s); //从当前玩家手牌中移除出的牌(机器人算法:移除可出的最小值)
                        cardInGame.add(s); //将此次出的牌加入到本局游戏所有牌（cardInGame）之中
                        cardPlayerSend = s;
                        flag = true;
                        break;
                    }
                }
                if (!flag && hasBoom(countCardArray) != ' ') {
                    for (int i = 0; i < cardHeap.size(); i++) {
                        if (cardHeap.get(i).charAt(1) == hasBoom(countCardArray)) {
                            cardPlayerSend = cardHeap.remove(i) + " ";
                            cardHeap.remove(i);
                        }
                    }

                    flag = true;
                }

                if (flag)
                    System.out.println(getClass().getName()+"已经出牌：" + cardPlayerSend);
                else
                    System.out.println("玩家过");
            }

        }
    }

}

