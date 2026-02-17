class DummyClass_19516 {
	@Test
	public void testAnnotate_01() {
		TraceRegion region = new TraceRegion(0, 1, 0, 0, true, 2, 3, 0, 0, null, newURI());
		assertEquals("<2:3[a]", region.getAnnotatedString("a"));
	}

}