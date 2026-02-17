class DummyClass_110157 {
	@Test(expected = RuntimeException.class)
	public void createDirectoryManagerNoConstructor() throws IOException {
		DirectoryManagerFactory
				.setDirectoryManagerClass(TestDirectoryManager.class);
		DirectoryManagerFactory.createDirectoryManager("/", true);
	}

}