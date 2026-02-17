class DummyClass_59590 {
	@Test
	public void generateWithLogoTest() {
		QrCodeUtil.generate(//
				"http://hutool.cn/", //
				QrConfig.create().setImg("e:/pic/face.jpg"), //
				FileUtil.file("e:/qrcodeWithLogo.jpg"));
	}

}