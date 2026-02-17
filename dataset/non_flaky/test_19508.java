class DummyClass_19508 {
	@Test
	public void testLeafIterator_TwoChildren_NoGaps() {
		TraceRegion parent = new TraceRegion(0, 2, 0, 2, true, 2, 3, 0, 0, null, newURI());
		TraceRegion first = new TraceRegion(0, 1, 0, 1, true, 2, 3, 0, 0, parent, null);
		TraceRegion second = new TraceRegion(1, 1, 1, 2, true, 3, 4, 0, 0, parent, null);
		Iterator<AbstractTraceRegion> iter = parent.leafIterator();
		assertEquals(Arrays.asList(first, second).iterator(), iter);
	}

}