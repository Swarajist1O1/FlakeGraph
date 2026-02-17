class DummyClass_19495 {
	@Test
	public void testURI() throws Exception {
		JavaIoFileSystemAccess fileSystemAccess = new JavaIoFileSystemAccess();
		fileSystemAccess.setOutputPath("testOutput", "/testDir");
		URI uri = fileSystemAccess.getURI("testFile", "testOutput");
		String expectedUri = new File(new File(File.separator + "testDir"), "testFile").toURI().toString();
		assertEquals(expectedUri, uri.toString());
	}

}