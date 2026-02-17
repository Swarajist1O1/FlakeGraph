class DummyClass_114029 {
	@Test
	public void testSanitizeInputPathShouldAllowSimpleParameter() throws Exception {
		assertEquals("foo", fileDownloadAction.sanitizeInputPath("foo"));
	}

}