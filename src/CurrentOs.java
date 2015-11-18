class CurrentOs {
	String os;
	int x;
	private int width;
	private int height;

	public void identifyOs() {
		os = System.getProperty("os.name").toLowerCase();

		if (os.indexOf("nux") >= 0) {
			this.setWidth(2);
			this.setHeight(23);
		} else if (os.indexOf("win") >= 0) {
			this.setWidth(6);
			this.setHeight(50);
		}
	}

	public String getCurrentOs() {
		return os;
	}

	public void setWidth(int w) {
		width = w;
	}

	public void setHeight(int h) {
		height = h;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}