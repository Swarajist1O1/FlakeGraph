class DummyClass_19481 {
	@Test(expected=IndexOutOfBoundsException.class)
	public void testExceedsOffset() {
		assertLineAndColumn("", 1, -1, -1);
	}

}