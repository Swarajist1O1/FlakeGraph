class DummyClass_19499 {
	@Test
	public void testConstructorWithParent() {
		TraceRegion parent = new TraceRegion(0, 1, 0, 0, true, 2, 3, 0, 0, null, newURI());
		TraceRegion region = new TraceRegion(0, 1, 0, 0, true, 2, 3, 0, 0, parent, null);
		assertEquals(newURI(), region.getAssociatedSrcRelativePath());
		assertEquals(parent, region.getParent());
	}

}