class DummyClass_110158 {
	@Test(expected = IOException.class)
	public void createDirectoryManagerIoException() throws IOException {
		DirectoryManagerFactory.createDirectoryManager(
				"/nonexisting-directory/123456789/hopefully", true);
	}

}