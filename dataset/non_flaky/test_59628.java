class DummyClass_59628 {
	@Test
	public void uploadTest() {
		Ftp ftp = new Ftp("looly.centos");
		
		List<String> ls = ftp.ls("/file");
		Console.log(ls);
		
		boolean upload = ftp.upload("/file/aaa", FileUtil.file("E:/qrcodeWithLogo.jpg"));
		Console.log(upload);
		
		IoUtil.close(ftp);
	}

}