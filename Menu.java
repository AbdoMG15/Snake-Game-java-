import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class Menu extends JFrame implements ActionListener{

    
    JButton playButton;

    Menu(){

        //Menu Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Snake Game🐍");
        this.setResizable(false);
        this.setSize(500,500);
        this.setVisible(true);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.black);

        
        
        
        //Play Button
        playButton = new JButton();
        playButton.setBounds(125, 125, 250, 100);
        playButton.addActionListener(this);
        playButton.setText("Press here to play");
        playButton.setBackground(Color.white);
        playButton.setForeground(Color.red);
        playButton.setFocusable(false);
        playButton.setFont(new Font("MV Boli", Font.PLAIN,25));
        

        
        //Score Label
        JLabel score = new JLabel();
        score.setText("Highest score: " + GameData.highestScore);
        score.setBounds(150, 300, 300, 50);
        score.setFont(new Font("MV Boli", Font.PLAIN,25));
        score.setForeground(Color.red);
        
        
        
        //Adding playButton and score to the menu
        this.add(playButton);
        this.add(score);
        
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton){

            int x = this.getX();
            int y = this.getY();

            this.dispose();
            GameMenu gamemenu = new GameMenu();
            gamemenu.setLocation(x,y);
        }
    }


}