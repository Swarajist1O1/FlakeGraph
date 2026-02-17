class DummyClass_59588 {
	@Test
	public void generateTest() {
		final BufferedImage image = QrCodeUtil.generate("https://hutool.cn/", 300, 300);
		Assert.assertNotNull(image);
	}

}