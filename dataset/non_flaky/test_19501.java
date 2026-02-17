class DummyClass_19501 {
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorInvalidArgs_02() {
		new TraceRegion(0, -1, 0, 0, true, 0, 0, 0, 0, null, newURI());
	}

}