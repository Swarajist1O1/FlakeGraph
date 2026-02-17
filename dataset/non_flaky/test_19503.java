class DummyClass_19503 {
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorInvalidArgs_04() {
		new TraceRegion(0, 0, 0, -1, true, 0, 0, 0, 0, null, newURI());
	}

}