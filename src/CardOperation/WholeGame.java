package CardOperation;

import java.util.HashMap;

public class WholeGame {
    public static int gameCount = 0;
    public static int cardCount = 54;
    public static boolean flag = false;
    public static HashMap<Character, Integer> cardWeight = new HashMap<>();
    public static HashMap<Integer, Character> countAndCard = new HashMap<>();

    public WholeGame() {
        cardWeight.put('3', 1);
        cardWeight.put('4', 2);
        cardWeight.put('5', 3);
        cardWeight.put('6', 4);
        cardWeight.put('7', 5);
        cardWeight.put('8', 6);
        cardWeight.put('9', 7);
        cardWeight.put('1', 8);
        cardWeight.put('J', 9);
        cardWeight.put('Q', 10);
        cardWeight.put('K', 11);
        cardWeight.put('A', 12);
        cardWeight.put('2', 13);
        cardWeight.put('小', 14);
        cardWeight.put('大', 15);
        countAndCard.put(0, '3');
        countAndCard.put(1, '4');
        countAndCard.put(2, '5');
        countAndCard.put(3, '6');
        countAndCard.put(4, '7');
        countAndCard.put(5, '8');
        countAndCard.put(6, '9');
        countAndCard.put(7, '1');
        countAndCard.put(8, 'J');
        countAndCard.put(9, 'Q');
        countAndCard.put(10, 'K');
        countAndCard.put(11, 'A');
        countAndCard.put(12, '2');
    }

    public boolean compareTo(String a, String b) { //出单比较 -> a>b情况下返回true
        //根据有王或无王情况适当调整charAt的index参数
        if (a.contains("王") && b.contains("王"))
            return cardWeight.get(a.charAt(0)) > cardWeight.get(b.charAt(0));
        else if (a.contains("王") && !b.contains("王"))
            return cardWeight.get(a.charAt(0)) > cardWeight.get(b.charAt(1));
        else if (!a.contains("王") && !b.contains("王"))
            return cardWeight.get(a.charAt(1)) > cardWeight.get(b.charAt(1));
        else if (!a.contains("王") && b.contains("王"))
            return cardWeight.get(a.charAt(1)) > cardWeight.get(b.charAt(0));
        return false;
    }

    public int calculateWeight(String card) {//计算card的权值 ， 返回值为card的权值大小
        int point = 0;
        int ansWeight = 0;
        while (point < card.length()) { //1最小为 49 最大Q为81
            if (card.charAt(point) >= 49 && card.charAt(point) <= 81&&card.charAt(point)!='0')//跳过花色及10号牌中的0
                ansWeight += cardWeight.get(card.charAt(point));
            point++;
        }
        return ansWeight;
    }

    public int compareToByWeight(String a, String b) { //多张牌比较 instance:(♣A♦A，♣3♦3)
        //♣A♦A
        return calculateWeight(a)-calculateWeight(b);

        //a和b不相同（♣3♣4 ， ♣4♣4）返回值小于 0  相同返回值等于 0  若a>b 返回值>0
    }

    public Character getCardByArrayIndex(int index) {
        return countAndCard.get(index);
    }

    public int getWeightByCardName(Character firstChar) {
        return cardWeight.get(firstChar);
    }
}
