class DummyClass_19474 {
	@Test
	public void testNonEmptyFolder() throws Exception {
		String path = pathTo("nonemptyFolder");
		Set<URI> uris = new PathTraverser().findAllResourceUris(path, everythingButDummy);
		assertEquals(2, uris.size());
	}

}