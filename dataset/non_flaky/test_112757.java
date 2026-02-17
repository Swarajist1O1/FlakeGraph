class DummyClass_112757 {
	@Test
	public void testSqlTypeValueConst() {
		int val = 12;
		SqlType type = SqlType.INTEGER;
		ThreadLocalSelectArg arg = new ThreadLocalSelectArg(type, val);
		assertTrue(arg.isValueSet());
		assertEquals(val, arg.getValue());
		assertEquals(type, arg.getSqlType());
	}

}