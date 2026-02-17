class DummyClass_59630 {
	@Test
	public void recursiveDownloadFolder() {
		Ftp ftp = new Ftp("looly.centos");
		ftp.recursiveDownloadFolder("/",FileUtil.file("d:/test/download"));

		IoUtil.close(ftp);
	}

}