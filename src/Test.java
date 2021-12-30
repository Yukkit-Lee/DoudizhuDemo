import CardOperation.Card;
import CardOperation.WholeGame;
import Player.RobotPlayer;
import Player.Player;
import Player.User;


//git config --global http.postBuffer 524288000
public class Test {
    public static void main(String[] args) throws InterruptedException {

        Card card = new Card();
        card.dealCard();
//        card.showHand(card.getCardHeap()); 内部调用查看所有牌


//        user.operation(CardOperation.WholeGame.gameCount);

//        card.showHand(card.getCardHeap());
//        UI userUI=new UI();

        WholeGame game = new WholeGame();
        Player robot1 = new RobotPlayer(card);
        Player robot2 = new RobotPlayer(card);
        User user = new User(card);

        robot1.sortCardHeap();
        robot2.sortCardHeap();
        user.sortCardHeap();

        robot1.showCard();
        robot2.showCard();
        user.showCard();
        /**规定三人共出n张牌 第n张牌由谁出则谁胜出*/
        System.out.println("规定三人共出"+game.cardCount+"张牌，第"+game.cardCount+"张牌由谁出则谁胜出");
        //内外侧循环条件一致
        while (true) {
            System.out.println();

            if (game.cardCount > 0) {
                if (game.isClearCardInGame(game.judgeClearArray))//判断是否需要清空桌上牌面
                {
                    game.judgeClearArray.clear();
                    game.cardInGame.clear();
                }
                if (!user.operation(user.getCardHeap(), game)) //添加失败则往List中添加0 反之1
                    game.judgeClearArray.add(0);
                else
                    game.judgeClearArray.add(1);
                Thread.sleep(2000);
            } else break;

            if (game.cardCount > 0) {
                System.out.println();
                if (game.isClearCardInGame(game.judgeClearArray))//判断是否需要清空桌上牌面
                {
                    game.judgeClearArray.clear();
                    game.cardInGame.clear();
                }
                if (!robot1.operation(robot1.getCardHeap(), game))
                    game.judgeClearArray.add(0);
                else
                    game.judgeClearArray.add(1);
                Thread.sleep(2000);
            } else break;

            if (game.cardCount > 0) {
                System.out.println();
                if (game.isClearCardInGame(game.judgeClearArray)) {
                    game.judgeClearArray.clear();
                    game.cardInGame.clear();
                }
                if (!robot2.operation(robot2.getCardHeap(), game))
                    game.judgeClearArray.add(0);
                else
                    game.judgeClearArray.add(1);
                Thread.sleep(2000);
            } else break;


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
