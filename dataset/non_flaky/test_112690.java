class DummyClass_112690 {
	@Test
	public void testExceptionWithSQLException() {
		String sqlReason = "sql exception message";
		String sqlState = "sql exception state";
		Throwable cause = new SQLException(sqlReason, sqlState);
		String msg = "hello";
		SQLException e = SqlExceptionUtil.create(msg, cause);
		assertEquals(msg, e.getMessage());
		assertEquals(sqlState, e.getSQLState());
		assertEquals(cause, e.getCause());
	}

}