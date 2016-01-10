import java.io.*;

public class Wall implements Serializable {
	int x = 0;
	int y = 0;
	int scale = 10;
	
	public Wall(int x, int y) {
		this.x = x;
		this.y = y;
	}
}