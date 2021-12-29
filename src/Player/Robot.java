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
    WholeGame game = new WholeGame();

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
                int sortAns = 0;
                if (s.contains("王") && t1.contains("王"))
                    sortAns = game.getWeightByCardName(s.charAt(0)) - game.getWeightByCardName(t1.charAt(0));
                else if (s.contains("王") && !t1.contains("王"))
                    sortAns = game.getWeightByCardName(s.charAt(0)) - game.getWeightByCardName(t1.charAt(1)); /*未完成*/
                else if (!s.contains("王") && !t1.contains("王"))
                    sortAns = game.getWeightByCardName(s.charAt(1)) - game.getWeightByCardName(t1.charAt(1));
                else if (!s.contains("王") && t1.contains("王"))
                    sortAns = game.getWeightByCardName(s.charAt(1)) - game.getWeightByCardName(t1.charAt(0));
                return sortAns;
            } //2021-12-30 00:23:46 已完成 -> 类比WholeGame类中CompareTo方法
        });
    }

    public int[] censusCard() { //统计当前手牌出现次数方法
        int[] cardAppearanceCount = new int[15];//定义一个数组 存放每张牌出现的次数
        int index = 0;
        while (index != cardHeap.size())// 3-9   index:0 -> card 3   index:1 -> card 4
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
        for (int i1 = 0; i1 < nums.length; i1++) {
            if (nums[i1] == 4)
                return game.getCardByArrayIndex(i1);
        }
        return ' ';
    }

    //[♣4, ♦4, ♥4, ♣5, ♥5, ♦7, ♠7, ♠8, ♣9, ♥9, ♦10, ♣J, ♥J, ♣Q, ♦K, ♣K, ♥2, ♦2, ♣2, 大王]
    //findTheSameCard(cardHeap,3) -> return ♣4♦4♥4
    public String[] findTheSameCard(ArrayList<String> cardHeap, int cardNeedLength) {//找手牌中cardNeedLength张一样的牌，返回符合条件牌组(n张)
        int count = 1, index = 0;
        String ansCards = new String();//ansCards为符合条件的几张牌
        String[] ans = new String[15];
        for (int i = 0; i < cardHeap.size(); i++) {
            ansCards = cardHeap.get(i);
            if (i < cardHeap.size() - 1 && game.calculateWeight(cardHeap.get(i)) == game.calculateWeight(cardHeap.get(i + 1))) {
                count++;
                ansCards += cardHeap.get(i + 1);
            } else //若接下来一张牌与前几张不同则count清零
                count = 1;
            if (count == cardNeedLength) {
                ans[index++] = ansCards;
                count = 1;//找到后count置零  排除统计超过cardNeedLength情况
            }
        }
        return ans;//没有相同牌直接返回null

        //[♠4, ♣4, ♠5, ♦6, ♣6, ♣7, ♥7, ♦7, ♠7, ♠8, ♣9, ♥9, ♥10, ♣10, ♦Q, ♣Q, ♥K, ♠K, ♦A, 小王] ->♠4♦6
    }

    /**
     * cardInGame存放此局所有牌    ->   2021-12-29 14:39:12 最新思路  ：牌库中所有牌进HashMap（根据每张牌权值put进去） 比较大小即比较权值
     */
    public void operation(ArrayList<String> cardHeap, ArrayList<String> cardInGame) {
        String lastCardInGame = cardInGame.get(cardInGame.size() - 1);//集合中最后一个元素 若上一家没出则不添加进集合，故总是拿有数据的一家相比
        int cardNum = lastCardInGame.length();//获取长度 若长度不相同 则 直接跳过  ♣3♣3
        System.out.println("lastCard=" + lastCardInGame);
        boolean boom = false;
        String cardPlayerSend = new String();
        int cardPlayerSendCount = 0; //统计玩家本轮出了多少张牌 与wholegame挂钩
        int[] countCardArray = censusCard();
//        System.out.println(Arrays.toString(countCardArray));//定义countCardArray变量接受censusCard方法返回值
        Map<Character, Integer> map = censusCardByHashMap();

        /**2021-12-30 00:59:56 switch应根据权值选择 (否则牌面为10则出现异常)*/
        //cardNum中case 2为单张牌情况
        switch (cardNum) {//User: [大王, ♣Q, ♠Q, ♥K, ♠K, ♣K, ♠J, ♣J, ♣A, ♦A, ♦9, ♦8, ♠8, ♥7, ♣3, ♥3, ♦3]
            case 2: {               //J:74 K:75 Q:81 9:57  0:48  A:65 [10]=10 [11]    [63]

                int minCardAscii = 51;
                // 10 J Q K
                boolean flag = false;

                for (String s : cardHeap) {

                    if (game.compareTo(s, lastCardInGame)) {
                        cardHeap.remove(s); //从当前玩家手牌中移除出的牌(机器人算法:移除可出的最小值)
                        cardPlayerSend = s;
                        flag = true;
                        break;
                    }
                }
                if (!flag && hasBoom(countCardArray) != ' ') {
                    Character boomChar = hasBoom(countCardArray);
                    for (int i = 0; i < cardHeap.size(); i++) {
                        if (cardHeap.get(i).charAt(1) == boomChar) {
                            for (int j = 0; j < 4; j++) {  //此处利用remove方法返回值为删除的元素 直接循环删除邻近的4个元素
                                cardPlayerSend += cardHeap.remove(i); //ArraysList动态 所以索引不用循环+1
                            }
                            break;
                        }
                    }
                    flag = true;
                }

                if (flag) {
                    System.out.println(getClass().getName() + "已经出牌：" + cardPlayerSend);
                    cardInGame.add(cardPlayerSend); //将此次出的牌加入到本局游戏所有牌（cardInGame）之中
                } else
                    System.out.println("玩家过");
                break;
            }

            case 4: {
                boolean flag = false;
                try {//perhapsAns可能为空 会出现空指针异常
                    String[] perhapsArray = findTheSameCard(cardHeap, 2);
                    for (int i = 0; i < perhapsArray.length; i++) {
//                        System.out.println(perhapsArray[i]);
//                        System.out.println(game.compareToByWeight(perhapsArray[i], lastCardInGame));
                        if (game.compareToByWeight(perhapsArray[i], lastCardInGame) > 0) {
                            //substring只适用于双数牌为2的情况 10不可用
                            int index = cardHeap.indexOf(perhapsArray[i].substring(0, 2));//获取此牌在手牌中的索引位置
                            for (int j = 0; j < 2; j++) {
                                cardPlayerSend += cardHeap.remove(index);
                            }
                            flag = true;
                            break;
                        }
                    }

                } catch (NullPointerException e) {   //开始使用炸弹
                    if (hasBoom(countCardArray) != ' ') {
                        Character boomChar = hasBoom(countCardArray);
                        for (int i = 0; i < cardHeap.size(); i++) {
                            if (cardHeap.get(i).charAt(1) == boomChar) {
                                for (int j = 0; j < 4; j++) {  //此处利用remove方法返回值为删除的元素 直接循环删除邻近的4个元素
                                    cardPlayerSend += cardHeap.remove(i); //ArraysList动态 所以索引不用循环+1
                                }
                                break;
                            }
                        }
                        flag = true;
                    }
                }
                if (flag) {
                    System.out.println(getClass().getName() + "已经出牌：" + cardPlayerSend);
                    cardInGame.add(cardPlayerSend); //将此次出的牌加入到本局游戏所有牌（cardInGame）之中
                } else
                    System.out.println("玩家过");
                break;

            }

        }
    }

}

