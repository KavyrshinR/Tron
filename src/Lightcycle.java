import java.awt.Color;

class Lightcycle {
    int[] coordX = new int[10000];
    int[] coordY = new int[10000];
    Color color;
    int len = 1;
    private int dir = 0;
    int win = 0;
	
    public Lightcycle(int x, int y, int d, int r, int g, int b) {
        this.color = new Color(r, g, b);
        coordX[0] = x; coordY[0] = y;
        dir = d;
    }

    public void setCoordinate(int x, int y) {
        this.len = 1;
        this.coordX = null;
        this.coordY = null;
        this.coordX = new int[10000];
        this.coordY = new int[10000];
        this.coordX[0] = x;
        this.coordY[0] = y;
    }

    public void setDirection(int d) {
        if(d >= 0 && d <= 3) {
            dir = d;
        }
    }

    public int getDirection() {
        return dir;
    }
	
    boolean isAlive(Lightcycle enemy) {
        for(int i = 1; i < len; i++) {
            if((coordX[0] == coordX[i]) && (coordY[0] == coordY[i])) { 
                return false;
            }
        }

        for(int i = 0; i < enemy.len; i++) {
            if((coordX[0] == enemy.coordX[i]) && (coordY[0] == enemy.coordY[i])) {
                return false;
            }
        }

        return true;
    }
    
    public void move() {

        for(int i = len; i > 0; i--) {
                coordX[i] = coordX[i - 1];
        }

        for(int i = len; i > 0; i--) {
                coordY[i] = coordY[i - 1];
        }

        if(dir == 0) {if(coordY[0] == 0){coordY[0] = MyTronGame.Height - 1;} else coordY[0] -= 1;}
        if(dir == 1) {if(coordY[0] == MyTronGame.Height - 1){coordY[0] = 0;} else coordY[0] += 1;}
        if(dir == 2) {if(coordX[0] == 0){coordX[0] = MyTronGame.Width - 1;} else coordX[0] -= 1;}
        if(dir == 3) {if(coordX[0] == MyTronGame.Width - 1){coordX[0] = 0;} else coordX[0] += 1;}
        this.len++;
    }
}
// LoooooooooooooooooooL