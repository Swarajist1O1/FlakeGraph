class DummyClass_19504 {
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorInvalidArgs_05() {
		new TraceRegion(0, 0, 0, 0, true, 0, 0, 0, 0, null, null);
	}

}