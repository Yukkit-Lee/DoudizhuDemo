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

    public boolean compareTo(String a, String b) {

        if (a.contains("王") && b.contains("王"))
            return cardWeight.get(a.charAt(0)) > cardWeight.get(b.charAt(0));
        else if (a.contains("王") && !b.contains("王"))
            return cardWeight.get(a.charAt(0)) > cardWeight.get(b.charAt(1));
        else if (!a.contains("王") && !b.contains("王"))
            return cardWeight.get(a.charAt(1)) > cardWeight.get(b.charAt(1));
        return false;
    }

    public Character getCardByArrayIndex(int index) {
        return countAndCard.get(index);
    }
}
