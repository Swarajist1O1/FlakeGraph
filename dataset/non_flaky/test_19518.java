class DummyClass_19518 {
	@Test
	public void testAnnotate_03() {
		TraceRegion parent = new TraceRegion(0, 4, 0, 0, true, 1, 2, 0, 0, null, newURI());
		new TraceRegion(0, 1, 0, 0, true, 3, 4, 0, 0, parent, null);
		new TraceRegion(2, 1, 0, 0, true, 5, 6, 0, 0, parent, null);
		new TraceRegion(3, 1, 0, 0, true, 7, 8, 0, 0, parent, null);
		assertEquals("<1:2[<3:4[a]b<5:6[c]<7:8[d]]e", parent.getAnnotatedString("abcde"));
	}

}