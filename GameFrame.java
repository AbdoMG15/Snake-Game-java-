import java.awt.Color;
import javax.swing.JFrame;

public class GameFrame extends JFrame{



    public GameFrame() {

        this.add(new GamePanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Snake Game🐍");
        this.setResizable(false);
        this.setSize(500,500);
        this.pack();
        this.getContentPane().setBackground(Color.black);
        this.setLocationRelativeTo(null);
        
        
        
        
        this.setVisible(true);
    }


}