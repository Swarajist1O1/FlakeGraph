class DummyClass_112741 {
	@Test
	public void testQueryForOne() throws Exception {
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		String statement = "insert bar";
		Object result = new Object();
		expect(conn.queryForOne(statement, null, null, null, null)).andReturn(result);
		conn.close();
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(conn);
		replay(conn);
		assertEquals(result, proxy.queryForOne(statement, null, null, null, null));
		proxy.close();
		verify(conn);
	}

}