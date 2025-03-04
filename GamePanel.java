import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.*;


public class GamePanel extends JPanel implements ActionListener{

    static final int SCREEN_WIDTH = 500;
    static final int SCREEN_HEIGHT = 500;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 75;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    JButton menuButton;


    

    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame(){
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
		
		if(running) {
			g.setColor(Color.red);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
		
			for(int i = 0; i< bodyParts;i++) {
                g.setColor(Color.white);
                g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);	
			}
        }
		else {
			gameOver(g);
		}
		
	}

	public void newApple(){
        //spawn a new apple in a randon place that the snake isn't on
        while(true){
            boolean sameSpot = false;
            appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
            appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
            
            for(int i = bodyParts;i >= 0; i--) {
                if (x[i] == appleX && y[i] == appleY) {
                    sameSpot = true;
                }
            }
            if(!sameSpot){
                break;
            }
        }
	}

	public void move(){

		for(int i = bodyParts;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;

		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;

		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;

		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
		
	}

	public void checkApple() {
		if((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			applesEaten++;
			newApple();
		}
	}

	public void checkCollisions() {

        for(int i = bodyParts;i>0;i--) {
            //checks if head collides with body
			if((x[0] == x[i])&& (y[0] == y[i])) {
				running = false;
			}
		}

        //check if head touches left border
        if(x[0] < 0) {
            x[0] = SCREEN_WIDTH;
        }

        //check if head touches right border
        if(x[0] > SCREEN_WIDTH) {
            x[0] = 0;
        }

        //check if head touches top border
        if(y[0] < 0) {
            y[0] = SCREEN_HEIGHT;
        }

        //check if head touches bottom border
        if(y[0] > SCREEN_HEIGHT) {
            y[0] = 0;
        }

		
		if(!running) {
			timer.stop();
		}
	}
	public void gameOver(Graphics g) {

		//Score
		g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten))/2, 330);
		//Game Over text
		g.setColor(Color.red);
		g.setFont( new Font("MV Boli", Font.PLAIN, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
        if( applesEaten > GameData.highestScore){
            GameData.highestScore = applesEaten;
        }

        //Play Button
        if (menuButton == null) {
            menuButton = new JButton("Main menu");
            menuButton.setBounds(125, SCREEN_HEIGHT - 120, 250, 100);
            menuButton.addActionListener(this);
            menuButton.setBackground(new Color(30, 30, 30));
            menuButton.setForeground(Color.red);
            menuButton.setFocusable(false);
            menuButton.setFont(new Font("MV Boli", Font.PLAIN, 25));
            this.add(menuButton);
            this.revalidate();
            this.repaint();
        }
	}


	@Override
    public void actionPerformed(ActionEvent e) {
        //starting the game
        if(running) {
            move();
            checkApple();
            checkCollisions();
            repaint();
        }
        
        //Returning to main menu
        if(e.getSource() instanceof JButton) {
            JButton clickedButton = (JButton) e.getSource();

            if (clickedButton.getText().equals("Main menu")) {
                int x = this.getTopLevelAncestor().getX();
                int y = this.getTopLevelAncestor().getY();
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                topFrame.dispose();
                Menu menu = new Menu();
                menu.setLocation(x, y);
            }
        }
    }

	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {

			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:

				if(direction != 'L') {
					direction = 'R';
				}
				break;

			case KeyEvent.VK_UP:

				if(direction != 'D') {
					direction = 'U';
				}
				break;

			case KeyEvent.VK_DOWN:

				if(direction != 'U') {
					direction = 'D';
				}
				break;
			}
		}
	}
}