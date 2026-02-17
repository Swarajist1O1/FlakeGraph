class DummyClass_19482 {
	@Test(expected=IndexOutOfBoundsException.class)
	public void testNegativeOffset() {
		assertLineAndColumn("", -1, -1, -1);
	}

}