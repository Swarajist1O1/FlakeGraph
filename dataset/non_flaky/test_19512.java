class DummyClass_19512 {
	@Test
	public void testLeafIterator_OneGrandChild_RightGap() {
		final TraceRegion root = new TraceRegion(0, 2, 0, 2, true, 2, 3, 0, 0, null, newURI());
		TraceRegion parent = new TraceRegion(0, 1, 0, 1, true, 3, 4, 0, 0, root, null);
		TraceRegion first = new TraceRegion(0, 1, 0, 1, true, 3, 4, 0, 0, parent, null);
		AbstractTraceRegion second = new AbstractStatefulTraceRegion(new TextRegionWithLineInformation(1, 1, 1, 2), true, new LocationData(2, 3, 0, 0, null), root) {};
		Iterator<AbstractTraceRegion> iter = root.leafIterator();
		assertEquals(Arrays.asList(first, second).iterator(), iter);
	}

}