class DummyClass_110155 {
	@Test
	public void createDirectoryManagerString() throws IOException {
		Path path = Paths.get(System.getProperty("user.dir"));
		DirectoryManagerFactory
				.setDirectoryManagerClass(DirectoryManagerImpl.class);
		DirectoryManager dm = DirectoryManagerFactory.createDirectoryManager(
				System.getProperty("user.dir"), true);
		assertTrue(dm instanceof DirectoryManagerImpl);
		DirectoryManagerImpl dmi = (DirectoryManagerImpl) dm;
		assertTrue(dmi.readOnly);
		assertEquals(path, dmi.directory);
	}

}