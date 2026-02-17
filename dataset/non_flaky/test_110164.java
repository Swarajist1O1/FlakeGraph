class DummyClass_110164 {
	@Test(expected = IOException.class)
	public void NoCreateFileStringReadOnly() throws IOException {
		dm.createFile("new-test-file.txt", "new contents");
	}

}