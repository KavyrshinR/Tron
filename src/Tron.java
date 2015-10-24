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
    public static int Width = 84;
    public static int Height = 84;
    JFrame frame = new JFrame("TronGame");
    GamePanel gp;
    Lightcycle moto1;
    Lightcycle moto2;
    Color backColor = new Color(80, 102, 80);
    Color sharpColor = new Color(51, 9, 221);
	
    public void go() {
        gp = new GamePanel();
        gp.setFocusable(true);
        gp.requestFocus();
        gp.addKeyListener(new KeyListen());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, gp);
        frame.setSize(Width * Scale + 6, Height * Scale + 28);
        frame.setVisible(true);
        frame.setResizable(false);
        moto1 = new Lightcycle(20, 40, 255, 82, 82);
        moto2 = new Lightcycle(70, 40, 200, 0, 100);

        Thread t = new Thread(new MyRunnable());
        t.start();
    }
	
	public void startNewGame() {
		moto1 = new Lightcycle(20, 40, 255, 82, 82);
		moto2 = new Lightcycle(70, 40, 200, 0, 100);
		this.go();
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
            if(e.getKeyCode() == KeyEvent.VK_UP && moto2.dir != 1) {moto2.dir = 0;}
            if(e.getKeyCode() == KeyEvent.VK_DOWN && moto2.dir != 0) {moto2.dir = 1;}
            if(e.getKeyCode() == KeyEvent.VK_LEFT && moto2.dir != 3) {moto2.dir = 2;}
            if(e.getKeyCode() == KeyEvent.VK_RIGHT && moto2.dir != 2) {moto2.dir = 3;}
            
            if(e.getKeyCode() == KeyEvent.VK_W && moto1.dir != 1) {moto1.dir = 0;}
            if(e.getKeyCode() == KeyEvent.VK_S && moto1.dir != 0) {moto1.dir = 1;}
            if(e.getKeyCode() == KeyEvent.VK_A && moto1.dir != 3) {moto1.dir = 2;}
            if(e.getKeyCode() == KeyEvent.VK_D && moto1.dir != 2) {moto1.dir = 3;}
        }
		
        public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}	
    }

    class MyRunnable implements Runnable {
        public void run() {
            while(moto1.isAlive(moto2) && moto2.isAlive(moto1)) {
                moto1.move(moto1.dir);
                moto2.move(moto2.dir);
                gp.repaint();
                try {
                    Thread.sleep(60);
                } catch(Exception ex) {}
            }
        }
    }
}