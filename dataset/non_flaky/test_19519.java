class DummyClass_19519 {
	@Test
	public void testAnnotate_04() {
		TraceRegion root = new TraceRegion(0, 4, 0, 0, true, 1, 2, 0, 0, null, newURI());
		TraceRegion parent = new TraceRegion(1, 2, 0, 0, true, 3, 4, 0, 0, root, null);
		new TraceRegion(2, 1, 0, 0, true, 5, 6, 0, 0, parent, null);
		assertEquals("<1:2[a<3:4[b<5:6[c]]d]e", root.getAnnotatedString("abcde"));
	}

}