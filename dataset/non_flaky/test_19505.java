class DummyClass_19505 {
	@Test
	public void testLeafIterator_NoChildren() {
		TraceRegion region = new TraceRegion(0, 1, 1, 2, true, 2, 3, 0, 0, null, newURI());
		Iterator<AbstractTraceRegion> iter = region.leafIterator();
		assertEquals(Collections.singleton(region).iterator(), iter);
	}

}