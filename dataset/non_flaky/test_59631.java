class DummyClass_59631 {
	@Test
	public void recursiveDownloadFolderSftp() {
		Sftp ftp = new Sftp("127.0.0.1", 22, "test", "test");

		ftp.cd("/file/aaa");
		Console.log(ftp.pwd());
		ftp.recursiveDownloadFolder("/",FileUtil.file("d:/test/download"));

		IoUtil.close(ftp);
	}

}