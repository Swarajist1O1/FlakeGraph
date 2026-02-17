class DummyClass_19509 {
	@Test
	public void testLeafIterator_OneChild_LeftGap() {
		final TraceRegion parent = new TraceRegion(0, 2, 0, 2, true, 2, 3, 0, 0, null, newURI());
		AbstractTraceRegion first = new AbstractStatefulTraceRegion(new TextRegionWithLineInformation(0, 1, 0, 1), true, new LocationData(2, 3, 0, 0, null), parent) {};
		TraceRegion second = new TraceRegion(1, 1, 1, 2, true, 3, 4, 0, 0, parent, null);
		Iterator<AbstractTraceRegion> iter = parent.leafIterator();
		assertEquals(Arrays.asList(first, second).iterator(), iter);
	}

}