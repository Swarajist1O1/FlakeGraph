class DummyClass_59591 {
	@Test
	public void decodeTest() {
		String decode = QrCodeUtil.decode(FileUtil.file("e:/pic/qr.png"));
		Console.log(decode);
	}

}