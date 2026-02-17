class DummyClass_19502 {
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorInvalidArgs_03() {
		new TraceRegion(0, 0, -1, 0, true, 0, 0, 0, 0, null, newURI());
	}

}