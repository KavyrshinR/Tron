class CurrentOs {
	String os;
	int x;
	private int width;
	private int height;

	public void identifyOs() {
		os = System.getProperty("os.name");

		if (os.indexOf("nux") >= 0) {
			this.setWidth(1);
			this.setHeight(22);
		} else if (os.indexOf("ndows") >= 0) {
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