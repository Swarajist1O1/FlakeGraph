class DummyClass_112731 {
	@Test
	public void testExecuteStatement() throws Exception {
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		String statement = "select foo from bar";
		int result = 1312321;
		expect(conn.executeStatement(statement, 0)).andReturn(result);
		conn.close();
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(conn);
		replay(conn);
		assertEquals(result, proxy.executeStatement(statement, 0));
		proxy.close();
		verify(conn);
	}

}