class DummyClass_19500 {
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorInvalidArgs_01() {
		new TraceRegion(-1, 0, 0, 0, true, 0, 0, 0, 0, null, newURI());
	}

}