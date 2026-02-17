class DummyClass_112733 {
	@Test
	public void testCompileStatementStringStatementTypeFieldTypeArrayInt() throws Exception {
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		String statement = "select foo from bar";
		StatementType type = StatementType.DELETE;
		int flags = 11253123;
		expect(conn.compileStatement(statement, type, null, flags, false)).andReturn(null);
		conn.close();
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(conn);
		replay(conn);
		proxy.compileStatement(statement, type, null, flags, false);
		proxy.close();
		verify(conn);
	}

}