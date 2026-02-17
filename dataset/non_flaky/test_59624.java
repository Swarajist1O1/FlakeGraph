class DummyClass_59624 {
	@Test
	public void sftpTest() {
		Session session = JschUtil.getSession("looly.centos", 22, "root", "123456");
		Sftp sftp = JschUtil.createSftp(session);
		sftp.mkDirs("/opt/test/aaa/bbb");
		Console.log("OK");
	}

}