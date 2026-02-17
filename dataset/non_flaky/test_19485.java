class DummyClass_19485 {
	@Test
	public void testPointsToLineBreak() {
		assertLineAndColumn("\n", 0, 1, 1);
		assertLineAndColumn("\r\n", 0, 1, 1);
	}

}