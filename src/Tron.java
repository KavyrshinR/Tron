import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class Tron {
    public static void main(String [] args) {
        MyTronGame mtg = new MyTronGame();
        mtg.go();
    }
}

class MyTronGame {
    public static int Scale = 8;
    public static int Width = 80;
    public static int Height = 80;
    JFrame frame = new JFrame("TronGame");
    CurrentOs os = new CurrentOs();
    Thread t;
    GamePanel gp;
    Lightcycle moto1;
    Lightcycle moto2;
    Color backColor = new Color(80, 102, 80);
    Color sharpColor = new Color(51, 9, 221);
	
    public void go() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");

        JMenuItem newGameMenuItem = new JMenuItem("New Game");
        JMenuItem resetGameResult = new JMenuItem("Reset Result");
        newGameMenuItem.addActionListener(new NewGameListener());
        resetGameResult.addActionListener(new ResetGameListener());

        menu.add(newGameMenuItem);
        menu.add(resetGameResult);

        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        gp = new GamePanel();
        gp.setFocusable(true);
        gp.requestFocus();
        gp.addKeyListener(new KeyListen());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, gp);

        os.identifyOs();
        frame.setSize(Width * Scale + os.getWidth(), Height * Scale + os.getHeight());

        frame.setVisible(true);
        frame.setResizable(false);

        moto1 = new Lightcycle(10, 40, 3, 255, 82, 82);
        moto2 = new Lightcycle(70, 40, 2, 200, 0, 100);

        t = new Thread(new MyRunnable());
        t.start();
    }
	
	public void startNewGame() {
		moto1.setCoordinate(10, 40);
        moto1.setDirection(3);
		moto2.setCoordinate(70, 40);
        moto2.setDirection(2);
		t = new Thread(new MyRunnable());
        t.start();
	}
	
    class GamePanel extends JPanel {
        private final Graphics2D G;
        private final BufferedImage buffer;

        public GamePanel() {
            buffer = new BufferedImage(Width * Scale, Height * Scale, BufferedImage.TYPE_INT_RGB);
            G = buffer.createGraphics();
        }

        @Override
        protected void paintComponent(Graphics g) {
            draw(G);
            g.drawImage(buffer, 0, 0, null);
        }

        public void draw(Graphics g) {
            
            g.setColor(backColor);
            g.fillRect(0, 0, Width * Scale, Height * Scale);
            
            g.setColor(sharpColor);
            for(int i = 0; i < Width * Scale; i += Scale * 3) {
                g.drawLine(i, 0, i, Width * Scale);
                g.drawLine(0, i, Height * Scale, i);
            }

            g.setColor(Color.WHITE);
            String str = "Player 1| " + moto1.win + " |vs| " + moto2.win + " |Player 2";
            g.drawString(str, 280, 40);

            g.setColor(moto1.color);
            for(int i = 0; i < moto1.len; i++) {
                g.fillRect(moto1.coordX[i] * Scale, moto1.coordY[i] * Scale, Scale - 1, Scale - 1);
            }
            
            g.setColor(moto2.color);
            for(int i = 0; i < moto2.len; i++) {
                g.fillRect(moto2.coordX[i] * Scale, moto2.coordY[i] * Scale, Scale - 1, Scale - 1);
            }
        }
    }
	
    class KeyListen implements KeyListener {
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_W && moto1.getDirection() != 1) {
                moto1.setDirection(0);
            }
            if(e.getKeyCode() == KeyEvent.VK_S && moto1.getDirection() != 0) {
                moto1.setDirection(1);
            }
            if(e.getKeyCode() == KeyEvent.VK_A && moto1.getDirection() != 3) {
                moto1.setDirection(2);
            }
            if(e.getKeyCode() == KeyEvent.VK_D && moto1.getDirection() != 2) {
                moto1.setDirection(3);
            }
            
            if(e.getKeyCode() == KeyEvent.VK_UP && moto2.getDirection() != 1) {
                moto2.setDirection(0);
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN && moto2.getDirection() != 0) {
                moto2.setDirection(1);
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT && moto2.getDirection() != 3) {
                moto2.setDirection(2);
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT && moto2.getDirection() != 2) {
                moto2.setDirection(3);
            }
        }
		
        public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}	
    }

    class NewGameListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            startNewGame();
        }
    }

    class ResetGameListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            moto1.win = 0;
            moto2.win = 0;
            gp.repaint();
        }
    }

    class MyRunnable implements Runnable {
        public void run() {
            while(true) {
                moto1.move(moto1.getDirection());
                moto2.move(moto2.getDirection());
                gp.repaint();

                if (!(moto1.isAlive(moto2)) && moto2.isAlive(moto1)) {
                    moto2.win++;
                    break;
                } else if (moto1.isAlive(moto2) && !(moto2.isAlive(moto1))) {
                    moto1.win++;
                    break;
                } else if (!(moto1.isAlive(moto2)) && !(moto2.isAlive(moto1))) {
                    System.out.println("Uhmm");
                    break;
                }
                try {
                    Thread.sleep(60);
                } catch(Exception ex) {}
            }
        }
    }
}
