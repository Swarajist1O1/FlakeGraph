class DummyClass_19511 {
	@Test
	public void testLeafIterator_OneGrandChild_LeftGap() {
		final TraceRegion root = new TraceRegion(0, 2, 0, 2, true, 2, 3, 0, 0, null, newURI());
		AbstractTraceRegion first = new AbstractStatefulTraceRegion(new TextRegionWithLineInformation(0, 1, 0, 1), true, new LocationData(2, 3, 0, 0, null), root) {};
		TraceRegion parent = new TraceRegion(1, 1, 1, 2, true, 3, 4, 0, 0, root, null);
		TraceRegion second = new TraceRegion(1, 1, 1, 2, true, 3, 4, 0, 0, parent, null);
		Iterator<AbstractTraceRegion> iter = root.leafIterator();
		assertEquals(Arrays.asList(first, second).iterator(), iter);
	}

}