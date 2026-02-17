class DummyClass_19484 {
	@Test
	public void testTwoCharsText() {
		assertLineAndColumn("ab", 0, 1, 1);
		assertLineAndColumn("ab", 1, 1, 2);
		assertLineAndColumn("ab", 2, 1, 3);
	}

}