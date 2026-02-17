class DummyClass_59589 {
	@Test
	public void generateCustomTest() {
		QrConfig config = new QrConfig();
		config.setMargin(0);
		config.setForeColor(Color.CYAN);
		// èæ¯è²éæ
		config.setBackColor(null);
		config.setErrorCorrection(ErrorCorrectionLevel.H);
		QrCodeUtil.generate("https://hutool.cn/", config, FileUtil.file("d:/qrcodeCustom.png"));
	}

}