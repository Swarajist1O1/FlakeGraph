class DummyClass_19506 {
	@Test
	public void testLeafIterator_OneChild() {
		TraceRegion parent = new TraceRegion(0, 1, 1, 2, true, 2, 3, 0, 0, null, newURI());
		TraceRegion region = new TraceRegion(0, 1, 1, 2, true, 2, 3, 0, 0, parent, null);
		Iterator<AbstractTraceRegion> iter = parent.leafIterator();
		assertEquals(Collections.singleton(region).iterator(), iter);
	}

}