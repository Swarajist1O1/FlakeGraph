class DummyClass_19515 {
	@Test
	public void testLeafIterator_TwoChildren_WithGaps() {
		final TraceRegion parent = new TraceRegion(0, 3, 0, 3, true, 2, 3, 0, 0, null, newURI());
		TraceRegion first = new TraceRegion(0, 1, 0, 1, true, 2, 3, 0, 0, parent, null);
		AbstractTraceRegion second = new AbstractStatefulTraceRegion(new TextRegionWithLineInformation(1, 1, 1, 2), true, new LocationData(2, 3, 0, 0, null), parent) {};
		AbstractTraceRegion third = new TraceRegion(2, 1, 2, 3, true, 3, 4, 0, 0, parent, null);
		Iterator<AbstractTraceRegion> iter = parent.leafIterator();
		assertEquals(Arrays.asList(first, second, third).iterator(), iter);
	}

}