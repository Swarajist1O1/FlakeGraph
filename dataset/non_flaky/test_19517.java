class DummyClass_19517 {
	@Test
	public void testAnnotate_02() {
		TraceRegion region = new TraceRegion(1, 1, 0, 0, true, 2, 3, 0, 0, null, newURI());
		assertEquals("a<2:3[b]c", region.getAnnotatedString("abc"));
	}

}