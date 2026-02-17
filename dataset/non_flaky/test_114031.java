class DummyClass_114031 {
	@Test
	public void testSanitizeInputPathShouldReturnNullForLeadingWebInf() throws Exception {
		assertNull(fileDownloadAction.sanitizeInputPath("WEB-INF/foo"));
	}

}