class DummyClass_59592 {
	@Test
	public void generateAsBase64Test(){
		String base64 = QrCodeUtil.generateAsBase64("http://hutool.cn/", new QrConfig(400, 400), "png");
		System.out.println(base64);

		byte[] bytes = FileUtil.readBytes(
			new File("d:/test/qr.png"));
		String encode = Base64.encode(bytes);
		String base641 = QrCodeUtil.generateAsBase64("http://hutool.cn/", new QrConfig(400, 400), "png", encode);
		System.out.println(base641);

	}

}