class DummyClass_112756 {
	@Test
	public void testValueConst() {
		int val = 12;
		ThreadLocalSelectArg arg = new ThreadLocalSelectArg(val);
		assertTrue(arg.isValueSet());
		assertEquals(val, arg.getValue());
	}

}