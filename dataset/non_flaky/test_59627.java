class DummyClass_59627 {
	@Test
	public void cdTest() {
		Ftp ftp = new Ftp("looly.centos");
		
		ftp.cd("/file/aaa");
		Console.log(ftp.pwd());
		
		IoUtil.close(ftp);
	}

}