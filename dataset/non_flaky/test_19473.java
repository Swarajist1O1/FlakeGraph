class DummyClass_19473 {
	@Test
	public void testEmptyFolder() throws Exception {
		String path = pathTo("emptyFolder");
		Set<URI> uris = new PathTraverser().findAllResourceUris(path, everythingButDummy);
		assertTrue(uris.isEmpty());
	}

}