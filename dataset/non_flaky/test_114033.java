class DummyClass_114033 {
	@Test
	public void testSanitizeInputPathShouldReturnNullForNonUppercaseWebInf() throws Exception {
		assertNull(fileDownloadAction.sanitizeInputPath("./wEB-Inf/foo"));
	}

}