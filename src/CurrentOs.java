class CurrentOs {
	String os;
	int x;

	public void identifyOs() {
		os = System.getProperty("os.name");
	}

	public String getCurrentOs() {
		return os;
	}

	public int getWidth() {
		if (os.indexOf("nux") >= 0) {
			x = 1;
			return x;
		} else if (os.indexOf("ndows") >= 0) {
			x = 6;
			return x;
		}
		return 0;
	}

	public int getHeight() {
		if (os.indexOf("nux") >= 0) {
			x = 22;
			return x;
		} else if (os.indexOf("ndows") >= 0) {
			x = 22 + 28;
			return x;
		}
		return 0;
	}
}