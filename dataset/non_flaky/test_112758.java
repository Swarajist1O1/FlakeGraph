class DummyClass_112758 {
	@Test
	public void testColumnNameTypeValueConst() {
		int val = 12;
		String columnName = "fewopjfewpfjwe";
		ThreadLocalSelectArg arg = new ThreadLocalSelectArg(columnName, val);
		assertTrue(arg.isValueSet());
		assertEquals(val, arg.getValue());
		assertEquals(columnName, arg.getColumnName());
	}

}