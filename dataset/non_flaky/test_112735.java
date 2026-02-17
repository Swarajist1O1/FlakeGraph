class DummyClass_112735 {
	@Test
	public void testInsert() throws Exception {
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		String statement = "insert bar";
		int result = 13712321;
		expect(conn.insert(statement, null, null, null)).andReturn(result);
		conn.close();
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(conn);
		replay(conn);
		assertEquals(result, proxy.insert(statement, null, null, null));
		proxy.close();
		verify(conn);
	}

}