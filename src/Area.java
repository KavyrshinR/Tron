import java.io.*;

public class Area implements Serializable {

	public Wall[] wallArray;
	int count = 0;
	int width = 80;
	int height = 80;
	
	public Area() {
		wallArray = new Wall[width * height];
	}
}