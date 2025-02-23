import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class GameMenu extends JFrame implements KeyListener{



    public GameMenu() {


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Snake Game🐍");
        this.setResizable(false);
        this.setSize(500,500);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.black);
        this.addKeyListener(this);
        
        
        
        
        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("You pressed key code: " + e.getKeyCode());
    }



}