class DummyClass_19510 {
	@Test
	public void testLeafIterator_OneChild_RightGap() {
		final TraceRegion parent = new TraceRegion(0, 2, 0, 2, true, 2, 3, 0, 0, null, newURI());
		AbstractTraceRegion first = new TraceRegion(0, 1, 0, 1, true, 3, 4, 0, 0, parent, null);
		AbstractTraceRegion second = new AbstractStatefulTraceRegion(new TextRegionWithLineInformation(1, 1, 1, 2), true, new LocationData(2, 3, 0, 0, null), parent) {};
		Iterator<AbstractTraceRegion> iter = parent.leafIterator();
		assertEquals(Arrays.asList(first, second).iterator(), iter);
	}

}