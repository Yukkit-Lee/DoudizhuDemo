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

        WholeGame game=new WholeGame();
        Player robot1=new RobotPlayer(card);
        Player robot2=new RobotPlayer(card);
        User user=new User(card);

        robot1.sortCardHeap();
        robot2.sortCardHeap();
        user.sortCardHeap();

        user.showCard();




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
