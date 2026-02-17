class DummyClass_19498 {
	@Test
	public void testConstructor() {
		TraceRegion region = new TraceRegion(0, 1, 0, 0, true, 2, 3, 0, 0, null, newURI());
		assertEquals(0, region.getMyOffset());
		assertEquals(1, region.getMyLength());
		assertEquals(2, region.getMergedAssociatedLocation().getOffset());
		assertEquals(3, region.getMergedAssociatedLocation().getLength());
		assertEquals(newURI(), region.getAssociatedSrcRelativePath());
		assertNull(region.getParent());
		assertTrue(region.getNestedRegions().isEmpty());
	}

}