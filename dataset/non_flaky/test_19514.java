class DummyClass_19514 {
	@Test
	public void testLeafIterator_TwoGrandChildren_NoGaps_02() {
		TraceRegion root = new TraceRegion(0, 2, 0, 2, true, 2, 3, 0, 0, null, newURI());
		TraceRegion firstParent = new TraceRegion(0, 1, 0, 1, true, 2, 3, 0, 0, root, null);
		TraceRegion first = new TraceRegion(0, 1, 0, 1, true, 2, 3, 0, 0, firstParent, null);
		TraceRegion secondParent = new TraceRegion(1, 1, 1, 2, true, 3, 4, 0, 0, root, null);
		TraceRegion second = new TraceRegion(1, 1, 1, 2, true, 3, 4, 0, 0, secondParent, null);
		Iterator<AbstractTraceRegion> iter = root.leafIterator();
		assertEquals(Arrays.asList(first, second).iterator(), iter);
	}

}