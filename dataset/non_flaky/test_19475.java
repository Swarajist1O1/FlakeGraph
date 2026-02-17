class DummyClass_19475 {
	@Test
	public void testArchive() throws Exception {
		String path = pathTo("nonemptyJar.jar");
		Set<URI> uris = new PathTraverser().findAllResourceUris(path, everythingButDummy);
		assertEquals(3, uris.size());
	}

}