class DummyClass_19513 {
	@Test
	public void testLeafIterator_TwoGrandChildren_NoGaps_01() {
		TraceRegion root = new TraceRegion(0, 2, 0, 2, true, 2, 3, 0, 0, null, newURI());
		TraceRegion parent = new TraceRegion(0, 2, 0, 2, true, 2, 3, 0, 0, root, null);
		TraceRegion first = new TraceRegion(0, 1, 0, 1, true, 2, 3, 0, 0, parent, null);
		TraceRegion second = new TraceRegion(1, 1, 1, 2, true, 3, 4, 0, 0, parent, null);
		Iterator<AbstractTraceRegion> iter = root.leafIterator();
		assertEquals(Arrays.asList(first, second).iterator(), iter);
	}

}