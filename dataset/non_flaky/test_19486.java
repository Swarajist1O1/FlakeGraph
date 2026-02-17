class DummyClass_19486 {
	@Test
	public void testPointsToBackslashNInWindowsLineBreak() {
		assertLineAndColumn("\r\n", 1, 1, 2);
		assertLineAndColumn("a\r\n", 2, 1, 3);
		assertLineAndColumn("a\r\n", 3, 2, 1);
	}

}