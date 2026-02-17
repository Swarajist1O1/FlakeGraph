class DummyClass_114032 {
	@Test
	public void testSanitizeInputPathShouldReturnNullForNonLeadingWebInf() throws Exception {
		assertNull(fileDownloadAction.sanitizeInputPath("./WEB-INF/foo"));
	}

}