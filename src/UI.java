import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {

        JButton startGame,gameHelp;
        JLabel a,b;
        int newWidth=0,newHeight=0;

        public UI(){
            setSize(Const.userInterfaceWidth,Const.userInterfaceHeight);
            setVisible(true);

        }
}
