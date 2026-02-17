class DummyClass_19472 {
	@Test
	public void testNoneExistingFile() throws Exception {
		String path = "fileNotExists";
		Set<URI> uris = new PathTraverser().findAllResourceUris(path, everythingButDummy);
		assertTrue(uris.isEmpty());
	}

}