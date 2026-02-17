class DummyClass_112755 {
	@Test
	public void testStuff() {
		ThreadLocalSelectArg arg = new ThreadLocalSelectArg();
		assertNull(arg.getValue());
		assertFalse(arg.isValueSet());
		arg.setValue(null);
		assertNull(arg.getValue());
		assertTrue(arg.isValueSet());
	}

}