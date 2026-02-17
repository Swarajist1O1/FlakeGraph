class DummyClass_19507 {
	@Test
	public void testLeafIterator_GrandChild() {
		TraceRegion root = new TraceRegion(0, 1, 1, 2, true, 2, 3, 0, 0, null, newURI());
		TraceRegion parent = new TraceRegion(0, 1, 1, 2, true, 2, 3, 0, 0, root, null);
		TraceRegion region = new TraceRegion(0, 1, 1, 2, true, 2, 3, 0, 0, parent, null);
		Iterator<AbstractTraceRegion> iter = root.leafIterator();
		assertEquals(Collections.singleton(region).iterator(), iter);
	}

}